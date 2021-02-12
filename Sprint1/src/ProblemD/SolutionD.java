package ProblemD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class YUtils {
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

public class SolutionD {
    private static boolean CheckGreaterValue(int[] series, int pivotalIndex, int offset){
        if (((pivotalIndex + offset)<0)||((pivotalIndex + offset)>series.length-1)){
            return true;
        }
        return series[pivotalIndex]>series[pivotalIndex+offset];
    }

    static String GetStochasticRatio(String[] input){
        int seriesLength =  YUtils.GetParamScalar(input[0], false);
        int seriesData[] = YUtils.GetParamsVector(input[1],seriesLength);
        int res = 0;
        for (int index = 0; index < seriesLength ; index++){
            boolean isPrevGreater = CheckGreaterValue(seriesData, index, -1);
            boolean isNextGreater = CheckGreaterValue(seriesData, index, 1);
            boolean isStochastic = isNextGreater&&isPrevGreater;

            if (isStochastic){
                res++;
            }
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(GetStochasticRatio(new String[]{reader.readLine(), reader.readLine()}));
    }
}
