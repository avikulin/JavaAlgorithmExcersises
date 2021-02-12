package ProblemA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SolutionA {
    public static int Calculate(String input){
        StringTokenizer tokenizer = new StringTokenizer(input);
        int[] coeffs = new int[4];
        for (int i=0; i<4; i++){
            coeffs[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return coeffs[0]*(int) Math.pow(coeffs[1],2)+coeffs[2]*coeffs[1]+coeffs[3];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(Calculate(reader.readLine()));
    }
}