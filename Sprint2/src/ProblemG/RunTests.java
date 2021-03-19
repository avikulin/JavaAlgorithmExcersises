// import testable class
package ProblemG;

// import test utilities

import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                SolutionG::processBatch,
                String::equals,
                s -> s,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                "EXAMPLE#1",
                new String[]{"10",
                        "pop",
                        "pop",
                        "push 4",
                        "push -5",
                        "push 7",
                        "pop",
                        "pop",
                        "get_max",
                        "pop",
                        "get_max"
                },
                "error\n" +
                            "error\n"+
                            "4\n"+
                            "None\n"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                "EXAMPLE#2",
                new String[]{"10",
                        "get_max",
                        "push -6",
                        "pop",
                        "pop",
                        "get_max",
                        "push 2",
                        "get_max",
                        "pop",
                        "push -2",
                        "push -6"
                },
                "None\n" +
                            "error\n" +
                            "None\n"+
                            "2\n"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "MAX-1",
                        new String[]{"20",
                                "get_max",
                                "push -1",
                                "push -1",
                                "get_max",
                                "push 2",
                                "push 1",
                                "get_max",
                                "push 3",
                                "push 4",
                                "push 3",
                                "push 4",
                                "get_max",
                                "pop",
                                "get_max",
                                "pop",
                                "get_max",
                                "pop",
                                "get_max",
                                "pop",
                                "get_max"
                        },
                        "None\n"+
                                "-1\n"+
                                "2\n"+
                                "4\n"+
                                "4\n"+
                                "4\n"+
                                "3\n"+
                                "2\n"
                )
        );

        // run all tests & print results to console
        tr.run();
    }
}
