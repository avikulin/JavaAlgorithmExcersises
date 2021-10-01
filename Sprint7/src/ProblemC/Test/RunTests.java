// import testable class
package ProblemC.Test;

// import test utilities
import ProblemC.SolutionC;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                SolutionC::process,
                String::equals,
                i -> i,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-1",
                        new String[]{"10",
                                    "3",
                                    "8 1",
                                    "2 10",
                                    "4 5"},
                        "36"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-2",
                        new String[]{"10000",
                                    "1",
                                    "4 20"},
                        "80"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SHORT-1",
                        new String[]{"0",
                                "1",
                                "5 10"},
                        "0"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SHORT-2",
                        new String[]{"2",
                                "1",
                                "5 10"},
                        "10"
                )
        );


        // run all tests & print results to console
        tr.run(/*new String[]{"EXAMPLE-2"}*/);
    }
}
