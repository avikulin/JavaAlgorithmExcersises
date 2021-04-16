// import testable class
package FinalA.Tests;

// import test utilities
import FinalA.FinalSolutionA;
import YTester.YTestCase;
import YTester.YTestRunner;

public class BinarySearchRunTests {
    public static void main(String[] args) {
        YTestRunner<Integer, String[]> tr = new YTestRunner<Integer, String[]>(
                FinalSolutionA::goSearch,
                Integer::equals,
                i -> String.valueOf(i),
                200);

        // add test case definitions
        tr.append(new YTestCase<Integer, String[]>(
                "TWO-SEQ-1",
                new String[]{"12",
                             "5",
                             "13 15 19 21 100 101 120 1 4 5 7 12"},
                9
                )
        );

        tr.append(new YTestCase<Integer, String[]>(
                        "TWO-SEQ-1-NEG",
                        new String[]{"12",
                                "1000",
                                "13 15 19 21 100 101 120 1 4 5 7 12"},
                        -1
                )
        );

        tr.append(new YTestCase<Integer, String[]>(
                        "TWO-SEQ-2",
                        new String[]{"4",
                                     "1",
                                     "13 15 19 1"},
                        3
                )
        );

        tr.append(new YTestCase<Integer, String[]>(
                        "TWO-SEQ-2-NEG",
                        new String[]{"4",
                                "1000",
                                "13 15 19 1"},
                        -1
                )
        );

        tr.append(new YTestCase<Integer, String[]>(
                        "TWO-SEQ-3",
                        new String[]{"3",
                                     "1",
                                     "13 15 1"},
                        2
                )
        );

        tr.append(new YTestCase<Integer, String[]>(
                        "TWO-SEQ-3-NEG",
                        new String[]{"3",
                                "1000",
                                "13 15 1"},
                        -1
                )
        );

        tr.append(new YTestCase<Integer, String[]>(
                        "TWO-SEQ-4",
                        new String[]{"4",
                                     "12",
                                     "13 15 1 12"},
                        3
                )
        );

        tr.append(new YTestCase<Integer, String[]>(
                        "TWO-SEQ-4-NEG",
                        new String[]{"4",
                                "1000",
                                "13 15 1 12"},
                        -1
                )
        );

        tr.append(new YTestCase<Integer, String[]>(
                        "ONE-SEQ-1",
                        new String[]{"3",
                                     "13",
                                     "13 15 19"},
                        0
                )
        );

        tr.append(new YTestCase<Integer, String[]>(
                        "ONE-SEQ-NEG",
                        new String[]{"3",
                                "1000",
                                "13 15 19"},
                        -1
                )
        );

        tr.append(new YTestCase<Integer, String[]>(
                        "ONE-SEQ-2",
                        new String[]{"2",
                                     "15",
                                     "13 15"},
                        1
                )
        );

        tr.append(new YTestCase<Integer, String[]>(
                        "ONE-SEQ-2-NEG",
                        new String[]{"2",
                                "1000",
                                "13 15"},
                        -1
                )
        );

        tr.append(new YTestCase<Integer, String[]>(
                        "ONE-SEQ-3",
                        new String[]{"1",
                                     "13",
                                     "13"},
                        0
                )
        );

        tr.append(new YTestCase<Integer, String[]>(
                        "ONE-SEQ-3-NEG",
                        new String[]{"1",
                                "1000",
                                "13"},
                        -1
                )
        );

        // run all tests & print results to console
        tr.run();
    }
}
