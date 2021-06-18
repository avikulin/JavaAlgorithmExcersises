package FinalB;

import java.util.*;

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
    private int numberOfLevels;

    private void initBase(Node root, int size) {
        rootNode = root;
        treeSize = size;
        flattedStorage = new Node[treeSize + 1];
        flattedStorage[1] = rootNode;
        flattedStorage[0] = null;
        numberOfLevels = (int) (Math.log(treeSize) / Math.log(2)) + 1; // считаем с единицы
        markedNodes = new HashSet<>();
    }

    TreeBuilder(Node root, int size) {
        initBase(root, size);
    }

    TreeBuilder(Node root) {
        List<Node> treeValues = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            Node currentHead = stack.pop();
            treeValues.add(currentHead);
            Node possibleRight = currentHead.getRight();
            Node possibleLeft = currentHead.getLeft();
            if (possibleRight != null)
                stack.push(possibleRight);
            if (possibleLeft != null)
                stack.push(possibleLeft);
        }
        initBase(root, treeValues.size());
        for (int i = 0; i < treeValues.size(); i++)
            flattedStorage[i + 1] = treeValues.get(i);
    }

    TreeBuilder(int[] values) {
        rootNode = new Node();
        rootNode.setValue(values[1]);
        initBase(rootNode, values.length - 1);

        Stack<Integer> valuesStack = new Stack<>();
        Stack<Node> nodesStack = new Stack<>();

        valuesStack.push(1);
        nodesStack.push(rootNode);

        while (!valuesStack.empty()) {
            int headPos = valuesStack.pop(); //на предыдущем шаге уже добавлен в дерево
            Node currentHeadNode = nodesStack.pop(); //на предыдущем шаге уже добавлен в дерево
            int rightPos = getRightNodePosition(values, headPos);
            if ((rightPos != -1) && (values[rightPos] != -1)) {
                Node node = new Node();
                node.setValue(values[rightPos]);
                currentHeadNode.setRight(node);
                nodesStack.push(node);
                valuesStack.push(rightPos);
                flattedStorage[rightPos] = node;
            }

            int leftPos = getLeftNodePosition(values, headPos);
            if ((leftPos != -1) && (values[leftPos] != -1)) {
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
        return (position < flattedTree.length) ? position : -1;
    }

    private int getRightNodePosition(int[] flattedTree, int head) {
        int position = head * 2 + 1;
        return (position < flattedTree.length) ? position : -1;
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
        final int TAB_TO_SPACES = 4;
        int nodesCount = flattedStorage.length - 1;

        int[] initialBias = new int[numberOfLevels];
        int[] intervalBias = new int[numberOfLevels + 3];
        int[] nodeTotalWidth = new int[numberOfLevels];

        initialBias[numberOfLevels - 1] = 0;
        intervalBias[numberOfLevels - 1] = 1;
        intervalBias[numberOfLevels] = 1;
        intervalBias[numberOfLevels + 1] = 0;
        intervalBias[numberOfLevels + 2] = 0;
        nodeTotalWidth[numberOfLevels - 1] = 1;
        nodeTotalWidth[numberOfLevels - 2] = 1;

        int corrector = (numberOfLevels % 2) == 1 ? 0 : 1;
        for (int i = numberOfLevels - 2; i >= 0; i--) {
            initialBias[i] = initialBias[i + 1] * 2 + ((i + corrector) % 2);
            intervalBias[i] = intervalBias[i + 2] * 3 + intervalBias[i + 3] * 2;
            if (i > 0)
                nodeTotalWidth[i - 1] = intervalBias[i];
        }

        intervalBias[0] = 0;

        StringBuilder res = new StringBuilder();
        String templateRegular = "%d";
        String templateMarked = "%d*";

        int maxPaddingLeft = initialBias[0];
        int maxPaddingRight = 0;

        int currentLevel = 0;
        while (currentLevel < numberOfLevels) {
            int numberOfElementsInLine = (int) Math.pow(2, currentLevel);
            int levelStartIdx = numberOfElementsInLine;
            int possibleEndOfTheLine = numberOfElementsInLine * 2 - 1;
            int levelEndIdx = possibleEndOfTheLine > nodesCount ? nodesCount : possibleEndOfTheLine;

            String initialBiasStr = "\t".repeat(initialBias[currentLevel]);
            String intervalBiasStr = "\t".repeat(intervalBias[currentLevel]);

            StringJoiner lineAssembler = new StringJoiner(intervalBiasStr, initialBiasStr, "\n");
            StringJoiner subLineAssembler = new StringJoiner(intervalBiasStr, initialBiasStr, "\n");

            int actualNodesInLine = 0;
            int paddingLeft = initialBias[currentLevel];
            int paddingRight = paddingLeft;

            for (int i = levelStartIdx; i <= levelEndIdx; i++) {
                if (flattedStorage[i] == null) {
                    String emptyStub = "\t".repeat(nodeTotalWidth[currentLevel]);
                    lineAssembler.add(emptyStub);
                    subLineAssembler.add(emptyStub);
                    if (actualNodesInLine == 0)
                        paddingLeft += nodeTotalWidth[currentLevel] + intervalBias[currentLevel];
                    continue;
                }
                actualNodesInLine++;
                paddingRight = levelEndIdx - i;
                System.out.println(String.format("%d) left - %d, right - %d (count - %d)",
                        currentLevel, paddingLeft, paddingRight, actualNodesInLine));

                String valueStr = "";
                if (markedNodes.contains(i))
                    valueStr = String.format(templateMarked, flattedStorage[i].getValue());
                else
                    valueStr = String.format(templateRegular, flattedStorage[i].getValue());

                int desiredEmptyStubStrWidth = nodeTotalWidth[currentLevel] * TAB_TO_SPACES - valueStr.length() - 2;
                String emptyStub;
                if ((valueStr.length() == 2) && (desiredEmptyStubStrWidth == 0))
                    emptyStub = "";
                else
                    emptyStub = (desiredEmptyStubStrWidth > 1) ? ".".repeat(desiredEmptyStubStrWidth) : " ";

                String nodeStr = String.format("[%s%s]", emptyStub, valueStr);
                String leftArrowStr = flattedStorage[i].getLeft() != null ? "/" : " ";
                String rightArrowStr = flattedStorage[i].getRight() != null ? "\\" : " ";
                String subNodeStr = String.format("%s%s%s",
                        leftArrowStr,
                        " ".repeat(nodeTotalWidth[currentLevel] * TAB_TO_SPACES - 2),
                        rightArrowStr);
                lineAssembler.add(nodeStr);
                subLineAssembler.add(subNodeStr);
            }

            if (paddingLeft < maxPaddingLeft) maxPaddingLeft = paddingLeft;
            if (paddingRight > maxPaddingRight) maxPaddingRight = paddingRight;

            res.append(lineAssembler.toString());
            if (currentLevel < numberOfLevels - 1)
                res.append(subLineAssembler.toString());

            currentLevel++;
        }
        System.out.println(maxPaddingLeft);
        System.out.println(maxPaddingRight);
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
        TreeBuilder treeBuilder = new TreeBuilder(new int[]{-1, 1, -1, 2, -1, -1, -1, 3, -1, -1, -1, -1, -1, -1, 4, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 5});
//        TreeBuilder treeBuilder = new TreeBuilder(new int[]{-1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16});
//        TreeBuilder treeBuilder = new TreeBuilder(new int[]{-1, 1, -1, 3, -1, -1, 6, 7, -1, -1, -1, -1, 12, 13});
        treeBuilder.markNode(3);
        System.out.println("Flat signature: ");
        System.out.println(treeBuilder.getTreeSignature());
        System.out.println("\nTree view: ");
        System.out.println(treeBuilder.getTreeHierarchyView());
    }
}

