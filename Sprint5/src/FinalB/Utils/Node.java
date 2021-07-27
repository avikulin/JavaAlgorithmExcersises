package FinalB.Utils;

/**
 * Реализация класса узла дерева, описанная в задании.
 */
public class Node {
    private Node left;
    private Node right;
    private int value;

    public int getValue() {
        return value;
    }

    public Node getRight() {
        return right;
    }

    public Node getLeft() {
        return left;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }
}