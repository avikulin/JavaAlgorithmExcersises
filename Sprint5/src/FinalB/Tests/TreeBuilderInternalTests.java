package FinalB.Tests;

import FinalB.Utils.Node;
import FinalB.Utils.TreeBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Внутренние тесты компонента TreeBuilder.
 */
public class TreeBuilderInternalTests {
    private static final String templatePassed = "%d) test passed.";
    private static final String templateFailed = "%d) test failed. Trees has been constructed are not equal:\n" +
            "\t tree one - %s,\n" +
            "\t tree two - %s.";

    public static void main(String[] args) {
        System.out.println("\n*** TREE BUILDER INTERNAL TESTS ***\n");

        List<Integer[]> cases = new ArrayList<>();
        cases.add(new Integer[]{-1, 1, -1, 2, -1, -1, -1, 3, -1, -1, -1, -1, -1, -1, 4, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 5});
        cases.add(new Integer[]{-1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16});
        cases.add(new Integer[]{-1, 1, -1, 3, -1, -1, 6, 7, -1, -1, -1, -1, 12, 13});

        System.out.println("[ Testing different modes of constructor ]");


        for (int i = 0; i < cases.size(); i++) {
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

        System.out.println("\n[ Testing replication constructor ]");

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

        System.out.println("\n\tA) Replicating the existing tree to TreeBuilder:");
        System.out.println(String.format("\t\tFlat signature of the replicated tree: %s", tb1.getTreeSignature()));
        System.out.println("\t\tTree view of the replicated tree: ");
        System.out.println(tb1.getTreeHierarchyView());

        System.out.println("\n\tB) Replicating the TreeBuilder content to another TreeBuilder instance:");

        TreeBuilder tb2 = new TreeBuilder(tb1.getFlattedTreeValues());
        tb2.markNode(3);
        System.out.println(String.format("\t\tFlat signature of new replicates content: %s", tb2.getTreeSignature()));
        System.out.println("\t\tTree view: ");
        System.out.println(tb2.getTreeHierarchyView());

        if (Arrays.equals(tb1.getFlattedTreeValues(), tb2.getFlattedTreeValues()))
            System.out.println(String.format(templatePassed, 3));
        else
            System.out.println(String.format(templateFailed, 3, tb1.getTreeSignature(), tb2.getTreeSignature()));

        System.out.println("\n[ Testing the copy assignment operator ]");
        TreeBuilder tb3 = new TreeBuilder(tb1);

        if (Arrays.equals(tb1.getFlattedTreeValues(), tb3.getFlattedTreeValues()))
            System.out.println(String.format(templatePassed, 4));
        else
            System.out.println(String.format(templateFailed, 4, tb1.getTreeSignature(), tb3.getTreeSignature()));


        System.out.println("\n[ Testing the projection operator ]");
        TreeBuilder tb4 = new TreeBuilder(tb1.projectToTree(), tb1.getHeight());
        if (Arrays.equals(tb1.getFlattedTreeValues(), tb4.getFlattedTreeValues()))
            System.out.println(String.format(templatePassed, 5));
        else
            System.out.println(String.format(templateFailed, 5, tb1.getTreeSignature(), tb4.getTreeSignature()));

        System.out.println("\n[ Testing the equality operator ]");
        TreeBuilder tb5 = new TreeBuilder(new int[]{-1, 50, 35, 75});
        TreeBuilder tb6 = new TreeBuilder(new int[]{-1, 50, 35, 76});
        TreeBuilder tb7 = new TreeBuilder(new int[]{-1, 50, 35, 75, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1});
        TreeBuilder tb8 = new TreeBuilder(new int[]{-1, 50, 35, 75, -1});
        TreeBuilder tb9 = new TreeBuilder(new int[]{-1, 50, 35, 75});

        TreeBuilder tb10 = new TreeBuilder(new int[]{-1, 50, 35});
        TreeBuilder tb11 = new TreeBuilder(new int[]{-1, 50, 35, -1});

        if (!tb5.equals(tb6))
            System.out.println(String.format(templatePassed, 6));
        else
            System.out.println(String.format(templateFailed, 6, tb5.getTreeSignature(), tb6.getTreeSignature()));

        if (tb5.equals(tb9))
            System.out.println(String.format(templatePassed, 7));
        else
            System.out.println(String.format(templateFailed, 7, tb5.getTreeSignature(), tb9.getTreeSignature()));

        if (tb5.equals(tb7))
            System.out.println(String.format(templatePassed, 8));
        else
            System.out.println(String.format(templateFailed, 8, tb5.getTreeSignature(), tb7.getTreeSignature()));

        if (tb5.equals(tb8))
            System.out.println(String.format(templatePassed, 9));
        else
            System.out.println(String.format(templateFailed, 9, tb5.getTreeSignature(), tb8.getTreeSignature()));


        if (tb7.equals(tb8))
            System.out.println(String.format(templatePassed, 10));
        else
            System.out.println(String.format(templateFailed, 10, tb7.getTreeSignature(), tb8.getTreeSignature()));

        if (tb7.equals(tb9))
            System.out.println(String.format(templatePassed, 11));
        else
            System.out.println(String.format(templateFailed, 11, tb7.getTreeSignature(), tb9.getTreeSignature()));

        if (!tb9.equals(tb10))
            System.out.println(String.format(templatePassed, 12));
        else
            System.out.println(String.format(templateFailed, 12, tb9.getTreeSignature(), tb10.getTreeSignature()));

        if (!tb9.equals(tb11))
            System.out.println(String.format(templatePassed, 13));
        else
            System.out.println(String.format(templateFailed, 13, tb9.getTreeSignature(), tb11.getTreeSignature()));

        System.out.println("\n*** END ***\n");
    }
}
