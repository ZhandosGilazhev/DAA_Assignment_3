package algorithm;

import metrics.PerformanceTracker;
import model.Edge;
import model.Graph;

import java.util.*;

public class Prim {

    private static PerformanceTracker tracker = new PerformanceTracker();

    public static ArrayList<Edge> findMST(Graph graph) {
        tracker.reset();
        tracker.startTimer();

        ArrayList<String> verticies = graph.verticies;
        ArrayList<Edge> edges = graph.edges;

        tracker.setVerticesCount(verticies.size());

        Set<String> connected = new HashSet<>();
        connected.add(verticies.get(0));

        while (connected.size() < verticies.size()) {
            Edge cheapest = null;

            for (Edge e : edges) {
                tracker.incOperations();

                String a = e.getFrom();
                String b = e.getTo();

                boolean aIn = connected.contains(a);
                boolean bIn = connected.contains(b);

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

        return tracker.getMstEdges();
    }

    public static PerformanceTracker getTracker(Graph graph) {
        findMST(graph);
        return tracker;
    }

}
