package ProblemA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SolutionA {
    public static String process(String input[]){
        if (input[0]=="0") return "0";

        int paramsCount = Integer.parseInt(input[0]);
        StringTokenizer tokenizer = new StringTokenizer(input[1]," ", true);
        int[] scores = new int[paramsCount + 2];

        boolean hasInStock = false;
        int purchasePrise = 0;
        int profitSum = 0;

        scores[0] = Integer.MAX_VALUE;
        scores[paramsCount + 1] = -1;
        for (int i=1; i<paramsCount + 1;i++){
            scores[i] = Integer.parseInt(tokenizer.nextToken());
            String stub = tokenizer.hasMoreTokens()? tokenizer.nextToken():"";

            if ((scores[i] > scores[i-1])&&(!hasInStock)){
                hasInStock = true;
                purchasePrise = scores[i-1];
            }
            if ((scores[i] < scores[i-1])&&(hasInStock)){
                int sellPrice = scores[i-1];
                profitSum += (sellPrice - purchasePrise);
                hasInStock = false;
            }
            if ((i == paramsCount)&&(hasInStock)){
                int sellPrice = scores[i];
                profitSum += (sellPrice - purchasePrise);
                hasInStock = false;
            }

        }

        return Integer.toString(profitSum);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputData = new String[2];
        inputData[0] = reader.readLine();
        inputData[1] = reader.readLine();

        System.out.println(process(inputData));
    }
}
