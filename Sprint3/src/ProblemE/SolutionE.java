package ProblemE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SolutionE {
    public static int getNumberOfHousesToBy(int budget, int[] prices){
        Arrays.sort(prices);
        int buysCount = 0;
        int totalBudget = budget;
        for (int i=0; i<prices.length; i++){
            if (prices[i] <= totalBudget){
                buysCount++;
                totalBudget -=prices[i];
            }

        }
        return buysCount;
    }

    public static String calculate(String[] input){
        StringTokenizer generalInfo = new StringTokenizer(input[0]);
        int numberOfHouses = Integer.parseInt(generalInfo.nextToken());
        int totalBudget = Integer.parseInt(generalInfo.nextToken());
        StringTokenizer prices = new StringTokenizer(input[1]);
        int[] pricesOfHouses = new int[numberOfHouses];
        for (int i=0; i< numberOfHouses; i++){
            pricesOfHouses[i] = Integer.parseInt(prices.nextToken());
        }
        return String.valueOf(getNumberOfHousesToBy(totalBudget, pricesOfHouses));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] buffer = new String[]{reader.readLine(), reader.readLine()};
        System.out.println(calculate(buffer));
    }
}
