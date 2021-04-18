// import testable class
package ProblemJ;

// import test utilities
import ProblemJ.SolutionJ;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, int[]> tr = new YTestRunner<String, int[]>(
                SolutionJ::sort,
                String::equals,
                i -> i,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, int[]>(
                "EXAMPLE#1",
                new int[]{4, 3, 9, 2, 1},
                "3 4 2 1 9\n" +
                            "3 2 1 4 9\n" +
                            "2 1 3 4 9\n" +
                            "1 2 3 4 9"
                )
        );

        tr.append(new YTestCase<String, int[]>(
                        "EXAMPLE#2",
                        new int[]{12, 8, 9, 10, 11},
                        "8 9 10 11 12"
                )
        );

        tr.append(new YTestCase<String, int[]>(
                        "REVERSE-ORDER",
                        new int[]{12, 11, 10, 9, 8},
                        "11 10 9 8 12\n" +
                                    "10 9 8 11 12\n" +
                                    "9 8 10 11 12\n" +
                                    "8 9 10 11 12"
                )
        );

        tr.append(new YTestCase<String, int[]>(
                        "TUPLE-TEST-1",
                        new int[]{1, 2},
                        "1 2"
                )
        );

        tr.append(new YTestCase<String, int[]>(
                        "TUPLE-TEST-2",
                        new int[]{1, 1},
                        "1 1"
                )
        );

        tr.append(new YTestCase<String, int[]>(
                        "TUPLE-TEST-3",
                        new int[]{2, 1},
                        "1 2"
                )
        );

        tr.append(new YTestCase<String, int[]>(
                        "MIN-TEST-1",
                        new int[]{1},
                        "1"
                )
        );

        tr.append(new YTestCase<String, int[]>(
                        "EMPTY-TEST-1",
                        new int[]{},
                        ""
                )
        );
        // run all tests & print results to console
        tr.run();
    }
}
