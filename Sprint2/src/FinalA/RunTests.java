// import testable class
package FinalA;

// import test utilities

import YTester.YTestCase;
import YTester.YTestRunner;

import java.util.ArrayList;
import java.util.List;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<List<String>, String[]> tr = new YTestRunner<List<String>, String[]>(
                FinalSolutionA::ProcessCommands,
                List::equals,
                (List<String> list) -> String.join("\n", list),
                200);

        // add test case definitions
        tr.append(new YTestCase<List<String>, String[]>(
                "EXAMPLE#1",
                new String[]{"4",
                             "4",
                             "push_front 861",
                             "push_front -819",
                             "pop_back",
                             "pop_back"
                },
                new ArrayList<String>() {{
                    add("861");
                    add("-819");
                }}));

        tr.append(new YTestCase<List<String>, String[]>(
                "EXAMPLE#2",
                new String[]{"7",
                             "10",
                             "push_front -855",
                             "push_front 720",
                             "pop_back",
                             "pop_back",
                             "push_back 844",
                             "pop_back"
                },
                new ArrayList<String>() {{
                    add("-855");
                    add("720");
                    add("844");
                }}));

        tr.append(new YTestCase<List<String>, String[]>(
                "EXAMPLE#3",
                new String[]{"6",
                             "6",
                             "push_front -201",
                             "push_back 959",
                             "push_back 102",
                             "push_front 20",
                             "pop_front",
                             "pop_back"
                },
                new ArrayList<String>() {{
                    add("20");
                    add("102");
                }}));

        tr.append(new YTestCase<List<String>, String[]>(
                "WRITE-TO-EMPTY-STORAGE",
                new String[]{"4",
                        "0",
                        "push_front 1",
                        "push_back 2",
                        "push_back 3",
                        "push_front 4"
                },
                new ArrayList<String>() {{
                    add("error");
                    add("error");
                    add("error");
                    add("error");
                }}));

        tr.append(new YTestCase<List<String>, String[]>(
                "READ-FROM-EMPTY-STORAGE",
                new String[]{"4",
                        "0",
                        "pop_front",
                        "pop_back",
                        "pop_back",
                        "pop_front"
                },
                new ArrayList<String>() {{
                    add("error");
                    add("error");
                    add("error");
                    add("error");
                }}));

        tr.append(new YTestCase<List<String>, String[]>(
                "WRITE-TO-ONE-CELL-STORAGE",
                new String[]{"2",
                            "1",
                            "push_front 1",
                            "pop_back"
                },
                new ArrayList<String>() {{
                    add("1");
                }}));

        tr.append(new YTestCase<List<String>, String[]>(
                "OVERFLOW-ONE-CELL-STORAGE-FRONT",
                new String[]{"5",
                            "1",
                            "push_front 1",
                            "pop_back",
                            "push_front 2",
                            "push_front 3",
                            "pop_front"
                },
                new ArrayList<String>() {{
                    add("1");
                    add("error");
                    add("2");
                }}));

        tr.append(new YTestCase<List<String>, String[]>(
                "OVERFLOW-ONE-CELL-STORAGE-BACK",
                new String[]{"5",
                        "1",
                        "push_back 1",
                        "pop_front",
                        "push_back 2",
                        "push_back 3",
                        "pop_front",
                },
                new ArrayList<String>() {{
                    add("1");
                    add("error");
                    add("2");
                }}));

        tr.append(new YTestCase<List<String>, String[]>(
                "READ-EMPTY-ONE-CELL-STORAGE-FRONT",
                new String[]{"5",
                        "1",
                        "push_back 1",
                        "pop_front",
                        "push_back 2",
                        "pop_front",
                        "pop_front",
                },
                new ArrayList<String>() {{
                    add("1");
                    add("2");
                    add("error");
                }}));

        tr.append(new YTestCase<List<String>, String[]>(
                "READ-EMPTY-ONE-CELL-STORAGE-BACK",
                new String[]{"5",
                        "1",
                        "push_front 1",
                        "pop_back",
                        "push_front 2",
                        "pop_back",
                        "pop_back"
                },
                new ArrayList<String>() {{
                    add("1");
                    add("2");
                    add("error");
                }}));

        // run all tests & print results to console
        tr.run();
    }
}
