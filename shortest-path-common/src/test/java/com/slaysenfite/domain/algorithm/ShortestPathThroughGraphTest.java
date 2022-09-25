package com.slaysenfite.domain.algorithm;

import org.junit.jupiter.api.Test;
import com.slaysenfite.domain.algorithm.model.Edge;
import com.slaysenfite.domain.algorithm.model.Graph;
import com.slaysenfite.domain.algorithm.model.Result;
import com.slaysenfite.domain.algorithm.model.Vertex;
import com.slaysenfite.domain.entity.Planet;
import com.slaysenfite.domain.entity.Route;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static com.slaysenfite.domain.algorithm.MockShortestPathGraphData.eightEdgesList;
import static com.slaysenfite.domain.algorithm.MockShortestPathGraphData.emptyGraph;
import static com.slaysenfite.domain.algorithm.MockShortestPathGraphData.sixVerticesList;

class ShortestPathThroughGraphTest {

    @Test
    public void Given_DegenerateGraph_When_ShortestPathIsToBeFound_Then_Return_EmptyPath() {
        Planet origin = new Planet("~", "AAA");
        Graph graph = emptyGraph();
        ShortestPathThroughGraph path = new ShortestPathThroughGraph(graph);
        path.findShortestPath(new Vertex(origin));
        List<Vertex> vertices = path.getPath(new Vertex(origin));

        assertThat(vertices).isEqualTo(new ArrayList<>());

        Result result = path.generateResult(vertices);

        assertThat(result.getTotalCost()).isEqualTo(BigDecimal.ZERO);
        assertThat(result.pathToString()).isEqualTo("");
    }

    @Test void Given_GraphWithOneEdge_When_ShortestPathIsToBeFound_Then_Return_OnlyExistingPath(){
        Planet origin = new Planet("~", "AAA");
        Planet destination = new Planet("`", "BBB");

        List<Vertex> vertices = new ArrayList<>();
        vertices.add(new Vertex(origin));
        vertices.add(new Vertex(destination));

        Route route = new Route(1, "~", "`", 2.0, 6.5);
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(route));

        Graph graph = new Graph(vertices, edges);

        ShortestPathThroughGraph path = new ShortestPathThroughGraph(graph);
        path.findShortestPath(vertices.get(0));
        List<Vertex> pathVertices = path.getPath(vertices.get(vertices.size() - 1));

        assertThat(pathVertices).isNotNull();
        assertThat(pathVertices.size()).isEqualTo(2);

        Result result = path.generateResult(pathVertices);

        assertThat(result.getTotalCost()).isEqualTo(BigDecimal.valueOf(13.0));
        assertThat(result.pathToString()).isEqualTo("~ - `");
    }

    @Test
    public void Given_InterstellarGraphData_When_ShortestPathThroughGraph_Then_OrderedListOfShortestPathIsReturned() {
        List<Vertex> nodes = sixVerticesList();
        List<Edge> edges = eightEdgesList();

        Graph graph = new Graph(nodes, edges);

        ShortestPathThroughGraph shortestPath = new ShortestPathThroughGraph(graph);
        shortestPath.findShortestPath(nodes.get(0));
        List<Vertex> path = shortestPath.getPath(nodes.get(nodes.size() - 1));

        assertThat(path).isNotNull();
        assertThat(path.size()).isGreaterThan(0);
        assertThat(path.size()).isLessThanOrEqualTo(nodes.size());

        Result result = shortestPath.generateResult(path);

        assertThat(result.getTotalCost()).isEqualTo(BigDecimal.valueOf(23.0));
        assertThat(result.pathToString()).isEqualTo("A - B - D - F");
    }
}
