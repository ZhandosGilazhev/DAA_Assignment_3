package cli;

import algorithm.Kruskal;
import algorithm.Prim;
import model.Graph;
import model.Edge;
import metrics.PerformanceTracker;
import util.WarmupRunner;

public class Benchmark {
    public static void runBenchmark(Graph graph){


        System.out.println("=================================");
        System.out.println();
        graph.printGraph();
        System.out.println();

        WarmupRunner.runWarmup(graph, 100);

        PerformanceTracker kruskalTracker = Kruskal.getTracker(graph);
        printTracker("Kruskal", kruskalTracker);
        System.out.println();

        System.out.println("---------------------------------");

        PerformanceTracker primTracker = Prim.getTracker(graph);
        printTracker("Prim", primTracker);
        System.out.println();

    }

    private static void printTracker(String name, Object obj) {
        System.out.println();
        System.out.println(name + " algorithm: ");
        System.out.println();

        if (obj instanceof PerformanceTracker t) {
            printDetails(t.getMstEdges(),t.getVerticesCount(), t.getEdgesCount(), t.getTotalCost(), t.getOperations(), t.getExecutionTime());
        }
    }

    private static void printDetails(Iterable<Edge> mst,int verts, int edges, int cost, int ops, double time) {
        System.out.println("MST edges:");
        for (Edge e : mst) System.out.println("  " + e);
        System.out.println("Vertices count: " + verts);
        System.out.println("Edges count: " + edges);
        System.out.println("Total cost: " + cost);
        System.out.println("Operation count: " + ops);
        System.out.println("Execution time: " + time + " ms");
    }
}
