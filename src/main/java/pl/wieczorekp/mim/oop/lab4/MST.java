package pl.wieczorekp.mim.oop.lab4;

public class MST {

    public static void main(String[] args) {
        ConnectedGraph g = new ConnectedGraph();
        g.readGraph();
        System.out.println(g);

        ConnectedGraph mst = g.findMST();
        System.out.println(mst);
    }
}
