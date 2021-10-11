package FinalA;

import java.util.ArrayDeque;
import java.util.Deque;

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
class PackedString {
    private String prefix;
    private int multiplicator;
    private String template;
    private String postfix;

    public PackedString(String prefix, int multiplicator, String template, String postfix) {
        this.prefix = prefix;
        this.multiplicator = multiplicator;
        this.template = template;
        this.postfix = postfix;
    }
}

public class FinalSolutionA {
    public static String unpackStringToken(String prefix, int multiplicator, String template) {
        StringBuilder res = new StringBuilder();
        if (prefix != null) {
            res.append(prefix);
        }
        for (int m = 0; m < multiplicator; m++) {
            res.append(template);
        }
        return res.toString();
    }

    public static String parseAndUnpackString(String str) {
        Deque<Integer> bracketsPos = new ArrayDeque<>();
       String recursiveResult = "";

        int rightPosition = 0;
        /*
                h  !  2  [  a  a  3  [  b  ]  c  3  [  d  ]  e  ]  ?  g  h
                0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19

                !  2  [  a  b  3  [  c  d  4  [  g  h  ]  ]  ]  ?  f
                0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17
         */
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '[') {
                bracketsPos.push(i);
            }
            if (str.charAt(i) == ']') {
                int openBracketPos = bracketsPos.pop();
                int multiplicator = Character.getNumericValue(str.charAt(openBracketPos - 1));
                if (openBracketPos > rightPosition) {
                    String templateStr = str.substring(openBracketPos + 1, i);
                    int prevBracketPos = bracketsPos.isEmpty() ? 0 : bracketsPos.peek();
                    int prefixBeginPos = Math.max(rightPosition, prevBracketPos) + 1;
                    String prefixStr = str.substring(prefixBeginPos, openBracketPos - 1);
                    recursiveResult = recursiveResult.concat(unpackStringToken(prefixStr, multiplicator, templateStr));
                } else {
                    // тут может быть только постфикс вложенного токена (все открывающие скобки уже погашены)
                    String postfixStr = str.substring(rightPosition + 1, i);
                    recursiveResult = recursiveResult.concat(postfixStr);
                    String templateStr = recursiveResult;
                    int prevBracketPos = bracketsPos.isEmpty() ? -1 : bracketsPos.peek();
                    int prefixBeginPos = prevBracketPos + 1;
                    String prefixStr = str.substring(prefixBeginPos, openBracketPos - 1);
                    recursiveResult = unpackStringToken(prefixStr, multiplicator, templateStr);
                }
                rightPosition = i;
            }
        }

        String finalPostfix = (rightPosition + 1 < str.length()) ? str.substring(rightPosition + 1, str.length()) : null;
        String res = (finalPostfix == null) ? recursiveResult : recursiveResult.concat(finalPostfix);

        return res;
    }

    public static void main(String[] args) {
          System.out.println(parseAndUnpackString("y!2[a2[2[b1[01]c2[23]d]e3[45]]f]?x"));
    }
}
