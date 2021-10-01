// import testable class
package ProblemD.Test;

// import test utilities
import ProblemD.SolutionD;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String> tr = new YTestRunner<String, String>(
                SolutionD::process,
                String::equals,
                i -> i,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String>(
                        "EXAMPLE-1",
                        "5",
                        "8"
                )
        );

        tr.append(new YTestCase<String, String>(
                        "EXAMPLE-2",
                        "2",
                        "2"
                )
        );

        tr.append(new YTestCase<String, String>(
                        "EXAMPLE-3",
                        "10",
                        "89"
                )
        );

        // 62232491515607091882574410635924603070626544377175485625797 mod 1 000 000 007
        tr.append(new YTestCase<String, String>(
                        "BIG-1",
                        "282",
                        "406167243"
                )
        );

        // run all tests & print results to console
        tr.run(/*new String[]{"EXAMPLE-2"}*/);
    }
}
