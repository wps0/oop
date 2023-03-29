package pl.wieczorekp.mim.oop.lab4;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;

@RequiredArgsConstructor
public class Graph {
    @Getter @NonNull
    protected ArrayList<Vertex> vertices;
    @NonNull
    protected int graphSize;
    @NonNull
    protected int edgeCount;

    public Graph() {
        this.vertices = new ArrayList<>();
        this.graphSize = 0;
        this.edgeCount = 0;
    }

    public void readGraph() {
        Scanner s = new Scanner(System.in);
        int graphSize = s.nextInt();
        int edgeCount = s.nextInt();

        clearGraph();
        initGraph(graphSize, edgeCount);

        // read input
        for (int i = 0; i < edgeCount; i++) {
            int a, b, cost;
            a = s.nextInt()-1;
            b = s.nextInt()-1;
            cost = s.nextInt();

            addEdge(a, b, cost);
        }
    }

    public void clearGraph() {
        this.graphSize = 0;
        this.edgeCount = 0;
        this.vertices.clear();
    }

    public void initGraph(int graphSize, int edgeCount) {
        this.edgeCount = edgeCount;
        for (int i = 0; i < graphSize; i++) {
            this.addVertex();
        }
    }

    public Vertex addVertex() {
        Vertex v = new Vertex(graphSize);
        vertices.add(v);
        graphSize++;
        return v;
    }

    protected Vertex addVertex(int id) {
        vertices.ensureCapacity(id+1);
        graphSize = Integer.max(graphSize, id+1);

        Vertex v = vertices.get(id);
        if (v != null)
            return v;

        vertices.set(id, new Vertex(id));
        return vertices.get(id);
    }

    public boolean containsVertex(int id) {
        if (id >= graphSize)
            return false;
        return vertices.get(id) != null;
    }

    public void addEdge(Edge e) {
        assert e.getSource() != null;
        addEdge(e.getSource().getId(), e.getDestination().getId(), e.getWeight());
    }

    public void addEdge(int a, int b, int cost) {
        Vertex aVertex = vertices.get(a);
        Vertex bVertex = vertices.get(b);
        vertices.get(a).addNeighbour(bVertex, cost);
        vertices.get(b).addNeighbour(aVertex, cost);
        edgeCount++;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "vertices=" + vertices +
                ", graphSize=" + graphSize +
                ", edgeCount=" + edgeCount +
                '}';
    }
}
