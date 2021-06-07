package FinalA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringJoiner;
import java.util.StringTokenizer;

/**
 * Класс инкапсулирует элементы множества участников соревнований
 */
class Participant implements Comparable<Participant> {
    private final String personalLogin;
    private final int scoreValue;
    private final int penaltyValue;

    /**
     * Конструктор элемента множества участников
     *
     * @param login   Имя учетной записи участника соревнований
     * @param score   Значение счета (количества решенных задач)
     * @param penalty Значение штрафа
     */
    Participant(String login, Integer score, Integer penalty) {
        personalLogin = login;
        scoreValue = score;
        penaltyValue = penalty;
    }

    /**
     * Возвращает имя учетной записи элемента
     *
     * @return Имя учетной записи.
     */
    String getLogin() {
        return personalLogin;
    }

    /**
     * Задает отношение (больше / меньше / равно) двух элементов множества
     *
     * @param other Ссылка на сравниваемый элемент
     * @return 0 - если элементы равны,
     * 1 - если сравниваемый элемент больше текущего,
     * (-1) - если сравниваемый элемент меньше текущего.
     */
    @Override
    public int compareTo(Participant other) {
        if (scoreValue != other.scoreValue)
            return scoreValue > other.scoreValue ? 1 : -1; // сортировка по убыванию

        if (penaltyValue != other.penaltyValue)
            return penaltyValue < other.penaltyValue ? 1 : -1; // сортировка по убыванию

        return -1 * personalLogin.compareTo(other.personalLogin); // сортировка по возрастанию
    }
}

class PriorityQueue implements Iterable<Participant> {
    private final Participant[] flatedHeapStorage;
    private int lastElementPointer;

    PriorityQueue(int queueSize) {
        flatedHeapStorage = new Participant[queueSize + 1];
        lastElementPointer = 0;
    }

    public void push(Participant value) {
        lastElementPointer++;
        flatedHeapStorage[lastElementPointer] = value;
        siftUp(lastElementPointer);
    }

    public Participant pop() {
        Participant res = flatedHeapStorage[1];
        flatedHeapStorage[1] = flatedHeapStorage[lastElementPointer];
        lastElementPointer--;
        siftDown(1);
        return res;
    }

    public int length() {
        return lastElementPointer;
    }

    private void swap(int idxOne, int idxTwo) {
        if (idxOne == idxTwo) return;
        Participant temp = flatedHeapStorage[idxOne];
        flatedHeapStorage[idxOne] = flatedHeapStorage[idxTwo];
        flatedHeapStorage[idxTwo] = temp;
    }

    private int getParentIdx(int idx) {
        return idx / 2;
    }

    private int getLeftChild(int headIdx) {
        int leftChildIdx = 2 * headIdx;
        return (leftChildIdx < lastElementPointer) ? leftChildIdx : -1;
    }

    private int getRightChild(int headIdx) {
        int rightChildIdx = headIdx * 2 + 1;
        return (rightChildIdx < lastElementPointer) ? rightChildIdx : -1;
    }

    private int getMaxChildIdx(int headIdx) {
        int leftChildIdx = getLeftChild(headIdx);
        int rightChildIdx = getRightChild(headIdx);

        if ((leftChildIdx == -1) && (rightChildIdx == -1)) return -1;

        if (leftChildIdx == -1) return rightChildIdx;
        if (rightChildIdx == -1) return leftChildIdx;

        return
                (flatedHeapStorage[leftChildIdx]
                        .compareTo(flatedHeapStorage[rightChildIdx]) > 0) ? leftChildIdx : rightChildIdx;
    }

    private int getIdxToSwap(int headIdx) {
        int maxChildIdx = getMaxChildIdx(headIdx);
        if (maxChildIdx == -1) return headIdx;
        return (flatedHeapStorage[headIdx].compareTo(flatedHeapStorage[maxChildIdx]) > 0) ? headIdx : maxChildIdx;
    }

    private void siftDown(int idx) {
        int idxToSwap = getIdxToSwap(idx);
        if (idxToSwap == idx) return;

        swap(idx, idxToSwap);
        siftDown(idxToSwap);
    }

    private void siftUp(int idx) {
        if (idx == 1) return;
        int parentNodeIdx = getParentIdx(idx);
        if (flatedHeapStorage[parentNodeIdx].compareTo(flatedHeapStorage[idx]) < 0) {
            swap(parentNodeIdx, idx);
            siftUp(parentNodeIdx);
        }
    }

    @Override
    public Iterator<Participant> iterator() {
        return new PrioryQueueIterator(this);
    }
}

class PrioryQueueIterator implements Iterator<Participant> {
    private final PriorityQueue obj;

    PrioryQueueIterator(PriorityQueue iterableObject) {
        obj = iterableObject;
    }

    @Override
    public boolean hasNext() {
        return obj.length() > 0;
    }

    @Override
    public Participant next() {
        return obj.pop();
    }
}

public class FinalSolutionA {
    public static String process(String[] input) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        PriorityQueue participants = new PriorityQueue(input.length);
        int numberOfRows = Integer.parseInt(input[0]);
        for (int i = 1; i <= numberOfRows; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input[i]);
            String login = tokenizer.nextToken();
            int score = Integer.parseInt(tokenizer.nextToken());
            int penalty = Integer.parseInt(tokenizer.nextToken());
            participants.push(new Participant(login, score, penalty));
        }

        for (Participant participant : participants) {
            stringJoiner.add(participant.getLogin());
        }

        return stringJoiner.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfRows = Integer.parseInt(reader.readLine());
        String[] buffer = new String[numberOfRows + 1];
        buffer[0] = String.valueOf(numberOfRows);
        for (int i = 0; i < numberOfRows; i++) {
            buffer[i] = reader.readLine();
        }
        System.out.println(process(buffer));
    }
}
