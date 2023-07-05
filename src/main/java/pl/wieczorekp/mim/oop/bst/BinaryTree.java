package pl.wieczorekp.mim.oop.bst;

public abstract class BinaryTree<T extends Comparable<T>> {
    protected Node<T> root;

    protected BinaryTree() {
        this.root = null;
    }

    protected BinaryTree(Node<T> root) {
        this.root = root;
    }

    public Node<T> search(T x) {
        if (root == null) {
            return null;
        }

        Node<T> container = root;
        while (container != null && container.value().compareTo(x) != 0) {
            if (container.value().compareTo(x) < 0) {
                container = container.right();
            } else {
                container = container.left();
            }
        }

        return container;
    }

    public void insert(T x) {
        insert(x, true);
    }

    public abstract void insert(T x, boolean ignoreNonUnique);

    public abstract void delete(T x);

    public T minimum() {
        if (root == null) {
            return null;
        }

        Node<T> min = root;
        while (min.left() != null)
            min = min.left();
        return min.value();
    }

    public T maximum() {
        if (root == null) {
            return null;
        }

        Node<T> min = root;
        while (min.right() != null)
            min = min.right();
        return min.value();

    }

    public T predecessor(Node<T> node) {
        if (node.left() != null) {
            return subtree(node.left()).maximum();
        }

        T val = node.value();
        while (node != null && val.compareTo(node.value()) <= 0) {
            node = node.parent();
        }

        return node != null ? node.value() : null;
    }

    public T successor(Node<T> node) {
        if (node.right() != null) {
            return subtree(node.right()).minimum();
        }

        T val = node.value();
        while (node != null && val.compareTo(node.value()) >= 0) {
            node = node.parent();
        }

        return node != null ? node.value() : null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public abstract BinaryTree<T> subtree(Node<T> node);
}
