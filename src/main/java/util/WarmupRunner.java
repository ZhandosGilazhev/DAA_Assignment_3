package util;

import algorithm.Kruskal;
import algorithm.Prim;
import model.Graph;

public class WarmupRunner {
    public static void runWarmup(Graph graph, int iterations) {
        for (int i = 0; i < iterations; i++) {
            Kruskal.findMST(graph);
            Prim.findMST(graph);
        }
    }
}
