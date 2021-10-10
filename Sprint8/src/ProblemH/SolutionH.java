package ProblemH;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class SolutionH {
    public static boolean searchSubSequence(int[] sequence, int[] template, int startIdx) {
        int constantBias = sequence[startIdx] - template[0]; //c учетом того, что startIdx уже спозиционирован
        for (int i = 0; i < template.length; i++) {
            if (template[i] != (sequence[startIdx + i] - constantBias)) return false;
        }
        return true;
    }


    public static String process(String[] input) {
        int numberOfMeasures = Integer.parseInt(input[0]);
        int[] measures = new int[numberOfMeasures];
        StringTokenizer measureTokens = new StringTokenizer(input[1], " ", true);
        for (int i = 0; i < numberOfMeasures; i++) {
            measures[i] = Integer.parseInt(measureTokens.nextToken());
            if (measureTokens.hasMoreTokens()) {
                measureTokens.nextToken();
            }
        }
        int templateLength = Integer.parseInt(input[2]);
        int[] template = new int[templateLength];
        StringTokenizer templateTokens = new StringTokenizer(input[3], " ", true);
        for (int i = 0; i < templateLength; i++) {
            template[i] = Integer.parseInt(templateTokens.nextToken());
            if (templateTokens.hasMoreTokens()) {
                templateTokens.nextToken();
            }
        }
        StringJoiner res = new StringJoiner(" ");
        int searchInterval = numberOfMeasures - templateLength + 1;
        for (int i = 0; i < searchInterval; i++) {
            if (searchSubSequence(measures, template, i)) {
                res.add(Integer.toString(i + 1));
            }
        }

        return res.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] buffer = new String[4];

        for (int i = 0; i < 4; i++) {
            buffer[i] = reader.readLine();
        }

        System.out.println(process(buffer));
    }

}
