package com.slaysenfite.domain.algorithm.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * This class is used to encapsulate data produced by the shortest path algorithm
 */
public class Result {
    private List<Vertex> shortestPath;
    private List<BigDecimal> costBetweenVertices;

    public Result(){}

    public Result(List<Vertex> shortestPath, List<BigDecimal> distances) {
        this.shortestPath = shortestPath;
        this.costBetweenVertices = distances;
    }

    public List<Vertex> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Vertex> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public List<BigDecimal> getCostBetweenVertices() {
        return costBetweenVertices;
    }

    public void setCostBetweenVertices(List<BigDecimal> costBetweenVertices) {
        this.costBetweenVertices = costBetweenVertices;
    }

    /**
     * Calculates the total cost of the shortest path
     * @return BigDecimal
     */
    public BigDecimal getTotalCost() {
        BigDecimal totalCost = BigDecimal.ZERO;
        for (BigDecimal cost : costBetweenVertices) {
            totalCost = totalCost.add(cost);
        }
        return totalCost;
    }

    /**
     * This method prints the shortest nodes in the order of shortest path traversal
     *
     * @return String
     */
    public String pathToString() {
        if (!shortestPath.isEmpty()) {
            return buildPathString();
        } else {
            return "";
        }
    }

    private String buildPathString() {
        String path = shortestPath.get(0).getPlanetNodeLabel();
        if (shortestPath.size() > 1) {
            path = formattedString(path);
        } else {
            return path;
        }
        return path;
    }

    //TODO: replace concatenation with string builder
    private String formattedString(String path) {
        for (int i = 1; i < shortestPath.size(); i++) {
            path += " - " + shortestPath.get(i).getPlanetNodeLabel();
        }
        return path;
    }
}
