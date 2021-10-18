// код отправки в Яндекс.Контест - 54985010

package FinalA; // эту строку нужно закомментировать перед отправкой в Яндекс.Контест

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

/*
    ОПИСАНИЕ РЕШЕНИЯ

    ЧАСТЬ А. Алгоритм распаковки строки.

    Формат упакованной строки представляет собой выражение (фразу) на формальном языке со строгой грамматикой:
    "<префикс><множитель>[<подвыражение>]<суффикс>". Грамматика опысывает строгую структуру лексемы - единичной
    интерпретируемой подпоследовательности символом выражения. Выражение представимо в виде дерева, в котором каждый
    узел представлен лексемой:

                              [суффикс]
                             /
                [ множитель ]
               /             \
    [ префикс ]              [ подвыражение ]

    Формальная грамматика определена так:
    а) Начало лексемы определено первым символом после предыдущей лексемы или перевым символом строки.
    б) Префикс определеяется последовательностью символов с конца предыдушей лексемы до позиции множителя.
       Может быть нулевой подпоследовательностью.
    в) Множитель представляет собой цифру 0-9, расположенную перед символом "[".
    г) Подвыражение определяется последовательностью символом, заключенных между симмволов "[" и "]".
       Может быть литералом. Не может быть пустой последовательностью.
    д) Суффикс определеяется последовательностью символов со следующего символа после конца подвыражения и
       до последнего символа лексемы("..]<суффикс>]...") или конца строки. Суффикс может быть пустым.

    Рассмотрим пример выражения:

    Схемы выражения в виде дерева лексем выглядит следующим образом:

                                  [ 2 ]
                                 /     \
                            [ e ]       \
                           /          [lm]
                      [ 2 ]             /
                     /     \        [ 3 ]
                [ c ]       [ d ]   /   \
               /                [ h ]   [ k ]
          [ 3 ]                /
         /    \           [ 3 ]
    [ a ]      [ b ]      /   \
                     [ f ]     [ g ]

    Данное дерево можно изобразить в виде многоуровневого ступенчатого дерева:

    Уровень 0:[ a ]-[ 3 ]-[ c ]-[ 2 ]-[ e ]-[ 2 ]-[no]
                      |           |           |
    Уровень 2:      [ b ]       [ d ]       [ f ]-[ 3 ]-[ h ]-[ 3 ]-[ lm ]
                                              |           |
    Уровень 2:                              [ g ]       [ k ]


    Такое представление дерева лексем удобно для вычисления в виде свой левооперабельности:
    а) каджый уровень вычисляется слева направо
    б) результат вычисления каждого подуровня передается на вышестоящий
       уровень в виде литерала (распакованной подстроки)
    в) пустые префиксы и суффиксы не нужно достраивать в виде пустых узлов.
    г) все вычисленные лексемы левее текущей рассматриваются, как литерал префикса;

    Поскольку ступенчатое лексическое дерево хорошо представимо в виде строки, то нам не требуется создвать какие-либо
    дополнительные структуры в памяти. Достаточно обеспечить рекурсивный обход уровней слева-направо.

    		  |<-лекс.#1->|<--лекс.#2--->|		  |<---лекс.#3-->|<---лекс.#4-->|
									     |<------------------ лекс.#5 ------------------>|
              a  3  [  b  ]  c  2  [  d  ]  e  2  [  f  3  [  g  ]  h  3  [  k  ]  l  m  ]  n  o
    		  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |
    Время   ->0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27
    Переход ->*	    +     -        +     -        +        +     -        +     -  		 -
    Уровень ->0	    1     0        1     0        1        2     1        2     1  		 0

    "+" - переход на следующий уровень дерева
    "-" - возврат на предыдущий уровень дерева

    На примере выше показана декомпозиция выражения на 5 лексем, которые вычисляются в порядке прохода по всем уровням
    ступенчатого дерева. Для вычисления использован конечный автомат, который реализует следующие операции и переходы:

    ЛЕКСИЧЕСКИЙ АНАЛИЗ:
    1) определение границы лексемы. Для этого используется переменная @backTrace, указывающая на границы последней
       вычисленной (т.е. распакованной) лексемы;
    ВРЕМЕННАЯ СЛОЖНОСТЬ: O(1)
    ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ: O(1)

    2) определение границ подвыражения путем сравнения анализируемого символа выражения с маркерами "[" и "]".
    ВРЕМЕННАЯ СЛОЖНОСТЬ: O(s), где s - длина строки исходного (запакованного) выражения.
    ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ: O(1)

    ОБРАБОТКА ГРАММАТИКИ:
    3) вычисление лексемы - распаковка строки: <префикс> + (<множитель> х [<подвыражение>]) + <суффикс>
    3а)     переход в поддерево (обозначен на схеме как "+")
    3б)     вычисление поддерева
    3в)     возврат из поддерева

    Для вычиления всего выражения в один проход используется итеративный подход и принцип динамического программирования
    "Back Tracing", когда мы сохраняем все вычисленные лексемы левее точки @backTrace (переменная @localResult) и
    используем это значение для вычисления следующей лексемы, стоящей правее @backTrace. При переходе в поддерево, мы
    сохраняем @localResult и множитель текущей лексемы в стеке. После возврата из поддерева - восстанавливаем эти
    значения из стека и продолжаем вычисления слева направо.

    Таким образом алгоритм формализуется следующим образом:

        ВХОД В ЛЕКСЕМУ (3а):
            1) вычислить множитель (предыдущая позиция относительно "[") и положить его в multiplierStack
            2) получить значение @backTrace и вычислить префикс
            3) объединить @localResult и префикс. Результат кладем в @resultStack
            4) обновить значение @backTrace

       ВРЕМЕННАЯ СЛОЖНОСТЬ: O(1) + O(p) + O(1) + O(1) = O(p), где p - длина префикса;
       ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ: O(1) + O(p) + O(l+p) + O(1) = O(l+p), где l - длина вычисленного ранее подвыражения,
                                                                                 слева от @backTrace

        ВЫХОД ИЗ ЛЕКСЕМЫ (3б и 3в):
            1) получить @backTrace
            2) вычислить локальный суффикс и прибавить его к @localResult
            3) достать префикс из стэка @resultStack
            4) достать множитель из стэка @multiplierStack
            5) вычислить лексему и сохранить результат в @localResult
            6) обновить значение @backTrace

       ВРЕМЕННАЯ СЛОЖНОСТЬ: O(1) + O(s) + O(1) + O(1) + O(p+s) + O(1) = O(p+s), где s - длина суффикаса;
       ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ: O(1) + O(p) + O(l+u) + O(1) = O(l+u), где u - длина вычисленной лексемы

    В итоге получаем следующие оценки для п.3:
    ВРЕМЕННАЯ СЛОЖНОСТЬ: O(p) + O(p+s) = O(p+s)
    ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ: O(l+p) + O(l+u) = O(l) + O(u) = O(l+u)

    Поскольку сумма длин всех k лексем, включенных в строку, соответствует общей длине строки, то получаем итоговую
    оценку сложночти алгоритма распаковки:

    ВРЕМЕННАЯ СЛОЖНОСТЬ: O(1) + O(s) + k*(O(p+s)) = 2*O(s) = O(s)
    ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ: O(1) + O(1) + k*O(u) = O(k*u) = O(U), где U - длина распакованной строки
                                                |
                                                левая часть выражения храниться в одном экземпляре в
                                                переменной @localResult и входит в оценку k*O(u)

    ЧАСТЬ Б. Определение наибольшего общего префикса.

    Для вычисления используется принцип динамического программирования, который гласит, что общий префикс для каждой
    последующей строки не превышает общий префикс,  вычисленный для предыдущей строки. Общий префикс определяется
    позицией последнего последовательно совпавшего символа строк. Поскольку общий префикс является инвариантом операции
    перестановки строк, то каждую i-ю строку нужно посимвольно сравнивать только с первой строкой. Причем посимвольное
    сравнение выволняется в диапазоне от первого символа и до вычисленной на предыдущей итерации позиции общего
    префикса. Если в i-й строке совпавшая символьная подпоследовательнось короче, чем в (i-1)-й строке, то значение
    позиции общего префикса корректируется в меньшую сторону.
    Причем в рамках данного алгоритма нам не нужно хранить массив распакованных строк. Мы храним только 1-ю и i-ю строки
    в памяти, а также значение позиции наибольшего общего префикса.

    Таким образом получаем оценки сложности алгоритма для вычисления общего префикса:

    ВРЕМЕННАЯ СЛОЖНОСТЬ (в худшем случае) = n * O(U) = O(n*s)
    ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ = O(n*U)
        где n - количество сравниваемых строк
            U - максимальная длина распакованной строки.

   ЧАСТЬ В. Итоговые оценки сложности алгоритма:

    ВРЕМЕННАЯ СЛОЖНОСТЬ (в худшем случае) = n*O(s) + n * O(U) = 2 * O(n*s) = O(n*s)
    ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ = On* O(U) + O(n*U) = 2 * O(n*U) = O(n*U)

 */


