package ProblemE;

class Node<V> {
    public V value;
    public Node<V> next;
    public Node<V> prev;

    public Node(V value, Node<V> next, Node<V> prev) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }
}

public class Solution {

    public static Node<String> solution(Node<String> head) {
        Node<String> tail = head;
        Node<String> currentNode = null;
        Node<String> newHead = null;

        while (tail.next != null) {
            currentNode = tail.next;

            // выкусываем узел, следующий после tail
            if (currentNode.next != null) {
                tail.next = currentNode.next; // ставим прямую ссылку
                currentNode.next.prev = tail; // ставим обратную ссылку
            } else {
                tail.next = null;
            }

            // отрабатываем начальное условие первого шага
            if (newHead == null) {
                newHead = currentNode;
                newHead.next = tail;
                newHead.prev = null;
                tail.prev = newHead;
            } else {
                currentNode.next = newHead;
                newHead.prev = currentNode;
                newHead = currentNode;
                newHead.prev = null;
            }
        }

        return newHead;
    }

    static void printList(Node<String> head) {
        Node<String> currentNode = head;
        while (currentNode != null) {
            System.out.println(currentNode.value == null ? "null" : currentNode.value);
            currentNode = currentNode.next;
        }
    }

    public static void main(String[] args) {
        Node<String> list = new Node<>("0[start]", null, null);
        Node<String> prevNode = list;
        for (int i = 1; i < 6; i++) {
            Node<String> currentNode = new Node<String>(String.format("Element {%d}", i), null, null);
            prevNode.next = currentNode;
            currentNode.prev = prevNode;
            prevNode = currentNode;
        }
        printList(list);
        System.out.println("----");
        Node<String> reverse = solution(list);
        printList(reverse);
    }
}

