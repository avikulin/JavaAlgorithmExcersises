// import testable class
package ProblemG.Test;

// import test utilities
import ProblemG.SolutionG;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                SolutionG::process,
                String::equals,
                i -> i,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-1",
                        new String[]{"5",
                                     "3",
                                     "3 2 1"},
                        "5"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-2",
                        new String[]{"3",
                                "2",
                                "2 1"},
                        "2"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-3",
                         new String[]{"8",
                                      "1",
                                      "5"},
                        "0"
                )
        );

        // run all tests & print results to console
        tr.run(/*new String[]{"LOAD-TEST-4"}*/);
    }
}
