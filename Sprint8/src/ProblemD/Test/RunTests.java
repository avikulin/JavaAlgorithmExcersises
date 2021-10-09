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
                        "aaaabb",
                        "aabbaa"
                )
        );

        tr.append(new YTestCase<String, String>(
                        "EXAMPLE-2",
                        "pabcd",
                        "a"
                )
        );

        tr.append(new YTestCase<String, String>(
                        "EXAMPLE-3",
                        "aaabbb",
                        "ababa"
                )
        );


        // run all tests & print results to console
        tr.run(/*new String[]{"EXAMPLE-3"}*/);
    }
}
