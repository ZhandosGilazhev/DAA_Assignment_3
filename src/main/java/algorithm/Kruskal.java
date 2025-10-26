package algorithm;

import model.Edge;
import model.Graph;

import java.util.*;


public class Kruskal {

    public static class Result{
        public ArrayList<Edge> mstEdges;
        public int totalCost;
        public int operations;
        public int verticesCount;
        public int edgesCount;
        public long executionTime;

        public Result(ArrayList<Edge> mstEdges, int totalCost, int operations, int verticesCount, int edgesCount, long executionTime) {
            this.mstEdges = mstEdges;
            this.totalCost = totalCost;
            this.operations = operations;
            this.verticesCount = verticesCount;
            this.edgesCount = edgesCount;
            this.executionTime = executionTime;
        }
    }

    public static Result runAlgorithm(Graph graph){
        long startTime = System.currentTimeMillis();

        ArrayList<Edge> edges = new ArrayList<>(graph.edges);
        Collections.sort(edges, Comparator.comparingInt(Edge::getWeight));

        Map<String, String> parent = new HashMap<>();
        for(String city : graph.verticies){
            parent.put(city, city);
        }

        ArrayList<Edge> mst = new ArrayList<>();
        int totalCost = 0;
        int operations = 0;

        for(Edge e : edges){
            operations++;
            String formRoot = find(parent, e.getFrom());
            String toRoot = find(parent, e.getTo());
            operations += 2;

            if(!formRoot.equals(toRoot)){
                mst.add(e);
                totalCost += e.getWeight();
                parent.put(toRoot, formRoot);
                operations++;
            }
        }

        long endTime = System.currentTimeMillis();

        return new Result(
                mst,
                totalCost,
                operations,
                graph.verticies.size(),
                graph.edges.size(),
                endTime - startTime
        );
    }

    private static String find(Map<String, String> parent, String node) {
        if(parent.get(node).equals(node)) return node;
        return find(parent, parent.get(node));
    }

}
