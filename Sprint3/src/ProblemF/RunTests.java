// import testable class
package ProblemF;

// import test utilities
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<Integer, Integer[]> tr = new YTestRunner<Integer, Integer[]>(
                SolutionF::getMaxPerimeter,
                Integer::equals,
                i -> String.valueOf(i),
                200);

        // add test case definitions
        tr.append(new YTestCase<Integer, Integer[]>(
                "EXAMPLE#1",
                new Integer[]{6,3, 3, 2},
                8
                )
        );

        tr.append(new YTestCase<Integer, Integer[]>(
                        "EXAMPLE#2",
                        new Integer[]{5, 3, 7, 2, 8, 3},
                        20
                )
        );

        tr.append(new YTestCase<Integer, Integer[]>(
                        "LINE-1",
                        new Integer[]{1, 1, 1, 1, 1, 1},
                        3
                )
        );

        tr.append(new YTestCase<Integer, Integer[]>(
                        "LINE-2",
                        new Integer[]{0, 0, 0, 1, 1, 1},
                        3
                )
        );

        tr.append(new YTestCase<Integer, Integer[]>(
                        "LINE-3",
                        new Integer[]{1, 1, 1, 1, 1, 2},
                        3
                )
        );

        tr.append(new YTestCase<Integer, Integer[]>(
                        "TOO-MUCH-1",
                        new Integer[]{10, 1, 5, 1, 3, 2},
                        0
                )
        );

        tr.append(new YTestCase<Integer, Integer[]>(
                        "ZEROES-1",
                        new Integer[]{10, 1, 0, 0, 0, 0},
                        0
                )
        );

        tr.append(new YTestCase<Integer, Integer[]>(
                        "TOO-MUCH-2",
                        new Integer[]{10, 1, 9, 9, 9, 9},
                        28
                )
        );

        tr.append(new YTestCase<Integer, Integer[]>(
                        "TOO-MUCH-3",
                        new Integer[]{10, 1, 5, 2, 0, 1},
                        0
                )
        );

        tr.append(new YTestCase<Integer, Integer[]>(
                        "MIN-1",
                        new Integer[]{1, 1, 2},
                        0
                )
        );

        tr.append(new YTestCase<Integer, Integer[]>(
                        "MIN-2",
                        new Integer[]{10, 1, 1},
                        0
                )
        );

        tr.append(new YTestCase<Integer, Integer[]>(
                        "MIN-3",
                        new Integer[]{1, 1, 1},
                        3
                )
        );

        tr.append(new YTestCase<Integer, Integer[]>(
                        "REGULAR-3",
                        new Integer[]{1, 2, 2},
                        5
                )
        );

        tr.append(new YTestCase<Integer, Integer[]>(
                        "DOUBLE-1",
                        new Integer[]{10, 2, 5, 4, 7, 5},
                        22
                )
        );

        tr.append(new YTestCase<Integer, Integer[]>(
                        "DOUBLE-2",
                        new Integer[]{1, 1, 1, 2, 2, 2},
                        6
                )
        );

        tr.append(new YTestCase<Integer, Integer[]>(
                        "TRIPLE-1",
                        new Integer[]{1, 1, 1, 2, 2, 2, 3, 3, 3},
                        9
                )
        );

        // run all tests & print results to console
        tr.run();
    }
}
