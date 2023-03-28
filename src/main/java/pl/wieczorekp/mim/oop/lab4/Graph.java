package pl.wieczorekp.mim.oop.lab4;

import lombok.Getter;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;

public class Graph {
    @Getter
    private ArrayList<ArrayList<SimpleEntry<Integer, Integer>>> g;
    private int graphSize;
    private int edgeCount;

    Graph(int graphSize, int edgeCount) {
        this.graphSize = graphSize;
        this.edgeCount = edgeCount;
    }

    Graph() {
        graphSize = 0;
        edgeCount = 0;
    }

    public void readGraph() {
        Scanner s = new Scanner(System.in);
        int graphSize = s.nextInt();
        int edgeCount = s.nextInt();
        g = new ArrayList<>(graphSize+1);

        this.graphSize = graphSize;
        this.edgeCount = edgeCount;

        for (int i = 0; i < edgeCount; i++) {
            int a, b, cost;
            a = s.nextInt();
            b = s.nextInt();
            cost = s.nextInt();

            g.get(a).add(b, new SimpleEntry<>(b, cost));
        }
    }

    public void addEdge(int src, int dst, int cost) {
        g.get(src).add(dst, new SimpleEntry<>(dst, cost));
    }

    public Graph findMST() {
        Graph mst = new Graph(graphSize, graphSize-1);
        boolean[] vis = new boolean[graphSize];

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        while (!pq.isEmpty()) {
            SimpleEntry<Integer, Integer> top = pq.poll();

            if (vis[top.getKey()])
                continue;

            addEdge()

            for (SimpleEntry<Integer, Integer> v : g.get(top.getKey())) {
                if (vis[v.getKey()])
                    continue;
                pq.add(v);
            }
        }

        return mst;
    }
}
