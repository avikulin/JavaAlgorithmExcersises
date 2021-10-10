package ProblemE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SolutionE {
    public static String process(String[] input) {
        int strCount = Integer.parseInt(input[0]);

        if (strCount == 1) return Integer.toString(input[1].length());

        int prefixPointer = 0;
        boolean prefixIsTheSame = true;
        while (prefixIsTheSame){
            if (prefixPointer >= input[1].length()){
                prefixPointer--;
                return Integer.toString(prefixPointer + 1);
            }
            char p = input[1].charAt(prefixPointer);
            for (int i=2; i<input.length; i++){
                String s = input[i];
                if (prefixPointer >= s.length()){
                    prefixPointer--;
                    return Integer.toString(prefixPointer + 1);
                }
                prefixIsTheSame = (s.charAt(prefixPointer)==p);
                if (!prefixIsTheSame){
                    prefixPointer--;
                    return Integer.toString(prefixPointer + 1);
                }
            }
            prefixPointer++;
        }
        return Integer.toString(prefixPointer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String strCountParam = reader.readLine();
        int count = Integer.parseInt(strCountParam);
        String[] buffer = new String[count + 1];
        buffer[0] = strCountParam;
        for (int i = 1; i < buffer.length; i++) {
            buffer[i] = reader.readLine();
        }
        System.out.println(process(buffer));
    }
}
