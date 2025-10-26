package tests;

import algorithm.Kruskal;
import algorithm.Prim;
import io.JsonReader;
import model.*;
import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AlgorithmTests {

    List<Graph> graphs = JsonReader.readGraphsFromJson("src/main/resources/input.json");

    @Test
    public void testMSTCostEquality() {
        for (Graph g : graphs) {
            PerformanceTracker primTracker = Prim.getTracker(g);
            PerformanceTracker kruskalTracker = Kruskal.getTracker(g);

            assertEquals(primTracker.getTotalCost(), kruskalTracker.getTotalCost(),
                    "MST total cost differs between Prim and Kruskal");
        }
    }

    @Test
    public void testMSTEdgeCount() {
        for (Graph g : graphs) {
            int expectedEdgeCount = g.verticies.size() - 1;

            PerformanceTracker primTracker = Prim.getTracker(g);
            PerformanceTracker kruskalTracker = Kruskal.getTracker(g);

            assertEquals(expectedEdgeCount, primTracker.getEdgesCount(), "Prim MST edge count incorrect");
            assertEquals(expectedEdgeCount, kruskalTracker.getEdgesCount(), "Kruskal MST edge count incorrect");
        }
    }

    @Test
    public void testMSTAcyclic() {
        for (Graph g : graphs) {
            PerformanceTracker primTracker = Prim.getTracker(g);
            PerformanceTracker kruskalTracker = Kruskal.getTracker(g);

            assertTrue(isAcyclic(primTracker.getMstEdges()), "Prim MST contains a cycle");
            assertTrue(isAcyclic(kruskalTracker.getMstEdges()), "Kruskal MST contains a cycle");
        }
    }

    @Test
    public void testMSTConnectivity() {
        for (Graph g : graphs) {
            PerformanceTracker primTracker = Prim.getTracker(g);
            PerformanceTracker kruskalTracker = Kruskal.getTracker(g);

            assertTrue(isConnected(primTracker.getMstEdges(), g.verticies.size()), "Prim MST is disconnected");
            assertTrue(isConnected(kruskalTracker.getMstEdges(), g.verticies.size()), "Kruskal MST is disconnected");
        }
    }

    @Test
    public void testDisconnectedGraphHandling() {
        Graph disconnected = new Graph("Disconnected");
        disconnected.addEdge("A", "B", 1);
        disconnected.addEdge("C", "D", 2);

        PerformanceTracker primTracker = Prim.getTracker(disconnected);
        PerformanceTracker kruskalTracker = Kruskal.getTracker(disconnected);

        assertTrue(primTracker.getEdgesCount() < disconnected.verticies.size() - 1,
                "Prim incorrectly built MST on disconnected graph");
        assertTrue(kruskalTracker.getEdgesCount() < disconnected.verticies.size() - 1,
                "Kruskal incorrectly built MST on disconnected graph");
    }


    @Test
    public void testExecutionTimeNonNegative() {
        for (Graph g : graphs) {
            PerformanceTracker primTracker = Prim.getTracker(g);
            PerformanceTracker kruskalTracker = Kruskal.getTracker(g);

            assertTrue(primTracker.getExecutionTime() >= 0, "Prim execution time negative");
            assertTrue(kruskalTracker.getExecutionTime() >= 0, "Kruskal execution time negative");
        }
    }

    @Test
    public void testOperationCountsNonNegative() {
        for (Graph g : graphs) {
            PerformanceTracker primTracker = Prim.getTracker(g);
            PerformanceTracker kruskalTracker = Kruskal.getTracker(g);

            assertTrue(primTracker.getOperations() >= 0, "Prim operations negative");
            assertTrue(kruskalTracker.getOperations() >= 0, "Kruskal operations negative");
        }
    }

    @Test
    public void testResultsReproducible() {
        for (Graph g : graphs) {
            PerformanceTracker primTracker1 = Prim.getTracker(g);
            PerformanceTracker primTracker2 = Prim.getTracker(g);
            PerformanceTracker kruskalTracker1 = Kruskal.getTracker(g);
            PerformanceTracker kruskalTracker2 = Kruskal.getTracker(g);

            assertEquals(primTracker1.getMstEdges().size(), primTracker2.getMstEdges().size(), "Prim MST not reproducible");
            assertEquals(kruskalTracker1.getMstEdges().size(), kruskalTracker2.getMstEdges().size(), "Kruskal MST not reproducible");
        }
    }


    private boolean isAcyclic(List<Edge> mst) {
        Map<String, String> parent = new HashMap<>();
        for (Edge e : mst) {
            parent.putIfAbsent(e.getFrom(), e.getFrom());
            parent.putIfAbsent(e.getTo(), e.getTo());

            String rootFrom = findParent(parent, e.getFrom());
            String rootTo = findParent(parent, e.getTo());

            if (rootFrom.equals(rootTo)) return false;
            parent.put(rootFrom, rootTo);
        }
        return true;
    }

    private String findParent(Map<String, String> parent, String node) {
        if (parent.get(node).equals(node)) return node;
        return findParent(parent, parent.get(node));
    }

    private boolean isConnected(List<Edge> mst, int vertexCount) {
        Set<String> verticesInMST = new HashSet<>();
        for (Edge e : mst) {
            verticesInMST.add(e.getFrom());
            verticesInMST.add(e.getTo());
        }
        return verticesInMST.size() == vertexCount;
    }
}
