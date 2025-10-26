package algorithm;
import model.Edge;
import model.Graph;

import java.util.*;

public class Prim {

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
        long startTime  = System.currentTimeMillis();

        ArrayList<String> verticies = graph.verticies;
        ArrayList<Edge> edges = graph.edges;

        Set<String> connected = new HashSet<>();
        connected.add(verticies.get(0));

        ArrayList<Edge> mst = new ArrayList<>();
        int totalCost = 0;
        int operations = 0;

        while(connected.size() < verticies.size()){
            Edge cheapest = null;

            for(Edge e : edges){
                operations++;

                String a = e.getFrom();
                String b = e.getTo();

                boolean aIn = connected.contains(a);
                boolean bIn = connected.contains(b);

                if ((aIn && !bIn) || (!aIn && bIn)){
                    if(cheapest == null || e.getWeight() < cheapest.getWeight()){
                        cheapest = e;
                        operations++;
                    }
                }
            }

            if(cheapest == null){
                System.out.println("Graph is disconnected, there is no MST");
                break;
            }

            String addVertex = connected.contains(cheapest.getFrom()) ? cheapest.getTo() : cheapest.getFrom();

            mst.add(cheapest);
            connected.add(addVertex);
            totalCost += cheapest.getWeight();
            operations++;
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

}
