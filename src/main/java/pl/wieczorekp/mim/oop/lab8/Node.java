package pl.wieczorekp.mim.oop.lab8;

import java.util.Objects;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
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

    public T getValue() {
        return value;
    }

    public Node<T> getParent() {
        return parent;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    void insertChild(Node<T> child) {
        if (child.compareTo(this) < 0) {
            setLeft(child);
        } else {
            setRight(child);
        }
    }

    void removeChild(Node<T> child) {
        if (this.left.compareTo(child) == 0) {
            this.left = null;
        } else {
            this.right = null;
        }
    }

    boolean containsChild(Node<T> child) {
        return left != null && left.compareTo(child) == 0 || right != null && right.compareTo(child) == 0;
    }

    boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public int compareTo(Node<T> tNode) {
        return this.value.compareTo(tNode.getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(value, node.value);
    }
}
