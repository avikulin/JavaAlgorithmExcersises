package FinalB;

import java.util.*;
import java.util.stream.Collectors;

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
}

class TreeBuilder {
    private Node[] flattedStorage;
    private Set<Integer> markedNodes;
    private Node rootNode;
    private int treeSize;

    TreeBuilder(Node root, int size) {
        rootNode = root;
        treeSize = size;
        flattedStorage = new Node[treeSize + 1];
        flattedStorage[0] = null;
        flattedStorage[1] = rootNode;
        markedNodes = new HashSet<>();
    }

    TreeBuilder(int[] values) {
        rootNode = new Node();
        rootNode.setValue(values[1]);

        treeSize = values.length - 1;
        flattedStorage = new Node[treeSize + 1];
        flattedStorage[0] = null;
        flattedStorage[1] = rootNode;

        markedNodes = new HashSet<>();

        Stack<Integer> valuesStack = new Stack<>();
        Stack<Node> nodesStack = new Stack<>();

        valuesStack.push(1);
        nodesStack.push(rootNode);


        while (!valuesStack.empty()) {
            int headPos = valuesStack.pop(); //на предыдущем шаге уже добавлен в дерево
            Node currentHeadNode = nodesStack.pop(); //на предыдущем шаге уже добавлен в дерево
            int rightPos = getRightNodePosition(values, headPos);
            if (rightPos != -1) {
                Node node = new Node();
                node.setValue(values[rightPos]);
                currentHeadNode.setRight(node);
                nodesStack.push(node);
                valuesStack.push(rightPos);
                flattedStorage[rightPos] = node;
            }

            int leftPos = getLeftNodePosition(values, headPos);
            if (leftPos != -1) {
                Node node = new Node();
                node.setValue(values[leftPos]);
                currentHeadNode.setLeft(node);
                nodesStack.push(node);
                valuesStack.push(leftPos);
                flattedStorage[leftPos] = node;
            }
        }
    }

    private int getLeftNodePosition(int[] flattedTree, int head) {
        int position = head * 2;
        return (position < flattedTree.length) ? flattedTree[position] : -1;
    }

    private int getRightNodePosition(int[] flattedTree, int head) {
        int position = head * 2 + 1;
        return (position < flattedTree.length) ? flattedTree[position] : -1;
    }

    public void markNode(int idx) {
        if ((idx < 1) && (idx > flattedStorage.length - 1))
            throw new IllegalArgumentException("Index is out of bounds");
        if (flattedStorage[idx] == null)
            throw new IllegalArgumentException("Trying to mark absent element");
        markedNodes.add(idx);
    }

    public Node getRootNode() {
        return rootNode;
    }

    public int[] getFlattedTreeValues() {
        int[] res = new int[flattedStorage.length];
        for (int i = 1; i < flattedStorage.length; i++)
            res[i] = flattedStorage[i] != null ? flattedStorage[i].getValue() : -1;
        return res;
    }

    public String getTreeSignature() {
        return Arrays.toString(getFlattedTreeValues());
    }

    public Node getNode(int Id) {
        if ((Id > 0) && (Id < flattedStorage.length))
            return flattedStorage[Id];
        return null;
    }

    public String getTreeHierarchyView() {
        int nodesCount = flattedStorage.length - 1;

        int numberOfLevels = (int) (Math.log(nodesCount + 1) / Math.log(2)); // считаем с 0-го уровня
        int[] initialBias = new int[numberOfLevels];
        int[] intervalBias = new int[numberOfLevels];

        initialBias[numberOfLevels - 1] = 0;
        intervalBias[numberOfLevels - 1] = 3;

        for (int i = numberOfLevels - 2; i >= 0; i--) {
            initialBias[i] = (int) Math.pow(2, numberOfLevels - i) - 2;
            intervalBias[i] = intervalBias[i + 1] * 2 + 1;
        }

        intervalBias[0] = 0;

        StringBuilder res = new StringBuilder();
        String templateRegular = "[%2d]";
        String templateMarked = "[%d*]";
        String templateNull = "[..]";

        int currentLevel = 0;
        while (currentLevel < numberOfLevels) {
            int numberOfElementsInLine = (int) Math.pow(2, currentLevel);
            int levelStartIdx = numberOfElementsInLine;
            int levelEndIdx = levelStartIdx * 2 - 1;

            String initialBiasStr = "\t".repeat(initialBias[currentLevel]);
            String intervalBiasStr = "\t".repeat(intervalBias[currentLevel]);

            StringJoiner lineAssembler = new StringJoiner(intervalBiasStr, initialBiasStr, "\n");
            for (int i = levelStartIdx; i <= levelEndIdx; i++) {
                if (flattedStorage[i] == null) {
                    lineAssembler.add(templateNull);
                    continue;
                }

                if (markedNodes.contains(i))
                    lineAssembler.add(String.format(templateMarked, flattedStorage[i].getValue()));
                else
                    lineAssembler.add(String.format(templateRegular, flattedStorage[i].getValue()));
            }
            res.append(lineAssembler.toString());
            currentLevel++;
        }
        return res.toString();
    }
}

public class Solution {


    public static Node remove(Node root, int key) {
        // случай, если узел - это корень дерева

        // случай, если узел только один в дереве
        // в т.ч. левый от
        return new Node();
    }

    public static void main(String[] args) {
        TreeBuilder treeBuilder = new TreeBuilder(new int[]{-1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16});
        treeBuilder.markNode(5);
        System.out.println("Flat signature: ");
        System.out.println(treeBuilder.getTreeSignature());
        System.out.println("\nTree view: ");
        System.out.println(treeBuilder.getTreeHierarchyView());
    }
}

