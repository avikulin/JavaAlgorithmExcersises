package FinalB.Tests;

import FinalB.Solution;
import FinalB.Utils.TreeBuilder;
import FinalB.Tests.TestParamsBundle;

// import test utilities
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, TestParamsBundle> tr = new YTestRunner<String, TestParamsBundle>(
                FinalB.Solution::process,
                String::equals,
                i -> i,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "",
                        new TestParamsBundle(new TreeBuilder(new int[]{-1, 50,
                                                                        25, -1,
                                                                        15, 35, -1, -1,
                                                                        5, 20, 30, 40, -1, -1, -1, -1}
                                                            ).getRootNode(), 3, 25),
                        "[-1, 50, 30, 15, 35, -1, -1, 5, 20, 40, -1, -1, -1, -1]"
                )
        );

        // run all tests & print results to console
        tr.run();
    }
}
