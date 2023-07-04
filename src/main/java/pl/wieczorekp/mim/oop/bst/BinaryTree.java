package pl.wieczorekp.mim.oop.bst;

public interface BinaryTree<T extends Comparable<T>> {
    Node<T> search(T x);
    void delete(T x);
    T minimum();
    T maximum();

    Node<T> predecessor(Node<T> node);
    Node<T> successor(Node<T> node);
}
