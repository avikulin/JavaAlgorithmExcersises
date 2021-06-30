package FinalB;

import FinalB.Tests.TestParamsBundle;
import FinalB.Utils.Node;
import FinalB.Utils.TreeBuilder;
import FinalB.Utils.NodeUtils;

import java.util.Stack;

enum Position {LEFT, RIGHT, ROOT, NULL};

class NodeInfo {
    private Node parent;
    private Node node;
    private Position position;


    NodeInfo(Node parent, Node node, Position position) {
        this.node = node;
        this.parent = parent;
        this.position = position;
    }

    public Node getNode() {
        return node;
    }

    public Node getParent() {
        return parent;
    }

    public Position getPosition() {
        return position;
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
        return String.format("NodeInfo{node = %s, parent=%s, relation to parent=%s, successors = %d}",
                NodeUtils.nodeToStr(node),
                NodeUtils.nodeToStr(parent),
                position,
                this.getNumberOfSuccessors());
    }
}

public class Solution {
    /**
     * Поиск возможной замены для узла, у которого существует 2 приемника
     *
     * @param nodeInfo Заменяемый узел
     * @return Найденный приемник
     */
    private static NodeInfo searchForPossibleReplace(NodeInfo nodeInfo) {
        if (nodeInfo == null) return new NodeInfo(null, null, Position.NULL);

        Stack<Node> nodeStack = new Stack<>();


        // если у узла нет потомков, - значит нет и приемников. Возвращаем NULL
        Node resNode = null;
        Node resParent = null;
        Position position = Position.NULL;

        // если у узла 1 потомок, - то он и будет приемником.
        if (nodeInfo.getNumberOfSuccessors() == 1) {
            if (nodeInfo.getNode().getLeft() == null) {
                resNode = nodeInfo.getNode().getRight();
                resParent = nodeInfo.getNode();
                position = Position.RIGHT;
            } else {
                resNode = nodeInfo.getNode().getLeft();
                resParent = nodeInfo.getNode();
                position = Position.LEFT;
            }
        }
        if (nodeInfo.getNumberOfSuccessors() == 2) {
            // если у узла 2 потомка - то ищем минимальный элемент в правом поддереве. Он и будет приемником.
            nodeStack.push(nodeInfo.getNode().getRight());
            resParent = nodeInfo.getNode();
            position = Position.RIGHT;
            while (!nodeStack.empty()) {
                resNode = nodeStack.pop();
                if (resNode.getLeft() != null) {
                    nodeStack.push(resNode.getLeft());
                    resParent = resNode;
                    position = Position.LEFT;
                }
            }
        }
        return new NodeInfo(resParent, resNode, position);
    }

    private static NodeInfo searchNode(Node root, int key) {
        Node parent = root;
        Node current = root;
        Position position = Position.ROOT;
        while (true) {
            if (current.getValue() == key) return new NodeInfo(parent, current, position); // элемент нашелся.
            if ((current.getLeft() == null) && (current.getRight() == null))
                return null; // в дереве нет нужного элемента.
            parent = current;
            if (key < parent.getValue()) {
                if (parent.getLeft() == null)
                    return null; // в дереве нет нужного элемента.
                else {
                    current = parent.getLeft();
                    position = Position.LEFT;
                }
            } else {
                if (parent.getRight() == null)
                    return null; // в дереве нет нужного элемента.
                else {
                    current = parent.getRight();
                    position = Position.RIGHT;
                }
            }

        }
    }

    private static Node replaceNode(Node root, NodeInfo nodeToReplace, NodeInfo newNode) {
        if (nodeToReplace.getPosition() == Position.ROOT) {
            return newNode.getNode();
        }

        if (nodeToReplace.getPosition() == Position.LEFT)
            nodeToReplace.getParent().setLeft(newNode.getNode());
        else
            nodeToReplace.getParent().setRight(newNode.getNode());

        return root;
    }

    private static Node deleteLeafNode(Node root, NodeInfo nodeToDelete) {
        return replaceNode(root, nodeToDelete, new NodeInfo(null, null, Position.NULL));
    }

    private static Node cutNodeWithOneChild(Node root, NodeInfo nodeToDelete, NodeInfo newNode) {
        return replaceNode(root, nodeToDelete, newNode);
    }

    private static Node cutNodeWithTwoChildren(Node root, NodeInfo nodeToDelete, NodeInfo newNode) {
        Node rightChildOfDeletingNode = nodeToDelete.getNode().getRight();
        Node leftChildOfDeletingNode = nodeToDelete.getNode().getLeft();
        Node rightChildOfNewNode = newNode.getNode().getRight();

        Node possiblyNewRoot = replaceNode(root, nodeToDelete, newNode);
        if (rightChildOfDeletingNode == newNode.getNode()) {       // если newNode является прямым правым потомком nodeToDelete.
            newNode.getNode().setLeft(leftChildOfDeletingNode);
        } else {                                        // если между newNode и nodeToDelete существует путь.
            newNode.getParent().setLeft(rightChildOfNewNode);
            newNode.getNode().setLeft(leftChildOfDeletingNode);
            newNode.getNode().setRight(rightChildOfDeletingNode);
        }
        return possiblyNewRoot;
    }

    public static Node remove(Node root, int key) {
        Node res = null;
        NodeInfo nodeToDelete = searchNode(root, key);
        if (nodeToDelete.getNode() == null) return root; //удалять нечего. дерево не изменилось.
        switch (nodeToDelete.getNumberOfSuccessors()) {
            case 0: {
                res = deleteLeafNode(root, nodeToDelete);
                break;
            }
            case 1: {
                NodeInfo replacementNode = searchForPossibleReplace(nodeToDelete);
                res = cutNodeWithOneChild(root, nodeToDelete, replacementNode);
                break;
            }
            case 2: {
                NodeInfo replacementNode = searchForPossibleReplace(nodeToDelete);
                res = cutNodeWithTwoChildren(root, nodeToDelete, replacementNode);
                break;
            }
        }

        return res;
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

        int[] keys = new int[]{15, 5, 30, 25, 35, 40, 50};
        Node[] trees = new Node[keys.length];

        for (int i = 0; i < trees.length; i++) {
            trees[i] = new TreeBuilder(tree.getFlattedTreeValues()).getRootNode();
            NodeInfo searchedNode = searchNode(trees[i], keys[i]);
            NodeInfo replacementNode = searchForPossibleReplace(searchedNode);
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

