package FinalB.Tests;

import FinalB.Utils.Node;

/**
 * Класс-обертка для пакетной передачи группы параметров в функцию. Используется для тестирования.
 */
public class TestParamsBundle {
    private final Node headOfTree;
    private final int keyToDelete;
    private final int treeHeight;

    /**
     * Конструктор класса-обертки.
     *
     * @param root  Ссылка на корневой узел дерева.
     * @param treeHeight    Высота дерева (количество уровней, начиная с 1-го).
     * @param keyToDelete   Значение удаляемого узла дерева.
     */
    public TestParamsBundle(Node root, int treeHeight, int keyToDelete){
        this.headOfTree = root;
        this.keyToDelete = keyToDelete;
        this.treeHeight = treeHeight;
    }

    /**
     * Получение ссылки на корневой узел дерева.
     * @return  Ссылка на корневой узел дерева.
     */
    public Node getRootNode(){return headOfTree;}

    /**
     * Получение высоты дерева.
     * @return  Значение высоты дерева (количество уровней, начиная с 1-го).
     */
    public int getTreeHeight(){return treeHeight;}

    /**
     * Получение ключа удаления.
     * @return  Значение удаляемого узла дерева.
     */
    public int getKeyToDelete(){return keyToDelete;}
}
