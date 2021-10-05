package ProblemF;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SolutionF {
    private static int getDpSequenceValue(int[] dpSequence, int dpRecursionDepth, int dpPosition) {
        int numberOfVariants = 0;
        for (int i = 1; i <= dpRecursionDepth; i++) {
            int dpArgIdx = dpPosition - i;
            if (dpArgIdx > 0) {
                int dpArgValue = dpSequence[dpArgIdx];
                if (dpArgValue > 0) {
                    numberOfVariants += dpArgValue % 1_000_000_007;
                    numberOfVariants = numberOfVariants % 1_000_000_007;
                }
            }
        }
        return numberOfVariants % 1_000_000_007;
    }

    public static String process(String input){
        StringTokenizer tokenizer = new StringTokenizer(input);
        int numberOfSteps = Integer.parseInt(tokenizer.nextToken());
        int maxHop = Integer.parseInt(tokenizer.nextToken());

        int[] dpCache = new int[numberOfSteps + 1];
        dpCache[0] = 0;
        dpCache[1] = 1;

        int currentStep = 1;
        while (currentStep <= numberOfSteps){
            if (dpCache[currentStep] == 0){
                dpCache[currentStep] = getDpSequenceValue(dpCache, maxHop, currentStep);
            }
            currentStep++;
        }
        return Integer.toString(dpCache[numberOfSteps]);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(process(reader.readLine()));
    }
}
