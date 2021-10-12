package FinalA;

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

    public String parseAndUnpackString(String str) {
        Deque<Integer> bracketsPos = new ArrayDeque<>(100);
        String recursiveResult = "";

        int backtrackingPosition = -1;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '[') {
                bracketsPos.push(i);
            }
            if (str.charAt(i) == ']') {
                // парсинг префикса и вычисление динамической части вложенного выражения
                int openBracketPos = bracketsPos.pop();
                int multiplicator = Character.getNumericValue(str.charAt(openBracketPos - 1));
                if (openBracketPos > backtrackingPosition) {
                    String templateStr = str.substring(openBracketPos + 1, i);
                    int prevBracketPos = bracketsPos.isEmpty() ? -1 : bracketsPos.peek();
                    int prefixBeginPos = Math.max(backtrackingPosition, prevBracketPos) + 1;
                    String prefixStr = str.substring(prefixBeginPos, openBracketPos - 1);
                    recursiveResult = recursiveResult.concat(unpackStringToken(prefixStr, multiplicator, templateStr));
                } else {
                    // парсинг статического постфикса вложенного выражения и вычисление внешнего выражения
                    String postfixStr = str.substring(backtrackingPosition + 1, i);
                    recursiveResult = recursiveResult.concat(postfixStr);
                    String templateStr = recursiveResult;
                    int prevBracketPos = bracketsPos.isEmpty() ? -1 : bracketsPos.peek();
                    int prefixBeginPos = prevBracketPos + 1;
                    String prefixStr = str.substring(prefixBeginPos, openBracketPos - 1);
                    recursiveResult = unpackStringToken(prefixStr, multiplicator, templateStr);
                }
                backtrackingPosition = i;
            }
        }

        String finalPostfix = (backtrackingPosition + 1 < str.length()) ?
                str.substring(backtrackingPosition + 1) : null;

        return (finalPostfix == null) ? recursiveResult : recursiveResult.concat(finalPostfix);
    }
}

public class FinalSolutionA {


    public static String unpackTestDriverFunc(String input) {
        Map<PackingKey, String> cache = new HashMap<>(100);
        StringProcessor processor = new StringProcessor(cache);
        return processor.parseAndUnpackString(input);
    }

    public static void main(String[] args) {
    }
}
