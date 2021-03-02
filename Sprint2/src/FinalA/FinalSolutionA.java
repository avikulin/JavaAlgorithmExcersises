package FinalA; // эту строку нужно закомментировать перед отправкой в Яндекс.Контест

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidAlgorithmParameterException;
import java.util.*;

/* Специализированные функциональные интерфейсы с поддержкой исключений, необходимых для бизнес-логики интерпретатора */
interface IConsumerFunction{
    void accept(Integer value) throws WriteOverflowException;
}

interface ISupplierFunction{
    Integer get() throws ReadEmptyQueueException;
}

/* Специализированные классы исключений, которые используются в бизнес-логике */

class WriteOverflowException extends Exception {
    WriteOverflowException(String errorMessage) {
        super(errorMessage);
    }
}

class ReadEmptyQueueException extends Exception {
    ReadEmptyQueueException(String errorMessage) {
        super(errorMessage);
    }
}

/* Глобальные параметры  */
enum FuncType {CONSUMER, SUPPLIER}
enum AccessType{FRONT, BACK}

class GlobalSettings {
    public static final String CMD_PUSH_FRONT = "push_front";
    public static final String CMD_POP_FRONT = "pop_front";
    public static final String CMD_PUSH_BACK = "push_back";
    public static final String CMD_POP_BACK = "pop_back";
}

/* Рализация основных классов интерпретатора команд */
class CommandFunctor {
    private IConsumerFunction refPushFrontCommand;
    private IConsumerFunction refPushBackCommand;
    private ISupplierFunction refPopFrontCommand;
    private ISupplierFunction refPopBackCommand;
    private final FuncType type;
    private AccessType direction;

    CommandFunctor(IConsumerFunction refCmdFunction, AccessType accessDirection) {
        type = FuncType.CONSUMER;
        direction = accessDirection;
        if (direction == AccessType.FRONT){
            refPushFrontCommand = refCmdFunction;
        } else {
            refPushBackCommand = refCmdFunction;
        }
    }

    CommandFunctor(ISupplierFunction refCmdFunction, AccessType accessDirection) {
        type = FuncType.SUPPLIER;
        direction = accessDirection;
        if (direction == AccessType.FRONT){
            refPopFrontCommand = refCmdFunction;
        } else {
            refPopBackCommand = refCmdFunction;
        }
    }

    FuncType getType() {
        return type;
    }

    AccessType getDirection() {
        return direction;
    }

    public String invokeAsConsumer(Integer inputValue) throws InvalidAlgorithmParameterException {
        if (type != FuncType.CONSUMER)
            throw new InvalidAlgorithmParameterException("Functor can't operate as consumer");

        try {
            if (direction == AccessType.FRONT){
                refPushFrontCommand.accept(inputValue);
            } else {
                refPushBackCommand.accept(inputValue);
            }
        } catch (WriteOverflowException ex) {
            return "error";
        }
        return new String();
    }

    public String invokeAsSupplier() throws InvalidAlgorithmParameterException {
        if (type != FuncType.SUPPLIER)
            throw new InvalidAlgorithmParameterException("Functor can't operate as consumer");
        Integer res;
        try {
            if (direction == AccessType.FRONT){
               res = refPopFrontCommand.get();
            } else {
                res = refPopBackCommand.get();
            }
        } catch (ReadEmptyQueueException ex) {
            return "error";
        }
        return res.toString();
    }
}

class Commander {
    private final HashMap<String, CommandFunctor> mapping;

    Commander(CommandFunctor funcPushFront, CommandFunctor funcRefPushBack,
              CommandFunctor funcPopFront, CommandFunctor funcRefPopBack) {
        mapping = new HashMap<>();
        mapping.put(GlobalSettings.CMD_PUSH_FRONT, funcPushFront);
        mapping.put(GlobalSettings.CMD_PUSH_BACK, funcRefPushBack);
        mapping.put(GlobalSettings.CMD_POP_FRONT, funcPopFront);
        mapping.put(GlobalSettings.CMD_POP_BACK, funcRefPopBack);
    }

    public String Interpret(String commandName, Integer parameterValue) throws InvalidAlgorithmParameterException {
        CommandFunctor functor = mapping.get(commandName);
        if (functor.getType() == FuncType.CONSUMER) {
            return functor.invokeAsConsumer(parameterValue);
        } else {
            return functor.invokeAsSupplier();
        }
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

    public void pushBack(Integer value) throws WriteOverflowException {
        int writePosition = tailPointer;
        if (fillCounter > 0)
            writePosition = normalizePosition(++writePosition);
        if (fillCounter == storageSize)
            throw new WriteOverflowException("Attempting to write beyond the bounds of the storage area");
        storage[writePosition] = value;
        tailPointer = writePosition;
        fillCounter++;
    }

    public void pushFront(Integer value) throws WriteOverflowException {
        int writePosition = headPointer;
        if (fillCounter > 0)
            writePosition = normalizePosition(--writePosition);
        if (fillCounter == storageSize)
            throw new WriteOverflowException("Attempting to write beyond the bounds of the storage area");
        storage[writePosition] = value;
        headPointer = writePosition;
        fillCounter++;
    }

    public Integer popBack() throws ReadEmptyQueueException {
        int readPosition = tailPointer;
        if (isEmpty()) throw new ReadEmptyQueueException("Attempting to read from empty storage");
        Integer value = storage[readPosition];
        tailPointer = normalizePosition(--readPosition);
        --fillCounter;
        return value;
    }

    public Integer popFront() throws ReadEmptyQueueException {
        int readPosition = headPointer;
        if (isEmpty()) throw new ReadEmptyQueueException("Attempting to read from empty storage");
        Integer value = storage[readPosition];
        headPointer = normalizePosition(++readPosition);
        --fillCounter;
        return value;
    }
}


/* Управляющий класс, реализующий запуск и выполнение бизнес-логики задания */
public class FinalSolutionA {

    public static List<String> ProcessCommands(String[] commandsToProcess)  {
        int dequeueSize = Integer.parseInt(commandsToProcess[1]);
        Deque deque = new Deque(dequeueSize);

        Commander cmd = new Commander(new CommandFunctor(deque::pushFront, AccessType.FRONT),
                                      new CommandFunctor(deque::pushBack, AccessType.BACK),
                                      new CommandFunctor(deque::popFront, AccessType.FRONT),
                                      new CommandFunctor(deque::popBack, AccessType.BACK));
        List<String> outBuffer = new ArrayList<>();
        for (int i = 2; i < commandsToProcess.length; i++) {
            String[] commandTokens = commandsToProcess[i].split(" ");
            String commandName = commandTokens[0];
            Integer commandValue = commandTokens.length > 1? Integer.parseInt(commandTokens[1]):0;
            try{
                String cmdResult = cmd.Interpret(commandName, commandValue);
                if (!cmdResult.isEmpty()) {
                    outBuffer.add(cmdResult);
                }
            }catch (InvalidAlgorithmParameterException ex){
                // Можно было бы сделать обработчик, но его не к чему ни прикрутить.
            }
        }
        return outBuffer;
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

        List<String> res = ProcessCommands(inputDataSet);
        System.out.println(String.join("\n", res));
    }
}
