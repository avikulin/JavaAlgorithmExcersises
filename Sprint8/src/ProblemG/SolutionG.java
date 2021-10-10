package ProblemG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class SolutionG {
    public static String process(String[] input) {
        Map<String, Integer> storage = new HashMap<>();
        for (int i = 1; i < input.length; i++) {
            storage.computeIfPresent(input[i], (key, count) -> count = count + 1);
            storage.putIfAbsent(input[i], 1);
        }

        int frequencyLimit = -1;
        String mostFrequentWord = new String();
        for (Map.Entry<String, Integer> kv : storage.entrySet()) {
            if (kv.getValue() > frequencyLimit) {
                mostFrequentWord = kv.getKey();
                frequencyLimit = kv.getValue();
            }

            if ((kv.getValue() == frequencyLimit) && (kv.getKey().compareTo(mostFrequentWord) == -1)) {
                mostFrequentWord = kv.getKey();
            }
        }

        return mostFrequentWord;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String strCount = reader.readLine();
        int num = Integer.parseInt(strCount);
        String[] buffer = new String[num + 1];
        buffer[0] = strCount;
        for (int i = 1; i < buffer.length; i++) {
            buffer[i] = reader.readLine();
        }
        System.out.println(process(buffer));
    }


}
