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
    static int maxGlobalPath = 0;

    public static int traverseTreePath(Node head){
        // базовый случай рекурсии - элемент является листом
        if ((head.left == null) && (head.right == null)) {
            if (head.value > maxGlobalPath) maxGlobalPath = head.value;
            return head.value;
        } else {
            int leftSidePath = head.left == null ? head.value : Math.max(head.value,// + head.left.value,
                                                                         head.value + traverseTreePath(head.left));

            int rightSidePath = head.right == null ? head.value : Math.max(head.value,// + head.right.value,
                                                                           head.value + traverseTreePath(head.right));

            int maxLocalPath = leftSidePath + rightSidePath - head.value; //((head.left==null)||(head.right==null)?0:head.value);

            if (maxLocalPath > maxGlobalPath) maxGlobalPath = maxLocalPath;

            //---добавляем логирование обхода путей в дереве---
            System.out.println(
                    String.format("node = %d (left path = %d, right path = %d, local max = %d, global max = %d)",
                            head.value, leftSidePath, rightSidePath, maxLocalPath, maxGlobalPath));


            return Math.max(leftSidePath, rightSidePath);
        }
    }

    public static int treeSolution(Node head) {
        maxGlobalPath = head.value;
        traverseTreePath(head);
        return maxGlobalPath;
    }

    public static void main(String[] args) {
    //--TEST #1-------------------

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

    //--TEST #2-------------------
    //  Node tree = new Node(2);
    //  tree.left = new Node(2);
    //  tree.right = new Node(-3);
    //  tree.right.left = new Node(5);
    //  tree.right.right = new Node(1);

    //------TEST #3-------------------
    //  Node tree = new Node(-2);
    //  tree.left = new Node(0);
    //  tree.left.right = new Node(-2);

        System.out.println(treeSolution(tree));
    }
}
