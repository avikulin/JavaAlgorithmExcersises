package YConsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class YUtils {
    static String[] GetFromConsole(BufferedReader reader, int numberOfStrings) throws IOException {
        String[] res = new String[numberOfStrings];
        for (int i=0; i < numberOfStrings; i++){
            res[i] = reader.readLine();
        }
        return res;
    }

    static int GetParamScalar(String input, boolean needToTrim) {
        if (needToTrim)
            input = input.trim();
        return Integer.parseInt(input);
    }

    static int[] GetParamsVector(String input, int numberOfParams) {
        int[] res = new int[numberOfParams];
        StringTokenizer tokenizer = new StringTokenizer(input);

        for (int i=0; i< numberOfParams; i++){
            String value = tokenizer.nextToken();
            res[i] = GetParamScalar(value, false);
        }
        return res;
    }
}