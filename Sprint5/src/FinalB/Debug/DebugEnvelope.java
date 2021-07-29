package FinalB.Debug;

import FinalB.Solution;
import FinalB.Utils.TreeBuilder;
import FinalB.Utils.Node;

/**
 * Класс-обертка для запуска отладчика и вывода результатов отладки функции "remove(...)"
 */
public class DebugEnvelope {
    public static void main(String[] args) {
        TreeBuilder tree = new TreeBuilder(new int[]{-1,
                50,
                25, -1,
                15, 35, -1, -1,
                5, 20, 30, 40, -1, -1, -1, -1}
        );
        System.out.println(String.format("Given tree:\n%s\n", tree.getTreeHierarchyView()));

        int[] keysToDelete = new int[]{25, 35, 50, 40, 5, 20, 15, 30};
        Node root = tree.projectToTree();
        int height = tree.getHeight();

        for (int i = 0; i < keysToDelete.length; i++) {
            System.out.println(Solution.getTraceOfOperation(root, keysToDelete[i]));

            root = Solution.remove(root, keysToDelete[i]);
            TreeBuilder tmp = new TreeBuilder(root, height);

            System.out.println(
                    String.format(
                            "Deleting key [%d]:\n\t-resulting tree:\n%s\n",
                            keysToDelete[i],
                            tmp.getTreeHierarchyView()
                    )
            );
        }
    }
}
