package ProblemA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class SolutionA {
    public static String process(String[] input) {
        String s1 = input[0];
        String s2 = input[1];
        if (s1.length() != s2.length()) return "NO";

        int[] charsOrderA = new int[27];
        int[] charsOrderB = new int[27];
        int charPosA = 1;
        int charPosB = 1;
        for (int i = 0; i < s1.length(); i++) {
            int charCodeA = s1.charAt(i) - 97;
            int charCodeB = s2.charAt(i) - 97;

            int currentCharA = -1;
            int currentCharB = -1;
            if (charsOrderA[charCodeA] == 0) {
                charsOrderA[charCodeA] = charPosA;
                currentCharA = charPosA;
                charPosA++;
            } else {
                currentCharA = charsOrderA[charCodeA];
            }

            if (charsOrderB[charCodeB] == 0) {
                charsOrderB[charCodeB] = charPosB;
                currentCharB = charPosB;
                charPosB++;
            } else {
                currentCharB = charsOrderB[charCodeB];
            }

            if (currentCharA != currentCharB) return "NO";
        }

        return "YES";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] buffer = new String[2];
        buffer[0] = reader.readLine();
        buffer[1] = reader.readLine();
        System.out.println(process(buffer));
    }
}
