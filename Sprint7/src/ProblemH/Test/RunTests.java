// import testable class
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
                        new String[]{"2 3",
                                     "101",
                                     "110"},
                         "3"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-2",
                        new String[]{"3 3",
                                     "100",
                                     "110",
                                     "001"},
                        "2"
                )
        );


        // run all tests & print results to console
        tr.run(/*new String[]{"LOAD-TEST-4"}*/);
    }
}
