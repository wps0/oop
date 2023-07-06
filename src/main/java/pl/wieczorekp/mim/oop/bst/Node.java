package pl.wieczorekp.mim.oop.bst;

public abstract class Node<T extends Comparable<T>> {
    private T value;
    protected Node<T> parent;
    protected Node<T> left;
    protected Node<T> right;


    protected Node(T value) {
        this.value = value;
    }

    public T value() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public abstract Node<T> parent();

    public abstract void setParent(Node<T> parent);

    public abstract Node<T> left();

    public abstract void setLeft(Node<T> node);

    public abstract Node<T> right();

    public abstract void setRight(Node<T> node);
    
    protected void replaceAppropriate(Node<T> node, Node<T> byNode) {
        if (left() == node)
            setLeft(byNode);
        else
            setRight(byNode);
    }
}
