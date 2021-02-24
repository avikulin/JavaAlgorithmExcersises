package ProblemG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SolutionG {
    public static String toBooleanString(int value){
        if (value <=1) return String.valueOf(value);

        StringBuilder sb = new StringBuilder();
        int testMask = 0b1;

        while (testMask <= value){
            sb.append(((value & testMask)==testMask)?"1":"0");
            testMask = testMask << 1;
        }
        sb.reverse();
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(toBooleanString(Integer.parseInt(reader.readLine())));
    }
}
