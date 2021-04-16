// import testable class
package ProblemE;

// import test utilities
import ProblemD.SolutionD;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                SolutionE::calculate,
                String::equals,
                s -> s,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                "EXAMPLE#1",
                new String[]{
                        "3 300",
                        "999 999 999"
                },
                "0"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE#2",
                        new String[]{
                                "3 1000",
                                "350 999 200"
                        },
                        "2"
                )
        );


        tr.append(new YTestCase<String, String[]>(
                        "NOT ENOUGH-1",
                        new String[]{
                                "3 1000",
                                "1001 1001 1001"
                        },
                        "0"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "NOT ENOUGH-2",
                        new String[]{
                                "1 1000",
                                "1001"
                        },
                        "0"
                )
        );


        tr.append(new YTestCase<String, String[]>(
                        "ENOUGH-1",
                        new String[]{
                                "3 300",
                                "100 100 100"
                        },
                        "3"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "ENOUGH-2",
                        new String[]{
                                "3 300",
                                "200 90 10"
                        },
                        "3"
                )
        );
        // run all tests & print results to console
        tr.run();
    }
}
