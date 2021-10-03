package ProblemE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SolutionE {
    private static int getDpSequenceValue(int[] dpSequence, int[] dpParams, int dpPosition) {
        PriorityQueue<Integer> dpValues = new PriorityQueue<>(dpParams.length);
        for (int i = 0; i < dpParams.length; i++) {
            int dpArgIdx = dpPosition - dpParams[i];
            if (dpArgIdx > 0) {
                int dpArgValue = dpSequence[dpArgIdx];
                if (dpArgValue > 0) {
                    dpValues.add(dpArgValue);
                }
            }
        }
        return (dpValues.size() > 0) ? dpValues.poll() + 1 : 0;
    }

    public static String process(String[] input) {
        int amountNeeded = Integer.parseInt(input[0]);
        int currencyTypes = Integer.parseInt(input[1]);
        StringTokenizer tokenizer = new StringTokenizer(input[2], " ", true);
        int[] dpSequenceParams = new int[currencyTypes];
        int[] dpCache = new int[10_001]; //максимальный номинал одной монеты = 1 000
        for (int i = 0; i < currencyTypes; i++) {
            int coinValue = Integer.parseInt(tokenizer.nextToken());
            dpSequenceParams[i] = coinValue;
            dpCache[coinValue] = 1;
            if (tokenizer.hasMoreTokens()) {
                tokenizer.nextToken();
            }
        }

        int dpProgressPointer = 1;
        while (dpProgressPointer <= amountNeeded) {
            int currentValue = dpCache[dpProgressPointer];
            if (currentValue == 0) {
                dpCache[dpProgressPointer] = getDpSequenceValue(dpCache, dpSequenceParams, dpProgressPointer);
            }
            dpProgressPointer++;
        }

        int res = dpCache[amountNeeded];
        return (res == 0) ? "-1" : Integer.toString(res);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = new String[3];
        input[0] = reader.readLine();
        input[1] = reader.readLine();
        input[2] = reader.readLine();
        System.out.println(process(input));
    }
}
