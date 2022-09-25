package com.slaysenfite.domain.algorithm.model;

import java.util.List;

/**
 * Model for a graph data structure
 */
public class Graph {
    private final List<Vertex> vertexes;
    private final List<Edge> edges;

    public Graph(List<Vertex> vertexes, List<Edge> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    public List<Vertex> getVertices() {
        return vertexes;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
