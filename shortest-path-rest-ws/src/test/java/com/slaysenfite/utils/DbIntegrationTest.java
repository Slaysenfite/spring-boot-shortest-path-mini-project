package com.slaysenfite.utils;

import com.slaysenfite.domain.repository.PlanetRepository;
import com.slaysenfite.domain.repository.RouteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.slaysenfite.config.DatabaseConfig;
import com.slaysenfite.domain.entity.Planet;
import com.slaysenfite.domain.entity.Route;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@ContextConfiguration(classes = DatabaseConfig.class)
public class DbIntegrationTest {

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Test
    public void Given_PlanetRepository_When_AllPlanetsNeedToBeRetrieved_Then_ListOfPlanetsIsReturned() {
        List<Planet> planets = planetRepository.findAll();

        assertThat(planets).isNotNull();
        assertThat(planets.size()).isEqualTo(38);
    }

    @Test
    public void Given_PlanetId_When_PlanetDetailsNeedToBeRetrieved_Then_PlantDetailsAreReturned() {
        Planet planet = planetRepository.findById(1L).get();

        assertThat(planet).isNotNull();
        assertThat(planet.getName()).isEqualTo("Earth");
    }

    @Test
    public void Given_PlanetDetails_When_PlanetNeedsToBeUpdated_Then_PlanetDetailsAreUpdated() {
        Planet planet = planetRepository.findById(1L).get();
        planet.setName("Unearth");
        planetRepository.save(planet);

        Planet updatedPlanet = planetRepository.findById(1L).get();
        assertThat(updatedPlanet.getName()).isEqualTo("Unearth");
    }

    @Test
    public void Given_PlanetDetail_When_PlanetNeedsToBeCreated_Then_PlanetCanBeCreated() {
        Planet planet = new Planet("g^", "Myanus");

        planetRepository.save(planet);

        List<Planet> planets = planetRepository.findAll();
        assertThat(planets.size()).isEqualTo(39);
    }

    @Test
    public void Given_PlanetId_when_PlanetIsToBeRemovedFromDatabase_Then_PlanetIsNoLongerPersisting() {
        planetRepository.deleteById(1L);

        List<Planet> planets = planetRepository.findAll();

        assertThat(planets.size()).isEqualTo(37);
    }

    @Test
    public void Given_RouteRepository_When_AllRoutesNeedToBeRetrieved_Then_ListOfRoutesIsReturned() {
        List<Route> routes = routeRepository.findAll();

        assertThat(routes).isNotNull();
        assertThat(routes.size()).isEqualTo(45);
    }

    @Test
    public void Given_RouteId_When_RouteDetailsNeedToBeRetrieved_Then_PlantDetailsAreReturned() {
        Route route = routeRepository.findById(1).get();

        assertThat(route).isNotNull();
        assertThat(route.getId()).isEqualTo(1);
    }

    @Test
    public void Given_RouteDetails_When_RouteNeedsToBeUpdated_Then_RouteDetailsAreUpdated() {
        Route route = routeRepository.findById(1).get();
        route.setDelay(0.34);
        routeRepository.save(route);

        Route updatedRoute = routeRepository.findById(1).get();
        assertThat(updatedRoute.getDelay()).isEqualTo(0.34);
    }

    @Test
    public void Given_RouteDetail_When_RouteNeedsToBeCreated_Then_RouteCanBeCreated() {
        Route route = new Route(52, "start", "destination", 45.78, 0.21);

        routeRepository.save(route);

        List<Route> routes = routeRepository.findAll();
        assertThat(routes.size()).isEqualTo(46);
    }

    @Test
    public void Given_RouteId_when_RouteIsToBeRemovedFromDatabase_Then_RouteIsNoLongerPersisting() {
        routeRepository.deleteById(1);

        List<Route> routes = routeRepository.findAll();

        assertThat(routes.size()).isEqualTo(44);
    }
}
