// import testable class
package FinalB.Tests;

// import test utilities
import FinalB.FinalSolutionB;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                FinalSolutionB::processRequests,
                String::equals,
                i -> i,
                200);

        // add test case definitions

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-1",
                        new String[]{"10",
                                     "get 1",
                                     "put 1 10",
                                     "put 2 4",
                                     "get 1",
                                     "get 2",
                                     "delete 2",
                                     "get 2",
                                     "put 1 5",
                                     "get 1",
                                     "delete 2"},
                        "None\n" +
                                    "10\n" +
                                    "4\n" +
                                    "4\n" +
                                    "None\n" +
                                    "5\n" +
                                    "None\n"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-2",
                        new String[]{"8",
                                     "get 9",
                                     "delete 9",
                                     "put 9 1",
                                     "get 9",
                                     "put 9 2",
                                     "get 9",
                                     "put 9 3",
                                     "get 9"},
                        "None\n" +
                                    "None\n" +
                                    "1\n" +
                                    "2\n" +
                                    "3\n"
                )
        );

        // run all tests & print results to console
        tr.run();
    }
}
