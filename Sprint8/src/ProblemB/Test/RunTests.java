package ProblemB.Test;

// import test utilities

import ProblemB.SolutionB;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String> tr = new YTestRunner<String, String>(
                SolutionB::process,
                String::equals,
                i -> i,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String>(
                        "EXAMPLE-1",
                        "one two three",
                        "three two one"
                )
        );

        tr.append(new YTestCase<String, String>(
                        "EXAMPLE-2",
                        "hello",
                        "hello"
                )
        );

        tr.append(new YTestCase<String, String>(
                        "EXAMPLE-3",
                        "may the force be with you",
                        "you with be force the may"
                )
        );

        tr.run();
    }
}
