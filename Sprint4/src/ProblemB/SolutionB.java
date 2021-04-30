package ProblemB;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

import ProblemA.SolutionA;

public class SolutionB {
    public static void randomArrayFill(char[] array, int minCode, int maxCode) {
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            int randomValue = Math.abs(random.nextInt());
            int rangedValue = randomValue % (maxCode - minCode + 1) + minCode;
            array[i] = (char) (rangedValue);
        }
    }

    public static void increaseSymbolInArray(char[] array, int position, int minCode, int maxCode) {
        char symbol = ++array[position];
        array[position] = (char) ((symbol - minCode) % (maxCode - minCode + 1) + minCode);
    }

    public static void fillPositionsQueue(Stack<Integer> queue, int posCurrent, int posMax) {
        for (int i = posCurrent; i <= posMax; i++)
            queue.push(i);
    }

    public static void main(String[] args) throws IOException {
        int base = 1000;
        int modulo = 123987123;

        int min = 97;  //letter "a"
        int max = 122; //letter "z"
        long globalIterationLimit = 1_000_000_000l;

        int probeStringLength = 20;
        int maxIndex = probeStringLength - 1;
        char[] charsOfStrA = new char[probeStringLength];
        char[] charsOfStrB;


        randomArrayFill(charsOfStrA, min, max);
        String strA = String.valueOf(charsOfStrA);
        Long hashA = SolutionA.getHash(strA, base, modulo);
        Long hashB = 0l;
        String strB = "";

        System.out.println(String.format("\nOrig. string - \'%s\' length - %d hash - %d", strA, strA.length(), hashA));

        charsOfStrB = Arrays.copyOf(charsOfStrA, charsOfStrA.length);
        int[] counters = new int[charsOfStrB.length];

        long globalIterationCounter = 0;

        long startTime = System.nanoTime();

        Stack<Integer> positionsToIterate = new Stack<>();
        fillPositionsQueue(positionsToIterate, 0, maxIndex);

        while (!positionsToIterate.empty()) {
            int incrementPos = positionsToIterate.peek();

            increaseSymbolInArray(charsOfStrB, incrementPos, min, max);
            counters[incrementPos]++;
            globalIterationCounter++;

            if ((counters[incrementPos] < (max - min - 1)) && (incrementPos != maxIndex)) {
                fillPositionsQueue(positionsToIterate, incrementPos + 1, maxIndex);
            }

            strB = String.valueOf(charsOfStrB);
            hashB = SolutionA.getHash(strB, base, modulo);

            if (hashA.equals(hashB)) break;

            if (globalIterationCounter == globalIterationLimit) {
                System.out.println("\nIteration limit exceeded:");
                String countersStr = Arrays.toString(counters);
                String queueStr = positionsToIterate.toString();
                System.out.println(String.format("\t iterationPos = %d", incrementPos));
                System.out.println(String.format("\t iteration queue = %s", queueStr));
                System.out.println(String.format("\t iteration counters = %s\n", countersStr));
                return;
            }

            if (counters[incrementPos] > (max - min - 1)) {
                counters[incrementPos] = 0;
                positionsToIterate.pop();
            }
        }

        long stopTime = System.nanoTime();
        long timeRange = stopTime - startTime;
        long timeRangeInSec = timeRange / 1_000_000_000L;
        long timeRangeInMillis = (timeRange % 1_000_000_000L) / 1_000_000L;

        System.out.println(String.format("Result string - \'%s\' length - %d hash - %d", strB, strB.length(), hashB));
        System.out.println(String.format("Time elapsed: %d sec. %d ms.", timeRangeInSec, timeRangeInMillis));
    }
}
