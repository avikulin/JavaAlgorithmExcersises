package ProblemE.Test;

// import test utilities

import ProblemE.SolutionE;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                SolutionE::process,
                String::equals,
                i -> i,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-1",
                        new String[]{"3",
                                     "abacaba",
                                     "abudabi",
                                     "abcdefg"},
                        "2"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-2",
                        new String[]{"2",
                                     "tutu",
                                     "kukuku"},
                        "0"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-3",
                        new String[]{"3",
                                     "qwe",
                                     "qwerty",
                                     "qwerpy"},
                        "3"
                )
        );

        // run all tests & print results to console
        tr.run();
    }
}
