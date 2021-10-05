package ProblemH;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SolutionH {

    private static void parseFieldMatrix(String[] source, int[][] dest, int startRow, int paddingLeft, char keySymbol) {
        for (int row = startRow; row < source.length; row++) {
            String sourceStr = source[row];
            for (int pos = 0; pos < sourceStr.length(); pos++) {
                if (sourceStr.charAt(pos) == keySymbol) {
                    dest[row - startRow][pos + paddingLeft] = 1;
                }
            }
        }
    }

    public static String process(String[] input) {
        StringTokenizer dimensionTokens = new StringTokenizer(input[0]);
        int numRows = Integer.parseInt(dimensionTokens.nextToken());
        int numCols = Integer.parseInt(dimensionTokens.nextToken());
        int[][] dpCache = new int[numRows + 1][numCols + 1];
        parseFieldMatrix(input, dpCache, 1, 1, '1');

        for (int row = numRows - 1; row >= 0; row--) {
            for (int col = 1; col <=numCols; col++) {
                int dpCurrentValue = dpCache[row][col];
                int dpRecursionLeft = dpCache[row][col - 1];
                int dpRecursionBottom = dpCache[row + 1][col];
                int dpResult = dpCurrentValue + Math.max(dpRecursionLeft, dpRecursionBottom);
                dpCache[row][col] = dpResult;
            }
        }

        return Integer.toString(dpCache[0][numCols]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fieldDimensionsStr = reader.readLine();
        StringTokenizer dimensionTokens = new StringTokenizer(fieldDimensionsStr);
        int numRows = Integer.parseInt(dimensionTokens.nextToken());
        String[] buffer = new String[numRows + 1];
        buffer[0] = fieldDimensionsStr;
        for (int i = 1; i < buffer.length; i++) {
            buffer[i] = reader.readLine();
        }
        System.out.println(process(buffer));
    }
}
