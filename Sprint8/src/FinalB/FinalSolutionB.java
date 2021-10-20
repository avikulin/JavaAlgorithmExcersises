/*
    Номер отравки в Яндрекс.Контест - 55065751
 */

package FinalB; //--эти строку нужно закоментировать перед отправкой

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
        ОПИСАНИЕ РЕШЕНИЯ

        Решение состоит из двух основных частей:
        1) Префиксное дерево <Trie>, заполненное поисковыми шаблонами.
           Использована стандартная реализая B-дерева на массиве.
           Каждый узел адресует "страницу" из 26 букв (по условию задачи), упорядоченных в алфавитном порядке.
           Терминальные узли имеют соответсвуюший признак <isTerminal>

           ВРЕМЕННАЯ СЛОЖНОСТЬ ПОCТРОЕНИЯ: O(sum(k)), где k- длины шаблонов
           ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ ПОСТРОЕНИЯ: O(sum(k) * 26) =O(sum(k))

           ВРЕМЕННАЯ СЛОЖНОСТЬ ПОИСК: O(max(k)), где k- длины шаблонов
           ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ ПОИСКА: O(1)

        2) Класс сравнения подстроки с шаблоном <StringMatcher>.
           В его работе используется принцип динамического программирования:
           строка разделяется на 2 части:
           a) подтвержденный префикс, который прошел обработку <Trie>
           b) неподтвержденный постфикс, который итеративно обрабатывается <Trie>, начиная с заданной позиции.

           Состояние динамического программирования <dpStateStore>: двоичный массив, в котором отмечается позиция
           каждого подтвержденного префикса из <Trie> + 1 символ (т.е. позиция первого символа неподтсвержденного
           постфикса, с которой нужно начинать следующую итерацию DP).

           Начальные условия: стандартные - нулевой префикс считаем подтвержденным.

           Функция перехода: провести обработку функцией наложения шаблонов, начиная с первой доступной постфиксной
           позиции в @dpStateStore (позиция, отмеченная признаком true).

           Результат DP: если строка может быть полностью покрыта префиксным деревом, то подтверженный префикс будет
           совпадать со всей строкой. В этом случае в последнем элементе @dpStateStore будет стоить true. В противном
           случае покрытие строки префиксным деревом невозможно.


           ВРЕМЕННАЯ СЛОЖНОСТЬ (в худшем случае): O(n*max(k)), где n - количество символов в строке поиска
           ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ: O(n)

           Рассмотрим пример №3 из задания:
            Шаг 1. i=0
                                  ┌- позиция суффикса, с которой начинается обработка
                                  ▼
                Шаг DP >          0 1 2 3 4 5 6
                                  | | | | | | |
                Строка >          a b a c a b a

                Шаблон >          a b a c ┐
                                  a b a ┐ │
                                        ▼▼
                dpStateStore >  [ 1 0 0 1 1 0 0 0 ]
                                        ▲▲
                                        └ └╴--позиции подтвержденных префиксов по итогам итерации
            Шаг 2. i=3
                                    ┌- позиция суффикса, с которой начинается обработка
                                    ▼
                Шаг DP >          0 1 2 3 4 5 6
                                  | | | | | | |
                Строка >          a b a c a b a

                Шаблон >                c a b a ┐
                                        ▼       ▼
                dpStateStore >  [ 1 0 0 1 1 0 0 1 ]
                                  ▲          ▲ ▲
                                  |           | └╴--позиция подтвержденных префиксов по итогам итерации
                                   полная длина
                                   всей строки

    3) Итоговый расчет сложности алгоритма:

        ВРЕМЕННАЯ СЛОЖНОСТЬ (в худшем случае): O(sum(k)) + O(n*max(k)) = O(n*max(k))
        ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ: O(sum(k)) + O(n) = O(sum(k)+n)

        где n - количество символов в строке поиска
            k- длины шаблонов
 */
class TrieNode {
    private int CHAR_SIZE = 26;
    private boolean isTerminal;
    private TrieNode[] childNodes;

    TrieNode() {
        childNodes = new TrieNode[CHAR_SIZE];
        isTerminal = false;
    }

    public TrieNode addChildNode(char symbol) {
        int offset = symbol - 'a';
        TrieNode node = childNodes[offset];
        if (node == null) {
            node = new TrieNode();
            childNodes[offset] = node;
        }
        return node;
    }

    TrieNode getChildNodeBySymbol(char symbol) {
        return childNodes[symbol - 'a'];
    }

    public void setTerminalState() {
        isTerminal = true;
    }

    boolean getTerminalState() {
        return isTerminal;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insertPattern(String searchPattern) {
        TrieNode trieNode = root;
        for (int i = 0; i < searchPattern.length(); i++) {
            char symbol = searchPattern.charAt(i);
            trieNode = trieNode.addChildNode(symbol);
        }
        trieNode.setTerminalState();
    }

    public TrieNode getRoot() {
        return root;
    }
}

class StringMatcher {
    private Trie patternsStore;
    private String searchContext;
    private boolean[] matchFlags;


    public StringMatcher(String s, Trie pattens) {
        patternsStore = pattens;
        searchContext = s;
        matchFlags = new boolean[searchContext.length() + 1];
        matchFlags[0] = true;
    }

    private void matchFrom(int startPos, int endPos) {
        TrieNode currentNode = patternsStore.getRoot();

        for (int i = startPos; i < endPos; i++) {
            if (currentNode != null) {
                currentNode = currentNode.getChildNodeBySymbol(searchContext.charAt(i));
            } else {
                break;
            }
            if (currentNode != null && currentNode.getTerminalState()) {
                matchFlags[i + 1] = true;
            }
        }
    }

    public boolean testCovering() {
        int charSequenceLength = searchContext.length();
        for (int i = 0; i < charSequenceLength; i++) {
            if (!matchFlags[i]) {
                continue;
            }
            matchFrom(i, charSequenceLength);
        }
        return matchFlags[charSequenceLength];
    }

}

public class FinalSolutionB {
    public static String process(String[] input) {
        Trie trie = new Trie();
        String strContext = input[0];
        for (int i = 2; i < input.length; i++) {
            trie.insertPattern(input[i]);
        }

        StringMatcher stringMatcher = new StringMatcher(strContext, trie);
        boolean res = stringMatcher.testCovering();
        return res ? "YES" : "NO";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String strToSearchIn = reader.readLine();
        String strNumberOfPatterns = reader.readLine();
        int numberOfPatterns = Integer.parseInt(strNumberOfPatterns);
        String[] buffer = new String[numberOfPatterns + 2];
        buffer[0] = strToSearchIn;
        buffer[1] = strNumberOfPatterns;
        int lowerBound = numberOfPatterns + 2;
        for (int i = 2; i < lowerBound; i++) {
            buffer[i] = reader.readLine();
        }
        System.out.println(process(buffer));
    }
}
