package ProblemJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class QueueNode{
    private int valueStorage;
    private QueueNode next;
    QueueNode(int value){
        valueStorage = value;
        next = null;
    }

    void setNext(QueueNode node){ next = node; }

    QueueNode getNext(){return next;}

    int getValue(){return  valueStorage;}
}

class LinkedQueue{
    private QueueNode head;
    private QueueNode tail;
    private int size;
    LinkedQueue(){
        head = null;
        tail = null;
        size = 0;
    }

    String put(QueueNode node){
        if (tail != null) {
            tail.setNext(node);
        }

        tail = node;
        if (head == null) head = node;
        size++;
        return "";
    }

    String get(){
        if (head == null) return "error";
        int res = head.getValue();
        head = head.getNext();
        size--;
        return String.valueOf(res);
    }

    String size() {return String.valueOf(size);}
}

public class SolutionJ {
    public static String process(String[] input){
        LinkedQueue obj = new LinkedQueue();
        StringBuilder builder = new StringBuilder();

        for (int i=1; i<input.length; i++){
            StringTokenizer tokenizer = new StringTokenizer(input[i]);
            String cmdName = tokenizer.nextToken();
            String paramName = tokenizer.hasMoreTokens()? tokenizer.nextToken(): "";
            String res = "";

            switch (cmdName){
                case "put":  res = obj.put(new QueueNode(Integer.parseInt(paramName))); break;
                case "get":  res = obj.get(); break;
                case "size": res = obj.size(); break;
            }

            if (!res.isEmpty()){
                builder.append(res);
                builder.append("\n");
            }

        }
        return builder.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int cmdCount = Integer.parseInt(reader.readLine());

        String[] buffer = new String[cmdCount+1];
        buffer[0] = String.valueOf(cmdCount);

        for (int i=1; i < cmdCount +1; i++){
            buffer[i] = reader.readLine();
        }
        System.out.println(process(buffer));
    }
}
