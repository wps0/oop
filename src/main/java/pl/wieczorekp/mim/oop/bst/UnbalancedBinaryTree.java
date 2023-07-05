package pl.wieczorekp.mim.oop.bst;

import java.util.NoSuchElementException;

public class UnbalancedBinaryTree<T extends Comparable<T>> extends BinaryTree<T> {
    public UnbalancedBinaryTree() {
    }

    public UnbalancedBinaryTree(Node<T> root) {
        super(root);
    }

    public void replace(Node<T> inNode, Node<T> node, Node<T> byNode) {
        if (inNode == null)
            root = byNode;
        else if (inNode.left() == node)
            inNode.setLeft(byNode);
        else if (inNode.right() == node)
            inNode.setRight(byNode);

        if (byNode != null)
            byNode.setParent(inNode);
    }

    @Override
    public void insert(T x, boolean requireUnique) {
        if (root == null) {
            this.root = new Node<>(x);
            return;
        }

        Node<T> p = null;
        Node<T> next = root;
        while (next != null) {
            p = next;
            int cmp = next.value().compareTo(x);
            if (cmp < 0) {
                next = p.right();
            } else if (cmp > 0) {
                next = p.left();
            } else {
                if (requireUnique)
                    throw new IllegalArgumentException(x + " already exists in the tree");
                return;
            }
        }

        Node<T> xNode = new Node<>(x);
        xNode.setParent(p);
        if (p.value().compareTo(x) > 0) {
            p.setLeft(xNode);
        } else {
            p.setRight(xNode);
        }
    }

    @Override
    public void delete(T x) {
        Node<T> node = search(x);
        if (node == null) {
            throw new NoSuchElementException();
        }

        if (node.left() == null && node.right() == null) {
            node.detach();
            if (node == root) {
                root = null;
            }
        } else if (node.left() == null) {
            replace(node.parent(), node, node.right());
        } else if (node.right() == null) {
            replace(node.parent(), node, node.left());
        } else {
            BinaryTree<T> rightSubtree = subtree(node.right());
            Node<T> replacement = rightSubtree.search(rightSubtree.minimum());

            if (replacement != node.right()) {
                replace(replacement.parent(), replacement, replacement.right());
                if (replacement.right() != null)
                    replacement.right().setParent(replacement.parent());
            }
            replace(node.parent(), node, replacement);
            replacement.setLeft(node.left());
            node.left().setParent(replacement);
        }
    }

    @Override
    public BinaryTree<T> subtree(Node<T> node) {
        Node<T> newRoot = new Node<>(node.value());
        newRoot.setLeft(node.left());
        newRoot.setRight(node.right());
        return new UnbalancedBinaryTree<>(newRoot);
    }
}