/**
 * Класс-функтор для вычисления лексемы
 */
class ExpressionProcessor {
    /**
     * Функция вычисления (распаковки лексемы)
     *
     * @param prefix       Литерал префикса
     * @param multiplicand Значение показателя повторения подвыражения (множитель)
     * @param template     Литерал подвыражения
     * @return Литерал распакованной лексемы
     */
    public String process(String prefix, int multiplicand, String template) {
        String res = prefix;
        String valueHolder = template;
        multiplicand--;
        for (int m = 0; m < multiplicand; m++) {
            valueHolder = valueHolder.concat(template);
        }
        res = res.concat(valueHolder);
        return res;
    }
}

/**
 * Класс-обработчик грамматик
 */
class GrammarProcessor {
    private String strContext;
    private final ExpressionProcessor expressionProcessor;
    private final Deque<String> resultStack;
    private final Deque<Integer> multiplierStack;
    private int backTracePosition;
    private String localResult;

    /**
     * Конструктор класса
     *
     * @param processor Ссылка на функтор вычисления лексем
     */
    public GrammarProcessor(ExpressionProcessor processor) {
        expressionProcessor = processor;
        resultStack = new ArrayDeque<>(200);
        multiplierStack = new ArrayDeque<>(200);
    }

    /**
     * Инициализация нового прохода по выражению
     *
     * @param context Строковый контекст исходного выражения (запакованной строки)
     */
    public void init(String context) {
        strContext = context;
        resultStack.clear();
        multiplierStack.clear();
        backTracePosition = 0;
        localResult = "";
    }

