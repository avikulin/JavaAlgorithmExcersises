// import testable class
package FinalA;

// import test utilities

import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                FinalSolutionA2::calculateDistance,
                String::equals,
                (String s) -> s,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                new String[]{"5", "0 1 4 9 0"},
                "0 1 2 1 0"));

        tr.append(new YTestCase<String, String[]>(
                new String[]{"6", "0 7 9 4 8 20"},
                "0 1 2 3 4 5"));

        tr.append(new YTestCase<String, String[]>(
                new String[]{"6", "20 7 9 4 8 0"},
                "5 4 3 2 1 0"));

        tr.append(new YTestCase<String, String[]>(
                "MINIMAL",
                new String[]{"1", "0"},
                "0"));

        tr.append(new YTestCase<String, String[]>(
                "MIN-SEQ-LEFT",
                new String[]{"2", "1 0"},
                "1 0"));

        tr.append(new YTestCase<String, String[]>(
                "MIN-SEQ-RIGHT",
                new String[]{"2", "0 1"},
                "0 1"));

        tr.append(new YTestCase<String, String[]>(
                "ALL-ZEROES",
                new String[]{"6", "0 0 0 0 0 0"},
                "0 0 0 0 0 0"));

        tr.append(new YTestCase<String, String[]>(
                new String[]{"6", "0 0 0 0 0 1"},
                "0 0 0 0 0 1"));

        tr.append(new YTestCase<String, String[]>(
                "ALL-ZEROES-RIGHT",
                new String[]{"6", "1 0 0 0 0 0"},
                "1 0 0 0 0 0"));

        tr.append(new YTestCase<String, String[]>(
                "ALT-ZEROES",
                new String[]{"6", "1 0 5 0 6 7"},
                "1 0 1 0 1 2"));

        tr.append(new YTestCase<String, String[]>(
                "DOUBLE-ZEROES-MIDDLE",
                new String[]{"6", "1 2 0 0 4 5"},
                "2 1 0 0 1 2"));

        tr.append(new YTestCase<String, String[]>(
                "DOUBLE-END-ZEROES",
                new String[]{"6", "0 2 0 0 4 0"},
                "0 1 0 0 1 0"));

        // run all tests & print results to console
        tr.run();
    }
}
