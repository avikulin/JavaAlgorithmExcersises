package ProblemB.Test;

import ProblemB.SolutionB;
import YTester.YTestCase;
import YTester.YTestRunner;

import javax.management.OperationsException;

public class RunTests {
    public static void main(String[] args) throws OperationsException {
        YTestRunner<String, String[]> testRunner = new YTestRunner<>(
                SolutionB::process,
                String::equals,
                i->i,
                200
        );

        testRunner.append(new YTestCase<String, String[]>(
                "EXAMPLE",
                new String[]{
                        "5 3",
                        "1 3",
                        "2 3",
                        "5 2"},
                "0 0 1 0 0\n" +
                            "0 0 1 0 0\n" +
                            "0 0 0 0 0\n" +
                            "0 0 0 0 0\n" +
                            "0 1 0 0 0"
        ));

        testRunner.append(new YTestCase<String, String[]>(
                "BIDIRECTIONAL",
                new String[]{
                        "2 2",
                        "1 2",
                        "2 1"},
                "0 1\n" +
                            "1 0"
        ));

        testRunner.append(new YTestCase<String, String[]>(
                "ONE-EDGE",
                new String[]{
                        "2 1",
                        "1 2"},
                "0 1\n" +
                            "0 0"
        ));


        testRunner.append(new YTestCase<String, String[]>(
                "ONE-VERTEX",
                new String[]{
                        "1 0"},
                "0"
        ));

        testRunner.append(new YTestCase<String, String[]>(
                "UNSORTED",
                new String[]{
                        "5 3",
                        "2 3",
                        "5 2",
                        "1 3"
                },
                "0 0 1 0 0\n" +
                            "0 0 1 0 0\n" +
                            "0 0 0 0 0\n" +
                            "0 0 0 0 0\n" +
                            "0 1 0 0 0"
        ));

        testRunner.append(new YTestCase<String, String[]>(
                "SIMPLE-LINE-1",
                new String[]{
                        "3 2",
                        "1 2",
                        "2 3"
                },
                "0 1 0\n" +
                            "0 0 1\n" +
                            "0 0 0"
        ));

        testRunner.append(new YTestCase<String, String[]>(
                "SIMPLE-LINE-2",
                new String[]{
                        "3 2",
                        "1 2",
                        "3 2"
                },
                "0 1 0\n" +
                            "0 0 0\n" +
                            "0 1 0"
        ));

        testRunner.append(new YTestCase<String, String[]>(
                "SIMPLE-TRIANGLE-1",
                new String[]{
                        "3 3",
                        "1 2",
                        "2 3",
                        "3 1"
                },
                "0 1 0\n" +
                            "0 0 1\n" +
                            "1 0 0"
        ));

        testRunner.append(new YTestCase<String, String[]>(
                "SIMPLE-TRIANGLE-2",
                new String[]{
                        "3 3",
                        "1 2",
                        "2 3",
                        "1 3"
                },
                "0 1 1\n" +
                            "0 0 1\n" +
                            "0 0 0"
        ));

        testRunner.append(new YTestCase<String, String[]>(
                "BIDIRECTIONAL-TRIANGLE-1",
                new String[]{
                        "3 4",
                        "1 2",
                        "2 3",
                        "1 3",
                        "2 1"
                },
                "0 1 1\n" +
                            "1 0 1\n" +
                            "0 0 0"
        ));

        testRunner.append(new YTestCase<String, String[]>(
                "BIDIRECTIONAL-TRIANGLE-2",
                new String[]{
                        "3 6",
                        "1 2",
                        "2 3",
                        "1 3",
                        "2 1",
                        "3 1",
                        "3 2"
                },
                "0 1 1\n" +
                            "1 0 1\n" +
                            "1 1 0"
        ));

        testRunner.run();//new String[]{"BIDIRECTIONAL"});
    }

}
