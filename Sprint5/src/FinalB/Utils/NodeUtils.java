package FinalB.Utils;

public class NodeUtils {
    /**
     * Преобразует узел дерева в строку по следующему шаблону:
     * Node(Value = v, Left = l, Right=r)
     * @param node Ссылка на узел, по которому нужно построить строковое представление
     * @return Строчное представление узла
     */
    public static String nodeToStr(Node node){
        if (node == null) return "Node(null)";
        Node leftChild = node.getLeft();
        Node rightChild = node.getRight();
        String leftStr = leftChild==null?"null":String.valueOf(leftChild.getValue());
        String rightStr = rightChild==null?"null": String.valueOf(rightChild.getValue());
        return String.format("Node(V=%s, L=%s, R=%s)", String.valueOf(node.getValue()), leftStr, rightStr);
    }
}
