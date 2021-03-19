package ProblemG;

/*

    [.............................]
            ^
            currentPos
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class StackMaxEffective {
    private int[] valueStorage;
    private int[] maxValuesStorage;
    private int[] maxValuesCounter;
    private int currentStackPos;
    private int currentMaxPos;


    StackMaxEffective(int size){
        valueStorage = new int[size];
        maxValuesStorage = new int[size];
        maxValuesCounter = new int[size];
        currentStackPos = -1;
        currentMaxPos = -1;
    }

    String push(int value){
        if (currentStackPos == valueStorage.length)
            return "error";

        currentStackPos++;
        valueStorage[currentStackPos] = value;

        // если список максимальных элементов пуст - добавляем первое значение
        if ((currentStackPos == 0)&&(currentMaxPos== -1)){
            currentMaxPos = 0;
            maxValuesStorage[currentMaxPos] = value;
            maxValuesCounter[currentMaxPos] = 1;
            return "";
        }

        // сравниваем добавляемое значение с текущим максимальным
        if (value == maxValuesStorage[currentMaxPos]){
            maxValuesCounter[currentMaxPos]++;
            return "";
        }

        if (value > maxValuesStorage[currentMaxPos]){
            currentMaxPos++;
            maxValuesStorage[currentMaxPos] = value;
            maxValuesCounter[currentMaxPos] = 1;
            return "";
        }
        return "";
    }

    String pop(){
        if (currentStackPos== -1) return "error";

        if (valueStorage[currentStackPos] == maxValuesStorage[currentMaxPos]){
            if (maxValuesCounter[currentMaxPos]>1)
                maxValuesCounter[currentMaxPos]--;
            else
                currentMaxPos--;
        }

        currentStackPos--;

        // приведение в состояние "ноль" на пустом стэке
        if (currentStackPos == -1) currentMaxPos = -1;
        return "";
    }

    String getMax(){
        if (currentStackPos==-1) return "None";
        return String.valueOf(maxValuesStorage[currentMaxPos]);
    }
}

class Commander{
    StackMaxEffective obj;
    Commander(StackMaxEffective stack){
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

public class SolutionG {
    static String processBatch (String[] input){
        int cmdCount = Integer.parseInt(input[0]);
        StackMaxEffective stack = new StackMaxEffective(cmdCount);
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
