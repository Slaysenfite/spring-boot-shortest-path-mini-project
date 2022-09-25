package com.slaysenfite.cucumber;

import com.slaysenfite.exception.PlanetNotFoundException;
import com.slaysenfite.service.AlgorithmService;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import com.slaysenfite.ShortestPathServiceAssembler;
import com.slaysenfite.domain.algorithm.model.Result;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = ShortestPathServiceAssembler.class)
@Transactional
public class ShortestPathStepDefs {

    @Autowired
    private AlgorithmService algorithmService;

    private String originPlanetName;
    private String destinationPlanetName;
    private Result result;


    @Given("^a user provides two planet names but a given name does not belong to an existing planet$")
    public void a_given_name_does_not_belong_to_an_existing_planet(List<String> planetNames) {
        originPlanetName = planetNames.get(1);
        destinationPlanetName = planetNames.get(2);
    }

    @When("^the user requires the shortest path between the two planets to be found$")
    public void the_shortest_path_between_the_two_planets_is_required() {
        result = new Result();
    }

    @Then("^the user notified that a given name belongs to a planet that does not exist$")
    public void a_custom_exception_is_thrown() {
        assertThrows(PlanetNotFoundException.class, () -> {
            result = algorithmService.findShortestPath(originPlanetName, destinationPlanetName);
        });
    }

    @Given("^a user provides two planet names$")
    public void two_planet_names(List<String> planetNames) {
        originPlanetName = planetNames.get(1);
        destinationPlanetName = planetNames.get(2);
    }

    @When("^the user requires the shortest path between the two planets$")
    public void theUserRequiresTheShortestPathBetweenTheTwoPlanets() {
        result = algorithmService.findShortestPath(originPlanetName, destinationPlanetName);
    }

    @Then("^the user is notified that the shortest path found is \"([^\"]*)\" hops$")
    public void the_shortest_path_found_is_hops(String arg1, List<Integer> hopCount) {
        assertThat(result).isNotNull();
        assertThat(result.getShortestPath().size()).isEqualTo(hopCount.get(0));
    }

    @And("^the user is provided with the route between the two planets$")
    public void theUserIsProvidedWithTheRouteBetweenTheTwoPlanets(List<String> nodes) {
        for (int i = 0; i < result.getShortestPath().size(); i++) {
            assertThat(result.getShortestPath().get(i).getPlanetNodeLabel()).isEqualTo(nodes.get(i + 1));
        }
    }
}
