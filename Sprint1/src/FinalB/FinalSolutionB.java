
package FinalB; // Для отправки в контест данную строку нужно закомментировать, так как в решении более одного класса

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Вспомогательный класс консольных утилит
 */
class YUtils {
    /**
     * Вспомогательный класс для пакетного зачитывания строк из консоли
     * @param reader    Ссылка на экземпляр BufferedReader.
     * @param numberOfStrings   Количество строк, которые нужно последовательно считать из консоли
     * @return  Мыссив считанных строк (порядок строк сохранен)
     * @throws IOException
     */
    static String[] GetFromConsole(BufferedReader reader, int numberOfStrings) throws IOException {
        String[] res = new String[numberOfStrings];
        for (int i=0; i < numberOfStrings; i++){
            res[i] = reader.readLine();
        }
        return res;
    }
}

/**
 * Основной класс решения
 */
public class FinalSolutionB {
    /**
     * Функция возвращает массив распределения количества цифр в конфигурации клавиш:
     *  индекс массива соответствует порядку цифр: 0 - цифра "1", 1 - цифра "2", и т.д
     *  значение массива соответствует количеству клавиш с данной цифрой к конфигурации игрового поля.
     * @param inputCriteria Массив входных строк, описывающих конфигурацию игрового поля.
     * @return  Массив распределения количества цифр в конфигурации клавиш игрового поля.
     */
    public static int[] ConstructGame(String[] inputCriteria){
        int[] res = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        String input = String.join("", inputCriteria);
        for (int i=0; i< input.length(); i++){
            char ch = input.charAt(i);
            if ( ch != '.'){
                res[Character.getNumericValue(ch)-1]++;
            }
        }
        return res;
    }

    /**
     * Основная функция подсчета баллов по результатам игры.
     * @param inputCriteria Полный массив входных данных, получаемых их Яндекс-Контеста.
     * @return  Расчитанное количество баллов по результатам игры.
     */
    public static int PlayGame(String[] inputCriteria){
        // параметр k - количество клавиш, которое может нажать один игрок в течение раунда
        int kCriteria = Integer.parseInt(inputCriteria[0]);
        kCriteria *= 2; // умножаем на 2, так как подсчет идет нажатия клавиш обоими игроками

        // получаем конфигурацию клавиш
        int[] keysConfiguration = ConstructGame(Arrays.copyOfRange(inputCriteria, 1, inputCriteria.length));

        // счетчик баллов, получаемых обоими игроками за раунд
        int res = 0;
        for (int i=0; i<9; i++){
            if ((keysConfiguration[i] <= kCriteria)&&(keysConfiguration[i]!= 0)){
                res++; // если количество клавиш не ноль и их общее количество меньше или равно k, то прибавляем балл
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int res = PlayGame(YUtils.GetFromConsole(reader,5));
        System.out.println(String.valueOf(res));
    }
}
