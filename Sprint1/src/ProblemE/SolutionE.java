package ProblemE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeMap;

class YUtils {
    static String[] GetFromConsole(BufferedReader reader, int numberOfStrings) throws IOException {
        String[] res = new String[numberOfStrings];
        for (int i=0; i < numberOfStrings; i++){
            res[i] = reader.readLine();
        }
        return res;
    }

    static int GetParamScalar(String input, boolean needToTrim) {
        if (needToTrim)
            input = input.trim();
        return Integer.parseInt(input);
    }

    static int[] GetParamsVector(String input, int numberOfParams) {
        int[] res = new int[numberOfParams];
        StringTokenizer tokenizer = new StringTokenizer(input);

        for (int i=0; i< numberOfParams; i++){
            String value = tokenizer.nextToken();
            res[i] = GetParamScalar(value, false);
        }
        return res;
    }
}

public class SolutionE {
    public static String[] GetLongestWord(String[] input){
        String sourceText = input[0].trim();
        StringTokenizer tokenizer = new StringTokenizer(sourceText);

        String token;
        String maxToken = new String();
        int maxTokenLength = 0;

        while (tokenizer.hasMoreTokens()){
            token = tokenizer.nextToken();
            if (token.length()>maxTokenLength){
                maxToken = token;
                maxTokenLength = token.length();
            }
        }
        return new String[]{maxToken, String.valueOf(maxTokenLength)};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = new String[]{reader.readLine(), reader.readLine()};
        String[] res = GetLongestWord(input);
        System.out.println(res[0]);
        System.out.println(res[1]);
    }
}
