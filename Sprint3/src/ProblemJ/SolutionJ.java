package ProblemJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class SolutionJ {
//    static Pattern replacePattern;

    private static String arrayToStr(int[] array) {
        if (array.length == 0) return "";
        return Arrays.stream(array).mapToObj(String::valueOf).collect(Collectors.joining(" "));

//        //String res = Arrays.toString(array).replace(", ", " ");
//        String res = replacePattern.matcher(Arrays.toString(array)).replaceAll(" ");
//        return res.substring(1, res.length()-1);
    }

    private static void swapElements(int[] sequence, int firstIdx, int secondIdx) {
        int temp = sequence[firstIdx];
        sequence[firstIdx] = sequence[secondIdx];
        sequence[secondIdx] = temp;
    }

    public static String sort(int[] sequence) {
        if (sequence.length == 0) return "";
        if (sequence.length == 1) return String.valueOf(sequence[0]);

//        replacePattern = Pattern.compile(", ", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

//        StringBuilder stringBuilder = new StringBuilder();
//        StringJoiner joiner = new StringJoiner("\n");
        List<String> res = new ArrayList<>();

        int totalNumberOfSwaps = 0;

        for (int iterationNumber = 1; iterationNumber < sequence.length; iterationNumber++) {
            boolean swapHappened = false;
            for (int elementIdx = 0; elementIdx < sequence.length - iterationNumber; elementIdx++) {
                if (sequence[elementIdx] > sequence[elementIdx + 1]) {
                    swapElements(sequence, elementIdx, elementIdx + 1);

                    swapHappened = true;
                    totalNumberOfSwaps++;
                }
            }

            if (swapHappened) {
//                stringBuilder.append(arrayToStr(sequence));
//                stringBuilder.append("\n");

//                stringJoiner.add(arrayToStr(sequence));
                res.add(arrayToStr(sequence));
                System.gc();
            } else {
                break;
            }
        }

        if (totalNumberOfSwaps == 0) {
//            stringBuilder.append(arrayToStr(sequence));
//            stringBuilder.append("\n");
//            joiner.add(arrayToStr(sequence));
            res.add(arrayToStr(sequence));
        }

        return res.stream().collect(Collectors.joining("\n"));
    }

    public static void main(String[] args) throws IOException {

//        while (true){
//            sort(new int[]{3,4,5,5,5,1,1,2,2,3,3,3,3,3,1,1,2,2,2,5,5,5,5,5,5,5,5,5,5,5,5,2,2,2,2,2,3,3,3,2,2,5,5,5,3,3,3});
//        }


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(reader.readLine());
        int[] inputSequence = new int[length];
        StringTokenizer sequenceTokens = new StringTokenizer(reader.readLine());

        for (int i = 0; i < length; i++)
            inputSequence[i] = Integer.parseInt(sequenceTokens.nextToken());

        System.out.println(sort(inputSequence));
    }
}
