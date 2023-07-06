package pl.wieczorekp.mim.oop.bst;

public class AVLNode<T extends Comparable<T>> extends Node<T> {
    // Balance factor
    private int bf;

    public AVLNode(T value) {
        super(value);
    }

    public int bf() {
        return bf;
    }

    public void setBf(int bf) {
        this.bf =  bf;
    }

    public void bfTiltLeft() {
        bf++;
    }
    
    public void bfTiltRight() {
        bf--;
    }
    
    public boolean needsRebalancing() {
        return Math.abs(bf) >= 2;
    }

    @Override
    public AVLNode<T> parent() {
        return (AVLNode<T>) parent;
    }

    @Override
    public void setParent(Node<T> parent) {
        checkNodeType(parent);
        this.parent = parent;
    }

    @Override
    public AVLNode<T> left() {
        return (AVLNode<T>) left;
    }

    @Override
    public void setLeft(Node<T> left) {
        checkNodeType(left);
        this.left = left;
    }

    @Override
    public AVLNode<T> right() {
        return (AVLNode<T>) right;
    }

    public void setRight(Node<T> right) {
        checkNodeType(right);
        this.right = right;
    }

    private void checkNodeType(Node<T> node) {
        if (node != null && !(node instanceof AVLNode<T>))
            throw new IllegalArgumentException("The node must be an instance of UnbalancedNode<T>");
    }
}
