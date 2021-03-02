package FinalA; // эту строку нужно закомментировать перед отправкой в Яндекс.Контест

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidAlgorithmParameterException;
import java.util.*;
import java.util.function.Function;


class GlobalSettings {
    public static final String CMD_PUSH_FRONT = "push_front";
    public static final String CMD_POP_FRONT = "pop_front";
    public static final String CMD_PUSH_BACK = "push_back";
    public static final String CMD_POP_BACK = "pop_back";
    public static final String ERR_MESSAGE = "error";
}


class Commander {
    private final HashMap<String, Function<Integer, String>> mapping;

    Commander(Function<Integer, String> funcPushFront, Function<Integer, String> funcRefPushBack,
              Function<Integer, String> funcPopFront, Function<Integer, String> funcRefPopBack) {
        mapping = new HashMap<>();
        mapping.put(GlobalSettings.CMD_PUSH_FRONT, funcPushFront);
        mapping.put(GlobalSettings.CMD_PUSH_BACK, funcRefPushBack);
        mapping.put(GlobalSettings.CMD_POP_FRONT, funcPopFront);
        mapping.put(GlobalSettings.CMD_POP_BACK, funcRefPopBack);
    }

    public String Interpret(String commandName, Integer parameterValue){
        Function<Integer, String> funcRef = mapping.get(commandName);
        return funcRef.apply(parameterValue);
    }
}

/* Класс бизнес-логики дэка */
class Deque {
    /*
        pushFront           pushBack
        popFront            popBack
          |                     |
        head->[0...........N]<-tail
     */
    private int storageSize;
    private int fillCounter;
    private final Integer[] storage;
    private int headPointer;
    private int tailPointer;

    public Deque(int size) {
        storageSize = size;
        storage = new Integer[size];
        headPointer = 0;
        tailPointer = 0;
        fillCounter = 0;
    }

    private int normalizePosition(Integer position){
        if (position < 0) return storageSize-1;
        if (position > storageSize - 1) return 0;
        return position;
    }

    public boolean isEmpty(){return fillCounter == 0;}

    public String pushBack(Integer value){
        int writePosition = tailPointer;
        if (fillCounter > 0)
            writePosition = normalizePosition(++writePosition);
        if (fillCounter == storageSize)
            return GlobalSettings.ERR_MESSAGE;
        storage[writePosition] = value;
        tailPointer = writePosition;
        fillCounter++;
        return new String();
    }

    public String pushFront(Integer value){
        int writePosition = headPointer;
        if (fillCounter > 0)
            writePosition = normalizePosition(--writePosition);
        if (fillCounter == storageSize)
            return GlobalSettings.ERR_MESSAGE;
        storage[writePosition] = value;
        headPointer = writePosition;
        fillCounter++;
        return new String();
    }

    public String popBack(Integer emptyParam){
        int readPosition = tailPointer;
        if (isEmpty()) return GlobalSettings.ERR_MESSAGE;
        Integer value = storage[readPosition];
        tailPointer = normalizePosition(--readPosition);
        --fillCounter;
        return value.toString();
    }

    public String popFront(Integer emptyParam){
        int readPosition = headPointer;
        if (isEmpty()) return GlobalSettings.ERR_MESSAGE;
        Integer value = storage[readPosition];
        headPointer = normalizePosition(++readPosition);
        --fillCounter;
        return value.toString();
    }
}


/* Управляющий класс, реализующий запуск и выполнение бизнес-логики задания */
public class FinalSolutionA {

    public static String ProcessCommands(String[] commandsToProcess)  {
        int dequeueSize = Integer.parseInt(commandsToProcess[1]);
        Deque deque = new Deque(dequeueSize);

        Commander cmd = new Commander(deque::pushFront, deque::pushBack, deque::popFront, deque::popBack);
        StringBuilder outLog = new StringBuilder();

        for (int i = 2; i < commandsToProcess.length; i++) {
            String[] commandTokens = commandsToProcess[i].split(" ");
            String commandName = commandTokens[0];
            Integer commandValue = commandTokens.length > 1? Integer.parseInt(commandTokens[1]):0;

            String cmdResult = cmd.Interpret(commandName, commandValue);
            if (!cmdResult.isEmpty()) {
                outLog.append(cmdResult);
                outLog.append("\n");
            }

        }
        return outLog.toString();
    }

    public static void main(String[] args) throws IOException, InvalidAlgorithmParameterException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstString = reader.readLine();
        int numberOfCommands = Integer.parseInt(firstString);
        String[] inputDataSet = new String[numberOfCommands + 2];
        inputDataSet[0] = firstString;

        for (int i = 1; i < numberOfCommands + 2; i++) {
            inputDataSet[i] = reader.readLine();
        }

        System.out.println(ProcessCommands(inputDataSet));
    }
}
