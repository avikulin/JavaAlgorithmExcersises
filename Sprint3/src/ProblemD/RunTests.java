// import testable class
package ProblemD;

// import test utilities
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<Integer, String[]> tr = new YTestRunner<Integer, String[]>(
                SolutionD::calculate,
                Integer::equals,
                i -> String.valueOf(i),
                200);

        // add test case definitions
        tr.append(new YTestCase<Integer, String[]>(
                "EXAMPLE#1",
                new String[]{
                        "2",
                        "1 2",
                        "3",
                        "2 1 3"
                },
                2
                )
        );

        tr.append(new YTestCase<Integer, String[]>(
                        "EXAMPLE#2",
                        new String[]{
                                "3",
                                "2 1 3",
                                "2",
                                "1 1"
                        },
                        1
                )
        );

        tr.append(new YTestCase<Integer, String[]>(
                        "ONE-COOCKIE-1",
                        new String[]{
                                "3",
                                "1 1 1",
                                "1",
                                "1"
                        },
                        1
                )
        );

        tr.append(new YTestCase<Integer, String[]>(
                        "ONE-COOCKIE-1",
                        new String[]{
                                "3",
                                "3 2 1",
                                "1",
                                "1"
                        },
                        1
                )
        );

        tr.append(new YTestCase<Integer, String[]>(
                        "ONE-COOCKIE-3",
                        new String[]{
                                "3",
                                "1 2 3",
                                "1",
                                "1"
                        },
                        1
                )
        );
        // run all tests & print results to console
        tr.run();
    }
}
