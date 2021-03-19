// import testable class
package ProblemH;

// import test utilities

import ProblemG.SolutionG;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<Boolean, String> tr = new YTestRunner<Boolean, String>(
                SolutionH::check,
                Boolean::equals,
                b -> String.valueOf(b),
                200);

        // add test case definitions
        tr.append(new YTestCase<Boolean, String>(
                "EXAMPLE#1",
                "{[()]}",
                true
                )
        );

        tr.append(new YTestCase<Boolean, String>(
                        "EXAMPLE#2",
                        "{}[()()()]{}",
                        true
                )
        );

        tr.append(new YTestCase<Boolean, String>(
                        "EXAMPLE#3",
                        "{{))",
                        false
                )
        );

        tr.append(new YTestCase<Boolean, String>(
                        "EXAMPLE#4",
                        "(()",
                        false
                )
        );

        tr.append(new YTestCase<Boolean, String>(
                        "EXAMPLE#5",
                        "{(}",
                        false
                )
        );


        tr.append(new YTestCase<Boolean, String>(
                        "EXAMPLE#6",
                        "(",
                        false
                )
        );

        tr.append(new YTestCase<Boolean, String>(
                        "EXAMPLE#7",
                        ")))(((",
                        false
                )
        );

        tr.append(new YTestCase<Boolean, String>(
                        "EMPTY",
                        "",
                        true
                )
        );


        // run all tests & print results to console
        tr.run();
    }
}
