// import testable class
package FinalB;

// import test utilities

import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        // set reference to testable function & setting time limit (time barrier)
        YTestRunner<Integer, String[]> tr = new YTestRunner<Integer, String[]>(
                FinalSolutionB::playGame,
                Integer::equals,
                String::valueOf,
                200);

        // add test case definitions
        tr.append(new YTestCase<Integer, String[]>(
                new String[]{
                        "3",
                        "1231",
                        "2..2",
                        "2..2",
                        "2..2"},
                2));

        tr.append(new YTestCase<Integer, String[]>(
                new String[]{
                        "4",
                        "1111",
                        "9999",
                        "1111",
                        "9911"},
                1));

        tr.append(new YTestCase<Integer, String[]>(
                new String[]{
                        "4",
                        "1111",
                        "1111",
                        "1111",
                        "1111"},
                0));

        tr.append(new YTestCase<Integer, String[]>(
                "ALL_DOTS",
                new String[]{
                        "1",
                        "....",
                        "....",
                        "....",
                        "...."},
                0));

        tr.append(new YTestCase<Integer, String[]>(
                "ONLY_ONE_KEY",
                new String[]{
                        "1",
                        "1122",
                        "2233",
                        "3344",
                        "4455"},
                2));

        tr.append(new YTestCase<Integer, String[]>(
                "TWO_KEYS",
                new String[]{
                        "2",
                        "1122",
                        "2233",
                        "3344",
                        "4455"},
                5));

        tr.append(new YTestCase<Integer, String[]>(
                "ALL_DOTS",
                new String[]{
                        "5",
                        "1111",
                        "1111",
                        "1122",
                        "3344"},
                4));

        // run all tests & print results to console
        tr.run();
    }
}
