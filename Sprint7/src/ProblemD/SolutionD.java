package ProblemD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SolutionD {
    public static String process(String param) {
        int fibLimit = Integer.parseInt(param);
        if ((fibLimit == 0) || (fibLimit == 1)) return "1";

        long[] dp = new long[fibLimit + 1];
        dp[0] = 1;
        dp[1] = 1;
        long modulo = 1_000_000_007;

        for (int i = 2; i <= fibLimit; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % modulo;
        }
        return Long.toString(dp[fibLimit]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fibonacciLimit = reader.readLine();
        System.out.println(process(fibonacciLimit));
    }
}
