// import testable class
package ProblemE.Tests;

// import test utilities
import ProblemE.SolutionE;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String> tr = new YTestRunner<String, String>(
                SolutionE::getLongestUniqueSubSequence,
                String::equals,
                i -> i,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String>(
                        "EXAMPLE-1",
                        "abcabcbb",
                        "3"
                )
        );
        tr.append(new YTestCase<String, String>(
                        "EXAMPLE-2",
                        "bbbbb",
                        "1"
                )
        );

        tr.append(new YTestCase<String, String>(
                        "EMPTY-1",
                        "",
                        "0"
                )
        );

        tr.append(new YTestCase<String, String>(
                        "FULL-1",
                        "abcdef",
                        "6"
                )
        );

        tr.append(new YTestCase<String, String>(
                        "COMPLEX-1",
                        "abcabdefab",
                        "6"
                )
        );

        tr.append(new YTestCase<String, String>(
                        "COMPLEX-2",
                        "abcdefab",
                        "6"
                )
        );

        tr.append(new YTestCase<String, String>(
                        "COMPLEX-3",
                        "abcabd",
                        "4"
                )
        );

        tr.append(new YTestCase<String, String>(
                        "EQUAL-1",
                        "abba",
                        "2"
                )
        );

        tr.append(new YTestCase<String, String>(
                        "EQUAL-2",
                        "abddef",
                        "3"
                )
        );
        // run all tests & print results to console
        tr.run();
    }
}
