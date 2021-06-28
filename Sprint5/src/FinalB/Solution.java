package FinalB;

import FinalB.Tests.TestParamsBundle;
import FinalB.Utils.Node;
import FinalB.Utils.TreeBuilder;
import FinalB.Utils.NodeUtils;

import java.util.HashMap;
import java.util.Stack;

enum Direction {LEFT, RIGHT, ROOT, NULL};

class NodeSearchResults {
    private Node parent;
    private Node node;
    private Direction direction;


    NodeSearchResults(Node parent, Node node, Direction direction) {
        this.node = node;
        this.parent = parent;
        this.direction = direction;
    }

    public Node getNode() {
        return node;
    }

    public Node getParent() {
        return parent;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getNumberOfSuccessors() {
        int res = 0;
        if (node == null) return res;
        if (node.getLeft() != null) res++;
        if (node.getRight() != null) res++;
        return res;
    }

    @Override
    public String toString() {
        return String.format("NodeSearchResults{node = %s, parent=%s, direction=%s, successors = %s}",
                NodeUtils.nodeToStr(node),
                NodeUtils.nodeToStr(parent),
                String.valueOf(direction),
                String.valueOf(this.getNumberOfSuccessors()));
    }
}

public class Solution {
    /**
     * Поиск возможной замены для узла, у которого существует 2 приемника
     *
     * @param node Заменяемый узел
     * @return Найденный приемник
     */
    private static NodeSearchResults searchForPossibleReplace(Node node) {
        if (node == null) return new NodeSearchResults(null, null, Direction.NULL);
        if (node.getRight() == null) return new NodeSearchResults(null, null, Direction.NULL);
        Stack<Node> nodeStack = new Stack<>();

        Node resNode = null;
        Node resParent = node;
        Direction direction = Direction.RIGHT;
        nodeStack.push(node.getRight()); // ишем в правом поддереве.
        while (!nodeStack.empty()) {

            resNode = nodeStack.pop();
            if (resNode.getLeft() != null) {
                nodeStack.push(resNode.getLeft());
                resParent = resNode;
                direction = Direction.LEFT;
            }
        }
        return new NodeSearchResults(resParent, resNode, direction);
    }

    private static NodeSearchResults searchNode(Node root, int key) {
        Node parent = root;
        Node current = root;
        Direction direction = Direction.ROOT;
        while (true) {
            if (current.getValue() == key) return new NodeSearchResults(parent, current, direction); // элемент нашелся.
            if ((current.getLeft() == null) && (current.getRight() == null))
                return null; // в дереве нет нужного элемента.
            parent = current;
            if (key < parent.getValue()) {
                if (parent.getLeft() == null)
                    return null; // в дереве нет нужного элемента.
                else {
                    current = parent.getLeft();
                    direction = Direction.LEFT;
                }
            } else {
                if (parent.getRight() == null)
                    return null; // в дереве нет нужного элемента.
                else {
                    current = parent.getRight();
                    direction = Direction.RIGHT;
                }
            }

        }
    }

    private static void replaceNode(Node parentOfNodeToReplace, Direction directionOfNodeToReplace, Node newNode) {
        if (directionOfNodeToReplace == Direction.LEFT)
            parentOfNodeToReplace.setLeft(newNode);
        else
            parentOfNodeToReplace.setRight(newNode);
    }

    private static void deleteLeafNode(Node nodeToDelete, Node parentOfNodeToDelete,
                                       Direction directionOfNodeToDelete) {
        replaceNode(parentOfNodeToDelete, directionOfNodeToDelete, null);

    }

    private static void cutNodeWithOneChild(Node nodeToDelete, Node parentOfNodeToDelete,
                                            Direction directionOfNodeToDelete) {
        Node nodeForReplacement = (nodeToDelete.getLeft() == null) ? nodeToDelete.getRight() : nodeToDelete.getLeft();
        replaceNode(parentOfNodeToDelete, directionOfNodeToDelete, nodeForReplacement);
    }

    private static void cutNodeWithTwoChildren(Node nodeToDelete, Node parentOfNodeToDelete,
                                               Direction directionOfNodeToDelete,
                                               Node newNode, Node newNodeParent) {
        Node rightChildOfDeletingNode = nodeToDelete.getRight();
        Node leftChildOfDeletingNode = nodeToDelete.getLeft();
        Node rightChildOfNewNode = newNode.getRight();

        replaceNode(parentOfNodeToDelete, directionOfNodeToDelete, newNode);
        if (rightChildOfDeletingNode == newNode) {       // если newNode является прямым правым потомком nodeToDelete.
            newNode.setLeft(leftChildOfDeletingNode);
        } else {                                        // если между newNode и nodeToDelete существует путь.
            newNodeParent.setLeft(rightChildOfNewNode);
            newNode.setRight(rightChildOfDeletingNode);
        }
    }

    public static Node remove(Node root, int key) {
        NodeSearchResults nodeToDelete = searchNode(root, key);
        if (nodeToDelete.getNode() == null) return root; //удалять нечего. дерево не изменилось.
        switch (nodeToDelete.getNumberOfSuccessors()) {
            case 0:
                    deleteLeafNode(nodeToDelete.getNode(), nodeToDelete.getParent(), nodeToDelete.getDirection());
                    break;
            case 1:
                    cutNodeWithOneChild(nodeToDelete.getNode(), nodeToDelete.getParent(), nodeToDelete.getDirection());
                    break;
            case 2:
                    NodeSearchResults replacementNode= searchForPossibleReplace(nodeToDelete.getNode());
                    cutNodeWithTwoChildren(nodeToDelete.getNode(),
                                           nodeToDelete.getParent(),
                                           nodeToDelete.getDirection(),
                                           replacementNode.getNode(),
                                           replacementNode.getParent());
                    break;
        }

        return root;
    }

    public static String process(TestParamsBundle params) {
        return new TreeBuilder(remove(params.getRootNode(), params.getKeyToDelete()),
                params.getTreeHeight()
        ).getTreeSignature();
    }

    public static void main(String[] args) {
        TreeBuilder tree = new TreeBuilder(new int[]{-1,
                50,
                25, -1,
                15, 35, -1, -1,
                5, 20, 30, 40, -1, -1, -1, -1}
        );
        System.out.println(String.format("Given tree:\n%s\n", tree.getTreeHierarchyView()));

        int[] keys = new int[]{25};//15, 5, 30, 25, 50};
        Node[] trees = new Node[keys.length];

        for (int i=0; i<trees.length;i++) {
            trees[i] = new TreeBuilder(tree.getFlattedTreeValues()).getRootNode();
            NodeSearchResults searchedNode = searchNode(trees[i], keys[i]);
            NodeSearchResults replacementNode = searchForPossibleReplace(searchedNode.getNode());
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println(String.format("Processing key %d:\n\t-%s,\n\t\t-possible replacement:\n\t\t\t-%s\n",
                    keys[i], searchedNode.toString(), replacementNode.toString()));
            Node root = remove(trees[i], keys[i]);
            TreeBuilder res = new TreeBuilder(root, tree.getHeight());
            System.out.println(String.format("\t\t-deleting key %d. \n\t\t\t-resulting tree:\n%s\n",
                    keys[i], res.getTreeHierarchyView()));
        }
    }
}

