package FinalA;

import java.util.StringJoiner;

/**
 * Класс инкапсулирует элементы множества участников соревнований
 */
class Participant implements Comparable<Participant> {
    private String personalLogin;
    private Integer scoreValue;
    private Integer penaltyValue;

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
     * @return
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
        if (!scoreValue.equals(other.scoreValue))
            return scoreValue > other.scoreValue ? -1 : 1; // сортировка по убыванию

        if (!penaltyValue.equals(other.penaltyValue))
            return penaltyValue < other.penaltyValue ? -1 : 1; // сортировка по убыванию

        return personalLogin.compareTo(other.personalLogin); // сортировка по возрастанию
    }
}

class PriorityQueue {
    private int[] flatedHeapStorage;
    private int dataFillPointer;

    PriorityQueue(int queueSize) {
        flatedHeapStorage = new int[queueSize + 1];
        dataFillPointer = 0;
    }

    public void push(int value) {
    // to do...
    }

    public int pop() {
    // to do...
        return 0;
    }

    private static void swap(int[] heap, int idxOne, int idxTwo) {
        if (idxOne == idxTwo) return;
        int temp = heap[idxOne];
        heap[idxOne] = heap[idxTwo];
        heap[idxTwo] = temp;
    }

    private static int getParentIdx(int idx) {
        return idx / 2;
    }

    private static int getLeftChild(int[] heap, int headIdx) {
        int leftChildIdx = 2 * headIdx;
        return (leftChildIdx < heap.length) ? leftChildIdx : -1;
    }

    private static int getRightChild(int[] heap, int headIdx) {
        int rightChildIdx = headIdx * 2 + 1;
        return (rightChildIdx < heap.length) ? rightChildIdx : -1;
    }

    private static int getMaxChildIdx(int[] heap, int headIdx) {
        int leftChildIdx = getLeftChild(heap, headIdx);
        int rightChildIdx = getRightChild(heap, headIdx);

        if (leftChildIdx == -1) return rightChildIdx;
        if (rightChildIdx == -1) return leftChildIdx;
        if ((leftChildIdx == -1) && (rightChildIdx == -1)) return -1;

        return (heap[leftChildIdx] > heap[rightChildIdx]) ? leftChildIdx : rightChildIdx;
    }

    private static int getIdxToSwap(int[] heap, int headIdx) {
        int maxChildIdx = getMaxChildIdx(heap, headIdx);
        if (maxChildIdx == -1) return headIdx;
        return (heap[headIdx] > heap[maxChildIdx]) ? headIdx : maxChildIdx;
    }

    public static int siftDown(int[] heap, int idx) {
        int idxToSwap = getIdxToSwap(heap, idx);
        int res;
        if (idxToSwap == idx)
            return idx;
        else {
            swap(heap, idx, idxToSwap);
            res = siftDown(heap, idxToSwap);
        }
        return res;
    }

    public static int siftUp(int[] heap, int idx) {
        if (idx == 1) return idx;
        int parentNodeIdx = getParentIdx(idx);
        if (heap[parentNodeIdx] < heap[idx]) {
            swap(heap, parentNodeIdx, idx);
            return siftUp(heap, parentNodeIdx);
        }
        return idx;
    }

}

/**
 * Класс инкапсулирует функционал турнирной таблицы
 */
class ScoreTable {
    private Participant[] scoresStorage;
    private int stackHeadPointer;

    /**
     * Конструктор экземпляра турнирной таблицы
     *
     * @param numberOfParticipants
     */
    ScoreTable(int numberOfParticipants) {
        scoresStorage = new Participant[numberOfParticipants];
        stackHeadPointer = -1;
    }

    /**
     * Добавление записи участника в турнирную таблицу
     *
     * @param participant Ссылка на экземпляр участника соревнований
     */
    public void appendParticipant(Participant participant) {
        stackHeadPointer++;
        scoresStorage[stackHeadPointer] = participant;
    }

    /**
     * Вывод упорядоченных (начиная с первого места) значений турнирной таблицы
     *
     * @return Текстовое представление турнирной таблцы (каждый участник на отдельной строке)
     */
    public String printList() {
        if (stackHeadPointer == -1) return "";

        StringJoiner joiner = new StringJoiner("\n", "", "");
        for (int i = 0; i <= stackHeadPointer; i++)
            joiner.add(scoresStorage[i].getLogin());

        return joiner.toString();
    }

    /**
     * Перестановка двух элементов множества участников
     *
     * @param firstIdx  Индекс первого элемента
     * @param secondIdx Индекс второго элемента
     */
    private void swap(int firstIdx, int secondIdx) {
        if (firstIdx == secondIdx) return;

        Participant temp = scoresStorage[firstIdx];
        scoresStorage[firstIdx] = scoresStorage[secondIdx];
        scoresStorage[secondIdx] = temp;
    }


}


public class FinalSolutionA {
}
