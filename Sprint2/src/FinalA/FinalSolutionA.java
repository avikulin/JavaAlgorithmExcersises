package FinalA; // эту строку нужно закомментировать перед отправкой в Яндекс.Контест

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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

/* Базовые интерфейсы функционального полиморфизма, используемого интерпретатором команд */

interface IBaseFunction {
}

@FunctionalInterface
interface IConsumerFunction<T> extends IBaseFunction {
    void invoke(T param) throws WriteOverflowException;
}

@FunctionalInterface
interface IProducerFunction<T> extends IBaseFunction {
    T invoke() throws ReadEmptyQueueException;
}

/* Глобальные параметры  */
enum FuncType {CONSUMER, PRODUCER}

class GlobalSettings {
    public static final String CMD_PUSH_FRONT = "push_front";
    public static final String CMD_POP_FRONT = "pop_front";
    public static final String CMD_PUSH_BACK = "push_back";
    public static final String CMD_POP_BACK = "pop_back";
}

/* Рализация основных классов интерпретатора команд */
class CommandFunctor<T> {
    private final FuncType type;
    private final IBaseFunction reference;

    CommandFunctor(FuncType funcType, IBaseFunction funcReference) {
        type = funcType;
        reference = funcReference;
    }

    FuncType getType() {
        return type;
    }

    IProducerFunction<T> asProducer() {
        if (type != FuncType.PRODUCER) {
            throw new ClassCastException("invalid casting the function as a producer");
        }
        return (IProducerFunction<T>) reference;
    }

    IConsumerFunction<T> asConsumer() {
        if (type != FuncType.CONSUMER) {
            throw new ClassCastException("invalid casting as consumer function");
        }
        return (IConsumerFunction<T>) reference;
    }
}

class CommandMapper<T> {
    private final IConsumerFunction<T> refPushFront;
    private final IConsumerFunction<T> refPushBack;
    private final IProducerFunction<T> refPopFront;
    private final IProducerFunction<T> refPopBack;

    CommandMapper(IConsumerFunction<T> funcPushFront,
                  IConsumerFunction<T> funcRefPushBack,
                  IProducerFunction<T> funcPopFront,
                  IProducerFunction<T> funcRefPopBack) {
        refPushFront = funcPushFront;
        refPushBack = funcRefPushBack;
        refPopFront = funcPopFront;
        refPopBack = funcRefPopBack;
    }

    public HashMap<String, CommandFunctor<T>> BuildMapping() {
        HashMap<String, CommandFunctor<T>> res = new HashMap<>();
        res.put(GlobalSettings.CMD_PUSH_FRONT, new CommandFunctor<>(FuncType.CONSUMER, refPushFront));
        res.put(GlobalSettings.CMD_PUSH_BACK, new CommandFunctor<>(FuncType.CONSUMER, refPushBack));
        res.put(GlobalSettings.CMD_POP_FRONT, new CommandFunctor<>(FuncType.PRODUCER, refPopFront));
        res.put(GlobalSettings.CMD_POP_BACK, new CommandFunctor<>(FuncType.PRODUCER, refPopBack));
        return res;
    }
}

class Commander {
    private final HashMap<String, CommandFunctor<Integer>> mapping;

    Commander(CommandMapper<Integer> mapper) {
        mapping = mapper.BuildMapping();
    }

    public String Interpret(String commandWithParameter) {
        StringTokenizer tokenizer = new StringTokenizer(commandWithParameter);
        String commandName = tokenizer.nextToken();

        CommandFunctor<Integer> functor = mapping.get(commandName);
        StringBuilder stringBuilder = new StringBuilder();

        if (functor.getType() == FuncType.CONSUMER) {
            try {
                int commandValue = Integer.parseInt(tokenizer.nextToken());
                functor.asConsumer().invoke(commandValue);
            } catch (WriteOverflowException ex) {
                stringBuilder.append("error");
            }
        }

        if (functor.getType() == FuncType.PRODUCER) {
            try {
                stringBuilder.append(functor.asProducer().invoke());
            } catch (ReadEmptyQueueException ex) {
                stringBuilder.append("error");
            }
        }
        return stringBuilder.toString();
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

    public static List<String> ProcessCommands(String[] commandsToProcess) {
        int dequeueSize = Integer.parseInt(commandsToProcess[1]);
        Deque deque = new Deque(dequeueSize);
        CommandMapper<Integer> mapper = new CommandMapper<>(deque::pushFront,
                                                            deque::pushBack,
                                                            deque::popFront,
                                                            deque::popBack);

        Commander cmd = new Commander(mapper);
        List<String> outBuffer = new ArrayList<>();
        for (int i = 2; i < commandsToProcess.length; i++) {
            String cmdResult = cmd.Interpret(commandsToProcess[i]);
            if (!cmdResult.isEmpty()) {
                outBuffer.add(cmdResult);
            }
        }
        return outBuffer;
    }

    public static void main(String[] args) throws IOException {
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
