package ProblemB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SolutionB {
    public enum ParityStatus{
        ODD,
        EVEN,
        UNKNOWN;

        static public ParityStatus testInt(int value){
            return  (0==(value & 1)) ? ParityStatus.EVEN: ParityStatus.ODD;
        }
    }
    public static boolean Calculate(String input){
        StringTokenizer tokenizer = new StringTokenizer(input);
        ParityStatus parity = ParityStatus.UNKNOWN;
        for (int i=0; i<3; i++){
            int value = Integer.parseInt(tokenizer.nextToken());
            if ((parity == ParityStatus.UNKNOWN)){
                parity = ParityStatus.testInt(value);
                continue;
            }

            if (!parity.equals(ParityStatus.testInt(value))){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(Calculate(reader.readLine()) ? "WIN":"FAIL");
    }
}
