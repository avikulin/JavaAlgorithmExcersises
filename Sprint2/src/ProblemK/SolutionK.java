package ProblemK;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SolutionK {
    public static int calc(int value){
        if (value == 0) return 1;
        if (value == 1) return 1;
        if (value > 1) return calc(value-1) + calc(value-2);
        return 0;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int param = Integer.parseInt(reader.readLine());
        System.out.println(calc(param));
    }
}
