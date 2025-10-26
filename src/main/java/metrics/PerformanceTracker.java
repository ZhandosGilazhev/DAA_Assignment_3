package metrics;

import model.Edge;
import java.util.ArrayList;

public class PerformanceTracker {
    private ArrayList<Edge> mstEdges = new ArrayList<>();
    private int totalCost = 0;
    private int operations = 0;
    private int verticesCount = 0;
    private int edgesCount = 0;
    private double executionTime = 0;

    private long startTime = 0;
    private long endTime = 0;

    public PerformanceTracker() {
        this.mstEdges = new ArrayList<>();
    }

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        endTime = System.nanoTime();
    }

    public void setExecutionTime() {
        this.executionTime = (endTime - startTime)/1_000_000.0;
    }

    public void incOperations() {
        this.operations++;
    }

    public void incTotalCost(int weight) {
        this.totalCost+=weight;
    }

    public void setVerticesCount(int count) {
        this.verticesCount = count;
    }

    public void setEdgesCount(int count) {
        this.edgesCount = count;
    }

    public void addMstEdge(Edge edge) {
        mstEdges.add(edge);
    }

    public void setMstEdges(ArrayList<Edge> mstEdges) {
        this.mstEdges = mstEdges;
    }

    public ArrayList<Edge> getMstEdges() {return mstEdges;}
    public int getTotalCost() {return totalCost;}
    public int getOperations() {return operations;}
    public int getVerticesCount() {return verticesCount;}
    public int getEdgesCount() {return edgesCount;}
    public double getExecutionTime() {return executionTime;}

    public void reset(){
        totalCost = 0;
        operations = 0;
        verticesCount = 0;
        edgesCount = 0;
        executionTime = 0;
        startTime = 0;
        endTime = 0;
        if (mstEdges != null) mstEdges.clear();
    }
}
