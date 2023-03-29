package pl.wieczorekp.mim.oop.lab4;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Edge implements Comparable<Edge> {
    @Getter
    private Vertex source;
    @NonNull @Getter
    private Vertex destination;
    @NonNull @Getter
    private int weight;

    public Edge(Vertex source, @NonNull Vertex destination, @NonNull int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex getNeighbourOf(Vertex v) {
        return source == v ? destination : source;
    }

    // compare by edge weight
    @Override
    public int compareTo(Edge edge) {
        if (weight < edge.getWeight())
            return -1;
        if (weight == edge.getWeight())
            return 0;
        return 1;
    }

    @Override
    public String toString() {
        return "Edge{" + source.getId() + " ~> " + destination.getId() +
                ", weight=" + weight +
                '}';
    }
}
