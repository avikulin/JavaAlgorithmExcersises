// import testable class
package ProblemJ;

// import test utilities
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                SolutionJ::process,
                String::equals,
                s -> s,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                "EXAMPLE#1",
                new String[]{
                        "10",
                        "put -34",
                        "put -23",
                        "get",
                        "size",
                        "get",
                        "size",
                        "get",
                        "get",
                        "put 80",
                        "size"
                },
                "-34\n" +
                            "1\n" +
                            "-23\n" +
                            "0\n" +
                            "error\n" +
                            "error\n" +
                            "1\n"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE#2",
                        new String[]{
                                "6",
                                "put -66",
                                "put 98",
                                "size",
                                "size",
                                "get",
                                "get"
                        },
                        "2\n" +
                                    "2\n" +
                                    "-66\n" +
                                    "98\n"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE#3",
                        new String[]{
                                "9",
                                "get",
                                "size",
                                "put 74",
                                "get",
                                "size",
                                "put 90",
                                "size",
                                "size",
                                "size"
                        },
                        "error\n" +
                                    "0\n" +
                                    "74\n" +
                                    "0\n" +
                                    "1\n" +
                                    "1\n" +
                                    "1\n"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EMPTY-1",
                        new String[]{
                                "9",
                                "get",
                                "put 1",
                                "put 2",
                                "get",
                                "get",
                                "get",
                                "put 3",
                                "get",
                                "size"

                        },
                        "error\n" +
                                "1\n" +
                                "2\n" +
                                "error\n" +
                                "3\n" +
                                "0\n"
                )
        );



        // run all tests & print results to console
        tr.run();
    }
}
