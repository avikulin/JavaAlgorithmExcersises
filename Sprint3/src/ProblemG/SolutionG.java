package ProblemG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class SolutionG {

    public static void fillSubSequence(Integer[] sequence, int startIdx, int value, int count){
        for(int pos = startIdx; pos < startIdx+count; pos++)
            sequence[pos] = value;
    }

    public static Integer[] sort(Integer[] sequence){
        int[] counterStorage = new int[3];
        for (int i=0; i<sequence.length; i++)
            counterStorage[sequence[i]]++;

        int startIndex = 0;
        for (int i=0; i<counterStorage.length; i++) {
            fillSubSequence(sequence, startIndex, i, counterStorage[i]);
            startIndex += counterStorage[i];
        }
        return sequence;
    }

    public static String printSequence(Integer[] sequence){
        StringJoiner joiner = new StringJoiner(" ","","");
        for (int i=0; i<sequence.length; i++)
            joiner.add(String.valueOf(sequence[i]));

        return joiner.toString();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(reader.readLine());
        Integer[] inputSequence = new Integer[length];
        StringTokenizer inputTokens = new StringTokenizer(reader.readLine());
        for (int i=0; i<length; i++)
            inputSequence[i] = Integer.parseInt(inputTokens.nextToken());

        System.out.println(printSequence(sort(inputSequence)));
    }
}
