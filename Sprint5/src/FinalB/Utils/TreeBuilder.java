package FinalB.Utils;

import java.util.*;

/**
 * Класс-конструктор для создания бинарных деревьев поиска.
 */
public class TreeBuilder {
    private Node[] flattedStorage;
    private Set<Integer> markedNodes;
    private Node rootNode;
    private int treeSize;
    private int numberOfLevels;

    /**
     * Метод базовой (для всех конструкторов) инициализации внутренних свойств.
     *
     * @param root Ссылка на корневой узел дерева.
     * @param size Количество (целевое) узлов в дереве.
     */
    private void initBase(Node root, int size) {
        rootNode = root;
        treeSize = size;
        flattedStorage = new Node[treeSize + 1];
        flattedStorage[1] = rootNode;
        flattedStorage[0] = null;
        numberOfLevels = (int) (Math.log(treeSize) / Math.log(2)) + 1; // считаем с единицы
        markedNodes = new HashSet<>();
    }

    /**
     * Конструктор класса на базе существующего дерева.
     *
     * @param root   Ссылка на корневой узел дерева.
     * @param height Значение высоты дерева (количество уровней, начиная с 1-го)
     */
    public TreeBuilder(Node root, int height) {
        initBase(root, (int) Math.pow(2, height) - 1);

        Queue<Integer> posQueue = new LinkedList<>();
        Queue<Node> nodeQueue = new LinkedList<>();

        if (root != null) {
            nodeQueue.add(root);
            posQueue.add(1);
        }

        while (!nodeQueue.isEmpty()) {
            int headPos = posQueue.poll();

            Node currentHead = nodeQueue.poll();
            flattedStorage[headPos] = currentHead; //-- при обходе добавляем только головной элемент.

            Node possibleRight = currentHead.getRight();
            Node possibleLeft = currentHead.getLeft();
            if (possibleLeft != null) {
                int pos = headPos * 2;
                if (pos > flattedStorage.length - 1)
                    throw new ArrayIndexOutOfBoundsException("Actual tree is bigger, than was previously initialized");
                posQueue.add(pos);
                nodeQueue.add(possibleLeft);
            }
            if (possibleRight != null) {
                int pos = headPos * 2 + 1;
                if (pos > flattedStorage.length - 1)
                    throw new ArrayIndexOutOfBoundsException("Actual tree is bigger, than was previously initialized");
                posQueue.add(pos);
                nodeQueue.add(possibleRight);
            }
        }
    }

