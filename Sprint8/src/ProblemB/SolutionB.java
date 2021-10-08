package ProblemB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SolutionB {
    public static String process(String input){
        Deque<String> reverseBuffer = new ArrayDeque<>();
        StringTokenizer tokenizer = new StringTokenizer(input, " ", true);
        while (tokenizer.hasMoreTokens()){
            reverseBuffer.push(tokenizer.nextToken());
        }

        StringBuilder res = new StringBuilder();
        while (!reverseBuffer.isEmpty()){
            res.append(reverseBuffer.pop());
        }
        return res.toString();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String buffer = reader.readLine();
        System.out.println(process(buffer));
    }
}
