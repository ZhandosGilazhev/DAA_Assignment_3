package cli;

import algorithm.Kruskal;
import algorithm.Prim;
import model.Graph;
import model.Edge;
import util.WarmupRunner;

public class Benchmark {
    public static void runBenchmark(Graph graph){

        System.out.println("=================================");
        System.out.println();
        graph.printGraph();
        System.out.println();

        WarmupRunner.runWarmup(graph, 100);

        Kruskal.Result kruskalResult = Kruskal.runAlgorithm(graph);
        printResult("Kruskal", kruskalResult);
        System.out.println();

        System.out.println("---------------------------------");

        Prim.Result primResult = Prim.runAlgorithm(graph);
        printResult("Prim", primResult);
        System.out.println();

    }

    private static void printResult(String name, Object res) {
        System.out.println();
        System.out.println(name + " algorithm: ");
        System.out.println();

        if (res instanceof Kruskal.Result r) {
            printDetails(r.mstEdges,r.verticesCount, r.edgesCount, r.totalCost, r.operations, r.executionTime);
        } else if (res instanceof Prim.Result r) {
            printDetails(r.mstEdges,r.verticesCount, r.edgesCount, r.totalCost, r.operations, r.executionTime);
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
