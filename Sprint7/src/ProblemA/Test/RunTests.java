// import testable class
package ProblemA.Test;

// import test utilities
import ProblemA.SolutionA;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                SolutionA::process,
                String::equals,
                i -> i,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-1",
                        new String[]{"6",
                                     "7 1 5 3 6 4"},
                        "7"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-2",
                        new String[]{"5",
                                     "1 2 3 4 5"},
                        "4"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-3",
                        new String[]{"6",
                                     "1 12 12 16 1 8"},
                        "22"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SHORT-1",
                        new String[]{"0",
                                ""},
                        "0"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SHORT-2",
                        new String[]{"1",
                                     "1"},
                        "0"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SHORT-3",
                        new String[]{"2",
                                     "1 2"},
                        "1"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SHORT-4",
                        new String[]{"1",
                                     "2 1"},
                        "0"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SHORT-1",
                        new String[]{"5",
                                     "1 1 1 1 1"},
                        "0"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "LONG-1",
                        new String[]{"6",
                                "12 11 10 9 5 1"},
                        "0"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "LONG-2",
                        new String[]{"6",
                                "12 11 10 9 5 15"},
                        "10"
                )
        );
        // run all tests & print results to console
        tr.run(/*new String[]{"EXAMPLE-2"}*/);
    }
}
