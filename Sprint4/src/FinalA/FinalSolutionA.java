//---код посылки в Яндекс.Контест - 51230008

package FinalA; // эту строку нужно закомментировать перед отправкой в Яндекс.Контест.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.atomic.LongAdder;

/*
        ОПИСАНИЕ РЕШЕНИЯ

        Основой решения вляется класс <FullTextIndex>, который реализует функционал индексирования входных документов
        и выполнение поиска по данному индексу с учетом критериев релевантности.

        ФУнкционал данного класса состоит из следующих основных частей:

        1) Построение индекса вхождения отдельных слов во фразу (частотный индекс). Функционал выполняет слеющее
        отображение <getIndexOfOccurrences>: "buy coffee in coffee shop" -> {"buy":1, "coffee":2, "in":1, "shop":1}

        Поскольку в реализации используется HashMap, то операции вставки и получения элемента осуществляются за О(1).
        В реализации есть преобразование входной строки в массив строк, после чего выполнение reduce-функции в потоке.
        Каждая из этих операций требует столько же дополнительной памяти, как и входная строка.

        ВРЕМЕННАЯ СЛОЖНОСТЬ: O(m), где m - количество слов в документе.
        ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ: 2*O(m*k) = O(m*k), где m - количество слов в документе, k - средняя длина слова.

        2) Построение обратного индекса (<constructReverseIndex>) из индекса вхождения:

            { "buy":{█}, "coffee":{█}, "in":{█}, "shop":{█} } ◀- HashMap<String, ... >
                     ▲
                     |
                     |
                     ┠────────────────────────────┐
                     |                            ▲
                     |                            |
                     |   ┌--- <индекс вхождения ключа в данных документ>
                     |   ▼
                     { 1: 1, 2:4, 3:2 } ◀- HashMap<Integer, Integer>
                       ▲
                       └--- <код (порядковый номер) индексированного документа>

        ВРЕМЕННАЯ СЛОЖНОСТЬ: O(n)*O(m),
                                        где m - количество слов в документе (в HashTable вставка производится за O(1)).
                                            n - количество добавляемых документов
        ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ: 2*O(m*k) = O(m*k), где m - количество слов в документе, k - средняя длина слова.

        3) построение обратного отображения <reverseReflection>. Дання функция слудит для преобразования следующего
        вида:
                reverseReflection:{1:'a', 2:'б', 3:'a', 4:'б'} -> {'a':[1, 3], 'б':[2, 4]}

                                получение Map.Entry<K, V> из HashMap-источника
                                |         добавление значения в TreeMap-приемник
                                |         |
        ВРЕМЕННАЯ СЛОЖНОСТЬ: O(1) * n * O(log n) = O(n*log n), где n - колчисетство уникальных пар в HashMap
        ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ: O(n) -  где n - колчисетство уникальных пар в HashMap

        ОПИСАНИЕ АЛГОРИТМОВ

        1) Добавление документа в индекс. Производится последовательный вызов функций:
            а) построения индекса вхождения (<getIndexOfOccurrences>) по содержимому документа;
            б) добавление полученного индекса входждения в общую структуру обратного индекса (<constructReverseIndex>);

            ВРЕМЕННАЯ СЛОЖНОСТЬ: O(m) + O(n)*O(m) = O(mn), где m - количество слов в документe, n-количество документов.
            ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ: O(m*k) + O(m*k) = 2 * O(mk) = O(mk), где
                                                                                    m - количество слов в документе,
                                                                                    k - средняя длина слова.
        2) Поиск документа в индексе:
            а) построение индекса вхождения по содержимому запроса
            б) поиск вхождения в обратный индекс каждого уникального слова из запроса.
            б) получение объединенной коллекции кортежей (<код документа>, <количество вхождений слова из запроса>).
                Поскольку все слова из запроса принимаются с одинаковым весом, то в данной коллекции количество
                вхождений трактуется как "количество вхождений любого слова из запроса".
            г) построение инвертированного индекса HashMap<> -> TreeMap<> с сохранением обратного порядка ключей:

                     количество вхождений любого слова из запроса в документ
                    /         |         |          |           |
            {<Док1>:1, <Док2>:2, <Док3>:3, <Док4>:<1>, <Док5>:<3>}->{1:[<Док1>, <Док4>], 2:[<Док2>], 3:[<Док3>, <Док5>]}
                                                                      \                  |           |
                                                                        индекс релевантности документа запросу
            д) сортировка (in-place) по возрастанию и получение первых 5 элементов из инвертированного индекса.

                                          получение 5 элементов из TreeMap по ключу
                                         /
               В в худшем случае это O(Log m) + O(n/5*log n/5) + 5 * O(1) = n*Log(n), где n - количество документов
                                                   \                   \                      в индексе
                                                    сортировка списка    получение документа
                                                    документов (пятая    из списка ArrayList<>
                                                    часть документов в
                                                    каждом ключе)

                                                            индекс вхождения
                                                            |       получение объединенной коллекции кортежей
                                                            |       |       построение инвертированного индекса
                                                            |       |       |            сортировка и получение
                                                            |       |       |            первых 5 элементов
                                                            |       |       |           /
                                                            |       |       |           |
           ИТОГО ВРЕМЕННАЯ СЛОЖНОСТЬ (в худшем случае):  O(m) + m*Log(m) + m*Log(m) + O(n*log n) =
                                                         = 2*O(m*log n) + O(n*log n) = O(n*log n), так как n>>m

                                                                    индекс вхождения по словам запроса
                                                                    |      коллекция кортежей <док>, <кол-во вхождений>)
                                                                    |       |       /    инвертированный индекс
                                                                    |       |       |       (1:[<Док1>, <Док4>])
                                                                    |       |       |
           ИТОГО ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ  (в худшем случае):  O(m*k) + O(m*n) + O(m*n) + O(1) = 2*O(m*n) = O(m*n)
                                                                                              |                 |
                                                                            хранение результата                 |
                                                                                              { так как m*n >> m*k }

           где  m - количество слов в запросе
                n - колчетство документов
                k - средняя длина слова.

 */


