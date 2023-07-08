package pl.wieczorekp.mim.oop.bst;

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

//        AVLNode<T> parent = node.parent();
        if (node.left() == null && node.right() == null) {
            node.detach();
            if (node == root) {
                root = null;
            }
        } else if (node.left() == null) {
            replace(node.parent(), node, node.right());
//            rebalance(node.parent());
        } else if (node.right() == null) {
            replace(node.parent(), node, node.left());
//            rebalance(node.parent());
        } else {
            BinaryTree<T> rightSubtree = subtree(node.right());
            AVLNode<T> replacement = (AVLNode<T>) rightSubtree.search(rightSubtree.minimum());
//            AVLNode<T> replParent = replacement.parent();

            if (replacement != node.right()) {
                replace(replacement.parent(), replacement, replacement.right());
                if (replacement.right() != null)
                    replacement.right().setParent(replacement.parent());
            }
            replace(node.parent(), node, replacement);
            replacement.setLeft(node.left());
            node.left().setParent(replacement);

//            rebalance(replParent);
        }

//        if (parent != null) {
//            rebalance(parent);
//        }
    }

    @Override
    public AVLTree<T> subtree(Node<T> node) {
        return null;
    }

    public AVLTree<T> subtree(AVLNode<T> node) {
        return new AVLTree<>(node);
    }

    public AVLNode<T> root() {
        return (AVLNode<T>) this.root;
    }

    public void setRoot(Node<T> node) {
        if (!(node instanceof AVLNode<T>))
            throw new IllegalArgumentException("The node must be an instance of AVLNode<T>");
        this.root = node;
    }

    protected AVLNode<T> rotateLeft(AVLNode<T> node) {
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
        return y;
    }

    protected AVLNode<T> rotateRight(AVLNode<T> node) {
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
        return y;
    }

    private void rebalance(AVLNode<T> node) {
        AVLNode<T> prv = node;
        node = node.parent();
        while (node != null) {
            node.updateHeight();
            int bf = node.bf();
            if (node.needsRebalancing()) {
                if (Integer.signum(node.bf()) == Integer.signum(prv.bf())) {
                    if (bf == 2)
                        rotateRight(node);
                    else if (bf == -2)
                        rotateLeft(node);
                    else
                        assert false;
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
                }
            }
            node.updateHeight();
            prv = node;
            node = prv.parent();
        }
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
}
