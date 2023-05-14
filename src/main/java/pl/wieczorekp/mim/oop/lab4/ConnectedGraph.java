package pl.wieczorekp.mim.oop.lab4;

import java.util.PriorityQueue;

public class ConnectedGraph extends Graph {

    public ConnectedGraph findMST() {
        ConnectedGraph mst = new ConnectedGraph();

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        mst.addVertex(0);
        pq.addAll(vertices.get(0).getConnectedEdges());

        while (!pq.isEmpty()) {
            Edge top = pq.poll();
            int uId = top.getSource().getId();
            int vId = top.getDestination().getId();

            if (mst.containsVertex(uId) && mst.containsVertex(vId)
                    && mst.getVertex(uId).hasNeighbours() && mst.getVertex(vId).hasNeighbours())
                continue;

            int notInMst = uId;
            if (mst.containsVertex(notInMst))
                notInMst = vId;
            mst.addVertex(notInMst);
            mst.addEdge(new Edge(mst.getVertex(uId), mst.getVertex(vId), top.getWeight()));

            pq.addAll(this.getVertex(notInMst).getConnectedEdges());
        }

        return mst;
    }
}
