// import testable class
package ProblemG;

// import test utilities
import ProblemG.SolutionG;
import YTester.YTestCase;
import YTester.YTestRunner;

import java.util.Arrays;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<Integer[], Integer[]> tr = new YTestRunner<Integer[], Integer[]>(
                SolutionG::sort,
                Arrays::equals,
                i -> String.valueOf(i),
                200);

        // add test case definitions
        tr.append(new YTestCase<Integer[], Integer[]>(
                "EXAMPLE#1",
                new Integer[]{0, 2, 1, 2, 0, 0, 1},
                new Integer[]{0, 0, 0, 1, 1, 2, 2}
                )
        );

        tr.append(new YTestCase<Integer[], Integer[]>(
                        "EXAMPLE#2",
                        new Integer[]{2, 1, 2, 0, 1},
                        new Integer[]{0, 1, 1, 2, 2}
                )
        );

        tr.append(new YTestCase<Integer[], Integer[]>(
                        "EXAMPLE#3",
                        new Integer[]{2, 1, 1, 2, 0, 2},
                        new Integer[]{0, 1, 1, 2, 2, 2}
                )
        );

        tr.append(new YTestCase<Integer[], Integer[]>(
                        "MIN-1",
                        new Integer[]{0},
                        new Integer[]{0}
                )
        );

        tr.append(new YTestCase<Integer[], Integer[]>(
                        "TUPLE-TEST-1",
                        new Integer[]{0, 1},
                        new Integer[]{0, 1}
                )
        );

        tr.append(new YTestCase<Integer[], Integer[]>(
                        "TUPLE-TEST-2",
                        new Integer[]{1, 0},
                        new Integer[]{0, 1}
                )
        );

        tr.append(new YTestCase<Integer[], Integer[]>(
                        "TUPLE-TEST-3",
                        new Integer[]{1, 1},
                        new Integer[]{1, 1}
                )
        );

        tr.append(new YTestCase<Integer[], Integer[]>(
                        "REVERSE-TEST-1",
                        new Integer[]{2, 1, 0, 1, 2},
                        new Integer[]{0, 1, 1, 2, 2}
                )
        );

        // run all tests & print results to console
        tr.run();
    }
}
