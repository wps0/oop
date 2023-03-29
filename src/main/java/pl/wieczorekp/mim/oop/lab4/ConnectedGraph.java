package pl.wieczorekp.mim.oop.lab4;

import java.util.PriorityQueue;

public class ConnectedGraph extends Graph {

    public ConnectedGraph findMST() {
        ConnectedGraph mst = new ConnectedGraph();

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        mst.addVertex(0);
        pq.addAll(this.vertices.get(0).getConnectedEdges());

        while (!pq.isEmpty()) {
            Edge top = pq.poll();

            if (mst.containsVertex(top.getSource().getId()) && mst.containsVertex(top.getDestination().getId()))
                continue;

            Vertex notInMst = top.getSource();
            if (mst.containsVertex(notInMst.getId()))
                notInMst = top.getDestination();
            mst.addVertex(notInMst.getId());
            mst.addEdge(top);

            pq.addAll(notInMst.getConnectedEdges());
        }

        return mst;
    }
}
