// import testable class
package ProblemD.Tests;

// import test utilities
import ProblemD.SolutionD;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                SolutionD::groupRecords,
                String::equals,
                i -> i,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-1",
                        new String[]{"8",
                                     "вышивание крестиком",
                                     "рисование мелками на парте",
                                     "настольный керлинг",
                                     "настольный керлинг",
                                     "кухня африканского племени ужасмай",
                                     "тяжелая атлетика",
                                     "таракановедение",
                                     "таракановедение"},
                        "вышивание крестиком\n" +
                                    "рисование мелками на парте\n" +
                                    "настольный керлинг\n" +
                                    "кухня африканского племени ужасмай\n" +
                                    "тяжелая атлетика\n" +
                                    "таракановедение"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EMPTY",
                        new String[]{"0",
                                ""},
                        ""
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "ONE-1",
                        new String[]{"1",
                                "one"},
                        "one"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SAME-1",
                        new String[]{"2",
                                     "one",
                                     "one"},
                        "one"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "CHECK-ORDER-1",
                        new String[]{"2",
                                "two",
                                "one"},
                        "two\n" +
                                     "one"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "CHECK-ORDER-1",
                        new String[]{"2",
                                "two",
                                "one",
                                "two",
                                "two",},
                        "two\n" +
                                    "one"
                )
        );

        // run all tests & print results to console
        tr.run();
    }
}
