package pl.wieczorekp.mim.oop.bst;

import lombok.SneakyThrows;

import javax.naming.OperationNotSupportedException;
import java.util.NoSuchElementException;

public class UnbalancedBinaryTree<T extends Comparable<T>> extends BinaryTree<T> {
    public UnbalancedBinaryTree() {
    }

    public UnbalancedBinaryTree(UnbalancedNode<T> root) {
        super(root);
    }

    @Override
    public void insert(T x, boolean ignoreNonUnique) {
        if (root == null) {
            this.root = new UnbalancedNode<>(x);
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
                if (ignoreNonUnique)
                    return;
                throw new IllegalArgumentException(x + " already exists in the tree");
            }
        }

        UnbalancedNode<T> xNode = new UnbalancedNode<>(x);
        xNode.setParent(p);
        if (p.value().compareTo(x) > 0) {
            p.setLeft(xNode);
        } else {
            p.setRight(xNode);
        }
    }

    @Override
    public void delete(T x) {
        UnbalancedNode<T> node = (UnbalancedNode<T>) search(x);
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
            UnbalancedNode<T> replacement = (UnbalancedNode<T>) rightSubtree.search(rightSubtree.minimum());

            if (replacement.value() != node.right().value()) {
                replace(replacement.parent(), replacement, replacement.right());
                replacement.setRight(node.right());
                node.right().setParent(replacement);
            }
            replace(node.parent(), node, replacement);
            replacement.setLeft(node.left());
            node.left().setParent(replacement);
        }
    }

    @Override
    public UnbalancedBinaryTree<T> subtree(Node<T> node) {
        if (node instanceof UnbalancedNode<T> uNode) {
            UnbalancedBinaryTree<T> roTree = new UnbalancedBinaryTree<>() {
                @SneakyThrows
                @Override
                public void insert(T x, boolean ignoreNonUnique) {
                    throw new OperationNotSupportedException("The tree is read-only");
                }

                @SneakyThrows
                @Override
                public void delete(T x) {
                    throw new OperationNotSupportedException("The tree is read-only");
                }
            };
            roTree.setRoot(uNode);

            return roTree;
        } else {
            throw new IllegalArgumentException("The only possible node type is UnbalancedNode<T>");
        }
    }

    @Override
    public void setRoot(Node<T> node) {
        if (!(node instanceof UnbalancedNode<T>))
            throw new IllegalArgumentException("The node must be an instance of AVLNode<T>");
        this.root = node;
    }
}
