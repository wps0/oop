package pl.wieczorekp.mim.oop.bst;

public class AVLNode<T extends Comparable<T>> extends Node<T> {
    // Balance factor
    private int bf;

    public AVLNode(T value) {
        super(value);
    }

    public int bf() {
        return bf;
    }

    public void setBf(int bf) {
        this.bf =  bf;
    }

    public void bfTiltLeft() {
        bf--;
    }
    
    public void bfTiltRight() {
        bf++;
    }
    
    public boolean needsRebalancing() {
        return Math.abs(bf) >= 2;
    }

    @Override
    public AVLNode<T> parent() {
        return (AVLNode<T>) parent;
    }

    @Override
    public void setParent(Node<T> parent) {
        if (!(parent instanceof AVLNode<T>))
            throw new IllegalArgumentException("The node must be an instance of AVLNode<T>");
        this.parent = parent;
    }

    @Override
    public AVLNode<T> left() {
        return (AVLNode<T>) left;
    }

    @Override
    public void setLeft(Node<T> left) {
        if (!(left instanceof AVLNode<T>))
            throw new IllegalArgumentException("The node must be an instance of AVLNode<T>");
        this.left = left;
    }

    @Override
    public AVLNode<T> right() {
        return (AVLNode<T>) right;
    }

    // This node is the upper node
//    protected void leftRotate() {
//        this.left().setParent(this.parent());
//        this.parent().replaceAppropriate(this, this.left());
//        this.setParent(left());
//
//        AVLNode<T> leftRightNode = this.left().right();
//        this.setRight(leftRightNode);
//        leftRightNode.setParent(this);
//    }

    protected AVLNode<T> leftRotate() {
        this.parent().replaceAppropriate(this, this.right());
        this.right().setParent(this.parent());
        this.setParent(right());

        AVLNode<T> rightLeftNode = this.right().left();
        this.right().setLeft(this);
        this.setRight(rightLeftNode);
        rightLeftNode.setParent(this);

        return this.parent();
    }

    protected AVLNode<T> rightRotate() {
        this.parent().replaceAppropriate(this, left());
        this.left().setParent(this.parent());
        this.setParent(this.left());

        AVLNode<T> leftRightNode = this.left().right();
        this.left().setRight(this);
        this.setLeft(leftRightNode);
        leftRightNode.setParent(this);

        return this.parent();
    }


    public void setRight(Node<T> right) {
        if (!(right instanceof AVLNode<T>))
            throw new IllegalArgumentException("The node must be an instance of AVLNode<T>");
        this.right = right;
    }
}
