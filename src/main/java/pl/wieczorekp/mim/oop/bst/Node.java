package pl.wieczorekp.mim.oop.bst;

public class Node<T extends Comparable<T>> {
    private T value;
    private Node<T> parent;
    private Node<T> left;
    private Node<T> right;

    public Node(T value) {
        this.value = value;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    public T value() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> parent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Node<T> left() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> right() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }


}
