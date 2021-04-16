// import testable class
package ProblemH;

// import test utilities
import ProblemH.SolutionH;
import YTester.YTestCase;
import YTester.YTestRunner;

import java.util.Arrays;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                SolutionH::constructValue,
                String::equals,
                i -> i,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                "EXAMPLE#1",
                new String[]{"15", "56", "2"},
                "56215"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE#2",
                        new String[]{"1", "783", "2"},
                        "78321"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE#3",
                        new String[]{"2", "4", "5", "2", "10"},
                        "542210"
                )
        );

        // run all tests & print results to console
        tr.run();
    }
}
