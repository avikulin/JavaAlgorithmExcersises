// import testable class
package FinalA;

// import test utilities

import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                FinalSolutionA::ProcessCommands,
                String::equals,
                s -> s,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                "EXAMPLE#1",
                new String[]{"4",
                             "4",
                             "push_front 861",
                             "push_front -819",
                             "pop_back",
                             "pop_back"
                },
                "861\n" +
                            "-819\n")
        );

        tr.append(new YTestCase<String, String[]>(
                "EXAMPLE#2",
                new String[]{"7",
                             "10",
                             "push_front -855",
                             "push_front 720",
                             "pop_back",
                             "pop_back",
                             "push_back 844",
                             "pop_back"
                },
                "-855\n" +
                            "720\n" +
                            "844\n")
        );

        tr.append(new YTestCase<String, String[]>(
                "EXAMPLE#3",
                new String[]{"6",
                             "6",
                             "push_front -201",
                             "push_back 959",
                             "push_back 102",
                             "push_front 20",
                             "pop_front",
                             "pop_back"
                },
                "20\n" +
                            "102\n")
        );

        tr.append(new YTestCase<String, String[]>(
                "WRITE-TO-EMPTY-STORAGE",
                new String[]{"4",
                        "0",
                        "push_front 1",
                        "push_back 2",
                        "push_back 3",
                        "push_front 4"
                },
                "error\n" +
                            "error\n" +
                            "error\n" +
                            "error\n")
        );

        tr.append(new YTestCase<String, String[]>(
                "READ-FROM-EMPTY-STORAGE",
                new String[]{"4",
                        "0",
                        "pop_front",
                        "pop_back",
                        "pop_back",
                        "pop_front"
                },
                "error\n" +
                        "error\n" +
                        "error\n" +
                        "error\n")
        );

        tr.append(new YTestCase<String, String[]>(
                "WRITE-TO-ONE-CELL-STORAGE",
                new String[]{"2",
                            "1",
                            "push_front 1",
                            "pop_back"
                },
                "1\n")
        );

        tr.append(new YTestCase<String, String[]>(
                "OVERFLOW-ONE-CELL-STORAGE-FRONT",
                new String[]{"5",
                            "1",
                            "push_front 1",
                            "pop_back",
                            "push_front 2",
                            "push_front 3",
                            "pop_front"
                },
                "1\n" +
                            "error\n" +
                            "2\n")
        );

        tr.append(new YTestCase<String, String[]>(
                "OVERFLOW-ONE-CELL-STORAGE-BACK",
                new String[]{"5",
                        "1",
                        "push_back 1",
                        "pop_front",
                        "push_back 2",
                        "push_back 3",
                        "pop_front",
                },
                "1\n" +
                            "error\n" +
                            "2\n")
        );

        tr.append(new YTestCase<String, String[]>(
                "READ-EMPTY-ONE-CELL-STORAGE-FRONT",
                new String[]{"5",
                        "1",
                        "push_back 1",
                        "pop_front",
                        "push_back 2",
                        "pop_front",
                        "pop_front",
                        "push_back 1",
                        "pop_front",
                },
                "1\n" +
                            "2\n" +
                            "error\n"+
                            "1\n")
        );

        tr.append(new YTestCase<String, String[]>(
                "READ-EMPTY-ONE-CELL-STORAGE-BACK",
                new String[]{"5",
                        "1",
                        "push_front 1",
                        "pop_back",
                        "push_front 2",
                        "pop_back",
                        "pop_back",
                        "push_front 1",
                        "pop_back",
                },
                "1\n" +
                        "2\n" +
                        "error\n"+
                        "1\n")
        );

        tr.append(new YTestCase<String, String[]>(
                "PUSH-POP-FRONT",
                new String[]{"5",
                        "2",
                        "push_front 1",
                        "push_front 2",
                        "pop_front",
                        "pop_front"
                },
                "2\n" +
                            "1\n")
        );


        tr.append(new YTestCase<String, String[]>(
                "CIRCULAR-1",
                new String[]{"8",
                        "4",
                        "push_front 1",
                        "push_back 2",
                        "push_back 3",
                        "push_back 4",
                        "pop_front",
                        "pop_front",
                        "pop_front",
                        "pop_front"
                },
                "1\n" +
                            "2\n" +
                            "3\n" +
                            "4\n")
        );

        tr.append(new YTestCase<String, String[]>(
                "CIRCULAR-2",
                new String[]{"8",
                        "4",
                        "push_front 2",
                        "push_front 1",
                        "push_back 3",
                        "push_back 4",
                        "pop_back",
                        "pop_back",
                        "pop_back",
                        "pop_back"
                },
                "4\n" +
                        "3\n" +
                        "2\n" +
                        "1\n")
        );

        tr.append(new YTestCase<String, String[]>(
                "CIRCULAR-3",
                new String[]{"8",
                        "4",
                        "push_front 1",
                        "push_back 2",
                        "push_back 3",
                        "push_back 4",
                        "pop_back",
                        "pop_back",
                        "pop_back",
                        "pop_back"
                },
                "4\n" +
                        "3\n" +
                        "2\n" +
                        "1\n")
        );

        tr.append(new YTestCase<String, String[]>(
                "CIRCULAR-4",
                new String[]{"6",
                        "4",
                        "push_back 111",
                        "push_front 861",
                        "pop_back",
                        "pop_back",
                        "push_front 555",
                        "pop_back"
                },
                "111\n"+
                        "861\n"+
                        "555\n")
        );

        tr.append(new YTestCase<String, String[]>(
                "CIRCULAR-5",
                new String[]{"6",
                        "4",
                        "push_front 111",
                        "push_back 861",
                        "pop_front",
                        "pop_front",
                        "push_back 555",
                        "pop_front"
                },
                "111\n"+
                            "861\n"+
                            "555\n")
        );

        tr.append(new YTestCase<String, String[]>(
                "CRUSH_TEST-1",
                new String[]{"1",
                        "1000",
                        "pop_back"
                },
                "error\n")
        );

        tr.append(new YTestCase<String, String[]>(
                "CRUSH_TEST-2",
                new String[]{"2",
                        "3",
                        "push_front -1000",
                        "pop_back"
                },
                "-1000\n")
        );

        // run all tests & print results to console
        tr.run();
    }
}
