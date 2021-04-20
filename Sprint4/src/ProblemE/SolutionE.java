package ProblemE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SolutionE {
    public static String getLongestUniqueSubSequence(String input) {
        int[] positionalIdx = new int[26];
        Arrays.fill(positionalIdx, -1);

        int res = 0;
        for (int startIdx = 0, endIdx = 0; endIdx < input.length(); endIdx++) {
            char currentChar = input.charAt(endIdx);
            int currentCharIdx = (int) currentChar - 97;

            if (positionalIdx[currentCharIdx] != -1)
                startIdx = Math.max(startIdx, positionalIdx[currentCharIdx] + 1);

            if (res < endIdx - startIdx + 1)
                res = endIdx - startIdx + 1;

            positionalIdx[currentCharIdx] = endIdx;
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(getLongestUniqueSubSequence(reader.readLine()));
    }
}
