// import testable class
package FinalB.Test;

// import test utilities
import FinalB.FinalSolutionB;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                FinalSolutionB::process,
                String::equals,
                i -> i,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-1",
                        new String[]{"4",
                                     "1 5 7 1"},
                         "True"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-2",
                        new String[]{"3",
                                     "2 10 9"},
                        "False"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EMPTY-1",
                        new String[]{"0",
                                     ""},
                        "False"
                )
        );


        tr.append(new YTestCase<String, String[]>(
                        "MIN-1",
                        new String[]{"1",
                                     "2"},
                        "False"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "TEST-4",
                        new String[]{"6",
                                     "7 9 3 4 6 7"},
                        "True"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "TEST-5",
                        new String[]{"8",
                                     "8 1 2 8 2 10 4 5"},
                        "True"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "TEST-6",
                        new String[]{"9",
                                     "4 2 4 0 4 8 0 1 3"},
                        "True"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "TEST-7",
                        new String[]{"3",
                                     "4 4 6"},
                        "False"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "TEST-9",
                        new String[]{"2",
                                     "1 9"},
                        "False"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "TEST-12",
                        new String[]{"63",
                                     "165 163 174 147 84 127 295 215 107 216 " +
                                             "172 222 68 100 185 296 245 254 297 25 " +
                                             "79 60 81 100 291 283 94 17 250 17 266 " +
                                             "263 196 52 292 12 1 156 69 267 21 176 " +
                                             "189 96 12 140 80 6 7 266 72 50 275 42 " +
                                             "257 238 113 6 12 230 3 68 140"},
                        "True"
                )
        );

        // run all tests & print results to console
        tr.run(/*new String[]{"TEST-9"}*/);
    }
}
