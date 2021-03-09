package ProblemC;

class Node<V> {
    public V value;
    public Node<V> next;

    public Node(V value, Node<V> next) {
        this.value = value;
        this.next = next;
    }
}

public class Solution {

    public static Node<String> solution(Node<String> head, int idx){
        if (idx==0){
            return head.next;
        }

        Node<String> node = head;
        for (int i=0; i<idx-1; i++){
            if (node == null) break;
            node = node.next;
        }
        node.next = node.next.next;
        return head;
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
            Node<String> currentNode = new Node<String>(String.valueOf(i), null);
            prevNode.next = currentNode;
            prevNode = currentNode;
        }
        list = solution(list, 0);
        list =   solution(list, 3);
        list =   solution(list, 1);
        list =   solution(list, 2);
        printList(list);
        System.out.println("---");
    }
}