    /**
     * Операция перехода в лексическое поддерево выражений
     *
     * @param offset Позиция символа начала нового подвыражения лексемы ("[")
     */
    public void enterSubExpression(int offset) {
        int multiplier = Character.getNumericValue(strContext.charAt(offset - 1));
        multiplierStack.push(multiplier);
        String prefix = strContext.substring(backTracePosition, offset - 1);
        localResult = localResult.concat(prefix);
        resultStack.push(localResult);
        localResult = "";
        backTracePosition = offset + 1;
    }

    /**
     * Операция выхода из лексического поддерева
     *
     * @param offset Позиция символа конца подвыражения лексемы ("]")
     */
    public void returnFromSubExpression(int offset) {
        String localSuffix = strContext.substring(backTracePosition, offset);
        localResult = localResult.concat(localSuffix);
        int multiplier = multiplierStack.pop();
        String prefix = resultStack.pop();
        localResult = expressionProcessor.process(prefix, multiplier, localResult);
        backTracePosition = offset + 1;
    }

    /**
     * Операция обработки суффикса на 0-м уровне лексического дерева
     */
    public void finalize() {
        String globalSuffix = strContext.substring(backTracePosition, strContext.length());
        localResult = localResult.concat(globalSuffix);
        backTracePosition = -1;
    }

    /**
     * Возврат накопленного значения вычисленных лексем (с 1-й по текущую)
     *
     * @return
     */
    public String getCurrentLocalResult() {
        return localResult;
    }
}

