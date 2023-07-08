package pl.wieczorekp.mim.oop.bst;

public class AVLNode<T extends Comparable<T>> extends Node<T> {
    private int height;

    public AVLNode(T value) {
        super(value);
        this.height = 1;
    }

    public int bf() {
        return height(left()) - height(right());
    }

    public boolean needsRebalancing() {
        return Math.abs(bf()) >= 2;
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

    public void detach() {
        if (parent() != null) {
            if (parent().left() == this)
                parent().setLeft(null);
            else
                parent().setRight(null);
            setParent(null);
        }
    }

    public int height() {
        return this.height;
    }

    public void updateHeight() {
        this.height = 1 + Integer.max(height(left()), height(right()));
    }

    private int height(AVLNode<T> node) {
        return node == null ? 0 : node.height();
    }

    private void checkNodeType(Node<T> node) {
        if (node != null && !(node instanceof AVLNode<T>))
            throw new IllegalArgumentException("The node must be an instance of UnbalancedNode<T>");
    }
}
