//---Номер посылки в Яндекс.Контест - 52253724

package FinalB;             // -- эту строку нужно закомментировать перед отправкой в Яндекс.Контест

import FinalB.Utils.Node;   // -- эту строку нужно закомментировать перед отправкой в Яндекс.Контест

/*
 *  - ОПИСАНИЕ АЛГОРИТМА -
 * Структурно, алгоритм состоит из следующих основных шагов:
 *
 *  0) Определение наполненности дерева узлами. Если передано на вход пустое дерево, то дальнейшее выполнение алгоритма
 *  прерывается и возвращается null (ссылка на пустое дерево).
 *
 *  1) Бинарный поиск удаляемого узла в дереве <searchNode(...)>. В случае, если элемент не найден - дальнейшее
 *  выполнение алгоритма прерывается и возвращается корень неизмененного дерева. Если элемент найден, то возвращается
 *  структура метаданных с описанием основных характеристик узла дерева:
 *      а) ссылка на найденный узел;
 *      б) ссылка на родителя найденного узла;
 *      в) тип положения найденного узла относительно родителя;
 *      г) количество подчиненных узлов у найденного узла;
 *
 *  ВРЕМЕННАЯ СЛОЖНОСТЬ (в худшем случае): O(Log(H)), где H - высота дерева.
 *  ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ (в худшем случае): O(1)
 *
 *  2) В зависимости от количества потомков у найденного узла запускается одна из ветвей алгоритма:
 *      а) найденный (удаляемый) узел является листовым.
 *         В таком случае он просто удаляется функцией <replaceNode(root, nodeToDelete, null)>.
 *
 *        ИТОГО ПО ШАГУ 2-а):
 *              ВРЕМЕННАЯ СЛОЖНОСТЬ (в худшем случае): O(1)
 *              ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ (в худшем случае): O(1)
 *
 *      б) найденный (удаляемый) узел имеет одного потомка.
 *          б1) функцией <searchForPossibleReplacement(... )> определяется приемник узла - единственный потомок.
 *              Временная сложность операции - O(1), пространственная сложность операции - O(1).
 *
 *          б2) узел заменяется своим потомком (ссылка родительского узла на удаляемый узел замыкается на
 *              потомка удаляемого узла): <replaceNode(root, nodeToDelete, replacementNode)>
 *              Временная сложность операции - O(1), пространственная сложность операции - O(1).
 *
 *         ИТОГО ПО ШАГУ 2-б):
 *              ВРЕМЕННАЯ СЛОЖНОСТЬ (в худшем случае): O(1) + O(1) = O(1)
 *              ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ (в худшем случае):  O(1) + O(1) = O(1)
 *
 *      в) найденный (удаляемый) узел имеет 2-х потомков.
 *          в1) функцией <searchForPossibleReplacement(... )> определяется приемник узла - минимальный элемент в правом
 *              поддереве удаляемого узла.  Временная сложность операции в худшем случае - O(Log(H-1)),
 *              пространственная сложность операции - O(1).
 *
 *          в2) функцией <replaceNode(root, nodeToDelete, replacementNode)> удаляемый узел заменяется приемником.
 *              Временная сложность операции - O(1), пространственная сложность операции - O(1).
 *
 *          в3) в зависимости от положения узла приемника относительно удаляемого узла, возможно две версии
 *              поведения алгоритма:
 *              в3-1) приемник является прямым правым потомком заменяемого узла:
 *
 *                                      			[................50]
 *                                                  /
 *                                      [........30] 	⯇-- удаляемый узел дерева
 *               разрываемая связь --⯈ ╳          🡤
 *                                  [15]---┐    	[35] 	⯇-- приемник (min в правом поддереве)
 *                                  /  \    ╲_______🡥  \
 *                              [ 5]	[20]			[40]
 *
 *              в этом случае:
 *                  в3-1-1) удаляемый узел заменяется приемником
 *                  в3-1-2) левое поддерево удаленного узла подключается левым поддеревом к примнику.
 *
 *              Суммарные пространственная и временная асимптотики шагов (в3-1-1)...(в3-1-2) соответствуют O(1).
 *
 *              в3-2) между удаляемым узлом и приемником существует путь в дереве.
 *
 *                                					[................50]
 *                                                  /
 *                                     [........25]	⯇-- удаляемый узел дерева
 *                                     /     🡡    \
 *               разрываемая связь --⯈╳      |     [ 35    ]
 *                                    /		  \    /🡤     \
 *                                   /         [30]   ╲     [40]
 *                                   __________🡥 ⯅ \   ├------полключение правого поддерева приемника
 *                                 🡥             |   [32 ]      левым поддеревом его родителя
 *                                /              |
 *  	                        [15]             └------------- приемник (min в правом поддереве)
 *                              /  \
 *                          [ 5]	[20]
 *
 *              в этом случае:
 *                  в3-2-1) удаляемый узел заменяется приемником
 *                  в3-2-2) правое поддерево приемника (левого поддерева не может быть по определению) подключается к
 *                          родителю приемника напрямую.
 *                  в3-2-3) левое поддерево удаленного узла подключается левым поддеревом к примнику.
 *                  в3-2-4) правое поддерево удаленного узла подключается правым поддеревом к примнику.
 *
 *              Суммарные пространственная и временная асимптотики шагов (в3-2-1)...(в3-2-4) соответствуют O(1).
 *
 *         ИТОГО ПО ШАГУ 2-в):
 *              ВРЕМЕННАЯ СЛОЖНОСТЬ (в худшем случае): O(Log(H-1)) + O(1) + МАX (O(1), O(1)) = O(Log(H))
 *              ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ (в худшем случае):  O(1) + O(1) + МАX (O(1), O(1)) = O(1)
 *
 *  ИТОГО ПО ШАГУ 2):
 *      ВРЕМЕННАЯ СЛОЖНОСТЬ (в худшем случае): МАХ (O(1), O(1), O(Log(H)) = O(Log(H)), где H - высота дерева.
 *      ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ (в худшем случае): МАХ (O(1), O(1), O(1)) = O(1)
 *
 *  3) возвращается корень нового дерева (выполняется функцией <replaceNode(...)>):
 *      а) если удаляемый узел не был корнем дерева, то возвращается ссылка на корневой узел исходного дерева.
 *      в) если удаляемый узел был корнем дерева, то возвращается ссылка на приемника.
 *
 *  ВРЕМЕННАЯ СЛОЖНОСТЬ (в худшем случае): O(1)
 *  ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ (в худшем случае): O(1)
 *
 *  -ИТОГОВЫЙ РАСЧЕТ СЛОЖНОСТИ АЛГОРИТМА-
 *  ВРЕМЕННАЯ СЛОЖНОСТЬ (в худшем случае): O(Log(H)) + O(Log(H)) + O(1) = O(Log(H)), где H - высота дерева.
 *  ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ (в худшем случае): O(1) + O(1) + O(1) = O(1)
 *
 */


