package algorithm;

import metrics.PerformanceTracker;
import model.Edge;
import model.Graph;
import java.util.*;


public class Kruskal {


    private static void findMST(Graph graph, PerformanceTracker tracker) {
        tracker.reset();
        tracker.startTimer();

        ArrayList<Edge> edges = new ArrayList<>(graph.edges);
        Collections.sort(edges, Comparator.comparingInt(Edge::getWeight));

        Map<String, String> parent = new HashMap<>();
        for(String city : graph.verticies){
            parent.put(city, city);
        }

        ArrayList<Edge> mst = new ArrayList<>();


        for(Edge e : edges){
            tracker.incOperations();
            String formRoot = find(parent, e.getFrom());
            String toRoot = find(parent, e.getTo());
            tracker.incOperations();
            tracker.incOperations();


            if(!formRoot.equals(toRoot)){
                mst.add(e);
                tracker.incTotalCost(e.getWeight());
                parent.put(toRoot, formRoot);
                tracker.incOperations();
            }
        }

        tracker.stopTimer();
        tracker.setExecutionTime();

        tracker.setVerticesCount(graph.verticies.size());
        tracker.setEdgesCount(mst.size());
        tracker.setMstEdges(mst);

    }

    private static String find(Map<String, String> parent, String node) {
        if(parent.get(node).equals(node)) return node;
        return find(parent, parent.get(node));
    }

    public static PerformanceTracker getTracker(Graph graph) {
        PerformanceTracker tracker = new PerformanceTracker();
        findMST(graph, tracker);
        return tracker;
    }

}
