package ProblemD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SolutionD {
    public static int getNumberOfSatisfiedKids(int[] angryFactors, int[] cookiesSizes){
        Arrays.sort(angryFactors);
        Arrays.sort(cookiesSizes);
        int angryPointer = 0;
        int cookiesPointer = 0;
        int satisfiedCount = 0;
        while ((angryPointer < angryFactors.length)&&(cookiesPointer< cookiesSizes.length)){
            if (angryFactors[angryPointer] <= cookiesSizes[cookiesPointer]){
                angryPointer++;
                satisfiedCount++;
            }
            cookiesPointer++;
        }
        return satisfiedCount;
    }

    public static int calculate(String[] input){
        int numberOfChildren = Integer.parseInt(input[0]);
        int[] angryFactors = new int[numberOfChildren];
        StringTokenizer angryFactorTokens = new StringTokenizer(input[1]);
        for (int i=0; i< numberOfChildren; i++)
            angryFactors[i] = Integer.parseInt(angryFactorTokens.nextToken());

        int numberOfCookies = Integer.parseInt(input[2]);
        int[] cookiesSequence = new int[numberOfCookies];

        StringTokenizer cookiesTokens = new StringTokenizer(input[3]);
        for (int i=0; i< numberOfCookies; i++)
            cookiesSequence[i] = Integer.parseInt(cookiesTokens.nextToken());

        return getNumberOfSatisfiedKids(angryFactors, cookiesSequence);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] buffer = new String[4];
        for (int i=0; i<4; i++)
            buffer[i] = reader.readLine();

        System.out.println(calculate(buffer));
    }
}