/**
 * Класс лексического анализа
 */
class LexicalParser {
    private final char START_SUBEXPRESSION_MARKER = '[';
    private final char END_SUBEXPRESSION_MARKER = ']';

    private final HashMap<String, String> phraseCache;
    private final GrammarProcessor gProcessor;

    /**
     * Конструктор класса
     *
     * @param processor Ссылка на процессор грамматик
     */
    public LexicalParser(GrammarProcessor processor) {
        gProcessor = processor;
        phraseCache = new HashMap<>(1000);
    }

    /**
     * Функция лексического анализа исходной строки с выражением
     *
     * @param rawString Литерал выражения (запакованная строка)
     * @return Литерал распакованной строки
     */
    public String process(String rawString) {
        if (rawString.isEmpty()) return "";

        String possibleResultFromCache = phraseCache.get(rawString);
        if (possibleResultFromCache != null) {
            return possibleResultFromCache;
        }

        gProcessor.init(rawString);

        int finalPos = rawString.length();
        for (int i = 0; i < finalPos; i++) {
            char testChar = rawString.charAt(i);
            if (testChar == START_SUBEXPRESSION_MARKER) {
                gProcessor.enterSubExpression(i);
            }
            if (testChar == END_SUBEXPRESSION_MARKER) {
                gProcessor.returnFromSubExpression(i);
            }
        }
        gProcessor.finalize();

        String res = gProcessor.getCurrentLocalResult();
        phraseCache.putIfAbsent(rawString, res);
        return res;
    }
}

/**
 * Основной класс решения
 */
public class FinalSolutionA {
    /**
     * Функция-драйвер теста распаковки. Используется только для тестирования.
     *
     * @param input Тестовый ввод (запакованная строка)
     * @return Тестовый вывод (распакованная строка)
     */
    public static String unpackTestDriverFunc(String input) {
        ExpressionProcessor expressionProcessor = new ExpressionProcessor();
        GrammarProcessor grammarProcessor = new GrammarProcessor(expressionProcessor);
        LexicalParser lexicalParser = new LexicalParser(grammarProcessor);
        return lexicalParser.process(input);
    }

    /**
     * Функция-драйвер основного решения
     *
     * @param input Коллекция запакованных строк
     * @return Литерал общего префикса коллекции строк после распаковки
     */
    public static String process(String[] input) {
        ExpressionProcessor expressionProcessor = new ExpressionProcessor();
        GrammarProcessor grammarProcessor = new GrammarProcessor(expressionProcessor);
        LexicalParser lexicalParser = new LexicalParser(grammarProcessor);

        int phraseCollectionLength = input.length;

        String controlString = lexicalParser.process(input[1]);
        if (phraseCollectionLength == 2) { //если в коллекции одна строка, то она соответствует макс. общ. префиксу
            return controlString;
        }

        int maxMutualPrefix = controlString.length() - 1;
        for (int i = 2; i < phraseCollectionLength; i++) {
            String testString = lexicalParser.process(input[i]); //распаковка i-й строки
            for (int j = 0; j <= maxMutualPrefix; j++) {
                char controlChar = controlString.charAt(j);
                char testChar = testString.charAt(j);
                if (controlChar != testChar) {
                    int actualMaxMutualPrefix = j - 1;
                    maxMutualPrefix = Math.min(actualMaxMutualPrefix, maxMutualPrefix);
                    break;
                }
            }
        }

        maxMutualPrefix = Math.max(maxMutualPrefix, 0); //если не совпало ни одного символа, то устанавливаем в 0
        return (maxMutualPrefix < controlString.length() - 1) ? controlString.substring(0, maxMutualPrefix + 1) :
                controlString;
    }

    /**
     * Точка входа в программу
     *
     * @param args Аргументы командной строки (для целей совместимости)
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String numStr = reader.readLine();
        int num = Integer.parseInt(numStr);
        String[] buffer = new String[num + 1];
        buffer[0] = numStr;
        for (int i = 1; i < buffer.length; i++) {
            buffer[i] = reader.readLine();
        }
        System.out.println(process(buffer));
    }
}
