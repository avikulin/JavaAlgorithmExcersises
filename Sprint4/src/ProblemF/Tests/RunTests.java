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
                        "BIG-3",
                        new String[]{"12",
                                     "abc zxy xyz  yxz cab bca zyx bac cba bac xzy yzx"},
                        "0 4 5 7 8 9\n" +
                                     "1 2 3 6 10 11"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "BIG-4",
                        new String[]{"12",
                                "xzy yzx abc zxy cba bac xyz  yxz cab bca zyx bac"},
                        "0 1 3 6 7 10\n" +
                                "2 4 5 8 9 11"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "BIG-5",
                        new String[]{"16",
                                "abc zxy xyz  ffff yxz cab bca gggg zyx bac cba ff bac xzy yzx ggg"},
                        "0 5 6 9 10 12\n" +
                                "1 2 4 8 13 14\n" +
                                "3\n" +
                                "7\n" +
                                "11\n" +
                                "15"
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
