package ProblemJ;

import java.util.Stack;

//// do not declare Node in your submit-file
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
    public static Node insert(Node root, int key) {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            Node currentNode = stack.pop();

            //---DEBUG LOGGING---
            System.out.println(String.format("\t-%d", currentNode.getValue()));

            if ((key < currentNode.getValue())) {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(new Node(null, null, key));
                    //---DEBUG LOGGING---
                    System.out.println(String.format("\t-%d (left)", currentNode.getLeft().getValue()));
                    return root;
                } else
                    stack.push(currentNode.getLeft());
            } else {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(new Node(null, null, key));
                    //---DEBUG LOGGING---
                    System.out.println(String.format("\t-%d (right)", currentNode.getRight().getValue()));
                    return root;
                } else
                    stack.push(currentNode.getRight());
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Node tree = new Node(null, null, 7);
        tree.setRight(new Node(null, null, 8));
        tree.getRight().setLeft(new Node(null, null, 7));
        insert(tree, 6);
    }
}

