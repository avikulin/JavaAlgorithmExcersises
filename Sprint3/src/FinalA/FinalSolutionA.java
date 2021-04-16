/*
    Код отправки в Яндекс.Контест - 49881040
 */

package FinalA; //---эту строку нужно закомментировать перед отправкой в Яндекс.Контест

/*
    ОПИСАНИЕ РЕШЕНИЯ

    Согласно условию входная последовательность представляет собой две подпоследовательности исходно монотонной (т.е.
    отсортированной) последовательности чисел. Следовательно каждая из данных подпоследовательностей также монотонна:
    подпоследовательность монотонной последоватетельности - есть монотонная последовательность.

    А значит в каждой из данных подпоследовательностей можно применять бинарный поиск за логарифмическое время. Для
    этого нужно предварительно выполнить 3 действия:

    1) определить границы подпоследовательностей
    2) определить целевую подпоследовательность содержащую искомый элемент
    3) выполнить бинарный поиск элемента в целевой подпоследовательности

    ШАГ 1. Определение границ подпоследовательностей

    Границу подпоследовательностей образует разделяющий (pivot) элемент, который указывает на первый элемент второй
    подпоследовательности. Для его определения мы используем интервальный бинарный поиск, который на каждом шаге
    выполняет тест монотонности подпоследовательностей слева и справо от потенциального разделяющего элемента:

    1) Тест монотонности левой подпоследовательности = [Первый элемент] < [Разделяющий элемент]
    2) Тест монотонности правой подпоследовательности = [Разделяющий элемент] < [Последний элемент]

    При любом разбиении возможны 2 варианта:

    1) Одна подпоследовательность монотонна, другая - нет. Рекурсивно продолжаем поиск разделяющего элемента в
        немонотонной подпоследовательности.
    2) Осталась одна немонотонная подпоследовательность из 2-х или 1-го элемента. В этом случае разделяющим элементом
        будет самый правый. Данный элемент является первым элементом второй подпоследовательности.
        Возвращаем его и выходим из рекурсии.

    Временная сложность: O(LOG(N)), так как мы используем бинарный поиск сокращая вдвое анализируемый
                         интервал на каждом шаге рекурсии.

    Пространственная сложность: O(1), так как дополнительная память расходуется только для временных переменных
                                и служебных указателей границ интервалов.

    ШАГ 2. Определение целевой подпоследовательности

    Поскольку обе подпоследовательности монотонны, то для определения, в какой из них находится искомый элемент,
    достаточно проверить попадание элемента в границы каждой из них. Это делается операциями сравнения:

        ([Первый элемент подпоследовательности] >= [Искомый элемент])
        И ([Искомый элемент] <=[Последний элемент подпоследовательности])

    Если элемент не входит ни в первую, ни во вторую подпоследовательности - значит он не входит в основную
    последовательность. В этом случае нужно прекратить работу процедуры и вернуть -1.

    Если же целевая подпоследовательность определена, то в ней на следующем шаге будет проводиться бинарный поиск.

    Временная сложность: O(1), так как в алгоритме только фиксированный набор операций без циклов.
    Пространственная сложность: O(1), так как дополнительная память расходуется только на временные переменные.

    ШАГ 3. Бинарный поиск элемента в целевой подпоследовательности.

    Применяется классический алгоритм рекурсивного разделения общей монотонной последовательности на 2 монотонные
    подпоследовательности разделяющим элементом. Если разделяющий элемент равен исходному - возвращаем его индекс и
    выходим из рекурсии. Если же разделяющий элемент отличается от искомого, то определяем целевую подпоследовательность
    (на основе сравнения искомого элемента с границами подпоследовательностей) и выполняем в ней рекурсивно процедуру
    бинарного поиска.

    Временная сложность: O(LOG(N)), так как мы используем бинарный поиск сокращая вдвое анализируемый
                         интервал на каждом шаге рекурсии.

    Пространственная сложность: O(1), так как дополнительная память расходуется только для временных переменных
                                и служебных указателей границ интервалов.


    ОБЩАЯ ОЦЕНКА СЛОЖНОСТИ РЕАЛИЗАЦИИ

    Временная сложность = O(LOG(N)) + O(1) + O(LOG(N)) = O(LOG(N))
    Пространственная сложность =  O(1) + O(1) + O(1) = O(1)

 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Основной класс решения задачи
 */
