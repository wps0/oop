package pl.wieczorekp.mim.oop.bst;

public class LinearBinaryTree<T extends Comparable<T>> implements BinaryTree<T> {
    private Node<T> root;

    public LinearBinaryTree() {
        this.root = null;
    }

    @Override
    public Node<T> search(T x) {
        return null;
    }

    @Override
    public void delete(T x) {

    }

    @Override
    public T minimum() {
        return null;
    }

    @Override
    public T maximum() {
        return null;
    }

    @Override
    public Node<T> predecessor(Node<T> node) {
        return null;
    }

    @Override
    public Node<T> successor(Node<T> node) {
        return null;
    }

    private Node<T> findOrGetParent(T x, Node<T> v) {
        if (x.compareTo(v.value()) == 0) {
            return v;
        } else if (x.compareTo(v.value()) < 0) {
            return v.left() == null ? v : findOrGetParent(x, v.left());
        }
        return v.right() == null ? v : findOrGetParent(x, v.right());
    }
}
