package pl.wieczorekp.mim.oop.lab4;

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

    public List<Vertex> getNeighbours() {
        return connectedEdges.stream()
                .map(Edge::getDestination)
                .toList();
    }

    public Edge addNeighbour(Vertex neighbour, int cost) {
        Edge e = new Edge(this, neighbour, cost);
        connectedEdges.add(e);
        return e;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "id=" + id +
                ", connectedEdges=" + connectedEdges +
                '}';
    }

}
