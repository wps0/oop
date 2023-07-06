package pl.wieczorekp.mim.oop.bst;

public class UnbalancedNode<T extends Comparable<T>> extends Node<T> {
    public UnbalancedNode(T value) {
        super(value);
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    @Override
    public UnbalancedNode<T> parent() {
        return (UnbalancedNode<T>) parent;
    }

    @Override
    public void setParent(Node<T> node) {
        checkNodeType(node);
        this.parent = node;
    }

    @Override
    public UnbalancedNode<T> left() {
        return (UnbalancedNode<T>) left;
    }

    @Override
    public void setLeft(Node<T> node) {
        checkNodeType(node);
        this.left = node;
    }

    @Override
    public UnbalancedNode<T> right() {
        return (UnbalancedNode<T>) right;
    }

    @Override
    public void setRight(Node<T> node) {
        checkNodeType(node);
        this.right = node;
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

    private void checkNodeType(Node<T> node) {
        if (node != null && !(node instanceof UnbalancedNode<T>))
            throw new IllegalArgumentException("The node must be an instance of UnbalancedNode<T>");
    }
}
