package ProblemC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class GoldHeap implements Comparable<GoldHeap> {
    private int unitPrice;
    private int weightValue;

    public GoldHeap(int price, int weight) {
        this.unitPrice = price;
        this.weightValue = weight;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getWeightValue() {
        return weightValue;
    }

    @Override
    public int compareTo(GoldHeap o) {
        if (this.equals(o)) return 0;
        return (this.unitPrice > o.unitPrice) ? -1 : 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoldHeap goldHeap = (GoldHeap) o;
        return unitPrice == goldHeap.unitPrice &&
                weightValue == goldHeap.weightValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitPrice, weightValue);
    }
}

public class SolutionC {
    public static String process(String[] input) {
        int knapsackLimit = Integer.parseInt(input[0]);
        int numberOfHeaps = Integer.parseInt(input[1]);

        if (knapsackLimit == 0) return "0";

        PriorityQueue<GoldHeap> pq = new PriorityQueue<>();
        for (int i = 2; i < numberOfHeaps + 2; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input[i], " ", true);
            int unitPrice = Integer.parseInt(tokenizer.nextToken());
            String stub = tokenizer.nextToken();
            int weightValue = Integer.parseInt(tokenizer.nextToken());
            pq.add(new GoldHeap(unitPrice, weightValue));
        }
        long goldTakenTotalPrice = 0;
        while ((!pq.isEmpty()) && (knapsackLimit > 0)) {
            GoldHeap heap = pq.poll();
            int takenValue = Math.min(heap.getWeightValue(), knapsackLimit);
            goldTakenTotalPrice += ((long) takenValue) * heap.getUnitPrice();
            knapsackLimit -= takenValue;
        }
        return Long.toString(goldTakenTotalPrice);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String knapsackLimit = reader.readLine();
        int numberOfHeaps = Integer.parseInt(reader.readLine());
        String[] inputData = new String[numberOfHeaps + 2];
        inputData[0] = knapsackLimit;
        inputData[1] = Integer.toString(numberOfHeaps);
        for (int i = 2; i < numberOfHeaps + 2; i++) {
            inputData[i] = reader.readLine();
        }
        System.out.println(process(inputData));
    }
}
