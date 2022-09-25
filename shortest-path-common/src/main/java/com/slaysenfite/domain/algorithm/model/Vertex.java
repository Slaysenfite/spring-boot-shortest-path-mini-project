package com.slaysenfite.domain.algorithm.model;

import com.slaysenfite.domain.entity.Planet;

/**
 * Model class for a vertex of a graph
 */
public class Vertex {

    private Planet planet;

    public Vertex(){}

    public Vertex(Planet planet){
        this.planet = planet;
    }

    public Planet getPlanet() {
        return planet;
    }

    public String getPlanetName(){
        return planet.getName();
    }

    public String getPlanetNodeLabel(){
        return planet.getNode();
    }
}
