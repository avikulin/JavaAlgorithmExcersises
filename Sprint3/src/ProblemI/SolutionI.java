package ProblemI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SolutionI {
    public static String getTopElements(int[] sequence, int topLimit){
        Map<Integer, Integer> presenceScore = new TreeMap<>();

        for (int i=0; i<sequence.length;i++)
            if (presenceScore.containsKey(sequence[i])) {
                int score = presenceScore.get(sequence[i]);
                presenceScore.put(sequence[i], ++score);
            } else {
                presenceScore.put(sequence[i], 1);
            }

        Map<Integer, SortedSet<Integer>> instituteByScore = new TreeMap<>(Collections.reverseOrder());

        for (Map.Entry<Integer, Integer> kv:presenceScore.entrySet()){
            int instituteID = kv.getKey();
            int scoreValue = kv.getValue();

            if (instituteByScore.containsKey(scoreValue)) {
                instituteByScore.get(scoreValue).add(instituteID);
            } else {
                SortedSet<Integer> set = new TreeSet<>();
                set.add(instituteID);
                instituteByScore.put(scoreValue, set);
            }
        }

        int elementCounter = 0;
        StringJoiner stringJoiner = new StringJoiner(" ", "","");
        for (Map.Entry<Integer, SortedSet<Integer>> kv: instituteByScore.entrySet()){
            for (Integer element:kv.getValue()) {
                elementCounter++;
                stringJoiner.add(String.valueOf(element));
                if (elementCounter == topLimit) return stringJoiner.toString();
            }
        }

        return stringJoiner.toString();
    }

    public static String process (String[] input){
        int numberOfStudents = Integer.parseInt(input[0]);
        int[] presenceFactsSequence = new int[numberOfStudents];

        StringTokenizer presenceTokens = new StringTokenizer(input[1]);
        for (int i=0; i<numberOfStudents;i++)
            presenceFactsSequence[i] = Integer.parseInt(presenceTokens.nextToken());

        int topLimit = Integer.parseInt(input[2]);

        return getTopElements(presenceFactsSequence, topLimit);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputParams = new String[3];
        for (int i=0; i<3; i++)
            inputParams[i] = reader.readLine();

        System.out.println(process(inputParams));
    }
}
