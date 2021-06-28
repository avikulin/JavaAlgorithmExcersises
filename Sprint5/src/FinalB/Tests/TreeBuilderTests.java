package FinalB.Tests;

import FinalB.Utils.Node;
import FinalB.Utils.TreeBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeBuilderTests {
    public static void main(String[] args) {
        System.out.println("\nTreeBuilder internal tests.\n");

        List<Integer[]> cases = new ArrayList<>();
        cases.add(new Integer[]{-1, 1, -1, 2, -1, -1, -1, 3, -1, -1, -1, -1, -1, -1, 4, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 5});
        cases.add(new Integer[]{-1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16});
        cases.add(new Integer[]{-1, 1, -1, 3, -1, -1, 6, 7, -1, -1, -1, -1, 12, 13});

        System.out.println("Testing in different modes of constructor:");

        String templatePassed = "%d) test passed.";
        String templateFailed = "%d) test failed. Trees has been constructed are not equal:\n" +
                                "\t tree one - %s,\n" +
                                "\t tree two - %s.";


        for (int i=0; i < cases.size(); i++){
            int[] treeArgs = Arrays.stream(cases.get(i)).mapToInt(Integer::intValue).toArray();
            TreeBuilder tb1 = new TreeBuilder(treeArgs);
            TreeBuilder tb2 = new TreeBuilder(tb1.getFlattedTreeValues());
            String tb1Signature = tb1.getTreeSignature();
            String tb2Signature = tb2.getTreeSignature();
            if (Arrays.equals(tb1.getFlattedTreeValues(), tb2.getFlattedTreeValues()))
                System.out.println(String.format(templatePassed, i));
            else
                System.out.println(String.format(templateFailed, i, tb1Signature, tb2Signature));
        }

        System.out.println("\n Testing replication constructor:");

        Node head = new Node();
            head.setValue(1);

        Node left1 = new Node();
            left1.setValue(2);
            head.setLeft(left1);

        Node right1 = new Node();
            right1.setValue(3);
            head.setRight(right1);

        Node left2 = new Node();
            left2.setValue(5);
            right1.setLeft(left2);

        Node right2 = new Node();
            right2.setValue(4);
            left1.setRight(right2);

        Node left3 = new Node();
            left3.setValue(6);
            right2.setLeft(left3);

        Node right3 = new Node();
            right3.setValue(7);
            left2.setRight(right3);

        TreeBuilder tb1 = new TreeBuilder(head, 4);
        tb1.markNode(3);

        System.out.println("\nA) Replicating the existing tree to TreeBuilder:");
        System.out.println(String.format("Flat signature of the replicated tree: %s", tb1.getTreeSignature()));
        System.out.println("Tree view of the replicated tree: ");
        System.out.println(tb1.getTreeHierarchyView());

        System.out.println("\nB) Replicating the TreeBuilder content to another TreeBuilder instance:");
        TreeBuilder tb2 = new TreeBuilder(tb1.getFlattedTreeValues());
        tb2.markNode(3);
        System.out.println(String.format("Flat signature of new replicates content: %s", tb2.getTreeSignature()));
        System.out.println("Tree view: ");
        System.out.println(tb2.getTreeHierarchyView());


        int[] a = tb1.getFlattedTreeValues();
        int[] b = tb2.getFlattedTreeValues();

        if (Arrays.equals(tb1.getFlattedTreeValues(), tb2.getFlattedTreeValues()))
            System.out.println(String.format(templatePassed, 3));
        else
            System.out.println(String.format(templateFailed, 3, tb1.getTreeSignature(), tb2.getTreeSignature()));
    }
}
