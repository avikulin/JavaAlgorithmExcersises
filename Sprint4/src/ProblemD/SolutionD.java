package ProblemD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

public class SolutionD {
    public static String groupRecords(String[] input){
        if (input.length ==0) return "";

        Map<String, Integer> groupsStorage = new LinkedHashMap<>();
        for (int i=1; i<input.length;i++){
            String key = input[i];
            if (groupsStorage.containsKey(key)){
                int currentValue = groupsStorage.get(key);
                groupsStorage.put(key, ++currentValue);
            } else {
                groupsStorage.put(key, 1);
            }
        }

        StringJoiner joiner = new StringJoiner("\n");
        groupsStorage.forEach((k,v)->joiner.add(k));

        return joiner.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numOfRecords = Integer.parseInt(reader.readLine());
        String[] buffer = new String[numOfRecords+1];
        buffer[0] = String.valueOf(numOfRecords);

        for (int i=1; i< buffer.length; i++)
            buffer[i] = reader.readLine();

        System.out.println(groupRecords(buffer));
    }
}
