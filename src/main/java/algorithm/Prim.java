package algorithm;
import model.Edge;
import model.Graph;

import java.util.*;

public class Prim {
    public static void runAlgorithm(Graph graph){
        ArrayList<String> verticies = graph.verticies;
        ArrayList<Edge> edges = graph.edges;

        if(verticies.isEmpty()){
            System.out.println("No verticies");
            return;
        }

        Set<String> connected = new HashSet<>();
        connected.add(verticies.get(0));

        ArrayList<Edge> mst = new ArrayList<>();
        int totalCost = 0;

        while(connected.size() < verticies.size()){
            Edge cheapest = null;

            for(Edge e : edges){

                String a = e.getFrom();
                String b = e.getTo();

                boolean aIn = connected.contains(a);
                boolean bIn = connected.contains(b);

                if (aIn && !bIn){
                    if(cheapest == null || e.getWeight() < cheapest.getWeight()){
                        cheapest = e;
                    }
                }else if(!aIn && bIn){
                    if(cheapest == null || e.getWeight() < cheapest.getWeight()){
                        cheapest = e;
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
        }

        System.out.println("Prim Algorithm Result: ");
        for(Edge e : mst){
            System.out.println(" " + e);
        }

        System.out.println("Total Cost: " + totalCost);
    }

}
