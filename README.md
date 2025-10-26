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

## Author:
#### Student: Zhandos Gilazhev
#### Course: Design and Analysis of Algorithms
#### Assignment: Optimization of a City Transportation Network (Minimum Spanning Tree)
