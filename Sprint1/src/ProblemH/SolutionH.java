package ProblemH;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SolutionH {
    private static String normalizeLength(String string, int length){
        if (string.length() >= length) return string;

        StringBuilder sb = new StringBuilder();
        for (int i=0; i < length - string.length();i++){
            sb.append("0");
        }
        sb.append(string);
        return sb.toString();
    }

    public static String binaryAdd(String[] input){
        int maxLengthIdx = input[0].length() > input[1].length() ? 0:1;
        input[1 - maxLengthIdx] = normalizeLength(input[1 - maxLengthIdx], input[maxLengthIdx].length());
        int numberOfDigits = input[maxLengthIdx].length();
        int curry[] = new int[numberOfDigits+1];// one more than original string of digits
        curry[numberOfDigits]=0; // last one curry is not applicable

        StringBuilder sb = new StringBuilder();
        for (int i=numberOfDigits-1; i>=0; i--){
            int digitOne = (input[0].charAt(i)=='0') ? 0: 1;
            int digitTwo = (input[1].charAt(i)=='0') ? 0: 1;

            int sum = digitOne + digitTwo + curry[i+1];
            curry[i] = (sum > 1) ? 1:0;
            sb.append(sum%2);
        }
        if (curry[0]==1)
            sb.append(1);

        sb.reverse();
        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(binaryAdd(new String[]{reader.readLine(), reader.readLine()}));
    }
}