/**
 * Типы позиций целевого узла относительно его родителя.
 */
enum Position {
    LEFT,  // целевой узел является левым потомком родителя
    RIGHT, // целевой узел является правым потомком родителя
    ROOT,  // целевой узел являет конем дерева (совпадает с родителем)
    NULL   // целевой узел не задан (пустая ссылка)
}

/**
 * Класс инкапсулирует информацию (метаданные) о положении узла в дереве относительно родителя и подчиненных узлов.
 */
class NodeInfo {
    private final Node parent;
    private final Node node;
    private final Position position;

    /**
     * Конструктор экземпляра NodeInfo.
     *
     * @param parent   Ссылка на родительский узел.
     * @param node     Ссылка на целевой узел (который описывает структура NodeInfo).
     * @param position Положение целевого узла относительно его родителя.
     */
    NodeInfo(Node parent, Node node, Position position) {
        this.node = node;
        this.parent = parent;
        this.position = position;
    }

    /**
     * Получение ссылку на целевой узел.
     *
     * @return Ссылка узел, который описывает структура NodeInfo.
     */
    public Node getNode() {
        return node;
    }

    /**
     * Получение ссылки на родителя целевого узла.
     *
     * @return Ссылка на родителя узла, который описывает структура NodeInfo.
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Получение позиции целевого узла относительно его родителя.
     *
     * @return Код позиции.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Получение количества подчиненных узлов у целевого узла.
     *
     * @return Количество подчиненных узлов.
     */
    public int getNumberOfSuccessors() {
        int res = 0;
        if (node == null) return res;
        if (node.getLeft() != null) res++;
        if (node.getRight() != null) res++;
        return res;
    }

    /**
     * Функция сериализации свойств узла дерева в строку. Используется для отладочного вывода.
     *
     * @param node Ссылка на узел дерева.
     * @return Строковое описание свойст узла.
     */
    private String nodeToStr(Node node) {
        if (node == null) return "Node(null)";
        Node leftChild = node.getLeft();
        Node rightChild = node.getRight();
        String leftStr = leftChild == null ? "null" : String.valueOf(leftChild.getValue());
        String rightStr = rightChild == null ? "null" : String.valueOf(rightChild.getValue());
        return String.format("Node(V=%s, L=%s, R=%s)", node.getValue(), leftStr, rightStr);
    }

    /**
     * Функция сериализации метаданных (структура NodeInfo) в строку. Используется для отладочного вывода.
     *
     * @return Строка свойств узла в формате NodeInfo.
     */
    @Override
    public String toString() {
        return String.format("NodeInfo{node = %s, parent=%s, relation to parent=%s, successors = %d}",
                nodeToStr(node),
                nodeToStr(parent),
                position,
                this.getNumberOfSuccessors());
    }
}

/**
 * - ОСНОВНОЙ КЛАСС РЕШЕНИЯ -
 */
