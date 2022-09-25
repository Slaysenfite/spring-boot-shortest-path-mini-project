package com.slaysenfite.domain.algorithm.model;

import com.slaysenfite.domain.entity.Route;

import java.util.List;

/**
 * Model class for an edge of a graph
 */
public class Edge {

    private final Route route;

    public Edge(Route route) {
        this.route = route;
    }

    public Route getRoute() {
        return route;
    }

    public String getOrigin() {
        return route.getOrigin();
    }

    public String getDestination() {
        return route.getDestination();
    }

    public Vertex getSourceVertex(List<Vertex> vertices) {
        for(Vertex vertex : vertices){
            if(vertex.getPlanetNodeLabel().equals(route.getOrigin()))
                return vertex;
        }
        throw new RuntimeException("Could not find source vertex");
    }

    public Vertex getDestinationVertex(List<Vertex> vertices) {
        for(Vertex vertex : vertices){
            if(vertex.getPlanetNodeLabel().equals(route.getDestination()))
                return vertex;
        }
        throw new RuntimeException("Could not find destination vertex");
    }

    public Double getDistance() {
        return route.getDistance();
    }

    public Double getDelay() {
        return route.getDelay();
    }

    public Double getWeight() {
        return route.getDelay()*route.getDistance();
    }
}
