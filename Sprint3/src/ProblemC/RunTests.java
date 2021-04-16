// import testable class
package ProblemC;

// import test utilities
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                SolutionC::test,
                String::equals,
                s -> s,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                "EXAMPLE#1",
                new String[]{
                        "abc\n",
                        "ahbgdcu"
                },
                "True"
                )
        );
        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE#2",
                        new String[]{
                                "abcp\n",
                                "ahpc"
                        },
                        "False"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EMPTY-1",
                        new String[]{
                                "\n",
                                "ahpc"
                        },
                        "False"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EMPTY-1",
                        new String[]{
                                "abc\n",
                                ""
                        },
                        "False"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EMPTY-2",
                        new String[]{
                                "\n",
                                ""
                        },
                        "False"
                )
        );


        tr.append(new YTestCase<String, String[]>(
                        "DOUBLE-1",
                        new String[]{
                                "adda\n",
                                "ammdmmmdmhja"
                        },
                        "True"
                )
        );
        tr.append(new YTestCase<String, String[]>(
                        "SHORT-1",
                        new String[]{
                                "a\n",
                                "a"
                        },
                        "True"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SHORT-2",
                        new String[]{
                                "a\n",
                                "b"
                        },
                        "False"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "NO-INTERSECTION-1",
                        new String[]{
                                "abc\n",
                                "def"
                        },
                        "False"
                )
        );
        // run all tests & print results to console
        tr.run();
    }
}
