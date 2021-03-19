// import testable class
package ProblemI;

// import test utilities


import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                SolutionI::process,
                String::equals,
                s -> s,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                "EXAMPLE#1",
                new String[]{
                        "8",
                        "2",
                        "peek",
                        "push 5",
                        "push 2",
                        "peek",
                        "size",
                        "size",
                        "push 1",
                        "size"
                },
                "None\n" +
                        "5\n" +
                        "2\n" +
                        "2\n" +
                        "error\n" +
                        "2\n"
                )
        );
        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE#2",
                        new String[]{
                                "10",
                                "1",
                                "push 1",
                                "size",
                                "push 3",
                                "size",
                                "push 1",
                                "pop",
                                "push 1",
                                "pop",
                                "push 3",
                                "push 3"
                        },
                        "1\n" +
                                    "error\n" +
                                    "1\n" +
                                    "error\n" +
                                    "1\n" +
                                    "1\n" +
                                    "error\n"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "CIRCULAR#1",
                        new String[]{
                                "11",
                                "5",
                                "push 1",
                                "push 2",
                                "push 3",
                                "push 4",
                                "push 5",
                                "pop",
                                "pop",
                                "pop",
                                "pop",
                                "pop",
                                "size"

                        },
                        "1\n"+
                                    "2\n" +
                                    "3\n" +
                                    "4\n" +
                                    "5\n" +
                                    "0\n"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "CIRCULAR-OVERFLOW-1",
                        new String[]{
                                "15",
                                "5",
                                "push 1",
                                "push 2",
                                "push 3",
                                "pop",
                                "pop",
                                "push 4",
                                "push 5",
                                "peek", //3
                                "pop",  //3
                                "peek", //4
                                "push 6",
                                "push 7",
                                "push 8",
                                "push 9", //error
                                "size" //5

                        },
                        "1\n" +
                                "2\n" +
                                "3\n" +
                                "3\n" +
                                "4\n" +
                                "error\n" +
                                "5\n"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EMPTY-1",
                        new String[]{
                                "5",
                                "1",
                                "pop",
                                "push 1",
                                "peek",
                                "pop",
                                "size"
                        },
                        "None\n" +
                                "1\n" +
                                "1\n" +
                                "0\n"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EMPTY-2",
                        new String[]{
                                "5",
                                "1",
                                "peek",
                                "pop",
                                "push 1",
                                "pop",
                                "size"
                        },
                        "None\n" +
                                "None\n" +
                                "1\n" +
                                "0\n"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EMPTY-2",
                        new String[]{
                                "9",
                                "1",
                                "push 1",
                                "pop",
                                "push 2",
                                "pop",
                                "pop",
                                "pop",
                                "push 3",
                                "peek",
                                "size"
                        },
                        "1\n" +
                                "2\n" +
                                "None\n" +
                                "None\n" +
                                "3\n" +
                                "1\n"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "PEEK-1",
                        new String[]{
                                "26",
                                "5",
                                "push 1",
                                "push 2",
                                "push 3",
                                "push 4",
                                "push 5",
                                "peek", //1
                                "size", //5
                                "pop",  //1
                                "peek", //2
                                "size", //4
                                "pop",  //2
                                "peek", //3
                                "size", //3
                                "pop",  //3
                                "peek", //4
                                "pop",  //4
                                "size", //1
                                "peek", //5
                                "pop",  //5
                                "size", //0
                                "peek", //None
                                "pop",  //None
                                "peek", //None
                                "push 6",
                                "peek", //6
                                "size"  //1
                        },
                        "1\n" +
                                    "5\n" +
                                    "1\n" +
                                    "2\n" +
                                    "4\n" +
                                    "2\n" +
                                    "3\n" +
                                    "3\n" +
                                    "3\n" +
                                    "4\n" +
                                    "4\n" +
                                    "1\n" +
                                    "5\n" +
                                    "5\n" +
                                    "0\n" +
                                    "None\n" +
                                    "None\n" +
                                    "None\n" +
                                    "6\n" +
                                    "1\n"
                )
        );


        // run all tests & print results to console
        tr.run();
    }
}
