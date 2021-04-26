package FinalA; // эту строку нужно закомментировать перед отправкой в Яндекс.Контест.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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

        ВРЕМЕННАЯ СЛОЖНОСТЬ: O(n)*O(m), где m - количество слов в документе.
        ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ: 2*O(m*k) = O(m*k), где m - количество слов в документе, k - средняя длина слова.


 */
class FullTextIndex {
    private Map<String, Map<Integer, Integer>> reverseIndexStore;
    private int docsCounter;
    private final int MAX_ITEMS_IN_RESPONCE = 5;

    FullTextIndex() {
        reverseIndexStore = new HashMap<>();
        docsCounter = 0;
    }

    private Map<String, Integer> getIndexOfOccurrences(String str) {
        String[] wordSequence = str.split(" ");
        return Arrays
                .stream(wordSequence)
                .collect(Collectors
                        .groupingBy(Function.identity(),
                                Collectors.
                                        collectingAndThen(Collectors.counting(),
                                                Long::intValue)));
    }

    private void constructReverseIndex(Map<String, Integer> occurrenceIdx, int docID) {
        for (Map.Entry<String, Integer> kv : occurrenceIdx.entrySet()) {
            Map<Integer, Integer> hitsRate = reverseIndexStore.get(kv.getKey());
            if (hitsRate == null) {
                Map<Integer, Integer> newHitsRate = new HashMap<>();
                newHitsRate.put(docID, kv.getValue());
                reverseIndexStore.put(kv.getKey(), newHitsRate);
            } else {
                hitsRate.put(docID, kv.getValue());
            }
        }
    }

    public void AddDocument(String document) {
        docsCounter++;
        Map<String, Integer> indexOfOccurrence = getIndexOfOccurrences(document);
        constructReverseIndex(indexOfOccurrence, docsCounter);
    }

    private void reverseReflection(Map<Integer, Integer> source, Map<Integer, List<Integer>> destination) {
        for (Map.Entry<Integer, Integer> kv : source.entrySet()) {
            int keyForReversedMap = kv.getValue();
            int valueForReversedMap = kv.getKey();
            List<Integer> relevantDocsIdSequence = destination.get(keyForReversedMap);
            if (relevantDocsIdSequence != null) {
                destination.get(keyForReversedMap).add(valueForReversedMap);
            } else {
                List<Integer> docsIdSequence = new ArrayList<>();
                docsIdSequence.add(valueForReversedMap);
                destination.put(keyForReversedMap, docsIdSequence);
            }
        }

    }

    public String findRelevantDocs(String query) {
        Map<String, Integer> queryUniqueTokens = getIndexOfOccurrences(query);
        Map<Integer, Integer> hitsSumRegister = new HashMap<>();

        for (Map.Entry<String, Integer> queryToken : queryUniqueTokens.entrySet()) {
            String queryKey = queryToken.getKey();
            Map<Integer, Integer> indexHits = reverseIndexStore.get(queryKey);

            if (indexHits != null) { //если слова нет в индексе - пропускаем итерацию цикла и переходим к следующему.
                Integer hitsValue = 0; //экономим время на создании и инициализации ссылочного типа
                for (Map.Entry<Integer, Integer> kv : indexHits.entrySet()) {
                    hitsValue = hitsSumRegister.get(kv.getKey()); //используем Integer как nullable-тип.
                    if (hitsValue != null) {
                        hitsValue += kv.getValue();
                        hitsSumRegister.put(kv.getKey(), hitsValue);
                    } else {
                        hitsSumRegister.put(kv.getKey(), kv.getValue());
                    }
                }
            }
        }

        //тут важен порядок элементов - поэтому TreeMap.
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
                if (responseItemCounter == MAX_ITEMS_IN_RESPONCE) return responseBuilder.toString();
            }
        }
        return responseBuilder.toString();
    }
}

public class FinalSolutionA {
    public static String processQueries(String[] inputSequence) {
        FullTextIndex indexManager = new FullTextIndex();
        int numOfDocs = Integer.parseInt(inputSequence[0]);

        for (int i = 1; i < numOfDocs + 1; i++)
            indexManager.AddDocument(inputSequence[i]);

        StringJoiner responseBuilder = new StringJoiner("\n");
        int numOfQueries = Integer.parseInt(inputSequence[numOfDocs + 1]);
        for (int i = numOfDocs + 2; i < numOfDocs + numOfQueries + 2; i++) {
            String res = indexManager.findRelevantDocs(inputSequence[i]);
            if (res != "")
                responseBuilder.add(res);
        }
        return responseBuilder.toString();
    }

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
