package FinalA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

class FullTextIndex{
    private Map<String, List<Integer>> reverseIndexStore;
    private Map<Integer, String> documentStore;
    private int docsCounter;
    FullTextIndex(){
        documentStore = new TreeMap<>();
        reverseIndexStore = new TreeMap<>();
        docsCounter = 0;
    }

    private TreeMap<String, Integer> getIndexOfOccurrences(String str){
        TreeMap<String, Integer> map = new TreeMap<>();
        StringTokenizer tokenizer = new StringTokenizer(str);
        while (tokenizer.hasMoreTokens()){
            String token = tokenizer.nextToken();
            if (map.containsKey(token)){
                int counter = map.get(token);
                map.put(token, ++counter);
            } else {
                map.put(token, 1);
            }
        }
        return map;
    }
}

public class FinalSolutionA {
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
        inputBuffer[numOfDocs] = String.valueOf(numOfQueries);
        for (int i=1; i<numOfDocs+numOfQueries+2;i++){
            if (i<numOfDocs)
                inputBuffer[i] = docBuffer[i-1];
            if (i>numOfDocs)
                inputBuffer[i] = queryBuffer[i - numOfDocs -1];
        }
    }
}
