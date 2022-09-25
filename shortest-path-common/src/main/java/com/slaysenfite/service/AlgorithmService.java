package com.slaysenfite.service;

import com.slaysenfite.domain.repository.PlanetRepository;
import com.slaysenfite.domain.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.slaysenfite.domain.algorithm.ShortestPathThroughGraph;
import com.slaysenfite.domain.algorithm.model.Edge;
import com.slaysenfite.domain.algorithm.model.Graph;
import com.slaysenfite.domain.algorithm.model.Result;
import com.slaysenfite.domain.algorithm.model.Vertex;
import com.slaysenfite.domain.entity.Planet;
import com.slaysenfite.domain.entity.Route;
import com.slaysenfite.exception.PlanetNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Algorithm service class. Provides business logic for web service requiring data from the shortest path algorithm.
 * All data regarding routes and planets are loaded into memory when this class is called.
 */
@Service
public class AlgorithmService {

    private PlanetRepository planetRepository;
    private RouteRepository routeRepository;

    private List<Vertex> vertices;
    private List<Edge> edges;
    private Graph graph;
    private ShortestPathThroughGraph shortestPath;

    @Autowired
    public AlgorithmService(PlanetRepository planetRepository, RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
        this.planetRepository = planetRepository;
    }

    public Result findShortestPath(String originPlanetName, String destinationPlanetName) {
        loadEnvironment();

        Vertex origin = findVertexByPlanetName(originPlanetName, vertices);
        Vertex destination = findVertexByPlanetName(destinationPlanetName, vertices);

        shortestPath.findShortestPath(origin);
        List<Vertex> shortestPathNodes = shortestPath.getPath(destination);

        return shortestPath.generateResult(shortestPathNodes);
    }

    private void loadEnvironment() {
        this.vertices = loadPlanetData();
        this.edges = loadRouteData();
        graph = new Graph(vertices, edges);
        shortestPath = new ShortestPathThroughGraph(graph);
    }

    private List<Vertex> loadPlanetData() {
        List<Vertex> vertices = new ArrayList<>();
        List<Planet> planets = planetRepository.findAll();
        for (Planet planet : planets) {
            vertices.add(new Vertex(planet));
        }
        return vertices;
    }

    private List<Edge> loadRouteData() {
        List<Edge> edges = new ArrayList<>();
        List<Route> routes = routeRepository.findAll();
        for (Route route : routes) {
            edges.add(new Edge(route));
        }
        return edges;
    }

    private Vertex findVertexByPlanetName(String planetName, List<Vertex> vertices) {
        for (Vertex vertex : vertices) {
            if (vertex.getPlanetName().equals(planetName)) {
                return vertex;
            }
        }
        throw new PlanetNotFoundException("Planet name - " + planetName + " does not exist",
            new Throwable().fillInStackTrace());
    }

}
