package ProblemH;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SolutionH {
    public static String constructValue(String[] inputNumbers){
        Arrays.sort(inputNumbers, new Comparator<String>() {
            @Override
            public int compare(String X, String Y) {
                // X = 14, Y=5
                // XY = 145, YX = 514

                String XY = X+Y;
                String YX = Y+X;
                return XY.compareTo(YX)*-1; //обратный порядок - большее число вперед
            }
        });

        StringBuilder builder = new StringBuilder();
        for(int i=0; i<inputNumbers.length;i++)
            builder.append(inputNumbers[i]);

        return builder.toString();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());
        String[] input = new String[size];
        StringTokenizer inputTokens = new StringTokenizer(reader.readLine());
        for (int i=0; i<size; i++)
            input[i] = inputTokens.nextToken();

        System.out.println(constructValue(input));
    }
}
