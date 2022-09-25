package com.slaysenfite.web.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.slaysenfite.domain.entity.Route;
import com.slaysenfite.domain.repository.RouteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.slaysenfite.web.controller.common.ControllerUtils.asJsonString;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Ignore
public class RouteControllerTest {
    private static final String BASE_ENDPOINT = "/routes";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RouteRepository routeRepository;

    @Test
    public void Given_RouteListEndpoint_When_EndpointIsCalled_Then_ListOfRoutesShouldBeReturned() throws Exception {
        when(routeRepository.findAll()).thenReturn(dummyRoutes());

        mockMvc.perform(get(BASE_ENDPOINT))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    public void Given_RouteDetailsEndpoint_When_EndpointIsCalled_Then_RouteDetailsShouldBeReturned() throws Exception {
        when(routeRepository.findById(1)).thenReturn(Optional.of(dummyRoutes().get(0)));

        mockMvc.perform(get(BASE_ENDPOINT + "/{id}", 1))
            .andDo(print())
            .andExpect(status().isOk());
    }

    //TODO: Test generates NPE
    @Test
    @Ignore
    public void Given_RouteCreationEndpoint_When_EndpointIsCalled_Then_CreatedStatusShouldBeReturned() throws Exception {
        when(routeRepository.save(dummyRoutes().get(0))).thenReturn(dummyRoutes().get(0));

        mockMvc.perform(post(BASE_ENDPOINT)
            .content(asJsonString(dummyRoutes().get(0)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    }

    @Test
    public void Given_EndpointToUpdateRouteDetails__When_EndpointIsCalled_Then_RouteDetailsShouldBeUpdated() throws Exception {
        when(routeRepository.findById(0)).thenReturn(Optional.of(dummyRoutes().get(0)));

        Route updatedRoute = dummyRoutes().get(0);
        updatedRoute.setDestination("4");

        when(routeRepository.save(updatedRoute)).thenReturn(updatedRoute);

        mockMvc.perform(put(BASE_ENDPOINT + "/{id}", 0)
            .content(asJsonString(updatedRoute))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    }

    @Test
    public void Given_EndpointToDeleteRoute_When_EndpointIsCalled_Then_RouteShouldBeDeleted() throws Exception {
        mockMvc.perform(delete(BASE_ENDPOINT + "/{id}", 0))
            .andExpect(status().isOk());
    }

    private List<Route> dummyRoutes() {
        List<Route> routes = new ArrayList<>();
        routes.add(new Route(0, "1", "2", 0.34, 0.97));
        routes.add(new Route(1, "2", "3", 0.37, 0.47));
        routes.add(new Route(2, "3", "4", 0.84, 0.99));
        return routes;
    }
}