public class Solution {
    /**
     * Поиск возможной замены для узла, в зависимости от его положения в дереве.
     *
     * @param nodeInfo Ссылка на данные о заменяемом узле.
     * @return Ссылка на данные о найденном приемнике.
     */
    private static NodeInfo searchForPossibleReplacement(NodeInfo nodeInfo) {
        if (nodeInfo == null) // для несуществующего узла - замены не существует.
            return new NodeInfo(null, null, Position.NULL);

        // если у узла нет потомков, - значит нет и приемников. Возвращаем NULL по умолчанию.
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
            resNode = nodeInfo.getNode().getRight();
            resParent = nodeInfo.getNode();
            position = Position.RIGHT;
            while (resNode.getLeft() != null) {
                resParent = resNode;
                resNode = resNode.getLeft();
                position = Position.LEFT;
            }
        }
        return new NodeInfo(resParent, resNode, position);
    }

    /**
     * Поиск удаляемого узла в дереве по значению.
     *
     * @param root Ссылка на корневой узел дерева.
     * @param key  Значение узла дерева, которое требуется найти.
     * @return Ссылка на метаданные найденного узла дерева.
     */
    private static NodeInfo searchNode(Node root, int key) {
        Node parent = root;
        Node current = root;
        Position position = Position.ROOT;

        // Возращаем данный объект, если в дереве нет нужного элемента.
        NodeInfo nullObj = new NodeInfo(null, null, Position.NULL);

        while (true) {
            if (current.getValue() == key)
                return new NodeInfo(parent, current, position); // элемент нашелся.

            if ((current.getLeft() == null) && (current.getRight() == null)) // дошли до листа, но элемент не нашли
                return nullObj;

            parent = current;
            if (key < parent.getValue()) {
                if (parent.getLeft() == null)
                    return nullObj; // в дереве нет нужного элемента.
                else {
                    current = parent.getLeft();
                    position = Position.LEFT;
                }
            } else {
                if (parent.getRight() == null)
                    return nullObj; // в дереве нет нужного элемента.
                else {
                    current = parent.getRight();
                    position = Position.RIGHT;
                }
            }
        }
    }

    /**
     * Замена одного узла дерева другим.
     *
     * @param root          Ссылка на корневой узел дерева.
     * @param nodeToReplace Ссылка на метаданные удаляемого узла.
     * @param newNode       Ссылка на метаданные узла, которым замещается удаляемый узел.
     * @return Ссылка на корневой узел дерева, полученного после выполнения операции замены узлов.
     */
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

    /**
     * Трассировка операции удаления узла дерева. Используется для отладочного вывода.
     *
     * @param root Ссылка на корневой узел дерева.
     * @param key  Значение узла дерева, которое требуется найти.
     * @return Строковое представлени трассы операции.
     */
    public static String getTraceOfOperation(Node root, int key) {
        NodeInfo searchedNode = searchNode(root, key);
        NodeInfo replacementNode = searchForPossibleReplacement(searchedNode);

        return String.format(
                "Tracing key [%d]:\n\t-node found: %s,\n\t\t-possible replacement:\n\t\t\t-%s\n",
                key,
                searchedNode.toString(),
                replacementNode.toString()
        );
    }

    /**
     * -ОСНОВНАЯ ФУНКЦИЯ УДАЛЕНИЯ УЗЛА ДЕРЕВА-
     *
     * @param root Ссылка на корневой узел дерева.
     * @param key  Значение узла дерева, которое требуется найти.
     * @return Ссылка на корневой узел дерева, полученного после выполнения операции удаления.
     */
    public static Node remove(Node root, int key) {
        if (root == null) return null; // не обрабатываем пустые деревья.

        Node res = null;
        NodeInfo nodeToDelete = searchNode(root, key);
        if (nodeToDelete.getNode() == null) return root; //удалять нечего. дерево не изменилось.
        switch (nodeToDelete.getNumberOfSuccessors()) {
            case 0: {
                res = replaceNode(root, nodeToDelete, new NodeInfo(null, null, Position.NULL));
                break;
            }
            case 1: {
                NodeInfo replacementNode = searchForPossibleReplacement(nodeToDelete);
                res = replaceNode(root, nodeToDelete, replacementNode);
                break;
            }
            case 2: {
                NodeInfo replacementNode = searchForPossibleReplacement(nodeToDelete);

                Node rightChildOfDeletingNode = nodeToDelete.getNode().getRight();
                Node leftChildOfDeletingNode = nodeToDelete.getNode().getLeft();
                Node rightChildOfNewNode = replacementNode.getNode().getRight();

                Node possiblyNewRoot = replaceNode(root, nodeToDelete, replacementNode);

                // если newNode является прямым правым потомком nodeToDelete.
                if (rightChildOfDeletingNode == replacementNode.getNode()) {
                    replacementNode.getNode().setLeft(leftChildOfDeletingNode);
                } else {
                    // если между newNode и nodeToDelete существует путь.
                    replacementNode.getParent().setLeft(rightChildOfNewNode);
                    replacementNode.getNode().setLeft(leftChildOfDeletingNode);
                    replacementNode.getNode().setRight(rightChildOfDeletingNode);
                }

                res = possiblyNewRoot;
                break;
            }
        }

        return res;
    }
}

