package ProblemD;

class Node<V> {
    public V value;
    public Node<V> next;

    public Node(V value, Node<V> next) {
        this.value = value;
        this.next = next;
    }
}

public class Solution {

    public static int solution(Node<String> head, String elem){
        Node<String> node = head;
        int idx=0;
        while (node != null){
            if (node.value.equals(elem)) return idx;
            node = node.next;
            idx++;
        }
        return -1;
    }

    static void printList(Node<String> head){
        Node<String> currentNode = head;
        while (currentNode!= null){
            System.out.println(currentNode.value==null?"null":currentNode.value);
            currentNode = currentNode.next;
        }
    }

    public static void main(String[] args) {
        Node<String> list = new Node<>("0[start]", null);
        Node<String> prevNode = list;
        for (int i=1; i<6; i++){
            Node<String> currentNode = new Node<String>(String.format("Element {%d}", i), null);
            prevNode.next = currentNode;
            prevNode = currentNode;
        }
        printList(list);
        System.out.println("---");
        System.out.println(solution(list, "0[start]"));
        System.out.println(solution(list, "Element {3}"));
        System.out.println(solution(list, "Element {5}"));
        System.out.println(solution(list, "xyz"));
    }
}

