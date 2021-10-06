//код посылки в Яндекс.Контест - 53995990

package FinalA; // эту строку нужно закомментировать перед отправкой

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    Описание алгоритма взято отсюда - https://habr.com/ru/post/117063/
    Поскольку речь о вычислении расстояния без восстановления самой последовательности, воспользуемся упрощенным
    алгоритмом Вагнера-Фишера.

    Решаем с использованием парадигмы динамического программирования в двумерной динамике:

    0) формируем матрицу (M,N), отображающую дискретную функцию D(M,N) = D(S1[0..M], S2[0..N]).

    Поскольку строки длиной до 1 000 знаков, матричная функция потребуется памяти 1 000 х 1 000 х 2 байта = 1,9 МБ ОЗУ.
    Поэтому можно использовать упрощенное (матричное) хранение состояния промежуточных расчетов.

    1) Базовый случай:
        а) D(0,0) = 0.
        б) D(i, 0) = i;
        в) D(0, j) = j.

    2) Функция перехода:
        а) если S1[i] == S2[j] -> D(i,j) = D(i-1,j-1),
        б) иначе -> D(i,j) = min( D(i-1,j), D(i,j-1), D(i-1,j-1) ) + 1.

    3) Порядок вычислений:
        поскольку функция D(M,N) задана через рекурсивное соотношение (i-k,j-l) -> (i, j), то вычисление происходит из
        левого верхнего угла матрицы в  правый нижний.

    4) Расстояние Левенштейна будет находиться в ячейке (M,N)

    Рассмотрим пример №1 из задания:

            a  b  a  c  a  b  a
        -------------------------
       | 0  1  2  3  4  5  6  7
      a| 1  0  1  2  3  4  5  6
      b| 2  1  0  1  2  3  4  5
      a| 3  2  1  0  1  2  3  4
      a| 4  3  2  1  2  1  2  3
      b| 5  4  3  2  2  2  1  2
      c| 6  5  4  3  2  3  2  [2] <- расстояние Левенштена

     ВРЕМЕННАЯ СЛОЖНОСТЬ АЛГОРИТМА: O(MN)
     ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ АЛГОРИТМА: O(MN)

 */

/**
 * Основной класс-решения
 */
public class FinalSolutionA {

    /**
     * Функция-драйвер (изолированная точка входа-выхода)
     * @param input Массив входных строк
     * @return  Расстояние Левенштейна между строками
     */
    public static String process(String[] input) {

        int xDimension = input[0].length();
        int yDimension = input[1].length();

        //обработка пустых строк
        if ((xDimension == 0) && (yDimension == 0)) return "0";
        if (xDimension == 0) return Integer.toString(yDimension);
        if (yDimension == 0) return Integer.toString(xDimension);

        //инициализация матрицы динамического программирования
        int[][] dpCache = new int[yDimension + 1][xDimension + 1];
        dpCache[0][0] = 0;
        int maxDimension = Math.max(xDimension, yDimension);
        for (int i = 1; i <= maxDimension; i++) {
            if (i <= xDimension) {
                dpCache[0][i] = i;
            }
            if (i <= yDimension) {
                dpCache[i][0] = i;
            }
        }

        //выполнение расчета
        for (int rowIdx = 1; rowIdx <= yDimension; rowIdx++) {
            for (int columnIdx = 1; columnIdx <= xDimension; columnIdx++) {
                boolean charsEqual = (input[0].charAt(columnIdx - 1) == input[1].charAt(rowIdx - 1));
                int minValueUpLeft = Math.min(dpCache[rowIdx - 1][columnIdx], dpCache[rowIdx][columnIdx - 1]);
                int minValueTotal = Math.min(minValueUpLeft, dpCache[rowIdx - 1][columnIdx - 1]);
                int dpRecursiveValue = dpCache[rowIdx - 1][columnIdx - 1];
                int dpNewValue = charsEqual ? dpRecursiveValue : minValueTotal + 1;
                dpCache[rowIdx][columnIdx] = dpNewValue;

            }
        }
        return Integer.toString(dpCache[yDimension][xDimension]);
    }

    /**
     * Точка входа в программу
     * @param args  Параметры командной строка (для целей совместимости)
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] buffer = new String[2];
        buffer[0] = reader.readLine();
        buffer[1] = reader.readLine();
        System.out.println(process(buffer));
    }
}