public class FinalSolutionA {
    /**
     * Поиск границ монотонности в заданном интервале входной последовательности. Функция разделяет входную немонотонную
     * последовательность на 2 монотонные последовательности, следующие друг за другом.
     *
     * @param sequence    Входная последовательность
     * @param leftBorder  Левая граница интервала
     * @param rightBorder Правая граница интервала
     * @return индекс первого элемента 2-й последовательности
     */
    public static int findMonotonousPivot(Integer[] sequence, int leftBorder, int rightBorder) {
        //базовый случай рекурсии - подпоследовательность из 2-х элементов (или меньше)
        if (rightBorder - leftBorder < 2) {
            if (sequence[rightBorder] < sequence[leftBorder])
                return rightBorder;
            else
                return -1;
        }

        int pivotElement = (leftBorder + rightBorder) / 2;
        boolean isLeftSubSequenceMonotonous = sequence[pivotElement] > sequence[leftBorder];
        boolean isRightSubSequenceMonotonous = sequence[rightBorder] > sequence[pivotElement];

        int result = -1; //дефолтное значение, на случай, если обе подпоследовательности монотонны.
        // По условию задачи такого быть не может, но все же в коде это предусмотрено.

        if (!isLeftSubSequenceMonotonous)
            result = findMonotonousPivot(sequence, leftBorder, pivotElement);
        if (!isRightSubSequenceMonotonous)
            result = findMonotonousPivot(sequence, pivotElement, rightBorder);

        return result;
    }

    /**
     * Функция бинарного поиска элемента в заданном интервале монотонной (отсортированной) последовательности.
     * @param sequence  Входная последовательность
     * @param leftBorder    Левая граница интервала поиска
     * @param rightBorder   Правая граница интервала поиска
     * @param searchValue   Искомое значение
     * @return  Индекс элемента, равного искомому, или -1 (если элемент не найден)
     */
    public static int binarySearch(Integer[] sequence, int leftBorder, int rightBorder, int searchValue) {
        // базовый случай рекурсии №1- подпоследовательность из 1-го элемента
        if (rightBorder <= leftBorder) {
            if (sequence[rightBorder] == searchValue) return rightBorder; //единственный элемент и есть искомый
            return -1; //искомый элемент не найден
        }

        int pivotalElement = (leftBorder + rightBorder) / 2;

        // базовый случай рекурсии №2 - искомый элемент найден
        if (sequence[pivotalElement] == searchValue) return pivotalElement;

        if (sequence[pivotalElement] > searchValue)
            return binarySearch(sequence, leftBorder, pivotalElement, searchValue);
        else
            return binarySearch(sequence, pivotalElement + 1, rightBorder, searchValue);
    }

    /**
     * Функция поиска значения в немонотонной последовательности
     * @param sequence Входная последовательность (содержит 2 вложенных монотонных подпоследовательности)
     * @param valueToSearch Искомое значение
     * @return Индекс элемента, равного искомому, или -1 (если элемент не найден)
     */
    public static int searchValue(Integer[] sequence, int valueToSearch) {
        int subSequencePivot = findMonotonousPivot(sequence, 0, sequence.length - 1);

        if (subSequencePivot == -1) //последовательность оказалась монотонной, хотя по условию задачи такого быть не может
            return binarySearch(sequence, 0, sequence.length - 1, valueToSearch);

        boolean isFirstSubSequence = (sequence[0] <= valueToSearch) && (valueToSearch <= sequence[subSequencePivot - 1]);
        boolean isSecondSubSequence = (sequence[subSequencePivot] <= valueToSearch) &&
                (valueToSearch <= sequence[sequence.length - 1]);

        if (isFirstSubSequence)
            return binarySearch(sequence, 0, subSequencePivot - 1, valueToSearch);

        if (isSecondSubSequence)
            return binarySearch(sequence, subSequencePivot, sequence.length - 1, valueToSearch);

        return -1; // искомый элемент не входит ни в первую, ни во вторую подпоследовательности.
    }

    /**
     * Базовая функция обработки входных данных.
     * Производит поиск во входной последовательности и возврашает его результат.
     * @param input Входная последовательность.
     * @return  Результат обработки, согласно условию задачи.
     */
    public static Integer goSearch(String[] input) {
        int sequenceLength = Integer.parseInt(input[0]);
        int valueToSearch = Integer.parseInt(input[1]);
        Integer[] sequence = new Integer[sequenceLength];
        StringTokenizer sequenceTokens = new StringTokenizer(input[2]);
        for (int i = 0; i < sequenceLength; i++) {
            sequence[i] = Integer.parseInt(sequenceTokens.nextToken());
        }

        return searchValue(sequence, valueToSearch);
    }

    /**
     * Точка входа в программу.
     * Зачитывание входных данных из консоли.
     * Вывод результатов обработки в консоль.
     * @param args Параметры командной строки (для целей совместимости).
     * @throws IOException Может выбрасываться исключение ввода/вывода.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] buffer = new String[3];
        for (int i = 0; i < 3; i++)
            buffer[i] = reader.readLine();

        System.out.println(goSearch(buffer));
    }
}
