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

    private void rebalance(AVLNode<T> newNode) {
        AVLNode<T> z = newNode;
        AVLNode<T> x, n;
        for (x = z.parent(); x != null; x = z.parent()) {
            if (z == x.right()) {
                if (x.bf() > 0) {
                    AVLNode<T> g = x.parent();
                    if (z.bf() < 0) {
                        z.rightRotate();
                        x.leftRotate();
                    } else {
                        x.leftRotate();
                    }
                } else {
                    if (x.bf() < 0) {
                        x.setBf(0);
                        break;
                    }
                    x.setBf(1);
                    z = x;
                }
            } else {
                // TODO!
            }
        }
        //            if (newNode.bf() > 0) {
//                newNode.left().rightRotate();
//                newNode.leftRotate();
//            } else {
//                //?
//                newNode.right().leftRotate();
//                newNode.rightRotate();
//            }

    }


}
