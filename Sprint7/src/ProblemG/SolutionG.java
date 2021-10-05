package ProblemG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SolutionG {
    public static String process(String[] input) {
        int totalSum = Integer.parseInt(input[0]);
        int numberOfNominals = Integer.parseInt(input[1]);
        int[] dpParamters = new int[numberOfNominals];
        StringTokenizer tokenizer = new StringTokenizer(input[2], " ", true);
        for (int i = 0; i < numberOfNominals; i++) {
            dpParamters[i] = Integer.parseInt(tokenizer.nextToken());
            if (tokenizer.hasMoreTokens()) {
                tokenizer.nextToken();
            }
        }

        Arrays.sort(dpParamters);

        int[] dpCache = new int[totalSum + 1];
        dpCache[0] = 1;

        for (int coinTypeIdx = 0; coinTypeIdx < dpParamters.length; coinTypeIdx++) {
            int coinNominalValue = dpParamters[coinTypeIdx];
            for (int i = coinNominalValue; i <= totalSum; i++) {
                dpCache[i] += dpCache[i - coinNominalValue];
            }
        }
        return Integer.toString(dpCache[totalSum]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputBuffer = new String[3];
        inputBuffer[0] = reader.readLine();
        inputBuffer[1] = reader.readLine();
        inputBuffer[2] = reader.readLine();
        System.out.println(process(inputBuffer));
    }
}