    /**
     * Конструктор на базе сериализованной структуры узлов дерева.
     *
     * @param values Упорядоченный массив значений узлов дерева.
     */
    public TreeBuilder(int[] values) {
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

    /**
     * Конструктур копирования
     *
     * @param other Ссылка на копируемый экземпляр класса.
     */
    public TreeBuilder(TreeBuilder other) {
        this(other.getFlattedTreeValues());
    }

    /**
     * Получение текушего значения высоты дерева.
     *
     * @return Высота дерева (количество уровней, начиная с 1-го)
     */
    public int getHeight() {
        return numberOfLevels;
    }

    /**
     * Получение номера левого подчиненного узла в упорядоченном (сериализованном) массиве.
     *
     * @param flattedTree Ссылка на сериализованные данные дерева.
     * @param head        Ссылка на текущий элемент.
     * @return Значение индекса левого элемента в упорядоченном (сериализованном) массиве.
     * В случает отсутствия элемента функция возвращает -1.
     */
    private int getLeftNodePosition(int[] flattedTree, int head) {
        int position = head * 2;
        return (position < flattedTree.length) ? position : -1;
    }

    /**
     * Получение номера правого подчиненного узла в упорядоченном (сериализованном) массиве.
     *
     * @param flattedTree Ссылка на сериализованные данные дерева.
     * @param head        Ссылка на текущий элемент.
     * @return Значение индекса правого элемента в упорядоченном (сериализованном) массиве.
     * В случает отсутствия элемента функция возвращает -1.
     */

    private int getRightNodePosition(int[] flattedTree, int head) {
        int position = head * 2 + 1;
        return (position < flattedTree.length) ? position : -1;
    }

    /**
     * Пометка узла дерева (логический признак).
     *
     * @param idx Индекс элемента упорядоченном (сериализованном) массиве.
     */
    public void markNode(int idx) {
        if ((idx < 1) && (idx > flattedStorage.length - 1))
            throw new IllegalArgumentException("Index is out of bounds");
        if (flattedStorage[idx] == null)
            throw new IllegalArgumentException("Trying to mark absent element");
        markedNodes.add(idx);
    }

    /**
     * Создание проекции (глубокой копии) внутреннего определения узлов и их связей в новое дерево.
     *
     * @return Ссылка на корневой узел полученного (спроецированного) дерева.
     */
    public Node projectToTree() {
        Node clonedRoot = new Node();
        clonedRoot.setValue(rootNode.getValue());

        Stack<Node> stackCurrentTree = new Stack<>();
        Stack<Node> stackClonedTree = new Stack<>();
        stackCurrentTree.push(rootNode);
        stackClonedTree.push(clonedRoot);
        while (!stackCurrentTree.empty()) {
            Node currentHead = stackCurrentTree.pop();
            Node clonedHead = stackClonedTree.pop();
            if (currentHead.getRight() != null) {
                Node rightNode = new Node();
                rightNode.setValue(currentHead.getRight().getValue());
                clonedHead.setRight(rightNode);
                stackCurrentTree.push(currentHead.getRight());
                stackClonedTree.push(rightNode);
            }
            if (currentHead.getLeft() != null) {
                Node leftNode = new Node();
                leftNode.setValue(currentHead.getLeft().getValue());
                clonedHead.setLeft(leftNode);
                stackCurrentTree.push(currentHead.getLeft());
                stackClonedTree.push(leftNode);
            }
        }
        return clonedRoot;
    }

    /**
     * Получение дерева в виде упорядоченного (сериализованного) массива значений его узлов.
     *
     * @return Упорядоченный массив значений узлов.
     */
    public int[] getFlattedTreeValues() {
        int lastNotNullPosition = this.flattedStorage.length - 1;
        while (this.flattedStorage[lastNotNullPosition] == null) {
            lastNotNullPosition--;
            if (lastNotNullPosition == 0) break;
        }

        int[] res = new int[lastNotNullPosition + 1];
        res[0] = -1;
        for (int i = 1; i <= lastNotNullPosition; i++)
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

    /**
     * Получение псевдографического изображения иерархии узлов дерева.
     *
     * @return Строковое представление (псевдографика) дерева.
     */
    public String getTreeHierarchyView() {
        final int TAB_TO_SPACES = 4; // количество пробелов в одной табуляции.
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

        List<String> res = new ArrayList<>();
        String templateRegular = "%d";
        String templateMarked = "%d*";

        int maxPaddingLeft = initialBias[0];
        int maxPaddingRight = 0;

        int currentLevel = 0;
        while (currentLevel < numberOfLevels) {
            int numberOfElementsInLine = (int) Math.pow(2, currentLevel);
            int levelStartIdx = numberOfElementsInLine;
            int possibleEndOfTheLine = numberOfElementsInLine * 2 - 1;
            int levelEndIdx = Math.min(possibleEndOfTheLine, nodesCount);

            String initialBiasStr = "\t".repeat(initialBias[currentLevel]);
            String intervalBiasStr = "\t".repeat(intervalBias[currentLevel]);

            StringJoiner lineAssembler = new StringJoiner(intervalBiasStr, initialBiasStr, "");
            StringJoiner subLineAssembler = new StringJoiner(intervalBiasStr, initialBiasStr, "");

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


            String nodesStr = lineAssembler.toString();
            String subNodesStr = subLineAssembler.toString();

            //---trimming right
            res.add(nodesStr.substring(0, nodesStr.length() - paddingRight));
            if (currentLevel < numberOfLevels - 1)
                res.add(subNodesStr.substring(0, subNodesStr.length() - paddingRight));

            currentLevel++;
        }

        //--trimming left
        for (int i = 0; i < res.size(); i++) {
            String s = res.get(i);
            res.set(i, s.substring(maxPaddingLeft, s.length()));
        }
        return String.join("\n", res);
    }

    /**
     * Оператор проверки эквивалентности (равно/не равно) двух объектов TreeBuilder.
     *
     * @param o Ссылка на сравниваемый объект.
     * @return Истина/Ложь.
     */
    @Override
    public boolean equals(Object o) {
        TreeBuilder other = (TreeBuilder) o;

        int[] signatureThis = this.getFlattedTreeValues();
        int[] signatureOther = other.getFlattedTreeValues();

        return Arrays.equals(signatureThis, signatureOther);
    }

}
