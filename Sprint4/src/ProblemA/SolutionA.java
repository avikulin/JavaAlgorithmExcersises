package ProblemA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SolutionA {
    public static long getHash(String str, int base, int modulo) {
        if (str.length() == 0) return 0;
        if (modulo == 1) return 0;

        base = base % modulo;
        long res = str.charAt(0) % modulo;
        for (int i = 1; i < str.length(); i++) {
            res = ((int) str.charAt(i) % modulo + ((res * base) % modulo)) % modulo;
        }

        return res;
    }

    public static String process(String[] input) {
        int hashingBase = Integer.parseInt(input[0]);
        int hashingModulo = Integer.parseInt(input[1]);
        String strToHash = input[2];
        return String.valueOf(getHash(strToHash, hashingBase, hashingModulo));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] buffer = new String[3];
        for (int i = 0; i < 3; i++) {
            buffer[i] = reader.readLine();
        }
        System.out.println(process(buffer));
    }
}
