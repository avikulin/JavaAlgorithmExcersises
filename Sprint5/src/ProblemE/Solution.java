package ProblemE;

import java.util.Stack;

class Node {
    int value;
    Node left;
    Node right;

    Node(int value) {
        this.value = value;
        right = null;
        left = null;
    }
}

public class Solution {
    private static boolean checkNodeForBST(Node node) {
        boolean leftSide = node.left == null || (node.left.value < node.value);
        boolean rightSide = node.right == null || (node.right.value > node.value);
        return leftSide && rightSide;
    }

    public static boolean treeSolution(Node head) {
        Stack<Node> stack = new Stack<>();
        Node currentNode = head;
        stack.push(currentNode);
        while (!stack.empty()) {
            if (checkNodeForBST(currentNode)) {
                if (currentNode.right != null) stack.push(currentNode.right);
                if (currentNode.left != null) stack.push(currentNode.left);

                currentNode = stack.pop();
            } else {
                return false;
            }
        }
        return true;
    }

    public static Node initTree() {
        Node head = new Node(100);

        head.left = new Node(30);

        head.left.left = new Node(15);
        head.left.left.left = new Node(14);
        head.left.left.right = new Node(16);

        head.left.right = new Node(40);
        head.left.right.right = new Node(43);

        head.right = new Node(150);

        head.right.left = new Node(120);

        head.right.right = new Node(160);
        head.right.right.left = new Node(159);
        head.right.right.right = new Node(161);

        return head;
    }

    public static void main(String[] args) {
        Node head = initTree();
        System.out.println(treeSolution(head));
    }
}

