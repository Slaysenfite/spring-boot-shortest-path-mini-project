package com.slaysenfite.client;

import org.apache.commons.httpclient.HttpStatus;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import com.slaysenfite.domain.entity.Route;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Client side tests
 * <p>
 * Ensure the service is running before running any tests
 */
public class RouteControllerClientTest {
    private static final String BASE_URL = "http://localhost:8080/routes";

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void Given_RouteListEndpoint_When_EndpointIsCalled_Then_ListOfRoutesIsReturned() {
        Route[] routes = restTemplate.getForObject(BASE_URL, Route[].class);

        assertThat(routes).isNotNull();
        assertThat(routes.length).isEqualTo(45);
        assertThat(routes[0].getDistance()).isEqualTo(0.44);
        assertThat(routes[0].getDelay()).isEqualTo(0.3);
    }

    @Test
    public void Given_RouteDetailsEndpoint_When_EndpointIsCalled_Then_RouteDetailsAreReturned() {
        Route route = restTemplate.getForObject(BASE_URL + "/{id}", Route.class, 1);

        assertThat(route).isNotNull();
        assertThat(route.getDistance()).isEqualTo(0.44);
        assertThat(route.getDelay()).isEqualTo(0.3);
    }

    @Test
    public void Given_RouteCreationEndpoint_When_EndpointIsCalled_Then_NewRouteIsAdded() {
        Route route = new Route(52, "start", "destination", 45.78, 0.21);

        URI location = restTemplate.postForLocation(BASE_URL + "/", route, HttpStatus.class);

        Route retrievedRoute = restTemplate.getForObject(location, Route.class);

        assertThat(retrievedRoute).isNotNull();
        assertThat(route.getDistance()).isEqualTo(45.78);
        assertThat(route.getDelay()).isEqualTo(0.21);
    }

    @Test
    public void Given_EndpointToUpdateRouteDetails__When_EndpointIsCalled_Then_RouteDetailsAreUpdated() {
        Route route = restTemplate.getForObject(BASE_URL + "/{id}", Route.class, 1);

        route.setDistance(4.65);
        restTemplate.put(BASE_URL + "/{id}", route, 1);

        Route updatedRoute = restTemplate.getForObject(BASE_URL + "/{id}", Route.class, 1);
        assertThat(updatedRoute).isNotNull();
        assertThat(route.getDistance()).isEqualTo(4.65);
        assertThat(route.getDelay()).isEqualTo(0.3);
    }

    @Test
    public void Given_EndpointToDeleteRoute_When_EndpointIsCalled_Then_RouteIsDeleted() {
        restTemplate.delete(BASE_URL + "/{id}", 1);

        Route[] routes = restTemplate.getForObject(BASE_URL, Route[].class);

        assertThat(routes).isNotNull();
        assertThat(routes.length).isEqualTo(44);
    }
}
