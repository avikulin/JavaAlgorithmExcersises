package ProblemD;

import java.util.LinkedList;
import java.util.Queue;

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

    private static boolean checkNodesTopology(Node one, Node two) {
        boolean leftIsEqual = ((one.left != null) && (two.left != null)) || ((one.left == null) && (two.left == null));
        boolean rightIsEqual = ((one.right != null) && (two.right != null)) || ((one.right == null) && (two.right == null));
        boolean valueIsEqual = one.value == two.value;

        return valueIsEqual && leftIsEqual && rightIsEqual;
    }

    private static void manageQueue(Queue<Node> queue, Node node) {
        if (node.left != null)
            queue.add(node.left);

        if (node.right != null)
            queue.add(node.right);
    }

    public static boolean treeSolution(Node head1, Node head2) {
        Queue<Node> queue1 = new LinkedList<>();
        Queue<Node> queue2 = new LinkedList<>();

        queue1.add(head1);
        queue2.add(head2);

        while (true) {
            if (queue1.isEmpty() && queue2.isEmpty()) return true; // оба дерева содержат равное кол-во узлов.
            if (queue1.isEmpty() && (!queue2.isEmpty())) return false; // второе дерево больше первого
            if ((!queue1.isEmpty()) && queue2.isEmpty()) return false; // первое дерево больше второго

            Node currentNode1 = queue1.poll();
            Node currentNode2 = queue2.poll();

            if (!checkNodesTopology(currentNode1, currentNode2)) return false;

            manageQueue(queue1, currentNode1);
            manageQueue(queue2, currentNode2);
        }

    }

    public static void main(String[] args) {
        Node tree1 = new Node(1);
        tree1.left = new Node(2);
        tree1.left.left = new Node(4);

        Node tree2 = new Node(1);
        tree2.left = new Node(2);
        tree2.left.left = new Node(4);

        System.out.println(treeSolution(tree1, tree2));
    }
}




