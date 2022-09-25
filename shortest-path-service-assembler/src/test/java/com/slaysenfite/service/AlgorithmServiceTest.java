package com.slaysenfite.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.slaysenfite.domain.algorithm.model.Result;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AlgorithmServiceTest {

    @Autowired
    private AlgorithmService algorithmService;

    @Test
    public void Given_RouteNodeNames_When_ShortestPathIsToBeFound_Then_ReturnCorrectResultObject() {
        Result result = algorithmService.findShortestPath("Earth", "Mystery Missing Planet");

        assertThat(result).isNotNull();
        assertThat(result.pathToString()).isEqualTo("A - C - F - J - R - L'");

        assertThat(result.getShortestPath()).isNotNull();
        assertThat(result.getShortestPath().size()).isEqualTo(6);
    }

    @Test(expected = RuntimeException.class)
    public void Given_IncorrectOriginNodeNames_When_ShortestPathIsToBeFound_Then_RuntimeExceptionThrown() {
        algorithmService.findShortestPath("Eareth", "Neptune");
    }

    @Test(expected = RuntimeException.class)
    public void Given_IncorrectDestinationNodeNames_When_ShortestPathIsToBeFound_Then_RuntimeExceptionThrown() {
        algorithmService.findShortestPath("Earth", "Phallicia");
    }

}
