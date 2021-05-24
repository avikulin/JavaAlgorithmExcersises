package ProblemF;

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
    public static int treeSolution(Node head) {
        Stack<Node> stackNodes = new Stack<>();
        Stack<Integer>  stackHeights = new Stack<>();

        stackNodes.push(head);
        stackHeights.push(1);

        int maxHeight = -1;
        while (!stackNodes.empty()) {
            Node currentNode = stackNodes.pop();
            int currentHeight = stackHeights.pop();

            if (currentHeight > maxHeight) maxHeight = currentHeight;

            if (currentNode.right != null) {
                stackNodes.push(currentNode.right);
                stackHeights.push(currentHeight + 1);
            }

            if (currentNode.left != null) {
                stackNodes.push(currentNode.left);
                stackHeights.push(currentHeight + 1);
            }
        }

        return maxHeight;
    }

    public static void main(String[] args) {
        Node tree = new Node(0);

        tree.left = new Node(1);
        tree.left.left = new Node(11);
        tree.left.right = new Node(12);
        tree.left.right.right = new Node(121);

        tree.right = new Node(2);
        tree.right.right = new Node(21);
        tree.right.right.left = new Node(211);
        tree.right.right.right = new Node(212);

        System.out.println(treeSolution(tree));
    }

}
