package ProblemF;

/*

    [.............................]
            ^
            currentPos
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class StackMax{
    private int[] storage;
    private int currentPos;
    private int stackLength;

    StackMax(int size){
        storage = new int[size];
        currentPos = 0;
        stackLength = 0;
    }

    String push(int value){
        if (stackLength == storage.length)
            return "error";

        if (stackLength != 0) currentPos++;
        stackLength++;
        storage[currentPos] = value;
        return "";
    }

    String pop(){
        if (stackLength == 0) return "error";
        stackLength--;
        if (currentPos > 0) currentPos--;
        return "";
    }

    String getMax(){
        if (stackLength==0) return "None";
        int res = storage[0];
        if (stackLength==1) return String.valueOf(storage[0]);

        for (int i=1; i<currentPos+1; i++){
            if (storage[i]>res) res = storage[i];
        }
        return String.valueOf(res);
    }
}
class Commander{
    StackMax obj;
    Commander(StackMax stack){
        obj = stack;
    }

    String interpret(String command){
        StringTokenizer tokenizer = new StringTokenizer(command);
        String cmdName = tokenizer.nextToken();
        String paramValue = tokenizer.hasMoreTokens()? tokenizer.nextToken():"";

        switch (cmdName){
            case "push": return obj.push(Integer.parseInt(paramValue));
            case "pop": return obj.pop();
            case "get_max": return obj.getMax();
        }
        return "";
    }
}

public class SolutionF {
    static String processBatch (String[] input){
        int cmdCount = Integer.parseInt(input[0]);
        StackMax stack = new StackMax(cmdCount);
        Commander cmd = new Commander(stack);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i=1; i< input.length; i++){
            String res = cmd.interpret(input[i]);
            if (res != "") {
                stringBuilder.append(res);
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int cmdCount = Integer.parseInt(reader.readLine());
        String[] cmdBuffer = new String[cmdCount+1];
        cmdBuffer[0] = String.valueOf(cmdCount);
        for (int i=1; i<cmdCount+1; i++){
            cmdBuffer[i] = reader.readLine();
        }
        System.out.println(processBatch(cmdBuffer));
    }
}
