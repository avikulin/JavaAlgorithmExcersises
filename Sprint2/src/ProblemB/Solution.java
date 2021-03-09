package ProblemB;

class Node<V> {
    public V value;
    public Node<V> next;

    public Node(V value, Node<V> next) {
        this.value = value;
        this.next = next;
    }
}

public class Solution {
    public static void solution(Node<String> head){
        Node<String> currentNode = head;
        while (currentNode!= null){
            System.out.println(currentNode.value);
            currentNode = currentNode.next;
        }
    }

    public static void main(String[] args) {
        Node<String> list = new Node<String>("start", null);
        Node<String> prevNode = list;
        for (int i=0; i<15; i++){
            Node<String> currentNode = new Node<String>(String.valueOf(i), null);
            prevNode.next = currentNode;
            prevNode = currentNode;
        }
        solution(list);
        System.out.println("---");
    }
}