/**
 * Основной класс, реализующий функционал построения индекса и обработки запросов к нему.
 */
class FullTextIndex {
    private Map<String, Map<Integer, Integer>> reverseIndexStore;
    private int docsCounter;
    private final int MAX_ITEMS_IN_RESPONSE = 5;

    FullTextIndex() {
        reverseIndexStore = new HashMap<>();
        docsCounter = 0;
    }

    /**
     * Построение индекса по содержимому переданного документа.
     *
     * @param document Строка содержимого документа.
     */
    public void addDocument(String document) {
        docsCounter++;
        Map<String, LongAdder> indexOfOccurrence = getIndexOfOccurrences(document);
        constructReverseIndex(indexOfOccurrence, docsCounter);
    }

    /**
     * Построение индекса вхождения по уникальным словам переданной входной строки.
     *
     * @param str Входная строка, по которой производится индексирование.
     * @return Структура вида {<слово>:<кол-во вхождений>}
     */
    private Map<String, LongAdder> getIndexOfOccurrences(String str){
        Map<String, LongAdder> res = new HashMap<>();
        String[] wordSequence = str.split("\\s");
        for (String word: wordSequence)
            res.computeIfAbsent(word, key -> new LongAdder()).increment();

        return res;
    }

    /**
     * Построение и сохранение обратного индекса по документу.
     *
     * @param occurrenceIdx Структура индекса вхождения, построенного по содержимому документа.
     * @param docID         Код индексируемого документа.
     */
    private void constructReverseIndex(Map<String, LongAdder> occurrenceIdx, int docID) {
        for (Map.Entry<String, LongAdder> kv : occurrenceIdx.entrySet()) {
            reverseIndexStore
                    .computeIfAbsent(kv.getKey(), key->new HashMap<>())
                        .put(docID, kv.getValue().intValue());
        }
    }

    /**
     * Построение обратного (инвертированного) отображения структуры данных индекса:
     * {1:'a', 2:'б', 3:'a', 4:'б'} -> {'a':[1, 3], 'б':[2, 4]}
     *
     * @param source      Исходный индекс {<ключ1>:<значение1>, <ключ2>:<значение1>, ...}
     * @param destination Инвертированный индекс {<значение1>:[<ключ1>, <ключ2>, ...]}
     */
    private void reverseReflection(Map<Integer, LongAdder> source, Map<Integer, List<Integer>> destination) {
        for (Map.Entry<Integer, LongAdder> kv : source.entrySet()) {
            int keyForReversedMap = kv.getValue().intValue();
            int valueForReversedMap = kv.getKey();
            destination
                    .computeIfAbsent(keyForReversedMap,ArrayList::new)
                    .add(valueForReversedMap);
        }

    }

