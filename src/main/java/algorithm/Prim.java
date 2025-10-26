package algorithm;

import metrics.PerformanceTracker;
import model.Edge;
import model.Graph;

import java.util.*;

public class Prim {

    private static void findMST(Graph graph, PerformanceTracker tracker) {
        tracker.reset();
        tracker.startTimer();

        ArrayList<String> vertices = graph.verticies;
        ArrayList<Edge> edges = graph.edges;

        tracker.setVerticesCount(vertices.size());

        Set<String> connected = new HashSet<>();
        connected.add(vertices.get(0));

        while (connected.size() < vertices.size()) {
            Edge cheapest = null;

            for (Edge e : edges) {
                tracker.incOperations();

                boolean aIn = connected.contains(e.getFrom());
                boolean bIn = connected.contains(e.getTo());

                if ((aIn && !bIn) || (!aIn && bIn)) {
                    if (cheapest == null || e.getWeight() < cheapest.getWeight()) {
                        cheapest = e;
                        tracker.incOperations();
                    }
                }
            }

            if (cheapest == null) {
                System.out.println("Graph is disconnected, there is no MST");
                break;
            }

            String addVertex = connected.contains(cheapest.getFrom()) ? cheapest.getTo() : cheapest.getFrom();

            tracker.addMstEdge(cheapest);
            tracker.incTotalCost(cheapest.getWeight());
            connected.add(addVertex);
            tracker.incOperations();
        }

        tracker.setEdgesCount(tracker.getMstEdges().size());

        tracker.stopTimer();
        tracker.setExecutionTime();
    }


    public static PerformanceTracker getTracker(Graph graph) {
        PerformanceTracker tracker = new PerformanceTracker();
        findMST(graph, tracker);
        return tracker;
    }
}

