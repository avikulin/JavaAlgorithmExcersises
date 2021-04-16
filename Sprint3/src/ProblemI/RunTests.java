// import testable class
package ProblemI;

// import test utilities
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                SolutionI::process,
                String::equals,
                i -> i,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                "EXAMPLE#1",
                new String[]{"7",
                             "1 2 3 1 2 3 4",
                             "3"},
                "1 2 3"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE#2",
                        new String[]{"6",
                                "1 1 1 2 2 3",
                                "1"},
                        "1"
                )
        );

        // run all tests & print results to console
        tr.run();
    }
}
