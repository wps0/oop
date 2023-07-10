package pl.wieczorekp.mim.oop.bst;

import lombok.SneakyThrows;

import javax.naming.OperationNotSupportedException;
import java.util.NoSuchElementException;

public class AVLTree<T extends Comparable<T>> extends BinaryTree<T> {
    public AVLTree() {
        super();
    }

    public AVLTree(AVLNode<T> root) {
        super(root);
    }

    @Override
    public void insert(T x, boolean ignoreNonUnique) {
        if (root == null) {
            setRoot(new AVLNode<>(x));
            return;
        }

        AVLNode<T> p = null;
        AVLNode<T> next = root();
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

        AVLNode<T> xNode = new AVLNode<>(x);
        xNode.setParent(p);
        if (p.value().compareTo(x) > 0) {
            p.setLeft(xNode);
        } else {
            p.setRight(xNode);
        }

        rebalance(xNode);
    }

    @Override
    public void delete(T x) {
        AVLNode<T> node = (AVLNode<T>) search(x);
        if (node == null) {
            throw new NoSuchElementException();
        }

        if (node.left() == null && node.right() == null) {
            AVLNode<T> nodeParent = node.parent();
            node.detach();
            if (node == root()) {
                root = null;
            }
            rebalance(nodeParent);
        } else if (node.left() == null) {
            replace(node.parent(), node, node.right());
            rebalance(node.right());
        } else if (node.right() == null) {
            replace(node.parent(), node, node.left());
            rebalance(node.left());
        } else {
            // This case will be entered at most once per delete method call, so the rebalancing will be performed
            //  by delete(replacement.value()) below.
            BinaryTree<T> rightSubtree = subtree(node.right());
            Node<T> replacement = rightSubtree.search(rightSubtree.minimum());
            delete(replacement.value());

            node.setValue(replacement.value());
        }
    }

    @Override
    public AVLTree<T> subtree(Node<T> node) {
        if (node instanceof AVLNode<T> uNode) {
            AVLTree<T> roTree = new AVLTree<>() {
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
    public AVLNode<T> root() {
        return (AVLNode<T>) this.root;
    }

    @Override
    public void setRoot(Node<T> node) {
        if (!(node instanceof AVLNode<T>))
            throw new IllegalArgumentException("The node must be an instance of AVLNode<T>");
        this.root = node;
    }

    protected void rotateLeft(AVLNode<T> node) {
        AVLNode<T> y = node.right();
        AVLNode<T> beta = node.right().left();

        node.setRight(beta);
        if (beta != null)
            beta.setParent(node);

        y.setParent(node.parent());
        if (node.parent() == null)
            this.setRoot(y);
        else
            node.parent().replaceAppropriate(node, y);

        y.setLeft(node);
        node.setParent(y);

        node.updateHeight();
        y.updateHeight();
    }

    protected void rotateRight(AVLNode<T> node) {
        AVLNode<T> y = node.left();
        AVLNode<T> beta = node.left().right();

        node.setLeft(beta);
        if (beta != null)
            beta.setParent(node);

        y.setParent(node.parent());
        if (node.parent() == null)
            this.setRoot(y);
        else
            node.parent().replaceAppropriate(node, y);

        y.setRight(node);
        node.setParent(y);

        node.updateHeight();
        y.updateHeight();
    }

    private void rebalance(AVLNode<T> node) {
        AVLNode<T> prv = null;
        while (node != null) {
            node.updateHeight();
            int bf = node.bf();
            if (node.needsRebalancing()) {
                if (prv == null || Integer.signum(prv.bf()) == 0 || Integer.signum(node.bf()) == Integer.signum(prv.bf())) {
                    if (bf == 2)
                        rotateRight(node);
                    else if (bf == -2)
                        rotateLeft(node);
                    else
                        assert false;

                    node.updateHeight();
                    node.parent().updateHeight();
                } else {
                    if (bf == 2) {
                        rotateLeft(prv);
                        rotateRight(node);
                    } else if (bf == -2){
                        rotateRight(prv);
                        rotateLeft(node);
                    } else {
                        System.out.println(bf);
                        assert false;
                    }

                    node.parent().left().updateHeight();
                    node.parent().right().updateHeight();
                    node.parent().updateHeight();
                }
            }
            prv = node;
            node = prv.parent();
        }
    }
}
