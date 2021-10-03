// import testable class
package ProblemF.Test;

// import test utilities
import ProblemF.SolutionF;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String> tr = new YTestRunner<String, String>(
                SolutionF::process,
                String::equals,
                i -> i,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String>(
                        "EXAMPLE-1",
                        "6 3",
                        "13"
                )
        );

        tr.append(new YTestCase<String, String>(
                        "EXAMPLE-2",
                        "7 7",
                        "32"
                )
        );

        tr.append(new YTestCase<String, String>(
                        "EXAMPLE-3",
                        "2 2",
                        "1"
                )
        );

        tr.append(new YTestCase<String, String>(
                        "LOAD-TEST-4",
                        "62 44",
                        "535806680"
                )
        );


        // run all tests & print results to console
        tr.run(/*new String[]{"LOAD-TEST-4"}*/);
    }
}
