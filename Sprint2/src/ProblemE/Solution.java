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

    public static Node<String> solution(Node<String> head){
        Node<String> list = head;
        while (list != null){
            Node<String> nextNode = 
        }
    }

    static void printList(Node<String> head){
        Node<String> currentNode = head;
        while (currentNode!= null){
            System.out.println(currentNode.value==null?"null":currentNode.value);
            currentNode = currentNode.next;
        }
    }

    public static void main(String[] args) {
        Node<String> list = new Node<>("0[start]", null, null);
        Node<String> prevNode = list;
        for (int i=1; i<6; i++){
            Node<String> currentNode = new Node<String>(String.format("Element {%d}", i), null, null);
            prevNode.next = currentNode;
            currentNode.prev = prevNode;
            prevNode = currentNode;
        }
        printList(list);
    }
}

