package ProblemK;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

class Node {
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

    public Node(Node left, Node right, int value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }
}

public class Solution {
    public static void printRange(Node root, int L, int R, BufferedWriter writer) throws IOException {
        //---базовый случай рекурсии---
        if (root == null) {
            System.out.println("\t-return");
            return;
        }

        System.out.println(
                String.format("%d (left - %d, right - %d)",
                        root.getValue(),
                        root.getLeft() == null ? -1 : root.getLeft().getValue(),
                        root.getRight() == null ? -1 : root.getRight().getValue()));

        //---если min меньше, чем текущий элемент - рекурентно ищем еще меньший (который ближе всего к min)
        if (L <= root.getValue())
            printRange(root.getLeft(), L, R, writer);

        //--- тут возвращаемся из рекурсии и выводим текущий элемент, если он попал в рамки поиска---
        if ((root.getValue() >= L) && (root.getValue() <= R)) {
            System.out.println(String.format("\t -value is in range: %d", root.getValue()));
            writer.write(String.valueOf(root.getValue()));
            writer.newLine();
        }

        //--- если max больше, чем текущий элемент - рекурентно ищем еще больший (который ближе всего к max)
        if (root.getValue() <= R)
            printRange(root.getRight(), L, R, writer);

    }

    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        Node tree = new Node(null, null, 1600);
        tree.setLeft(new Node(null, null, 1400));
        tree.getLeft().setLeft(new Node(null, null, 900));
        tree.getLeft().setRight(new Node(null, null, 1600));
        tree.getLeft().getRight().setLeft(new Node(null, null, 1550));

        tree.setRight(new Node(null, null, 2000));
        tree.getRight().setLeft(new Node(null, null, 1800));
        tree.getRight().setRight(new Node(null, null, 2200));

        tree.getRight().getLeft().setLeft(new Node(null, null, 1600));

        printRange(tree, 1600, 1600, writer);
        writer.flush();
    }
}