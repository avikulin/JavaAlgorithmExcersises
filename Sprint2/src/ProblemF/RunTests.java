// import testable class
package ProblemF;

// import test utilities

import ProblemF.SolutionF;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                SolutionF::processBatch,
                String::equals,
                s -> s,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                "EXAMPLE#1",
                new String[]{"8",
                        "get_max",
                        "push 7",
                        "pop",
                        "push -2",
                        "push -1",
                        "pop",
                        "get_max",
                        "get_max"
                },
                "None\n" +
                            "-2\n"+
                            "-2\n"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                "EXAMPLE#2",
                new String[]{"7",
                        "get_max",
                        "pop",
                        "pop",
                        "pop",
                        "push 10",
                        "get_max",
                        "push -9"
                },
                "None\n" +
                            "error\n" +
                            "error\n"+
                            "error\n"+
                            "10\n"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "MAX-1",
                        new String[]{"2",
                                "push 1",
                                "get_max"

                        },
                        "1\n"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "MAX-4",
                        new String[]{"6",
                                "push 1",
                                "push 2",
                                "push 100",
                                "push 3",
                                "pop",
                                "get_max"

                        },
                        "100\n"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "MAX-EMPTY-1",
                        new String[]{"6",
                                "push 1",
                                "pop",
                                "push 100",
                                "pop",
                                "pop",
                                "get_max"
                        },
                        "error\n"+
                                    "None\n"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "MAX-EMPTY-2",
                        new String[]{"3",
                                "push 1",
                                "pop",
                                "get_max"
                        },
                        "None\n"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EMPTY-4",
                        new String[]{"1",
                                "get_max"
                        },
                        "None\n"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "CIRCULAR-EMPTY",
                        new String[]{"7",
                                "push 1",
                                "push 2",
                                "push 3",
                                "pop",
                                "pop",
                                "pop",
                                "get_max"
                        },
                        "None\n"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "CIRCULAR-1",
                        new String[]{"6",
                                "push 1",
                                "push 2",
                                "push 3",
                                "pop",
                                "pop",
                                "get_max"
                        },
                        "1\n"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "CIRCULAR-2",
                        new String[]{"6",
                                "push 100",
                                "pop",
                                "pop",
                                "push 2",
                                "push 3",
                                "get_max"
                        },
                        "error\n" +
                                    "3\n"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "CIRCULAR-3",
                        new String[]{"8",
                                "pop",
                                "pop",
                                "push 100",
                                "get_max",
                                "push 2",
                                "get_max",
                                "push 3",
                                "get_max"
                        },
                        "error\n" +
                                "error\n" +
                                "100\n" +
                                "100\n" +
                                "100\n"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "CIRCULAR-3",
                        new String[]{"8",
                                "push 1",
                                "push 2",
                                "push 100",
                                "get_max",
                                "pop",
                                "get_max",
                                "pop",
                                "get_max"
                        },
                        "100\n" +
                                "2\n" +
                                "1\n"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "CIRCULAR-4",
                        new String[]{"15",
                                "push -1000",
                                "push -2000",
                                "push -100",
                                "push -100",
                                "push -100",
                                "push -100",
                                "get_max",
                                "pop",
                                "get_max",
                                "pop",
                                "pop",
                                "pop",
                                "pop",
                                "pop",
                                "pop",
                                "pop",
                                "push 1000",
                                "get_max"
                        },
                        "-100\n" +
                                "-100\n" +
                                "error\n" +
                                "error\n" +
                                "1000\n"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "CIRCULAR-5",
                        new String[]{"22",
                                "push 1",
                                "push 2",
                                "push 3",
                                "push 4",
                                "push 5",
                                "push 6",
                                "get_max", //6
                                "pop",
                                "get_max", //5
                                "pop",
                                "get_max", //4
                                "pop",
                                "get_max", //3
                                "pop",
                                "get_max", //2
                                "pop",
                                "get_max", //1
                                "pop",
                                "pop", //error
                                "pop", //error
                                "push 1000",
                                "get_max" //100
                        },
                        "6\n" +
                                "5\n" +
                                "4\n"+
                                "3\n"+
                                "2\n"+
                                "1\n"+
                                "error\n" +
                                "error\n" +
                                "1000\n"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "CIRCULAR-6",
                        new String[]{"25",
                                "push 10",
                                "push -6",
                                "push 10",
                                "push 11",
                                "push -2",
                                "get_max", //11
                                "pop",
                                "pop",
                                "pop",
                                "pop",
                                "pop",
                                "pop", //error
                                "push 1",
                                "pop",
                                "push -3",
                                "pop",
                                "get_max", //None
                                "pop", //error
                                "pop", //error
                                "pop", //error
                                "push 1",
                                "pop",
                                "push -3",
                                "pop",
                                "get_max" //None
                        },
                        "11\n" +
                                "error\n" +
                                "None\n"+
                                "error\n"+
                                "error\n"+
                                "error\n"+
                                "None\n"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "CIRCULAR-6",
                        new String[]{"25",
                                "push 7",
                                "push 5",
                                "push 3",
                                "get_max", //7
                                "pop", //удалил 3, макс - 7
                                "pop", //удалил 5, макс - 7
                                "pop",
                                "pop",
                                "pop",
                                "pop", //error
                                "push 1",
                                "pop",
                                "push -3",
                                "pop",
                                "get_max", //None
                                "pop", //error
                                "pop", //error
                                "pop", //error
                                "push 1",
                                "pop",
                                "push -3",
                                "pop",
                                "get_max" //None
                        },
                        "11\n" +
                                "error\n" +
                                "None\n"+
                                "error\n"+
                                "error\n"+
                                "error\n"+
                                "None\n"
                )
        );

        // run all tests & print results to console
        tr.run();
    }
}
