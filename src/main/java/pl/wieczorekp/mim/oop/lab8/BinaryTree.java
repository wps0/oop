package pl.wieczorekp.mim.oop.lab8;

import java.util.NoSuchElementException;

// Testy: samochod po maksymalnej predkosci
// AVL

public class BinaryTree<T extends Comparable<T>> {
    private Node<T> root;

    public BinaryTree(Node<T> root) {
        this.root = root;
    }

    public Node<T> add(T value) {
        Node<T> newNode = new Node<>(value);
        add(newNode);
        return newNode;
    }

    public void remove(T value) {
        Node<T> targetNode = find(value);
        if (targetNode.isLeaf()) {
            targetNode.getParent().removeChild(targetNode);
        }

        BinaryTree<T> subtree = new BinaryTree<>(targetNode.getRight());
        Node<T> gapFiller = find(subtree.min());
        // 1. zamien target node gap fillerem
        // 2. zapisz node z prawego gap fillera
        // 3. podlacz gap fillera
        // 4. wstaw prawy do drzewa
    }

    public T min() {
        Node<T> curNode = root;
        while (curNode.getLeft() != null) {
            curNode = curNode.getLeft();
        }

        return curNode.getValue();
    }

    private void add(Node<T> newNode) {
        if (root == null) {
            this.root = newNode;
        }

        Node<T> parent = getPotentialParent(newNode);
        newNode.setParent(parent);
        parent.insertChild(newNode);
    }

    private Node<T> find(T value) {
        Node<T> valueNode = new Node<>(value);
        Node<T> parent = getPotentialParent(valueNode);
        if (parent == null || !parent.containsChild(valueNode)) {
            throw new NoSuchElementException("value not found");
        }

        return valueNode.compareTo(parent) < 0 ? parent.getLeft() : parent.getRight();
    }

    private Node<T> getPotentialParent(Node<T> node) {
        Node<T> curNode = root;

        while (curNode != null && !curNode.isLeaf() && !curNode.containsChild(node)) {
            int cmp = node.compareTo(curNode);
            if (cmp < 0) {
                curNode = curNode.getLeft();
            } else if (cmp > 0) {
                curNode = curNode.getRight();
            } else {
                throw new RuntimeException("????");
            }
        }

        return curNode;
    }
}
