package ProblemI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
            header          tail
            |               |
        [....<.<.<.<.<.<.<.<..................]
 */
class Queue{
    private int header;
    private int tail;
    private int[] storage;

    Queue(int sizeOfQueue){
        storage = new int[sizeOfQueue];
        header = 0;
        tail = 0;
    }

    String push(int value){
        if (Math.abs(tail-header) == storage.length) return "error";
        storage[tail% storage.length] = value;
        tail++;
        return "";
    }

    String pop(){
        if (header == tail) return "None";
        int res = storage[header % storage.length];
        header++;
        return String.valueOf(res);

    }

    String peek(){
        if (header == tail) return "None";
        return String.valueOf(storage[header % storage.length]);
    }

    String size(){
        return String.valueOf(tail - header);
    }
}

public class SolutionI {
    public static String process(String[] input){
        Queue obj = new Queue(Integer.parseInt(input[1]));
        StringBuilder builder = new StringBuilder();

        for (int i=2; i<input.length; i++){
            StringTokenizer tokenizer = new StringTokenizer(input[i]);
            String cmdName = tokenizer.nextToken();
            String paramName = tokenizer.hasMoreTokens()? tokenizer.nextToken(): "";
            String res = "";

            switch (cmdName){
                case "push": res = obj.push(Integer.parseInt(paramName)); break;
                case "pop": res = obj.pop(); break;
                case "peek": res = obj.peek(); break;
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
        int maxSize =  Integer.parseInt(reader.readLine());
        String[] buffer = new String[cmdCount+2];
        buffer[0] = String.valueOf(cmdCount);
        buffer[1] = String.valueOf(maxSize);
        for (int i=2; i < cmdCount +2; i++){
            buffer[i] = reader.readLine();
        }
        System.out.println(process(buffer));
    }
}
