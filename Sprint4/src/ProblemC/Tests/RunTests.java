// import testable class
package ProblemC.Tests;

// import test utilities
import ProblemC.SolutionC;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                SolutionC::process,
                String::equals,
                i -> i,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-2",
                        new String[]{"1000",
                                     "1000009",
                                     "abcdefgh",
                                     "7",
                                     "1 1",
                                     "1 5",
                                     "2 3",
                                     "3 4",
                                     "4 4",
                                     "1 8",
                                     "5 8"},
                        "97\n" +
                                    "225076\n" +
                                    "98099\n" +
                                    "99100\n" +
                                    "100\n" +
                                    "436420\n" +
                                    "193195"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-2",
                        new String[]{"100",
                                "10",
                                "a",
                                "1",
                                "1 1"},
                        "7"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-2",
                        new String[]{"1000",
                                "1000009",
                                "abcdefgh",
                                "7",
                                "1 1",
                                "2 2",
                                "3 3",
                                "4 4",
                                "5 5",
                                "6 6",
                                "7 7"},
                        "97\n" +
                                "98\n" +
                                "99\n" +
                                "100\n" +
                                "101\n" +
                                "102\n" +
                                "103"
                )
        );

        // run all tests & print results to console
        tr.run();
    }
}
