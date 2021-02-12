package ProblemC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

class Utils {
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

class Matrix{
    int rows;
    int cols;
    int targetRowIdx;
    int targetColIdx;
    ArrayList<Integer[]> steps;

    int[][] matrixData;
    public Matrix(int number_of_rows, int number_of_columns){
        rows = number_of_rows;
        cols = number_of_columns;
        matrixData = new int[number_of_rows][number_of_columns];

        //---steps to collect neighbors around the target element
        steps = new ArrayList<Integer[]>();
        steps.add(new Integer[]{-1, 0});
        steps.add(new Integer[]{1, 0});
        steps.add(new Integer[]{0, -1});
        steps.add(new Integer[]{0, 1});
    }

    static Matrix fromTextRepresentation(String[] input_buffer){
        int rows_count = Utils.GetParamScalar(input_buffer[0], false);
        int cols_count = Utils.GetParamScalar(input_buffer[1], false);
        Matrix matrix = new Matrix(rows_count, cols_count);
        for (int i = 0; i< rows_count;i++){
            matrix.InsertRow(Utils.GetParamsVector(input_buffer[2 + i],cols_count),i);
        }

        int targetCellRowIdx = Utils.GetParamScalar(input_buffer[input_buffer.length - 2], false);
        int targetCellColIdx = Utils.GetParamScalar(input_buffer[input_buffer.length - 1], false);

        matrix.SetTargetCell(targetCellRowIdx,targetCellColIdx);
        return matrix;
    }

    public void InsertRow(int[] row_data, int row_index){
        System.arraycopy(row_data, 0, matrixData[row_index], 0, row_data.length);
    }

    public void SetTargetCell(int row_idx, int col_Idx){
        targetRowIdx = row_idx;
        targetColIdx = col_Idx;
    }

    public void Print(){
        for (int[] row: matrixData){
            System.out.println(Arrays.toString(row));
        }

        System.out.println(String.format("Target: [%d %d]\r\n", this.targetRowIdx, this.targetColIdx));
    }

    private boolean CheckBounds (Integer[] search_vector){
        int x = this.targetColIdx + search_vector[0];
        int y = this.targetRowIdx + search_vector[1];
        return ((x<this.cols)&&(x>=0))&&((y<this.rows)&&(y>=0));
    }

    private int GetByOffset(Integer[] offset_vector){
        return this.matrixData[this.targetRowIdx+offset_vector[1]][this.targetColIdx+offset_vector[0]];
    }

    public String GetNeighbors(){
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (Integer[] step: steps){
            if (CheckBounds(step)){
                res.add(GetByOffset(step));
            }
        }
        res.sort(Comparator.naturalOrder());
        return res.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
    }
}

public class SolutionC {
    public static String Calculate(String[] input){
        Matrix m = Matrix.fromTextRepresentation(input);
        return m.GetNeighbors();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int number_of_rows = Utils.GetParamScalar(reader.readLine(), false);
        int number_of_cols = Utils.GetParamScalar(reader.readLine(), false);
        Matrix m = new Matrix(number_of_rows, number_of_cols);
        for (int i=0; i<number_of_rows; i++){
            m.InsertRow(Utils.GetParamsVector(reader.readLine(), number_of_cols),i);
        }
        m.SetTargetCell(
                Utils.GetParamScalar(reader.readLine(), false),
                Utils.GetParamScalar(reader.readLine(), false));
        System.out.println(m.GetNeighbors());
    }
}
