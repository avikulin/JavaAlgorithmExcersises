package ProblemD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SolutionD {
    public static String process(String buffer) {
        int[] charsCart = new int[26];

        int numberOfEvens = buffer.length();
        for (int i = 0; i < buffer.length(); i++) {
            int charCode = (int) buffer.charAt(i) - 97;
            charsCart[charCode]++;
            if ((charsCart[charCode] > 0) && (charsCart[charCode] % 2 == 0)) {
                numberOfEvens++;
            } else {
                numberOfEvens--;
            }
        }

        int resLength = numberOfEvens + ((numberOfEvens < buffer.length()) ? 1 : 0);
        int lengthOfEvenSubSequence = resLength / 2;
        char[] res = new char[resLength];
        if (resLength - 2 * lengthOfEvenSubSequence == 1) {
            for (int i = 0; i < charsCart.length; i++) {
                if (charsCart[i] % 2 == 1) {
                    res[resLength - lengthOfEvenSubSequence - 1] = (char) (97 + i);
                    charsCart[i]--;
                    break;
                }
            }
        }

        int palindromicPos = 0;

        for (int i = 0; i < charsCart.length; i++) {
            if (charsCart[i] > 1) {
                for (int j = 0; j < charsCart[i] / 2; j++) {
                    res[palindromicPos] = (char) (97 + i);
                    res[resLength - palindromicPos - 1] = (char) (97 + i);
                    palindromicPos++;
                }
            }
        }
        return new String(res);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String buffer = reader.readLine();
        System.out.println(process(buffer));
    }
}
