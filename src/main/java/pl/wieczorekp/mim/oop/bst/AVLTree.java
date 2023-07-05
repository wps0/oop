package pl.wieczorekp.mim.oop.bst;

public class AVLTree<T extends Comparable<T>> extends BinaryTree<T> {
    public AVLTree() {
    }

    public AVLTree(AVLNode<T> root) {
        super(root);
    }

    @Override
    public void insert(T x, boolean ignoreNonUnique) {
    }

    @Override
    public void delete(T x) {

    }

    @Override
    public AVLTree<T> subtree(Node<T> node) {
        return subtree(new AVLNode<>(node));
    }

    public AVLTree<T> subtree(AVLNode<T> node) {
        return new AVLTree<>(node);
    }
}
