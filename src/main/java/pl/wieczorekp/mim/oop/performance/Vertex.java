package pl.wieczorekp.mim.oop.performance;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

public class Vertex {
    @Getter
    private int id;
    @Getter
    private List<Edge> connectedEdges;

    public Vertex(int id) {
        this.id = id;
        this.connectedEdges = new LinkedList<>();
    }

    public Edge addNeighbour(Vertex neighbour, int cost) {
        Edge e = new Edge(this, neighbour, cost);
        connectedEdges.add(e);
        return e;
    }

    public boolean hasNeighbours() {
        return !connectedEdges.isEmpty();
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "id=" + id +
                ", connectedEdges=" + connectedEdges +
                '}';
    }

}
