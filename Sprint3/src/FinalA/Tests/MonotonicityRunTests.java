// import testable class
package FinalA.Tests;

// import test utilities
import FinalA.FinalSolutionA;
import YTester.YTestCase;
import YTester.YTestRunner;

public class MonotonicityRunTests {
    public static void main(String[] args) {
        YTestRunner<Integer, Integer[]> tr = new YTestRunner<Integer, Integer[]>(
                (arr)-> FinalSolutionA.findMonotonousPivot(arr,0, arr.length-1),
                Integer::equals,
                i -> String.valueOf(i),
                200);

        // add test case definitions
        tr.append(new YTestCase<Integer, Integer[]>(
                "TWO-SEQ-1",
                new Integer[]{13, 15, 19, 21, 100, 101, 120, 1, 4, 5, 7, 12},
                7
                )
        );

        tr.append(new YTestCase<Integer, Integer[]>(
                        "TWO-SEQ-2",
                        new Integer[]{13, 15, 19, 1},
                        3
                )
        );

        tr.append(new YTestCase<Integer, Integer[]>(
                        "TWO-SEQ-3",
                        new Integer[]{13, 15, 1},
                        2
                )
        );

        tr.append(new YTestCase<Integer, Integer[]>(
                        "TWO-SEQ-4",
                        new Integer[]{13, 15, 1, 12},
                        2
                )
        );

        tr.append(new YTestCase<Integer, Integer[]>(
                        "ONE-SEQ-1",
                        new Integer[]{13, 15, 19},
                        -1
                )
        );
        tr.append(new YTestCase<Integer, Integer[]>(
                        "ONE-SEQ-2",
                        new Integer[]{13, 15},
                        -1
                )
        );
        tr.append(new YTestCase<Integer, Integer[]>(
                        "ONE-SEQ-3",
                        new Integer[]{13},
                        -1
                )
        );



        // run all tests & print results to console
        tr.run();
    }
}
