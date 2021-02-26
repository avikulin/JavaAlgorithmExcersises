package ProblemJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class SolutionJ {
    public static String Factorize(int value){
        ArrayList<Integer> factors = new ArrayList<Integer>();
        while (value % 2 == 0){
            factors.add(2);
            value /= 2;
        }

        int sequenceSupremum = (int)Math.sqrt(value);
        for (int factor = 3; factor <= sequenceSupremum; factor += 2){
            while (value % factor == 0){
                factors.add(factor);
                value/=factor;
            }
        }

        if (value > 2) factors.add(value);
        factors.sort(Comparator.naturalOrder());

        return factors.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(Factorize(Integer.parseInt(reader.readLine())));
    }
}
