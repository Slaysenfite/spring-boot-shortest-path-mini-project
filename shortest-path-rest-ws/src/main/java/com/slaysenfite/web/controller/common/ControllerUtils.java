package com.slaysenfite.web.controller.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Utility class containing a common utility method for the web services rest controllers
 */
public final class ControllerUtils {

    private ControllerUtils() {
    }

    /**
     * This method creates a uri for a resource based on request mapping path and resource id
     * to create a response for post, and put requests
     *
     * @param path
     * @param resourceId
     * @return ResponseEntity
     */
    public static ResponseEntity<Void> entityWithLocation(String path, Object... resourceId) {
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequestUri()
            .path(path)
            .buildAndExpand(resourceId)
            .toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * This method maps a java object to a json representation
     * <p>
     * Used for testing only
     *
     * @param obj
     * @return Json string representation of object
     */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
