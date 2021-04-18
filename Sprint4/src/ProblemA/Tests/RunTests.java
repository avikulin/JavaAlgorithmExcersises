// import testable class
package ProblemA.Tests;

// import test utilities
import ProblemA.SolutionA;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                SolutionA::process,
                String::equals,
                i -> i,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-2",
                        new String[]{"123", "100003","hash"},
                        "6080"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-2",
                        new String[]{"123", "100003","HaSH"},
                        "56156"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "NAIVE-1",
                        new String[]{"123", "10013","aaaaaaaaaa"},
                        "9920"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "NAIVE-1",
                        new String[]{"123", "10013","aaaaaaaaaa"},
                        "9920"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "NAIVE-2",
                        new String[]{"1", "1","aaaaaaaaaa"},
                        "0"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "NAIVE-3",
                        new String[]{"2", "2","aaaaaaaaaa"},
                        "1"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "NAIVE-4",
                        new String[]{"64", "32","aaaaaaaaaa"},
                        "1"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "NAIVE-5",
                        new String[]{"64", "128","aaaaaaaaaa"},
                        "33"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SIMPLE-1",
                        new String[]{"123", "10013","abaa"},
                        "2481"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SIMPLE-2",
                        new String[]{"123", "10013","aaba"},
                        "7501"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SIMPLE-3",
                        new String[]{"123", "10013","abba"},
                        "2604"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SIMPLE-4",
                        new String[]{"123", "10013","baab"},
                        "5828"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SHORT-1",
                        new String[]{"123","10013","a"},
                        "97"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SHORT-2",
                        new String[]{"123","10013","A"},
                        "65"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SHORT-3",
                        new String[]{"123","10013","Aa"},
                        "8092"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SHORT-4",
                        new String[]{"123","10013","aA"},
                        "1983"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "LONG-1",
                        new String[]{"110017","144967","hashhashhashhashhashhashhashhashhashhashhashhashhashhashhashhashhashhashhash"},
                        "60841"
                )
        );

        // run all tests & print results to console
        tr.run();
    }
}
