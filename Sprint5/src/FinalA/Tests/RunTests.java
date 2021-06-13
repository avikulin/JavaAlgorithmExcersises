package FinalA.Tests;

// import testable class
import FinalA.FinalSolutionA;

// import test utilities
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                FinalSolutionA::process,
                String::equals,
                s -> s,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                "EXAMPLE-1",
                new String[]{"5",
                             "alla 4 100",
                             "gena 6 1000",
                             "gosha 2 90",
                             "rita 2 90",
                             "timofey 4 80"},
                "gena\n" +
                            "timofey\n" +
                            "alla\n" +
                            "gosha\n" +
                            "rita"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-2",
                        new String[]{"5",
                                     "alla 0 0",
                                     "gena 0 0",
                                     "gosha 0 0",
                                     "rita 0 0",
                                     "timofey 0 0"},
                        "alla\n" +
                                    "gena\n" +
                                    "gosha\n" +
                                    "rita\n" +
                                    "timofey"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                "SIMPLE-SORT-1",
                new String[]{"2",
                             "gena 6 0",
                             "timofey 4 0",
                            },
            "gena\n" +
                        "timofey"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SIMPLE-SORT-2",
                        new String[]{"2",
                                "gena 4 0",
                                "timofey 4 0",
                        },
                        "gena\n" +
                                "timofey"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SIMPLE-SORT-2",
                        new String[]{"2",
                                "gena 4 0",
                                "timofey 6 0",
                        },
                    "timofey\n" +
                                "gena"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SIMPLE-SORT-3",
                        new String[]{"2",
                                "gena 100 20",
                                "timofey 100 10",
                        },
                        "timofey\n" +
                                "gena"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SIMPLE-SORT-4",
                        new String[]{"2",
                                "gena 100 10",
                                "timofey 100 10",
                        },
                        "gena\n" +
                                "timofey"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SIMPLE-SORT-5",
                        new String[]{"2",
                                "gena 100 10",
                                "timofey 100 20",
                        },
                        "gena\n" +
                                "timofey"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SIMPLE-SORT-6",
                        new String[]{"2",
                                "gena 100 10",
                                "timofey 100 10",
                        },
                        "gena\n" +
                                "timofey"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SIMPLE-SORT-7",
                        new String[]{"2",
                                "timofey 100 10",
                                "gena 100 10",
                        },
                        "gena\n" +
                                "timofey"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "IDEMPOTENT-SORT-1",
                        new String[]{"5",
                                "gena 6 1000",
                                "timofey 4 80",
                                "alla 4 100",
                                "gosha 2 90",
                                "rita 2 90"},
                    "gena\n" +
                                "timofey\n" +
                                "alla\n" +
                                "gosha\n" +
                                "rita"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "IDEMPOTENT-SORT-2",
                        new String[]{"2",
                                "gosha 2 90",
                                "rita 2 90"},
                    "gosha\n" +
                                "rita"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "IDEMPOTENT-SORT-3",
                        new String[]{"1",
                                "gena 6 1000",
                                },
                        "gena"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "IDEMPOTENT-SORT-4",
                        new String[]{"5",
                                "gena 1000 1",
                                "timofey 1000 2",
                                "alla 1000 3",
                                "gosha 1000 4",
                                "rita 1000 5"},
                        "gena\n" +
                                "timofey\n" +
                                "alla\n" +
                                "gosha\n" +
                                "rita"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "LEXICOGRAPHICAL SORT-1",
                        new String[]{"5",
                                "gena3 0 0",
                                "gena1 0 0",
                                "gena2 0 0",
                                "gena4 0 0",
                                "gena4 0 0"},
                    "gena1\n" +
                                "gena2\n" +
                                "gena3\n" +
                                "gena4\n" +
                                "gena4"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "LEXICOGRAPHICAL SORT-2",
                        new String[]{"5",
                                "gena3 0 0",
                                "gena1 0 0",
                                "gena4 0 0",
                                "gena4 0 0",
                                "gena2 0 0"},
                    "gena1\n" +
                                "gena2\n" +
                                "gena3\n" +
                                "gena4\n" +
                                "gena4"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "LEXICOGRAPHICAL SORT-3",
                        new String[]{"5",
                                "gena5 1000 1000",
                                "gena4 1000 1000",
                                "gena3 1000 1000",
                                "gena2 1000 1000",
                                "gena1 1000 1000"},
                    "gena1\n" +
                                "gena2\n" +
                                "gena3\n" +
                                "gena4\n" +
                                "gena5"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "STABLE SORT-1",
                        new String[]{"5",
                                "gena2 40 0",
                                "gena1 40 0",
                                "gena3 30 0",
                                "gena4 20 0",
                                "gena5 10 0"},
                    "gena1\n" +
                                "gena2\n" +
                                "gena3\n" +
                                "gena4\n" +
                                "gena5"
                )
        );

        // run all tests & print results to console
        tr.run();
    }
}
