package FinalA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class FullTextIndex{
    private Map<String, Map<Integer, Integer>> reverseIndexStore;
    private int docsCounter;
    private final int MAX_ITEMS_IN_RESPONCE = 5;

    FullTextIndex(){
        reverseIndexStore = new TreeMap<>();
        docsCounter = 0;
    }

    private Map<String, Integer> getIndexOfOccurrences(String str){
        Map<String, Integer> map = new HashMap<>();
        StringTokenizer tokenizer = new StringTokenizer(str);
        while (tokenizer.hasMoreTokens()){
            String wordOccurrenceKey = tokenizer.nextToken();
            Integer wordOccurrenceValue = map.get(wordOccurrenceKey); //используем Integer как nullable-тип.
            if (wordOccurrenceValue != null){
                map.put(wordOccurrenceKey, ++wordOccurrenceValue);
            } else {
                map.put(wordOccurrenceKey, 1);
            }
        }
        return map;
    }

    private void constructReverseIndex(Map<String, Integer> occurrenceIdx, int docID){
        for (Map.Entry<String, Integer> kv: occurrenceIdx.entrySet()){
            Map<Integer, Integer> hitsRate = reverseIndexStore.get(kv.getKey());
            if (hitsRate == null){
                Map<Integer, Integer> newHitsRate  = new TreeMap<>();
                newHitsRate.put(docID, kv.getValue());
                reverseIndexStore.put(kv.getKey(), newHitsRate);
            } else {
                hitsRate.put(docID,kv.getValue());
            }
        }
    }

    public void AddDocument(String document){
        docsCounter++;
        Map<String, Integer> indexOfOccurrence = getIndexOfOccurrences(document);
        constructReverseIndex(indexOfOccurrence, docsCounter);
    }

    private void reverseReflection(Map<Integer, Integer> source, Map<Integer, List<Integer>> destination){
        for(Map.Entry<Integer, Integer> kv :source.entrySet()){
            int keyForReversedMap = kv.getValue();
            int valueForReversedMap = kv.getKey();
            List<Integer> relevantDocsIdSequence = destination.get(keyForReversedMap);
            if (relevantDocsIdSequence != null){
                destination.get(keyForReversedMap).add(valueForReversedMap);
            } else {
                List<Integer> docsIdSequence = new ArrayList<>();
                docsIdSequence.add(valueForReversedMap);
                destination.put(keyForReversedMap, docsIdSequence);
            }
        }

    }

    public String findRelevantDocs(String query){
        StringTokenizer tokenizer = new StringTokenizer(query);
        Map<Integer, Integer> hitsSumRegister = new TreeMap<>();
        while (tokenizer.hasMoreTokens()){
            String queryKey = tokenizer.nextToken();
            Map<Integer, Integer> indexHits = reverseIndexStore.get(queryKey);
            if (indexHits != null) { //если слова нет в индексе - пропускаем итерацию цикла и переходим к следующему.
                for (Map.Entry<Integer, Integer> kv : indexHits.entrySet()) {
                    Integer hitsValue = hitsSumRegister.get(kv.getKey()); //используем Integer как nullable-тип.
                    if (hitsValue != null) {
                        hitsValue += kv.getValue();
                        hitsSumRegister.put(kv.getKey(), hitsValue);
                    } else {
                        hitsSumRegister.put(kv.getKey(), kv.getValue());
                    }
                }
            }
        }

        Map<Integer, List<Integer>> relevantDocsIdx = new TreeMap<>(Collections.reverseOrder());
        reverseReflection(hitsSumRegister, relevantDocsIdx);

        StringJoiner responseBuilder = new StringJoiner(" ");
        int responseItemCounter = 0;
        for (Map.Entry<Integer, List<Integer>> kv : relevantDocsIdx.entrySet()){
            List<Integer> docsIdSequence = kv.getValue();
            docsIdSequence.sort(Comparator.naturalOrder());
            for (int id: docsIdSequence){
                responseBuilder.add(String.valueOf(id));
                responseItemCounter++;
                if (responseItemCounter == MAX_ITEMS_IN_RESPONCE) return responseBuilder.toString();
            }
        }
        return responseBuilder.toString();
    }
}



public class FinalSolutionA {
    public static String processQueries(String[] inputSequence){
        FullTextIndex indexManager = new FullTextIndex();
        int numOfDocs = Integer.parseInt(inputSequence[0]);

        for (int i=1; i < numOfDocs+1; i++)
            indexManager.AddDocument(inputSequence[i]);

        StringJoiner responseBuilder = new StringJoiner("\n");
        int numOfQueries = Integer.parseInt(inputSequence[numOfDocs+1]);
        for (int i = numOfDocs+2; i < numOfDocs +numOfQueries + 2; i++){
            String res = indexManager.findRelevantDocs(inputSequence[i]);
            if (res!="")
                responseBuilder.add(res);
        }
        return responseBuilder.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numOfDocs = Integer.parseInt(reader.readLine());
        String[] docBuffer = new String[numOfDocs];
        for (int i=0; i < numOfDocs; i++)
            docBuffer[i] = reader.readLine();

        int numOfQueries = Integer.parseInt(reader.readLine());
        String[] queryBuffer = new String[numOfQueries];
        for (int i=0; i < numOfQueries; i++)
            queryBuffer[i] = reader.readLine();

        String[] inputBuffer = new String[numOfDocs + numOfQueries + 2];
        inputBuffer[0] = String.valueOf(numOfDocs);
        inputBuffer[numOfDocs + 1] = String.valueOf(numOfQueries);
        for (int i=1; i<numOfDocs+numOfQueries+2;i++){
            if (i<numOfDocs+1)
                inputBuffer[i] = docBuffer[i-1];

            if (i>numOfDocs+1)
                inputBuffer[i] = queryBuffer[i - numOfDocs - 2];
        }

        System.out.println(processQueries(inputBuffer));
    }
}
