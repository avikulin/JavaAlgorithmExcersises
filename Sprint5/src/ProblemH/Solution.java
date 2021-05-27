package ProblemH;

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
    private static int res = 0;

    private static void traverseTree(Node vertex, int prevLevelValue){
        //---базовый случай рекурсии--------------
        if ((vertex.left==null)&&(vertex.right==null)){
            int pathLocalRes = prevLevelValue/10;
            System.out.println(String.format("\t- %d", pathLocalRes));
            res += pathLocalRes;
            return;
        }

        if (vertex.left!=null)  traverseTree(vertex.left, (prevLevelValue + vertex.left.value)*10);
        if (vertex.right!=null)  traverseTree(vertex.right, (prevLevelValue + vertex.right.value)*10);
    }

    public static int treeSolution(Node head){
        traverseTree(head, head.value*10);
        return res;
    }

    public static void main(String[] args) {
        Node tree = new Node(1);
        tree.left = new Node(2);
        tree.right = new Node(3);
        tree.right.left = new Node(2);
        tree.right.right = new Node(1);
        System.out.println(treeSolution(tree));
    }
}
