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
import com.slaysenfite.domain.entity.Planet;
import com.slaysenfite.domain.repository.PlanetRepository;

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
public class PlanetControllerTest {

    private static final String BASE_ENDPOINT = "/planets";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlanetRepository planetRepository;

    @Test
    public void Given_PlanetListEndpoint_When_EndpointIsCalled_Then_ListOfPlanetsShouldBeReturned() throws Exception {
        when(planetRepository.findAll()).thenReturn(dummyPlanets());

        mockMvc.perform(get(BASE_ENDPOINT))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    public void Given_PlanetDetailsEndpoint_When_EndpointIsCalled_Then_PlanetDetailsShouldBeReturned() throws Exception {
        when(planetRepository.findById(1L)).thenReturn(Optional.of(dummyPlanets().get(0)));

        mockMvc.perform(get(BASE_ENDPOINT + "/{id}", 1))
            .andDo(print())
            .andExpect(status().isOk());
    }

    //TODO: Test generates NPE
    @Test
    @Ignore
    public void Given_PlanetCreationEndpoint_When_EndpointIsCalled_Then_CreatedStatusShouldBeReturned() throws Exception {
        when(planetRepository.save(dummyPlanets().get(0))).thenReturn(dummyPlanets().get(0));

        mockMvc.perform(post(BASE_ENDPOINT)
            .content(asJsonString(dummyPlanets().get(0)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    }

    @Test
    public void Given_EndpointToUpdatePlanetDetails__When_EndpointIsCalled_Then_PlanetDetailsShouldBeUpdated() throws Exception {
        when(planetRepository.findById(0L)).thenReturn(Optional.of(dummyPlanets().get(0)));

        Planet updatedPlanet = dummyPlanets().get(0);
        updatedPlanet.setName("Oone");

        when(planetRepository.save(updatedPlanet)).thenReturn(updatedPlanet);

        mockMvc.perform(put(BASE_ENDPOINT + "/{id}", 0)
            .content(asJsonString(updatedPlanet))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    }

    @Test
    public void Given_EndpointToDeletePlanet_When_EndpointIsCalled_Then_PlanetShouldBeDeleted() throws Exception {
        mockMvc.perform(delete(BASE_ENDPOINT + "/{id}", 0))
            .andExpect(status().isOk());
    }

    private List<Planet> dummyPlanets() {
        List<Planet> planets = new ArrayList<>();
        planets.add(new Planet("1", "One"));
        planets.add(new Planet("2", "Two"));
        planets.add(new Planet("3", "Three"));
        planets.get(0).setEntityId(0L);
        planets.get(1).setEntityId(1L);
        planets.get(2).setEntityId(2L);
        return planets;
    }

}
