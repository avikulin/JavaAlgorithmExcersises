package ProblemH.Test;

// import test utilities

import ProblemH.SolutionH;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                SolutionH::process,
                String::equals,
                i -> i,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-1",
                        new String[]{"9",
                                     "3 9 1 2 5 10 9 1 7",
                                     "2",
                                     "4 10"},
                        "1 8"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-2",
                        new String[]{"5",
                                     "1 2 3 4 5",
                                     "3",
                                     "10 11 12"},
                        "1 2 3"
                )
        );

        // run all tests & print results to console
        tr.run( /*new String[]{"TEST-10"}*/);
    }
}
