package FinalA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringJoiner;

/**
 * Вспомогательный класс консольных утилит
 */

/**
 * Основной класс решения.
 */
public class FinalSolutionA {

    /**
     * Функция получения индексов с нулевыми значениями входной последовательности.
     * @param input     Входная последовательность номеров домов или нулей.
     * @return Возрастающая последовательность индексов нулевых значений во входной последовательности.
     */
    private static int[] GetZeroIndexes(int[] input){
        ArrayList<Integer> zeroIdxs = new ArrayList<>();
        for (int i=0; i< input.length; i++){
            if (input[i] == 0)
                zeroIdxs.add(i);
        }
        zeroIdxs.sort(Comparator.naturalOrder());
        return zeroIdxs.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
    * Проверка на принадлежность точки к интервалу.
    *  @param  leftMargin   Левая граница интервала
    *  @param  rightMargin  Правая граница интервала
    *  @param  target   Точка, внутри заданного интервала
    *  @return  Истина / Ложь
    */
    private static boolean CheckInterval(int leftMargin, int rightMargin, int target){
        return (target > leftMargin)&&(target < rightMargin);
    }

    /**
    * Определяем какая из границ интервала ближе к искомой точке (если границы равноудалены - возрвращается меньшая)
    *  @param  leftMargin   левая граница интервала
    *  @param  rightMargin  правая граница интервала
    *  @param  target   точка, внутри заданного интервала
    *  @return  Граница интервала, наиболее близкая к искомой точке
    */
    private static int DetermineClosestPoint(int leftMargin, int rightMargin, int target){
        return Math.abs(leftMargin - target) <= Math.abs(rightMargin - target) ? leftMargin: rightMargin;
    }

    /**
     * Функция бинарного поиска ближайшего нушевого индекса по отношению к заданному номеру участка.
     * @param sequence  Последовательность индексов с пустыми домами.
     * @param target    Номер участка, по отношению к которому ищется ближайший нулевой индекс (пустой учачток).
     * @return  Номер ближайшего нулевого индекса (пустого участка) в последовательности домов.
     */
    private static int GetNearestElement(int[] sequence, int target){
        int leftMargin = 0;
        int rightMargin = sequence.length-1;

        /**
            Проводим бинарный поиск нужного нам интервала.
            рассматриваем граничные случаи:
         1) искомая точка меньше первого члена последовательности
         2) искомая точка больше последнего члена последовательности
         3) на вход подана последовательность из одного элемента
            (данный элемент будет ближайшим для любой искомой точки)
         */

        if (sequence.length == 1) return sequence[0];
        if (target< sequence[leftMargin]) return sequence[leftMargin];
        if (target > sequence[rightMargin]) return sequence[rightMargin];

        while (leftMargin < rightMargin){
            int midPoint = (leftMargin + rightMargin)/2;
            int midValue = sequence[midPoint];

            /** рассматриваем 3 базовых варианта
             * 1) найденный n-элемент - т.е. минимальное расстояние до точки = 0;
             * 2) искомое значение попало в интервал (n-1; n), если (n-1) не выходит за левую границу последовательности;
             * 3) искомое значение попало в инервал (n; n+1), если (n+1) не выходит за правую границу последовательности;
             */

            if (midValue ==target) return target;

            if (target < midValue){
                if (((midPoint-1)>=leftMargin)&&(CheckInterval(sequence[midPoint - 1], midValue, target)))
                    return DetermineClosestPoint(sequence[midPoint - 1], midValue, target);
                rightMargin = midPoint - 1;
            } else {
                if (((midPoint+1)<=rightMargin)&&(CheckInterval(midValue, sequence[midPoint + 1], target)))
                    return DetermineClosestPoint(midValue, sequence[midPoint + 1], target);
                leftMargin = midPoint + 1;
            }
        }

        throw new RuntimeException("Edge cases of algorithm doesn't work properly");
    }

    /**
     * Функция расчета дистанции от каждого номера участка до ближайшего пустого участка
     * @param input     Входная последовательность строк, которая приходит из Яндекс-Контекста.
     * @return      Выходная строка с расчетом дистанции.
     */
    public static String CalculateDistance(String[] input){
        int   sequenceLength  = Integer.parseInt(input[0]);
        int[] sequenceData    = YUtils.getParamsVector(input[1], sequenceLength);
        int[] zeroIdxSequence = GetZeroIndexes(sequenceData);

        StringJoiner stringJoiner = new StringJoiner(" ","","");
        for (int i=0; i<sequenceLength; i++){
            if (sequenceData[i]==0){
                stringJoiner.add("0");
                continue;
            }
            stringJoiner.add(String.valueOf(Math.abs(i - GetNearestElement(zeroIdxSequence, i))));
        }
        return stringJoiner.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(CalculateDistance(YUtils.getFromConsole(reader, 2)));
    }

}
