package com.slaysenfite.client;

import org.apache.commons.httpclient.HttpStatus;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import com.slaysenfite.domain.entity.Planet;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Client side tests
 * <p>
 * Ensure the service is running before running any tests
 */
public class PlanetControllerClientTest {

    private static final String BASE_URL = "http://localhost:8080/planets";

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void Given_PlanetListEndpoint_When_EndpointIsCalled_Then_ListOfPlanetsIsReturned() {
        Planet[] planets = restTemplate.getForObject(BASE_URL, Planet[].class);

        assertThat(planets).isNotNull();
        assertThat(planets.length).isEqualTo(38);
        assertThat(planets[0].getName()).isEqualTo("Earth");
    }

    @Test
    public void Given_PlanetDetailsEndpoint_When_EndpointIsCalled_Then_PlanetDetailsAreReturned() {
        Planet planet = restTemplate.getForObject(BASE_URL + "/{id}", Planet.class, 1L);

        assertThat(planet).isNotNull();
        assertThat(planet.getName()).isEqualTo("Earth");
        assertThat(planet.getNode()).isEqualTo("A");
    }

    @Test
    public void Given_PlanetCreationEndpoint_When_EndpointIsCalled_Then_NewPlanetIsAdded() {
        Planet planet = new Planet("$$", "Cash Dollar Moon");

        URI location = restTemplate.postForLocation(BASE_URL + "/", planet, HttpStatus.class);

        Planet retrievedPlanet = restTemplate.getForObject(location, Planet.class);

        assertThat(retrievedPlanet).isNotNull();
        assertThat(retrievedPlanet.getNode()).isEqualTo("$$");
        assertThat(retrievedPlanet.getName()).isEqualTo("Cash Dollar Moon");

        Planet[] planets = restTemplate.getForObject(BASE_URL, Planet[].class);

        assertThat(planets).isNotNull();
        assertThat(planets.length).isEqualTo(39);
    }

    @Test
    public void Given_EndpointToUpdatePlanetDetails__When_EndpointIsCalled_Then_PlanetDetailsAreUpdated() {
        Planet planet = restTemplate.getForObject(BASE_URL + "/{id}", Planet.class, 1L);

        planet.setName("Unearth");
        restTemplate.put(BASE_URL + "/{id}", planet, 1L);

        Planet updatedPlanet = restTemplate.getForObject(BASE_URL + "/{id}", Planet.class, 1L);
        assertThat(updatedPlanet).isNotNull();
        assertThat(updatedPlanet.getName()).isEqualTo("Unearth");
        assertThat(updatedPlanet.getNode()).isEqualTo("A");
    }

    @Test
    public void Given_EndpointToDeletePlanet_When_EndpointIsCalled_Then_PlanetIsDeleted() {
        restTemplate.delete(BASE_URL + "/{id}", 1L);

        Planet[] planets = restTemplate.getForObject(BASE_URL, Planet[].class);

        assertThat(planets).isNotNull();
        assertThat(planets.length).isEqualTo(37);
    }

}
