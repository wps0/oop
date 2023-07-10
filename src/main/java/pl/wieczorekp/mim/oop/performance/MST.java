package pl.wieczorekp.mim.oop.performance;

import java.io.File;
import java.io.FileNotFoundException;

public class MST {

    public static void main(String[] args) throws FileNotFoundException {
        ConnectedGraph g = new ConnectedGraph();
        g.readGraph(new File(args[0]));

        ConnectedGraph mst = g.findMST();
        System.out.println(mst.sumEdgeWeights());
    }
}
