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
                        new String[]{"examiwillpasstheexam",
                                     "5",
                                     "will",
                                     "pass",
                                     "the",
                                     "exam",
                                     "i"},
                        "YES"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-2",
                        new String[]{"abacaba",
                                     "2",
                                     "abac",
                                     "caba"},
                        "NO"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-3",
                        new String[]{"abacaba",
                                     "3",
                                     "abac",
                                     "caba",
                                     "aba"},
                        "YES"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "TEST-1",
                        new String[]{"wwwfc",
                                     "3",
                                     "w",
                                     "ww",
                                     "wwfc"},
                        "YES"
                )
        );

        // run all tests & print results to console
        tr.run( /*new String[]{"TEST-1"}*/);
    }
}
