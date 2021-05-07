package ProblemB;


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

class DepthResult {
    int heightValue;
    boolean isBalanced;

    DepthResult(int height, boolean balanced) {
        heightValue = height;
        isBalanced = balanced;
    }
}

public class Solution {

    private static DepthResult checkSubTrees(Node node) {
        // базовый случай
        if (node == null) return new DepthResult(0, true); //нулевое дерево сбалансировано по определению.

        // рекуррентный случай
        DepthResult leftDepth = checkSubTrees(node.left);
        DepthResult rightDepth = checkSubTrees(node.right);

        int increment = (node.left == null) && (node.right == null) ? 0 : 1;
        int nodeDepth = Math.max(leftDepth.heightValue, rightDepth.heightValue) + increment;

        if ((!leftDepth.isBalanced) || (!rightDepth.isBalanced))
            return new DepthResult(nodeDepth, false);

        boolean nodeIsBalanced = false;
        if ((node.left != null) && (node.right != null))
            nodeIsBalanced = Math.abs(leftDepth.heightValue - rightDepth.heightValue) < 2;
        else
            nodeIsBalanced = ((leftDepth.heightValue == 0) && (node.right == null))
                    || ((node.left == null) && (rightDepth.heightValue == 0));

        // немного диагностической информации
        System.out.println(String.format("%-" + (nodeDepth < 1 ? 1 : nodeDepth) + "s %d (height - %d, height left - %d," +
                        " height right - %d balanced - %b)",
                " ", node.value, nodeDepth,
                leftDepth.heightValue, rightDepth.heightValue, nodeIsBalanced));

        return new DepthResult(nodeDepth, nodeIsBalanced);
    }

    public static boolean treeSolution(Node head) {
        return checkSubTrees(head).isBalanced; //корень дерева имеет нулевую высоту
    }

    public static void main(String[] args) {
        /* FULL TREE
                        ___0___             0   5
                       /       \
                      1         2           1   4
                     / \       / \
                    3   4     5   6         2   3
                  /  \       /     \
                 7    8     9       10      3   2
                           / \        \
                         11   12       13   4   1
                        /
                      14                    5   0
         */

        Node tree = new Node(0);
        System.out.println(treeSolution(tree));

        tree.left = new Node(1);
        System.out.println(treeSolution(tree));

        tree.right = new Node(2);
        System.out.println(treeSolution(tree));

        tree.left.right = new Node(4);
        tree.left.left = new Node(3);
        System.out.println(treeSolution(tree));

        tree.left.left.left = new Node(7);
        System.out.println(treeSolution(tree));

        tree.left.left.right = new Node(8);
        System.out.println(treeSolution(tree));

        tree.right.left = new Node(5);
        tree.right.left.left = new Node(9);
        tree.right.left.left.left = new Node(11);
        tree.right.left.left.left.left = new Node(14);

        tree.right.left.left.right = new Node(12);

        tree.right.right = new Node(6);
        tree.right.right.right = new Node(10);
        tree.right.right.right.right = new Node(13);

        System.out.println("\nRESULT:");
        System.out.println(treeSolution(tree));
    }
}
