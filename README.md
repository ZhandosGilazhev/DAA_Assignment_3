# **Assignment 3: Optimization of a City Transportation Network (Minimum Spanning Tree)**

## Overview:

This project was developed as part of the course

The main goal is to implement and test two key algorithms for finding the Minimum Spanning Tree (MST) in a graph: Prim's Algorithm and Kruskal's Algorithm.

## Project structure:

src/<br>
└── main/<br>
├── java/<br>
│ ├── model/<br>
│ │ ├── Edge.java<br>
│ │ └── Graph.java<br>
│ ├── algorithm/<br>
│ │ ├── Prim.java<br>
│ │ └── Kruskal.java<br>
│ ├── io/<br>
│ │ ├── JsonReader.java<br>
│ │ ├── JsonWriter.java<br>
│ │ └── CsvWriter.java<br>
│ ├── cli/<br>
│ │ ├── Benchmark.java<br>
│ │ └── JsonBenchmarkExporter.java<br>
│ ├── metrics/<br>
│ │ └── PerformanceTracker.java<br>
│ ├── util/<br>
│ │ └── WarmupRunner.java<br>
│ └── Main.java<br>
├── resources/<br>
│ │ ├── input.json<br>
│ │ ├── output.json<br>
│ │ └── performance_summary.csv<br>
└──test<br>
│ │ └──java/tests/AlgorithmTests.java<br>

## How to launch a project:

1. Make sure that **Java 17+** and **Maven** are installed.
2. Import the project into IntelliJ IDEA (as a Maven project).
3. Make sure the **Gson** and Junit libraries are included in `pom.xml`:
```xml
<dependency>
<groupId>com.google.code.gson</groupId>
<artifactId>gson</artifactId>
<version>2.10.1</version>
</dependency>
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.0</version>
    <scope>test</scope>
</dependency>
```
4. Place input.json in the src/main/resources/ folder.
5. Run the Main class.

## Input data description (input.json)
### Example of input file structure:
```json
{
    "graphs": [
        {
        "name": "Small_KZ",
        "vertices": ["Almaty", "Astana", "Shymkent", "Atyrau"],
        "edges": [
                {"from": "Almaty", "to": "Astana", "weight": 5},
                {"from": "Almaty", "to": "Atyrau", "weight": 3},
                {"from": "Atyrau", "to": "Shymkent", "weight": 2},
                {"from": "Astana", "to": "Shymkent", "weight": 8}
            ]
        }
    ]
}
```
## Output data (output.json)
After execution, the program creates (or overwrites) the output.json file with the results:
```json
{
  "results": [
    {
      "graph_id": 1,
      "input_stats": {
        "vertices": 4,
        "edges": 4
      },
      "prim": {
        "mst_edges": [
          {
            "from": "Almaty",
            "to": "Taldykorgan",
            "weight": 3
          }
        ],
        "total_cost": 97,
        "operations_count": 983,
        "execution_time_ms": 0.2683
      }
    }
  ]
}

```

## Example console output:
```
=====================================

Cities: [Almaty, Astana, Atyrau, Shymkent]
Roads: 
  Almaty -> Astana ( 5 ) 
  Almaty -> Atyrau ( 3 ) 
  Atyrau -> Shymkent ( 2 ) 
  Astana -> Shymkent ( 8 ) 

Prim Algorithm Result: 
 Almaty -> Atyrau ( 3 ) 
 Atyrau -> Shymkent ( 2 ) 
 Almaty -> Astana ( 5 ) 
Total Cost: 10

Kruskal Algorithm results: 
  Atyrau -> Shymkent ( 2 ) 
  Almaty -> Atyrau ( 3 ) 
  Almaty -> Astana ( 5 ) 
Total cost: 10
=====================================
```
# Report: Comparison of Prim’s and Kruskal’s Algorithms for City Graphs

## 1. Input Data and Results

I used three graphs of different sizes: Small, Medium, and Large. Each graph contains a list of cities (vertices) and roads (edges) with weights.

| Graph Name   | Vertices | Edges | Algorithm | MST Total Cost | Operations Count | Execution Time (ms) |
|-------------|----------|-------|-----------|----------------|-----------------|------------------|
| Small_KZ    | 4        | 4     | Prim      | 10             | 20              | 0.0141           |
| Small_KZ    | 4        | 4     | Kruskal   | 10             | 15              | 0.0273           |
| Medium_KZ   | 10       | 14    | Prim      | 34             | 150             | 0.0148           |
| Medium_KZ   | 10       | 14    | Kruskal   | 34             | 51              | 0.0246           |
| Large_KZ    | 25       | 38    | Prim      | 97             | 983             | 0.0825           |
| Large_KZ    | 25       | 38    | Kruskal   | 97             | 138             | 0.0552           |

**How I measured time:**
- First, I measured it in nanoseconds and then converted to milliseconds.
- I ran “warm-up” executions to remove the delay from the first iteration caused by the JVM.

**Observations:**
- Both algorithms give the same MST total cost.
- Prim performs more operations, especially on the large graph.
- The difference in execution time is small on small graphs, but Kruskal is faster on larger graphs.

---

## 2. Algorithm Comparison

**Prim’s Algorithm:**
- Starts from one vertex and gradually adds the nearest edges.
- Good for dense graphs.
- Theoretical complexity without a heap: O(V^2).

**Kruskal’s Algorithm:**
- Sorts all edges and adds them in ascending order, avoiding cycles.
- Good for sparse graphs.
- Theoretical complexity: O(E log E).

---

## 3. Automated Tests

For each graph, I ran automated tests (JUnit) to check:

- MST total cost is the same for Prim and Kruskal.
- Number of edges in MST = V − 1.
- MST contains no cycles.
- MST connects all vertices.
- Disconnected graphs are handled correctly (no MST or clear indication).
- Metrics (time, operations) are non-negative and reproducible.

These tests give confidence that my measurements and MST results are correct.

---

## 4. Theory vs Practice Through Operations

**Idea:**
- I used the number of operations as an “O-metric” to check if code behavior matches theory.

| Graph     | V   | E  | Theory Prim O(V²) | Actual Operations Prim | Theory Kruskal O(E log E) | Actual Operations Kruskal |
|-----------|-----|----|-------------------|-----------------------|---------------------------|---------------------------|
| Small_KZ  | 4   | 4  | 16                | 20                    | 4 × log₂4 = 8             | 15                        |
| Medium_KZ | 10  | 14 | 100               | 150                   | 14 × log₂14 = +-54        | 51                        |
| Large_KZ  | 25  | 38 | 625               | 983                   | 38 × log₂38 = +-194       | 138                       |

**How I calculated it:**
- E = number of edges, V = number of vertices
- “Actual operations” = counter in the code (Prim: minimum edge selection, comparisons; Kruskal: unions, cycle checks)

**Conclusion:**
- Prim without heap grows roughly as V^2, matching theory for dense graphs.
- Kruskal grows as O(E log E), also matching theory.
- Practice confirms theory: on large graphs, Prim requires more operations, and Kruskal is more predictable.

---

## 5. Conclusions

- For **dense graphs**, Prim is better.
- For **sparse graphs**, Kruskal is more convenient and faster.
- For small graphs, either algorithm works fine.
- Theory matches practice:
    - Operation growth aligns with theoretical estimates.
    - MST is correct and identical for all graphs.
    - Execution time grows as expected.

---


## Author:
#### Student: Zhandos Gilazhev
#### Group: SE - 2439
#### Course: Design and Analysis of Algorithms
#### Assignment: Optimization of a City Transportation Network (Minimum Spanning Tree)
