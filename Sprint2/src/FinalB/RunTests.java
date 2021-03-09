// import testable class
package FinalB;

// import test utilities

import FinalA.FinalSolutionA;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<Integer, String> tr = new YTestRunner<Integer, String>(
                FinalSolutionB::Calculate,
                (a, b)-> a==b,
                x -> String.valueOf(x),
                200);

        // add test case definitions
        tr.append(new YTestCase<Integer, String>(
                "EXAMPLE#1",
                "2 1 + 3 *",
                9)
        );

        tr.append(new YTestCase<Integer, String>(
                "EXAMPLE#2",
                "7 2 + 4 * 2 +",
                38)
        );

        tr.append(new YTestCase<Integer, String>(
                "DIVISION",
                "7 3 / 2 /",
                1)
        );

        tr.append(new YTestCase<Integer, String>(
                "NEGATIVE-DIVISION-1",
                "-7 3 / 2 /",
                -2)
        );

        tr.append(new YTestCase<Integer, String>(
                "NEGATIVE-DIVISION-2",
                "-7 -3 / 2 /",
                1)
        );

        // run all tests & print results to console
        tr.run();
    }
}
