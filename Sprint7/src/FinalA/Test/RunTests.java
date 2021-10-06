// import testable class
package FinalA.Test;

// import test utilities
import FinalA.FinalSolutionA;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                FinalSolutionA::process,
                String::equals,
                i -> i,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-1",
                        new String[]{"abacaba",
                                     "abaabc"},
                         "2"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-2",
                        new String[]{"innokentiy",
                                     "innnokkentia"},
                        "3"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-3",
                        new String[]{"r",
                                     "x"},
                        "1"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EMPTY-1",
                        new String[]{"",
                                     ""},
                        "0"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EMPTY-2",
                        new String[]{"abc",
                                     ""},
                        "3"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EMPTY-3",
                        new String[]{"",
                                     "xyz"},
                        "3"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EQUAL-1",
                        new String[]{"xyz",
                                     "xyz"},
                        "0"
                )
        );

        // run all tests & print results to console
        tr.run(/*new String[]{"EXAMPLE-1"}*/);
    }
}
