package FinalB.Tests;

import FinalB.Utils.Node;

public class TestParamsBundle {
    private Node headOfTree;
    private int keyToDelete;
    private int treeHeight;

    public TestParamsBundle(Node root, int treeHeight, int keyToDelete){
        this.headOfTree = root;
        this.keyToDelete = keyToDelete;
        this.treeHeight = treeHeight;
    }

    public Node getRootNode(){return headOfTree;}
    public int getTreeHeight(){return treeHeight;}
    public int getKeyToDelete(){return keyToDelete;}
}
