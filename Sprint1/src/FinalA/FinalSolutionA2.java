// ID посылки  Яндекс.Контест - 48753848
package FinalA; // эту строчку нужно закоментировать перед отправкой в Яндекс.Контест.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Вспомогательный класс консольных утилит
 */
class YUtils {

    /**
     * Вспомогательный класс для пакетного зачитывания строк из консоли
     *
     * @param reader          Ссылка на экземпляр BufferedReader.
     * @param numberOfStrings Количество строк, которые нужно последовательно считать из консоли
     * @return Массив считанных строк (порядок строк сохранен)
     * @throws IOException Может выбросить исключение ввода-вывода
     */
    static String[] getFromConsole(BufferedReader reader, int numberOfStrings) throws IOException {
        String[] res = new String[numberOfStrings];
        for (int i = 0; i < numberOfStrings; i++) {
            res[i] = reader.readLine();
        }
        return res;
    }

    /**
     * Функция получения целочисленного значения из строки.
     *
     * @param input Входная строка.
     * @return Целочисленное значение, полученное из строки.
     */
    static int getParamScalar(String input) {
        input = input.trim();
        return Integer.parseInt(input);
    }

    /**
     * Функция получения списка целочисленных параметров из строки цифр, разделенных пробелами.
     *
     * @param input          Входная строка с цифровыми параметрами.
     * @param numberOfParams Количество параметров в строке.
     * @return Целочисленный массив с параметрами, полученными из входной строки.
     */
    static int[] getParamsVector(String input, int numberOfParams) {
        int[] res = new int[numberOfParams];
        StringTokenizer tokenizer = new StringTokenizer(input);

        for (int i = 0; i < numberOfParams; i++) {
            String value = tokenizer.nextToken();
            res[i] = getParamScalar(value);
        }
        return res;
    }
}

/**
 * Основной класс решения.
 */
public class FinalSolutionA2 {
    /**
     * Поиск в последовательности ближайшего нулевого элемента справа от указанной позиции
     *
     * @param input      Входная последовательность.
     * @param startIndex Начальная позиция для поиска.
     * @return Индекс найденного нулевого элемента.
     */
    static int findNextZero(int[] input, int startIndex) {
        for (int i = startIndex; i < input.length; i++) {
            if (input[i] == 0) return i;
        }
        return -1;
    }

    /**
     * Функция расчета дистанции от каждого номера участка до ближайшего пустого участка
     *
     * @param input Входная последовательность строк, которая приходит из Яндекс-Контекста.
     * @return Выходная строка с расчетом дистанции.
     */
    public static String calculateDistance(String[] input) {
        int sequenceLength = Integer.parseInt(input[0]);
        int[] sequenceData = YUtils.getParamsVector(input[1], sequenceLength);

        StringJoiner stringJoiner = new StringJoiner(" ", "", "");

        int leftZeroIdx = -1;
        int rightZeroIdx = findNextZero(sequenceData, 0);

        for (int i = 0; i < sequenceLength; i++) {
            if (sequenceData[i] == 0) {
                leftZeroIdx = i;
                rightZeroIdx = (i + 2) < sequenceLength ? findNextZero(sequenceData, i + 1) : Integer.MAX_VALUE;
                stringJoiner.add("0");
            } else {
                int distanceLeft = leftZeroIdx == -1 ? Integer.MAX_VALUE : Math.abs(i - leftZeroIdx);
                int distanceRight = rightZeroIdx == -1 ? Integer.MAX_VALUE : Math.abs(i - rightZeroIdx);
                int res = Math.min(distanceLeft, distanceRight);
                stringJoiner.add(String.valueOf(res));
            }
        }
        return stringJoiner.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(calculateDistance(YUtils.getFromConsole(reader, 2)));
    }

}
