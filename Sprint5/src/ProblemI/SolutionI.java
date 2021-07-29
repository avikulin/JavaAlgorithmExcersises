package ProblemI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SolutionI {
    /**
     * Вычисление биномиального коеффициента С(k,n)
     *
     * @param n Количество элементов в множестве
     * @param k Число выбираемых элементов
     * @return
     */
    private static long binomialCoefficient(int n, int k) {
        // C(k, n) = C(n - k, n)
        if (k == n) return 1;
        if (k > n - k) k = n - k;

        long res = 1;
        for (int i = 0; i < k; i++) {
            res *= (n - i);
            res /= (i + 1);
        }

        return res;
    }

    private static long getCatalanNumber(int number) {
        if (number == 0) return 0;
        return binomialCoefficient(number * 2, number) / (number + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int sequenceLength = Integer.parseInt(reader.readLine());
        System.out.println(getCatalanNumber(sequenceLength));
    }

}
