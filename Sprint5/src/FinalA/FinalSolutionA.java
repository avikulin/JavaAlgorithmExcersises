//---код посылки в Яндекс.Контест - 51928434

package FinalA;// эту строку нужно закомментировать перед отправкой в Яндекс.Контест.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringJoiner;
import java.util.StringTokenizer;
/*
        ОПИСАНИЕ РЕШЕНИЯ

        Решение состоит из следующих основных частей:

        Класс <Participant>, описывающий участника соревнования. Класс обеспечивает хранение информации об
        участнике (personalLogin, scoreValue, penaltyValue), а также реализует операцию сравнения двух элементов
        множества (compareTo).

        Класс <PriorityQueue>, инкапсулирующий в себе функционал пирамидального хранения с сортировки сведений об
        участниках соревнований (экземпляры класса <Participant>). Для работы с данным класс предоставляет методы
        помещения (push) и извлечения значения (pop) из сортированной кучи, а также предоставляет внешний интерфейс
        последовательного чтения (реализация Iterable<Participant>). Кроме этого, для работы с кучей класс включает ряд
        частных методов и функций, обеспечивающих упорядочивание кучи после каждой мутации:
            - Просеивание вниз (siftDown)
            - Просеивание вверх (siftUp)

        Класс <PrioryQueueIterator>, выполняющий функции адаптера последовательного чтения из упорядоченной
        коллекции  <PriorityQueue>. Класс реализует интерфейс Iterator<Participant>. Все функции реализуют асимпотику
        О(1) как по времени, так и по пространству. Таким образом использования вспомогательного класса
        <PrioryQueueIterator> не меняет асимптотики работы методом основного класса <PriorityQueue>.

        ОПИСАНИЕ АЛГОРИТМОВ

        1) Алгоритм просеивание вниз (siftDown). Алгоритм реализован с использование следующих вложенных под-алгоритмов:

        # Вычисление левого потомка (getLeftChild). Поскольку куча представлена в виде массива, то
        операция вычисляется за O(1) по времени и O(1), так как функция не имеет собственного состояния.

        Вычисление правого потомка (getRightChild). Поскольку куча представлена в виде массива, то
        операция вычисляется за O(1) по времени и O(1), так как функция не имеет собственного состояния.

        # Вычисление наибольшего потомка (getMaxChildIdx).
         Алгоритм выглядит в виде следующей последовательности действий:
         а) вычислить левого потомка.
         б) вычислить правого потомка.
         в) если нет ни левого, ни правого потомка - значит это листовой элемент, и наибольшего потомка не существует.
         г) если есть только правый потомок - значит он наибольший из имеющизся.
         д) если есть только левый потомок - значит он наимбольший из имеющихся.
         е) если есть есть оба потомка, то наибольший из них тот, которому функция compareTo(...) вернула
           наибольший результат.

       Оценка временной слодности - O(1), так как нет циклов, количество операций фиксировано, и у всех вложенных
                                          операций асимптотика О(1).
       Оценка пространственной сложности - О(1), так как функция не имеет собственного состояния.

       # Вычисление кандидата на обмен значениями (getIdxToSwap) по отношению к заданному узлу.  Алгоритм выглядит в
       виде следующей последовательности действий:
        а) вычислить максимального потомка (getMaxChildIdx).
        б) если максимального потомка не существует, значит кандидат на обмен - сам заданный узел (identity function).
        в) если максимальный потомок существует, то сравниваем его с заданным узлом и возвращаем индекс наибольшего.

      Оценка временной слодности - O(1), так как нет циклов, количество операций фиксировано, и у всех вложенных
                                         операций асимптотика О(1).
      Оценка пространственной сложности - О(1), так как функция не имеет собственного состояния.

      # Обмен значений двух элементов кучи (swap). Поскольку куча представлена в виде массива, то
        операция вычисляется за O(1) по времени и O(1), так как состояние функции включает память для временного
        сохранения одного элемента кучи.

    (!) Основной алгоритм просеивания вниз (siftDown). Алгоритм используют рекурсивный план выполнения следующего вида:
     а) вычислить кандадата на обмен значениями (getIdxToSwap).
     б) если кандидат на обмен значениями соответствует заданному узлу - то прекратить просеивание (базовый случай
        рекурсии) и выйти из процедуры.
     в) ... если нет - то обменять значение текущего узла и узла-кандидата, вычисленного на этапе "а)"/
     г) ... рекуррентно вызвать просеивание для заданного значения, перемещенного в позицию кучи,
            вычителнную на шаге "а)" (рекуррентный случай).

                                       вычисление кандадата на  ┐   сравнение
                                       обмен значениями         |   элементов ┌----- обмен значениями 2-х элементов
                     количество просеиваний ----------┐         |     |       |
                                                      ▼        ▼     ▼      ▼
    Оценка временной слодности (в худшем случае) = log(n) * ( O(1) + O(1) + O(1) ) = log(n) * O(4) =
                                                 = O(4 * log(n)) = O( log(n) )

           количество просеиваний ----------┐
                                            ▼
    Оценка пространственной сложности - log(n) *О(1) = O (log (n))
                                                 ▲
                                                 └--- на каждой итерации рекурсии аллоцируется постоянное
                                                       пространство памяти под хранения элемента стэка вызовов.

   2) Алгоритм просеивания вверх (siftUp). Алгоритм реализован с использование следующих вложенных под-алгоритмов:

    # Вычисление родительского элемента (getParentIdx). Поскольку куча представлена в виде массива, то
        операция вычисляется за O(1) по времени и O(1), так как функция не имеет собственного состояния.

   (!) Основной алгоритм просеивания вверх (siftUp). Алгоритм используют рекурсивный план выполнения следующего вида:
    а) Вычислить родительский элемента (getParentIdx) /
    в) Если родительского элемента не существует (элемент является вершиной кучи) - то прекратить просеивание (базовый
       случай рекурсии) и выйти из процедуры.
    г) ... иначе - сравнить текущий узел с родительским, вычисленным на шаге "а)".
    в) ... если родительский узел меньше текущего, то обменять их значения и рекуррентно вызвать просеивание вверх для
           заданного значения, перемещенного в позицию кучи, вычителнную на шаге "а)" (рекуррентный случай).

                                       вычисление родительского ˥   сравнение
                                       элемента                 |   элементов ┌----- обмен значениями 2-х элементов
                     количество просеиваний ----------┐         |     |       |
                                                      ▼        ▼     ▼      ▼
    Оценка временной слодности (в худшем случае) = log(n) * ( O(1) + O(1) + O(1) ) = log(n) * O(4) =
                                                 = O(4 * log(n)) = O( log(n) )

           количество просеиваний ----------┐
                                            ▼
    Оценка пространственной сложности - log(n) *О(1) = O (log (n))
                                                 ▲
                                                 └--- на каждой итерации рекурсии аллоцируется постоянное
                                                       пространство памяти под хранения элемента стэка вызовов.


   3) Алгоритм добавления элемента в кучу (push).
      Алгоритм реализован с использование следующих вложенных под-алгоритмов:

    а) Добавить новый элемент в конец кучи. Асимптотика по времени и пространству - О(1).
    б) Запустить просеивание вверх (siftUp).

    Оценка временной слодности (в худшем случае) = n * [ O(1) + O( log (n) ) ] = n * O( log(n) + 1 ) = n * O( log(n) ).
    Оценка пространственной сложности = n * [ О(1) + О( log(n) ) ] = n * O(log(n) + 1) =  n * O( log(n) ).

   4) Алгоритм извлечения элемента из кучи (push).
      Алгоритм реализован с использование следующих вложенных под-алгоритмов:

    а) Получить вершину кучи. Асимптотика по времени и пространству - О(1).
    б) Переместить последний элемент кучи в вершину. Асимптотика по времени и пространству - О(1).
    б) Запустить просеивание вниз (siftDown).

    Оценка временной слодности (в худшем случае) = n * [ O(1) + O(1) + O( log (n) ) ] = n * O( log(n) + 2 ) =
                                                 = n * O( log(n) ).
    Оценка пространственной сложности = n * [ О(1) + О(1) + О( log(n) ) ] = n * O(log(n) + 2) =  n * O( log(n) ).

 */

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
        if (scoreValue != other.scoreValue) {
            return scoreValue > other.scoreValue ? 1 : -1; // сортировка по возрастанию
        }
        if (penaltyValue != other.penaltyValue) {
            return penaltyValue < other.penaltyValue ? 1 : -1; // сортировка по возрастанию
        }
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
        return (leftChildIdx <= lastElementPointer) ? leftChildIdx : -1;
    }

    private int getRightChild(int headIdx) {
        int rightChildIdx = headIdx * 2 + 1;
        return (rightChildIdx <= lastElementPointer) ? rightChildIdx : -1;
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

        if (maxChildIdx == -1) {
            return headIdx;
        }

        return (flatedHeapStorage[headIdx].compareTo(flatedHeapStorage[maxChildIdx]) > 0) ? headIdx : maxChildIdx;
    }

    private void siftDown(int idx) {
        int idxToSwap = getIdxToSwap(idx);
        if (idxToSwap == idx) return; // базовый случай.

        swap(idx, idxToSwap);
        siftDown(idxToSwap); // рекуррентный случай.
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
        for (int i = 1; i < numberOfRows + 1; i++) {
            buffer[i] = reader.readLine();
        }
        System.out.println(process(buffer));
    }
}
