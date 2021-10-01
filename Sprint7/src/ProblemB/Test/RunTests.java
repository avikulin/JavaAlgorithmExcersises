// import testable class
package ProblemB.Test;

// import test utilities
import ProblemB.SolutionB;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                SolutionB::process,
                String::equals,
                i -> i,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-1",
                        new String[]{"5",
                                     "9 10",
                                     "9.3 10.3",
                                     "10 11",
                                     "10.3 11.3",
                                     "11 12"},
                        "3\n" +
                                    "9 10\n" +
                                    "10 11\n" +
                                    "11 12"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-2",
                        new String[]{"3",
                                     "9 10",
                                     "11 12.25",
                                     "12.15 13.3"},
                        "2\n" +
                                    "9 10\n" +
                                    "11 12.25"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-3",
                        new String[]{"7",
                                    "19 19",
                                    "7 14",
                                    "12 14",
                                    "8 22",
                                    "22 23",
                                    "5 21",
                                    "9 23"},
                        "3\n" +
                                    "7 14\n" +
                                    "19 19\n" +
                                    "22 23"
                )
        );


        // run all tests & print results to console
        tr.run(/*new String[]{"EXAMPLE-1"}*/);
    }
}
