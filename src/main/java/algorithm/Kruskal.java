package algorithm;

import model.Edge;
import model.Graph;

import java.util.*;


public class Kruskal {
    public static void runAlgorithm(Graph graph){
        ArrayList<Edge> edges = new ArrayList<>(graph.edges);
        Collections.sort(edges, Comparator.comparingInt(Edge::getWeight));

        Map<String, String> parent = new HashMap<>();
        for(String city : graph.verticies){
            parent.put(city, city);
        }

        ArrayList<Edge> mst = new ArrayList<>();
        int totalCost = 0;

        for(Edge e : edges){
            String formRoot = find(parent, e.getFrom());
            String toRoot = find(parent, e.getTo());

            if(!formRoot.equals(toRoot)){
                mst.add(e);
                totalCost += e.getWeight();
                parent.put(toRoot, formRoot);
            }
        }

        System.out.println("Kruskal Algorithm results: ");
        for(Edge e : mst){
            System.out.println("  " + e);
        }
        System.out.println("Total cost: " + totalCost);

    }

    private static String find(Map<String, String> parent, String node) {
        if(parent.get(node).equals(node)) return node;
        return find(parent, parent.get(node));
    }

}
