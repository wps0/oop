package pl.wieczorekp.mim.oop.lab4;

import java.io.File;
import java.io.FileNotFoundException;

public class MST {

    public static void main(String[] args) throws FileNotFoundException {
        ConnectedGraph g = new ConnectedGraph();
        g.readGraph(new File(args[0]));
//        System.out.println(g);
//        System.out.println(g.isConnected());

        ConnectedGraph mst = g.findMST();
//        System.out.println(mst.isConnected());
//        System.out.println(mst);
        System.out.println(mst.sumEdgeWeights());
    }
}
