package FinalB.Tests;

import FinalB.Utils.Node;
import FinalB.Utils.TreeBuilder;

/**
 * Фабрика генерации бинарных деревьев разной конфигурации с тестовыми данными в значениях услов.
 */
public class TestDataFabric {
   /* Библиотека тестовых деревьев

    0) *        1) /       2)  \         3) /\

    4) /        5) \       6)  /         7) \
      /             \          \            /

    8)  /\     9) /\       10)   /\      11)  \      12)  /
       /  \         \           /             /\         /\

    13) /\     14)  /\     15)   /\      16)   /\    17)  /\
        \            /           \ \          / /         \/

    18)  /\    19)  /\     20)   /\      21)   /\    22)   /\
        /\           /\         /\ \          / /\        /\/\

    23)   /    24) \       25) /         26)  \      27)  /      28)  \     29)  \      30) /
         /          \          \              /          /             \         /          \
        /            \          \            /           \             /        /\          /\
                                                                                               __
    31)   /    32) \       33)  /        34)  \      35)  /      36)   \    37)   /\    38)   /  \
          \        /           /\             /\         /\            /\        /\/\        /\  /\
          /        \            /             \         /\              /\      /\  /\      /\/\/\/\

     */

    private static final int[][] treeData = {
            {-1, 50},            // Шаблон 0
            {-1, 50, 35, -1},    // Шаблон 1
            {-1, 50, -1, 75},    // Шаблон 2
            {-1, 50, 35, 75},    // Шаблон 3
            {-1, 50, 35, -1, 15, -1, -1, -1},    // Шаблон 4
            {-1, 50, -1, 75, -1, -1, -1, 95},    // Шаблон 5
            {-1, 50, 35, -1, -1, 45, -1, -1},    // Шаблон 6
            {-1, 50, -1, 75, -1, -1, 65, -1},    // Шаблон 7
            {-1, 50, 35, 75, 15, -1, -1, 95},    // Шаблон 8
            {-1, 50, 35, 75, -1, -1, -1, 95},    // Шаблон 9
            {-1, 50, 35, 75, 15, -1, -1, -1},    // Шаблон 10
            {-1, 50, -1, 75, -1, -1, 65, 95},    // Шаблон 11
            {-1, 50, 35, -1, 15, 45, -1, -1},    // Шаблон 12
            {-1, 50, 35, 75, -1, 45, -1, -1},    // Шаблон 13
            {-1, 50, 35, 75, -1, -1, 65, -1},    // Шаблон 14
            {-1, 50, 35, 75, -1, 45, -1, 95},    // Шаблон 15
            {-1, 50, 35, 75, 15, -1, 65, -1},    // Шаблон 16
            {-1, 50, 35, 75, -1, 45, 65, -1},    // Шаблон 17
            {-1, 50, 35, 75, 15, 45, -1, -1},    // Шаблон 18
            {-1, 50, 35, 75, -1, -1, 65, 95},    // Шаблон 19
            {-1, 50, 35, 75, 15, 45, -1, 95},    // Шаблон 20
            {-1, 50, 35, 75, 15, -1, 65, 95},    // Шаблон 21
            {-1, 50, 35, 75, 15, 45, 65, 95},    // Шаблон 22
            {-1, 50, 35, -1, 15, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1},    // Шаблон 23
            {-1, 50, -1, 75, -1, -1, -1, 95, -1, -1, -1, -1, -1, -1, -1, 100},   // Шаблон 24
            {-1, 50, 35, -1, -1, 45, -1, -1, -1, -1, -1, 49, -1, -1, -1, -1},    // Шаблон 25
            {-1, 50, -1, 75, -1, -1, 65, -1, -1, -1, -1, -1, 60, -1, -1, -1},    // Шаблон 26
            {-1, 50, 35, -1, 15, -1, -1, -1, -1, 20, -1, -1, -1, -1, -1, -1},    // Шаблон 27
            {-1, 50, -1, 75, -1, -1, -1, 95, -1, -1, -1, -1, -1, -1, 90, -1},    // Шаблон 28
            {-1, 50, -1, 75, -1, -1, 65, -1, -1, -1, -1, -1, 60, 70, -1, -1},    // Шаблон 29
            {-1, 50, 35, -1, -1, 45, -1, -1, -1, -1, 40, 49, -1, -1, -1, -1},    // Шаблон 30
            {-1, 50, 35, -1, -1, 45, -1, -1, -1, -1, 40, -1, -1, -1, -1, -1},    // Шаблон 31
            {-1, 50, -1, 75, -1, -1, 65, -1, -1, -1, -1, -1, -1, 70, -1, -1},    // Шаблон 32
            {-1, 50, 35, -1, 15, 45, -1, -1, -1, -1, 40, -1, -1, -1, -1, -1},    // Шаблон 33
            {-1, 50, -1, 75, -1, -1, 65, 95, -1, -1, -1, -1, -1, 70, -1, -1},    // Шаблон 34
            {-1, 50, 35, -1, 15, 45, -1, -1, 10, 20, -1, -1, -1, -1, -1, -1},    // Шаблон 35
            {-1, 50, -1, 75, -1, -1, 65, 95, -1, -1, -1, -1, -1, -1, 90, 100},   // Шаблон 36
            {-1, 50, 35, 75, 15, 45, 65, 95, 10, 20, -1, -1, -1, -1, 90, 100},   // Шаблон 37
            {-1, 50, 35, 75, 15, 45, 65, 95, 10, 20, 40, 50, 60, 70, 90, 100}    // Шаблон 38
    };

    /**
     * Получение тестового дерева по заданному шаблону.
     *
     * @param templateID Код шаблона конфигурации дерева.
     * @return Ссылка на корневой узел сгенерированного дерева.
     */
    public static Node getTree(int templateID) {
        if ((templateID < 0) || (templateID > 38))
            throw new IllegalArgumentException("There is no such template in fabric");

        return new TreeBuilder(treeData[templateID]).projectToTree();
    }
}
