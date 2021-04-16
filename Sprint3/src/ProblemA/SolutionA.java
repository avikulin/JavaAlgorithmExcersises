package ProblemA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Sequence implements Comparable<Sequence> {
    private String seq;
    private int sum;

    Sequence(){
        seq = "(";
        sum = 1;
    }

    Sequence(Sequence other){
        seq = other.seq;
        sum = other.sum;
    }

    void addOpenBrace(){
        seq += "(";
        if (sum >=0) sum++;
    }

    void addClosingBrace(){
        seq += ")";
        sum--;
    }

    boolean checkIsCorrect(){
        return sum ==0;
    }

    String get() {return seq; }

    @Override
    public int compareTo(Sequence other) {
        return seq.compareTo(other.seq);
    }
}

class Collector{
    private List<Sequence> storage;
    private int lengthToConstruct;

    Collector(int length){
       storage = new ArrayList<>();
       lengthToConstruct = length;
    }

    private void generate(Sequence input, int lengthToRemain){
        if (lengthToRemain == 0){
            if (input.checkIsCorrect()) storage.add(input);
            return;
        }

        Sequence leftBranch = new Sequence(input);
        leftBranch.addOpenBrace();

        Sequence rightBranch = new Sequence(input);
        rightBranch.addClosingBrace();

        generate(leftBranch, lengthToRemain - 1);
        generate(rightBranch, lengthToRemain -1);
    }

    public String getResult(){
        generate(new Sequence(), lengthToConstruct - 1);
        storage.sort(Sequence::compareTo);

        StringBuilder builder = new StringBuilder();
        for (Sequence seq: storage){
            builder.append(seq.get());
            builder.append("\n");
        }
        return builder.toString();
    }
}


public class SolutionA {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(reader.readLine());
        length *=2;
        Collector obj = new Collector(length);
        System.out.println(obj.getResult());
    }
}