    /**
     * Поиск документов в индексе по критерию релевантности запроса.
     *
     * @param query Содержимое строки запроса
     * @return Строка с номерами документов вида "Док1, Док2, Док3, ... "
     */
    public String findRelevantDocs(String query) {
        Map<String, LongAdder> queryUniqueTokens = getIndexOfOccurrences(query);
        Map<Integer, LongAdder> hitsSumRegister = new HashMap<>();

        for (Map.Entry<String, LongAdder> queryToken : queryUniqueTokens.entrySet()) {
            String queryKey = queryToken.getKey();
            Map<Integer, Integer> indexHits = reverseIndexStore.get(queryKey);
            if (indexHits != null) { //если слова нет в индексе - пропускаем итерацию цикла и переходим к следующему.
                for (Map.Entry<Integer, Integer> kv : indexHits.entrySet()) {
                    hitsSumRegister.computeIfAbsent(kv.getKey(),key -> new LongAdder()).add(kv.getValue());
                }
            }
        }

        // Тут важен порядок элементов - поэтому TreeMap.
        // За сохранение порядка платим логарифмическим временем доступа.
        Map<Integer, List<Integer>> relevantDocsIdx = new TreeMap<>(Collections.reverseOrder());
        reverseReflection(hitsSumRegister, relevantDocsIdx);

        StringJoiner responseBuilder = new StringJoiner(" ");
        int responseItemCounter = 0;
        for (Map.Entry<Integer, List<Integer>> kv : relevantDocsIdx.entrySet()) {
            List<Integer> docsIdSequence = kv.getValue();
            docsIdSequence.sort(Comparator.naturalOrder());
            for (int id : docsIdSequence) {
                responseBuilder.add(String.valueOf(id));
                responseItemCounter++;
                if (responseItemCounter == MAX_ITEMS_IN_RESPONSE) return responseBuilder.toString();
            }
        }
        return responseBuilder.toString();
    }
}

/**
 * Основной класс решения и точка входа в него.
 */
public class FinalSolutionA {
    /**
     * Функция приема и обработки входных параметров
     *
     * @param inputSequence Массив строкового представления входных параметров.
     * @return Строковое представление результата.
     */
    public static String processQueries(String[] inputSequence) {
        FullTextIndex indexManager = new FullTextIndex();
        int numOfDocs = Integer.parseInt(inputSequence[0]);

        for (int i = 1; i < numOfDocs + 1; i++)
            indexManager.addDocument(inputSequence[i]);

        StringJoiner responseBuilder = new StringJoiner("\n");
        int numOfQueries = Integer.parseInt(inputSequence[numOfDocs + 1]);
        for (int i = numOfDocs + 2; i < numOfDocs + numOfQueries + 2; i++) {
            String res = indexManager.findRelevantDocs(inputSequence[i]);
            if (res != "")
                responseBuilder.add(res);
        }
        return responseBuilder.toString();
    }

    /**
     * Точка входа в программу.
     * Зачитываение входных параметров из консоли.
     * Вывод результатов работы программы в консоль.
     *
     * @param args Аргументы командной строки (для целей совместимости).
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numOfDocs = Integer.parseInt(reader.readLine());
        String[] docBuffer = new String[numOfDocs];
        for (int i = 0; i < numOfDocs; i++)
            docBuffer[i] = reader.readLine();

        int numOfQueries = Integer.parseInt(reader.readLine());
        String[] queryBuffer = new String[numOfQueries];
        for (int i = 0; i < numOfQueries; i++)
            queryBuffer[i] = reader.readLine();

        String[] inputBuffer = new String[numOfDocs + numOfQueries + 2];
        inputBuffer[0] = String.valueOf(numOfDocs);
        inputBuffer[numOfDocs + 1] = String.valueOf(numOfQueries);
        for (int i = 1; i < numOfDocs + numOfQueries + 2; i++) {
            if (i < numOfDocs + 1)
                inputBuffer[i] = docBuffer[i - 1];

            if (i > numOfDocs + 1)
                inputBuffer[i] = queryBuffer[i - numOfDocs - 2];
        }

        System.out.println(processQueries(inputBuffer));
    }
}
