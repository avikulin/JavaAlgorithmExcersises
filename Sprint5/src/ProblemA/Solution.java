package ProblemA;

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
        // базовый случай - вершина является листом.
        if ((head.left == null) && (head.right == null))
            return head.value;
        // рекуррентный случай - запуск в рекурсию левой и правой веток
        int maxValueLeft = head.left != null ? treeSolution(head.left) : head.value;
        int maxValueRight = head.right != null ? treeSolution(head.right) : head.value;

        // реуррентный случай - объединение результатов.
        return Math.max(maxValueLeft, maxValueRight);
    }

    public static Node initTree() {
        Node head = new Node(1);

        head.left = new Node(3);
        head.left.left = new Node(8);
        head.left.left.left = new Node(14);
        head.left.left.right = new Node(15);

        head.left.right = new Node(10);
        head.left.right.right = new Node(3);

        head.right = new Node(5);

        head.right.left = new Node(2);

        head.right.right = new Node(6);
        head.right.right.left = new Node(0);
        head.right.right.right = new Node(1);

        return head;
    }

    public static void main(String[] args) {
        Node head = initTree();
        System.out.println(treeSolution(head));
    }
}
