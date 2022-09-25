package com.slaysenfite.web.controller.common;

import org.junit.jupiter.api.Test;
import com.slaysenfite.domain.entity.Route;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static com.slaysenfite.web.controller.common.ControllerUtils.asJsonString;

public class ControllerUtilsTest {

    @Test
    public void Given_Object_When_ObjectIsToBeSerialized_Then_ObjectCanBeRepresentedAsAJsonString() throws FileNotFoundException {
        Route route = new Route(2, "3", "4", 0.84, 0.99);

        String jsonString = asJsonString(route);
        String dummyRouteAsJson = new Scanner(new File("src/test/resources/dummyJsonString.json")).useDelimiter("\\Z").next();

        assertThat(jsonString).isNotNull();
        assertThat(jsonString).isEqualTo(dummyRouteAsJson);
    }
}
