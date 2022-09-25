package com.slaysenfite.domain.algorithm;

import com.slaysenfite.domain.algorithm.model.Edge;
import com.slaysenfite.domain.algorithm.model.Graph;
import com.slaysenfite.domain.algorithm.model.Result;
import com.slaysenfite.domain.algorithm.model.Vertex;
import com.slaysenfite.domain.entity.Planet;
import com.slaysenfite.domain.entity.Route;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MockShortestPathGraphData {

    public static Graph emptyGraph(){
        return new Graph(new LinkedList<>(), new LinkedList<>());
    }

    public static Result emptyResult(){
        return new Result(new ArrayList<>(), new ArrayList<>());
    }


    public static List<Vertex> sixVerticesList(){
        List<Vertex> vertices = new ArrayList<>();

        vertices.add(new Vertex(new Planet("A", "planet1")));
        vertices.add(new Vertex(new Planet("B", "planet2")));
        vertices.add(new Vertex(new Planet("C", "planet3")));
        vertices.add(new Vertex(new Planet("D", "planet4")));
        vertices.add(new Vertex(new Planet("E", "planet5")));
        vertices.add(new Vertex(new Planet("F", "planet6")));

        return vertices;
    }

    public static List<Edge> eightEdgesList() {
        List<Edge> edges = new ArrayList<>();

        edges.add(new Edge(new Route(1, "A", "B", 10.00, 1.00)));
        edges.add(new Edge(new Route(2, "A", "C", 15.00, 1.00)));
        edges.add(new Edge(new Route(3, "B", "D", 12.00, 1.00)));
        edges.add(new Edge(new Route(4, "B", "F", 15.00, 1.00)));
        edges.add(new Edge(new Route(5, "C", "E", 10.00, 1.00)));
        edges.add(new Edge(new Route(6, "D", "E", 2.00, 1.00)));
        edges.add(new Edge(new Route(7, "D", "F", 1.00, 1.00)));
        edges.add(new Edge(new Route(8, "F", "E", 5.00, 1.00)));

        return edges;
    }
}
