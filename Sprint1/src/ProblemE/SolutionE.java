package ProblemE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SolutionE {
    public static String[] GetLongestWord(String[] input){
        String sourceText = input[1].trim();
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
