package ProblemC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class SolutionC {
    public static boolean testSequence(String one, String two){
        if (one.isEmpty()||two.isEmpty()) return true;

        int pointerOne = 0;
        int pointerTwo = 0;

        while (pointerTwo < two.length()){
            char charOne = one.charAt(pointerOne);
            char charTwo = two.charAt(pointerTwo);
            if (charOne == charTwo) pointerOne++;
            if (pointerOne == one.length()) return true;
            pointerTwo++;
        }

        return false;
    }
    public static String test(String[] input){
        return testSequence(input[0].replace("\n", "").replace("\r", ""),
                input[1].replace("\n", "").replace("\r", ""))?"True":"False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = new String[2];

        input[0] = reader.readLine();
        input[1] = reader.readLine();

        System.out.println(test(input));
    }
}
