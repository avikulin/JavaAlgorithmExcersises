package FinalA.Test;

// import test utilities

import FinalA.FinalSolutionA;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTest_mutualPrefixFunc {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                FinalSolutionA::process,
                String::equals,
                i -> i,
                200);

        tr.append(new YTestCase<String, String[]>(
                        "TEST-1",
                        new String[]{"3",
                                     "2[a]2[ab]",
                                     "3[a]2[r2[t]]",
                                     "a2[aa3[b]]"},
                        "aaa"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "TEST-2",
                        new String[]{"3",
                                     "abacabaca",
                                     "2[abac]a",
                                     "3[aba]"},
                        "aba"
                )
        );

        // run all tests & print results to console
        tr.run(/*new String[]{"TEST-6"}*/);
    }
}
