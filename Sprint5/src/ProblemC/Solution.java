package ProblemC;

import java.util.LinkedList;
import java.util.Queue;
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

    private static boolean checkAnagram(Queue<Node> queue) {
        Stack<Node> checkList = new Stack<>();
        int numberInLine = 0;
        int firstHalfBound = queue.size() / 2;
        for (Node node : queue) {
            if (numberInLine < firstHalfBound) // предполагаем, что дерево - бинарное
                checkList.push(node);
            else if (node.value != checkList.pop().value) return false;

            numberInLine++;
        }
        return true;
    }

    public static boolean treeSolution(Node head) {
        Queue<Node> queue = new LinkedList<>();

        queue.add(head);
        int depth = 1;
        int emptyNodeCount = 0;

        while (true) {
            Node currentNode = queue.poll();
            if (currentNode.value == -1)
                emptyNodeCount--;

            if (currentNode.left != null)
                queue.add(currentNode.left);
            else {
                queue.add(new Node(-1));
                emptyNodeCount++;
            }

            if (currentNode.right != null)
                queue.add(currentNode.right);
            else {
                queue.add(new Node(-1));
                emptyNodeCount++;
            }

            if (emptyNodeCount == queue.size()) return true; // последний уровень дерева пройден.

            if (queue.size() == (int) Math.pow(2, depth)) {
                if (!checkAnagram(queue)) return false;
                depth++;
            }

        }

    }

    public static void main(String[] args) {
        Node tree = new Node(1);
        tree.left = new Node(2);
        tree.left.left = new Node(4);
        tree.left.left.left = new Node(5);
        tree.left.left.right = new Node(6);

        tree.left.right = new Node(3);
        tree.left.right.left = new Node(7);
        tree.left.right.right = new Node(8);

        tree.right = new Node(2);

        tree.right.left = new Node(3);
        tree.right.left.left = new Node(8);
        tree.right.left.right = new Node(7);

        tree.right.right = new Node(4);
        tree.right.right.left = new Node(6);
        tree.right.right.right = new Node(5);


        System.out.println(treeSolution(tree));
    }
}




