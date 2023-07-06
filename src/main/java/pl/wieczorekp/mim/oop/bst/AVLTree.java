package pl.wieczorekp.mim.oop.bst;

public class AVLTree<T extends Comparable<T>> extends BinaryTree<T> {
    public AVLTree() {
        super();
    }

    public AVLTree(AVLNode<T> root) {
        super(root);
    }

    @Override
    public void insert(T x, boolean ignoreNonUnique) {
        if (root() == null) {
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
                p.bfTiltRight();
            } else if (cmp > 0) {
                next = p.left();
                p.bfTiltLeft();
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
        return;
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

        return y;
    }

    private void rebalance(AVLNode<T> node) {
        AVLNode<T> prv = node;
        node = node.parent();
        while (node != null) {
            if (node.needsRebalancing()) {
                assert prv.bf() == 1 || prv.bf() == -1;

                if (Integer.signum(node.bf()) == Integer.signum(prv.bf())) {
                    if (prv == node.left())
                        rotateRight(node);
                    else
                        rotateLeft(node);
                    prv.setBf(0);
                    node.setBf(0);
                } else {
                    if (node.bf() == 2) {
                        assert prv.bf() == -1;
                        rotateLeft(prv);
                        rotateRight(prv.parent());

                        prv.setBf(node.parent().bf() == -1 ? 1 : 0);
                        node.parent().setBf(node.parent().bf() == 1 ? 1 : 0);

                    } else if (node.bf() == -2){
                        assert prv.bf() == 1;
                        rotateRight(prv);
                        rotateLeft(prv.parent());

                        prv.setBf(node.parent().bf() == 1 ? -1 : 0);
                        node.parent().setBf(node.parent().bf() == -1 ? -1 : 0);
                    }

                    node.setBf(0);
                }
            }
            prv = node;
            node = prv.parent();
        }
    }

}
