// import testable class
package ProblemF.Tests;

// import test utilities
import ProblemF.SolutionF;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                SolutionF::groupAnagrams,
                String::equals,
                i -> i,
                200);

        // add test case definitions

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-1",
                        new String[]{"6",
                                     "tan eat tea ate nat bat"},
                        "0 4\n" +
                                    "1 2 3\n" +
                                    "5"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EMPTY-1",
                        new String[]{"0",
                                     ""},
                        ""
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "MIN-1",
                        new String[]{"1",
                                "a"},
                        "0"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "ONE-1",
                        new String[]{"1",
                                     "one"},
                        "0"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "ONE-2",
                        new String[]{"2",
                                     "one one"},
                        "0 1"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "ONE-3",
                        new String[]{"2",
                                     "one eno"},
                        "0 1"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "ONE-3",
                        new String[]{"2",
                                "o o"},
                        "0 1"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "ONE-TWO-1",
                        new String[]{"2",
                                "one two"},
                        "0\n" +
                                    "1"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "ONE-TWO-2",
                        new String[]{"2",
                                "a b"},
                        "0\n" +
                                    "1"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "DIFF-1",
                        new String[]{"5",
                                "one two three four five"},
                        "0\n" +
                                    "1\n" +
                                    "2\n"+
                                    "3\n"+
                                    "4"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SORT-1",
                        new String[]{"8",
                                "abc tan eat bac tea ate nat bat"},
                        "0 3\n" +
                                    "1 6\n" +
                                    "2 4 5\n" +
                                    "7"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "BIG-1",
                        new String[]{"4",
                                        "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa " +
                                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaazzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz " +
                                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaazzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz " +
                                        "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"},
                        "0 1 2 3"
                )
        );


        tr.append(new YTestCase<String, String[]>(
                        "BIG-2",
                        new String[]{"4",
                                "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa " +
                                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaayyzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz " +
                                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaazzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzyy " +
                                "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"},
                        "0 3\n" +
                                    "1 2"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SAME-1",
                        new String[]{"6",
                                     "a b a b a b"},
                        "0 2 4\n" +
                                    "1 3 5"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SAME-2",
                        new String[]{"3",
                                "a a a"},
                        "0 1 2"
                )
        );

        // run all tests & print results to console
        tr.run();
    }
}
