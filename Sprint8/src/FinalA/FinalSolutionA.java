package FinalA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
     Обработка упакованной строки a2[aa3[b]]c3[d]e
                        multiplicator
                             ▼
                            a2[aa3[b]fssdf]c3[d]e
                               ▲        ▲
                           start         end
     1) парсинг:
        - позиция множителя
        - начало шаблона
        - конец шаблона
        - признак наличия вложенного шаблона
     2) конструирование класса упакованной строки
        -

    Кэш:
        3[b] -> "bbb"
        aa3[b] -> "aabbb"
        2[aa3[b]] -> "aabbbaabbb"
        3[d] -> "ddd"
 */
class PackingKey {
    private final int multiplicand;
    private final String template;

    public PackingKey(int multiplicand, String template) {
        this.multiplicand = multiplicand;
        this.template = template;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        PackingKey that = (PackingKey) o;
        return multiplicand == that.multiplicand &&
                template.equals(that.template);
    }

    @Override
    public int hashCode() {
        return Objects.hash(multiplicand, template);
    }
}

class StringProcessor {
    private final Map<PackingKey, String> cacheStorage;

    public StringProcessor(Map<PackingKey, String> cache) {
        cacheStorage = cache;
    }

    public String unpackStringToken(String prefix, int multiplicand, String template) {
        String res = prefix;
        //попробуем получить распакованный паттерн из кэша
        PackingKey key = new PackingKey(multiplicand, template);
        String valueFromCache = cacheStorage.get(key);
        if (valueFromCache == null) {
            String valueHolder = "";
            for (int m = 0; m < multiplicand; m++) {
                valueHolder = valueHolder.concat(template);
            }
            //добавление распакованного значения паттерна в кэш
            cacheStorage.put(key, valueHolder);
            res = res.concat(valueHolder);
        } else {
            res = res.concat(valueFromCache);
        }

        return res;
    }

    /*
        3  [  a  ]  2  [  r  2  [  t  ]  ] = aaarttrtt
        |  |  |  |  |  |  |  |  |  |  |  |
        0  1  2  3  4  5  6  7  8  9 10 11
     */
    public String parseAndUnpackString(String str) {
        Deque<Integer> bracketsPos = new ArrayDeque<>(100);
        Deque<String> recursiveStack = new ArrayDeque<>(100);
        Deque<Integer> backTrackingBarrier = new ArrayDeque<>(100);
        //String recursiveResult = "";

        //int backtrackingPosition = -1;
        backTrackingBarrier.push(-1);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '[') {
                bracketsPos.push(i);
            }
            if (str.charAt(i) == ']') {
                // парсинг префикса и вычисление динамической части вложенного выражения
                int openBracketPos = bracketsPos.pop();
                int multiplicator = Character.getNumericValue(str.charAt(openBracketPos - 1));
                int backtrackingPosition = backTrackingBarrier.peek();
                if (openBracketPos > backtrackingPosition) {
                    String templateStr = str.substring(openBracketPos + 1, i);
                    int prevBracketPos = bracketsPos.isEmpty() ? -1 : bracketsPos.peek();
                    int prefixBeginPos = Math.max(backtrackingPosition, prevBracketPos) + 1;
                    String prefixStr = str.substring(prefixBeginPos, openBracketPos - 1);
                    //recursiveResult = recursiveResult.concat(unpackStringToken(prefixStr, multiplicator, templateStr));
                    recursiveStack.push(unpackStringToken(prefixStr, multiplicator, templateStr));
                } else {
                    // парсинг статического постфикса вложенного выражения и вычисление внешнего выражения
                    if (backTrackingBarrier.size() > 1){
                        backTrackingBarrier.pop();
                    }
                    backtrackingPosition = backTrackingBarrier.peek();
                    String postfixStr = str.substring(backtrackingPosition + 1, i);
                    //recursiveResult = recursiveResult.concat(postfixStr);
                    //String templateStr = recursiveResult;
                    String templateStr = recursiveStack.pop();
                    int prevBracketPos = bracketsPos.isEmpty() ? -1 : bracketsPos.peek();
                    //int prefixBeginPos = prevBracketPos + 1;
                    int prefixBeginPos = Math.max(backtrackingPosition, prevBracketPos) + 1;
                    String prefixStr = str.substring(prefixBeginPos, openBracketPos - 1);
                    //recursiveResult = unpackStringToken(prefixStr, multiplicator, templateStr);
                    recursiveStack.push(unpackStringToken(prefixStr, multiplicator, templateStr));
                }
                //backtrackingPosition = i;
                backTrackingBarrier.push(i);
            }
        }

        String res = "";
        while (!recursiveStack.isEmpty()){
            res = res.concat(recursiveStack.pollLast());
        }

        int lastProcessedPos = backTrackingBarrier.peek();
        String finalPostfix = (lastProcessedPos + 1 < str.length()) ?
                str.substring(lastProcessedPos + 1) : null;

        //return (finalPostfix == null) ? recursiveResult : recursiveResult.concat(finalPostfix);
        return (finalPostfix == null) ? res : res.concat(finalPostfix);
    }
}

public class FinalSolutionA {


    public static String unpackTestDriverFunc(String input) {
        Map<PackingKey, String> cache = new HashMap<>(100);
        StringProcessor processor = new StringProcessor(cache);
        return processor.parseAndUnpackString(input);
    }

    public static String process(String[] input) {
        Map<PackingKey, String> cache = new HashMap<>(100);
        StringProcessor processor = new StringProcessor(cache);

        int lowerBound = input.length;
        for (int i = 1; i < lowerBound; i++) {
            input[i] = processor.parseAndUnpackString(input[i]);
        }

        int mutualPrefixBound = -1;
        String sampleString = input[0];
        int sampleLength = sampleString.length(); //экономим время на вычислении length() вне цикла
        while (mutualPrefixBound < sampleLength) {
            mutualPrefixBound++;
            char sampleChar = sampleString.charAt(mutualPrefixBound);
            for (int i = 1; i < lowerBound; i++) {
                String testString = input[i];
                if ((mutualPrefixBound == testString.length()) || (testString.charAt(mutualPrefixBound) != sampleChar)) {
                    mutualPrefixBound--;
                    return (mutualPrefixBound > 0) ? sampleString.substring(0, mutualPrefixBound + 1) : "";
                }
            }
        }
        return sampleString;
    }

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
