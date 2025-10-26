package cli;

import algorithm.Kruskal;
import algorithm.Prim;
import model.Graph;
import model.Edge;

public class Benchmark {
    public static void runBenchmark(Graph graph){

        System.out.println("=================================");
        System.out.println();
        graph.printGraph();
        System.out.println();

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
            printDetails(r.mstEdges, r.totalCost, r.operations, r.executionTime);
        } else if (res instanceof Prim.Result r) {
            printDetails(r.mstEdges, r.totalCost, r.operations, r.executionTime);
        }
    }

    private static void printDetails(Iterable<Edge> mst, int cost, int ops, long time) {
        System.out.println("MST edges:");
        for (Edge e : mst) System.out.println("  " + e);
        System.out.println("Total cost: " + cost);
        System.out.println("Operations: " + ops);
        System.out.println("Execution time: " + time + " ms");
    }
}
