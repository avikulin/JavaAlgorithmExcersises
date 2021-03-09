package ProblemA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SolutionA {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int rowsNumber = Integer.parseInt(reader.readLine());
        int colsNumber = Integer.parseInt(reader.readLine());
        String[][] matrixTransposed = new String[colsNumber][rowsNumber];
        for (int i=0; i<rowsNumber; i++){
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int j=0; j<colsNumber;j++){
                matrixTransposed[j][i] = tokenizer.nextToken();
            }
        }

        for(int i=0; i<colsNumber; i++){
            System.out.println(String.join(" ", matrixTransposed[i]));
        }
    }
}
