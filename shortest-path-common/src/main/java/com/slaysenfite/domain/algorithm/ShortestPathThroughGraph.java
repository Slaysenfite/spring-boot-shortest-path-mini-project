package com.slaysenfite.domain.algorithm;

import com.slaysenfite.domain.algorithm.model.Edge;
import com.slaysenfite.domain.algorithm.model.Graph;
import com.slaysenfite.domain.algorithm.model.Result;
import com.slaysenfite.domain.algorithm.model.Vertex;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class is an implementation of Dijkstra's Shortest Path Algorithm
 *
 * This implementation was "inspired" by the following tutorials:
 *  https://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html
 *  https://blog.cleancoder.com/uncle-bob/2016/10/26/DijkstrasAlg.html
 *  https://www.baeldung.com/java-dijkstra
 */
public class ShortestPathThroughGraph {

    private final List<Vertex> nodes;
    private final List<Edge> edges;
    private Set<Vertex> settledNodes;
    private Set<Vertex> unsettledNodes;
    private Map<Vertex, Vertex> predecessorMap;
    private Map<Vertex, BigDecimal> distanceMap;

    public ShortestPathThroughGraph(Graph graph) {
        this.nodes = new ArrayList<>(graph.getVertices());
        this.edges = new ArrayList<>(graph.getEdges());
    }

    public void findShortestPath(Vertex source) {
        initializeAlgorithm(source);
        while (!unsettledNodes.isEmpty()) {
            evaluateUnsettledNodes();
        }
    }

    private void evaluateUnsettledNodes() {
        Vertex node = getVertexWithMinDistance(unsettledNodes);
        settledNodes.add(node);
        unsettledNodes.remove(node);
        findMinDistance(node);
    }

    private void initializeAlgorithm(Vertex source) {
        settledNodes = new HashSet<>();
        unsettledNodes = new HashSet<>();
        distanceMap = new HashMap<>();
        predecessorMap = new HashMap<>();
        distanceMap.put(source, BigDecimal.ZERO);
        unsettledNodes.add(source);
    }

    private void findMinDistance(Vertex node) {
        List<Vertex> adjacentNodes = getNeighbors(node);
        for (Vertex target : adjacentNodes) {
            if (getShortestDistance(target).compareTo(getShortestDistance(node).add(getDistance(node, target))) == 1) {
                distanceMap.put(target, getShortestDistance(node).add(getDistance(node, target)));
                predecessorMap.put(target, node);
                unsettledNodes.add(target);
            }
        }
    }

    private BigDecimal getDistance(Vertex node, Vertex target) {
        BigDecimal weight = null;
        for (Edge edge : edges) {
            if (edge.getSourceVertex(this.nodes).equals(node)
                && edge.getDestinationVertex(this.nodes).equals(target)) {
                weight = BigDecimal.valueOf(edge.getWeight());
            }
        }
        return weight;
    }

    private List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        for (Edge edge : edges) {
            if (edge.getSourceVertex(this.nodes).equals(node)
                && !isSettled(edge.getDestinationVertex(this.nodes))) {
                neighbors.add(edge.getDestinationVertex(this.nodes));
            }
        }
        return neighbors;
    }

    private boolean isSettled(Vertex destination) {
        return settledNodes.contains(destination);
    }

    private BigDecimal getShortestDistance(Vertex destination) {
        BigDecimal distance = distanceMap.get(destination);
        if (distance == null) {
            return BigDecimal.valueOf(Double.MAX_VALUE);
        } else {
            return distance;
        }
    }

    private Vertex getVertexWithMinDistance(Set<Vertex> unsettledNodes) {
        Vertex minimum = null;
        for (Vertex vertex : unsettledNodes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex).compareTo(getShortestDistance(minimum)) == -1) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    public List<Vertex> getPath(Vertex target) {
        List<Vertex> path = new LinkedList<>();
        Vertex step = target;
        if (predecessorMap.get(step) == null) {
            return new ArrayList<>();
        }
        path.add(step);
        while (predecessorMap.get(step) != null) {
            step = predecessorMap.get(step);
            path.add(step);
        }
        Collections.reverse(path);
        return path;
    }

    public Result generateResult(List<Vertex> shortestPath){
        List<BigDecimal> distances = new ArrayList<>();
        for(int i = 0; i < shortestPath.size() - 1; i++){
            distances.add(getDistance(shortestPath.get(i), shortestPath.get(i + 1)));
        }
        return new Result(shortestPath, distances);
    }
}
