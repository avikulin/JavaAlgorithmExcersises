package ProblemF;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class SolutionF {
    private final static int codeA = (int)'A';
    private final static int codeZ = (int)'Z';
    private final static int code0 = (int)'0';
    private final static int code9 = (int)'9';

    private static String normalize(String input){
        input = input.trim().toUpperCase(Locale.ROOT);
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<input.length(); i++){
            char value = input.charAt(i);
            if (((value>=codeA)&&(value<=codeZ))||
                    ((value>=code0)&&(value<=code9))){
                sb.append(value);
            }
        }
        return sb.toString();
    }

    public static boolean isPalindromic(String input){
        if (input.length()==0) return false;

        input = normalize(input);
        if (input.length()==0) return false;
        if (input.length()==1) return true;

        for (int i=0; i<(int)input.length()/2;i++){
            if (input.charAt(i)!=input.charAt(input.length()-i-1)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(isPalindromic(reader.readLine()) ? "True":"False");
    }
}
