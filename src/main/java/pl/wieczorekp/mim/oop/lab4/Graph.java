package pl.wieczorekp.mim.oop.lab4;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;

@RequiredArgsConstructor
public class Graph {
    @Getter @NonNull
    protected ArrayList<Vertex> vertices;
    protected int graphSize;
    protected int edgeCount;

    public Graph() {
        this.vertices = new ArrayList<>();
        this.graphSize = 0;
        this.edgeCount = 0;
    }

    public void readGraph(File sourceFile) throws FileNotFoundException {
        assert this.graphSize == 0;

        Scanner s = new Scanner(sourceFile);
        int graphSize = s.nextInt();
        int edgeCount = s.nextInt();

        initGraph(graphSize);

        // read input
        for (int i = 0; i < edgeCount; i++) {
            int a, b, cost;
            a = s.nextInt()-1;
            b = s.nextInt()-1;
            cost = s.nextInt();

            addEdge(a, b, cost);
        }
    }

    public void initGraph(int graphSize) {
        for (int i = 0; i < graphSize; i++) {
            this.addVertex();
        }
    }

    public Vertex addVertex() {
        return addVertex(graphSize);
    }

    protected Vertex addVertex(int id) {
        if (graphSize <= id) {
            for (int i = graphSize; i <= id; i++) {
                vertices.add(new Vertex(i));
            }
            graphSize = id+1;
        }
        return vertices.get(id);
    }

    public Vertex getVertex(int id) {
        assert id < graphSize;
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

    public boolean isConnected() {
        boolean[] vis = new boolean[graphSize];
        return isConnectedDfs(0, vis) == graphSize;
    }

    private int isConnectedDfs(int v, boolean[] vis) {
        vis[v] = true;
        Vertex cur = vertices.get(v);

        int visCnt = 1;
        for (Edge e : cur.getConnectedEdges()) {
            Vertex neigh = e.getNeighbourOf(cur);

            if (vis[neigh.getId()])
                continue;
            visCnt += isConnectedDfs(neigh.getId(), vis);
        }

        return visCnt;
    }

    public int sumEdgeWeights() {
        int sum = 0;

        for (Vertex v : vertices) {
            for (Edge e : v.getConnectedEdges()) {
                Vertex u = e.getNeighbourOf(v);
                if (u.getId() <= v.getId())
                    sum += e.getWeight();
            }
        }

        return sum;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Graph (|V|: " + graphSize + ", |E|: " + edgeCount + ")\n");
        for (Vertex v : vertices) {
            stringBuilder.append(" ");
            stringBuilder.append(v);
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}
