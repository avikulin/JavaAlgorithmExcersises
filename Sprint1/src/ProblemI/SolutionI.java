package ProblemI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SolutionI {
    public static boolean checkPower4(int value){
        if (value == 1) return true;
        if (value % 4 != 0) return false;

        int testValue = 4;
        while (testValue <= value){
            if (testValue == value) return true;
            testValue *= 4;
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(checkPower4(Integer.parseInt(reader.readLine()))?"True":"False");
    }
}
