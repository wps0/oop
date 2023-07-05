package pl.wieczorekp.mim.oop.bst;

public class AVLNode<T extends Comparable<T>> extends Node<T> {
    // Balance factor
    private int bf;

    public AVLNode(T value) {
        super(value);
        this.bf = 0;
    }

    public AVLNode(AVLNode<T> node) {
        super(node);
        this.bf = node.bf;
    }

    public int bf() {
        return bf;
    }

    public void bfTiltLeft() {
        bf--;
    }
    
    public void bfTiltRight() {
        bf++;
    }
    
    public boolean needsRebalancing() {
        return Math.abs(bf) >= 2;
    }

}
