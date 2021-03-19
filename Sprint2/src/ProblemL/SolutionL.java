package ProblemL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SolutionL {
    public static int fibonacci(int number, int modulo){
        if (number == 0) return 1;
        if (number == 1) return 1;

        int fib_first = 1;
        int fib_second = 1;
        int modBase = (int)Math.pow(10, modulo);
        for (int i=2; i <= number; i++){
            int res = (fib_first + fib_second) % modBase;
            fib_first = fib_second;
            fib_second = res;
        }
        return fib_second;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(input);
        int fibNumber = Integer.parseInt(tokenizer.nextToken());
        int moduloNumber = Integer.parseInt(tokenizer.nextToken());
        System.out.println(fibonacci(fibNumber, moduloNumber));
    }
}
