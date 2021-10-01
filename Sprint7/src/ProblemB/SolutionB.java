package ProblemB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.StringJoiner;
import java.util.StringTokenizer;

class Period implements Comparable<Period> {
    private final double startTime;
    private final double endTime;

    public Period(double start, double end) {
        this.startTime = start;
        this.endTime = end;
    }

    public double getStartTime() {
        return startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    @Override
    public int compareTo(Period o) {
        if (this.equals(o)) return 0;
        if (this.endTime != o.endTime) {
            return (this.endTime > o.endTime) ? 1 : -1;
        }
        return (this.startTime > o.startTime) ? 1 : -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return Double.compare(period.startTime, startTime) == 0 &&
                Double.compare(period.endTime, endTime) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime);
    }

    private boolean checkInt(double value) {
        return (value - (int) value) == 0;
    }

    @Override
    public String toString() {
        StringJoiner res = new StringJoiner(" ");
        res.add(checkInt(startTime) ? Integer.toString((int) startTime) : Double.toString(startTime));
        res.add(checkInt(endTime) ? Integer.toString((int) endTime) : Double.toString(endTime));
        return res.toString();
    }
}

public class SolutionB {
    public static String process(String[] input) {
        StringJoiner res = new StringJoiner("\n");
        PriorityQueue<Period> pq = new PriorityQueue<>();
        int numEntries = Integer.parseInt(input[0]);
        for (int i = 1; i < numEntries + 1; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input[i], " ", true);
            double start = Double.parseDouble(tokenizer.nextToken());
            String stub = tokenizer.nextToken();
            double end = Double.parseDouble(tokenizer.nextToken());
            pq.add(new Period(start, end));
        }

        double currentEndTime = -1;
        int numberOfPeriods = 0;
        while (!pq.isEmpty()) {
            Period currentPeriod = pq.poll();
            if (currentPeriod.getStartTime() >= currentEndTime) {
                res.add(currentPeriod.toString());
                currentEndTime = currentPeriod.getEndTime();
                numberOfPeriods++;
            }
        }
        String outStr = Integer.toString(numberOfPeriods) +"\n" + res.toString();
        return outStr;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numEntries = Integer.parseInt(reader.readLine());
        String[] inputData = new String[numEntries + 1];
        inputData[0] = Integer.toString(numEntries);

        for (int i = 1; i < numEntries + 1; i++) {
            inputData[i] = reader.readLine();
        }
        System.out.println(process(inputData));
    }
}
