package ProblemG;

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
    static int maxGlobalPath = -1;

    public static int treeSolution(Node head) {
        // базовый случай рекурсии - элемент является листом
        if ((head.left == null) && (head.right == null))
            return head.value;
        else {

            int leftSubTreePath = head.left == null ? head.value : Math.max(head.value + head.left.value,
                                                                            head.value + treeSolution(head.left));

            int rightSubTreePath = head.right == null ? head.value : Math.max(head.value + head.right.value,
                                                                              head.value + treeSolution(head.right));

            int maxLocalPath = leftSubTreePath + rightSubTreePath - head.value;

            if (maxLocalPath > maxGlobalPath) maxGlobalPath = maxLocalPath;

            System.out.println(
                    String.format("node = %d (left path = %d, right path = %d, local max = %d, global max = %d)",
                            head.value, leftSubTreePath, rightSubTreePath, maxLocalPath, maxGlobalPath));


            return maxLocalPath;
        }
    }

    public static void main(String[] args) {
        Node tree = new Node(-1);
        tree.left = new Node(2);
        tree.left.left = new Node(7);
        tree.left.left.left = new Node(-1);

        tree.left.right = new Node(3);
        tree.left.right.left = new Node(9);
        tree.left.right.right = new Node(-6);

        tree.right = new Node(3);
        tree.right.left = new Node(4);
        tree.right.right = new Node(0);

        System.out.println(treeSolution(tree));
    }
}
