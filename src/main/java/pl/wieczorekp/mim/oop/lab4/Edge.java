package pl.wieczorekp.mim.oop.lab4;

public class Edge implements Comparable<Edge> {
    private int src;
    private int dst;
    private int cost;

    @Override
    public int compareTo(Edge edge) {
        if (v1.getKey() == v2.getKey())
            return 0;
        return v1.getKey() < v2.getKey() ? -1 : 1;
    }
}
