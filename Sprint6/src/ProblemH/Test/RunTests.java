package ProblemH.Test;

import ProblemH.SolutionH;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {


    YTestRunner<String, String[]> runner = new YTestRunner<>(
        SolutionH::process,
            String::equals,
            s->s,
            200
    );

    runner.append(new YTestCase<>("EXAMPLE-1",
                                    new String[]{
                                                "6 8",
                                                "2 6",
                                                "1 6",
                                                "3 1",
                                                "2 5",
                                                "4 3",
                                                "3 2",
                                                "1 2",
                                                "1 4"
                                              },
                        "0 11\n" +
                                    "1 6\n" +
                                    "8 9\n" +
                                    "7 10\n" +
                                    "2 3\n" +
                                    "4 5\n"));

    runner.append(new YTestCase<>("EXAMPLE-2",
                                    new String[]{
                                                "3 2",
                                                "1 2",
                                                "2 3"
                                               },
                        "0 5\n" +
                                    "1 4\n" +
                                    "2 3\n"));

        runner.run(/*new String[]{"EXAMPLE-1"}*/);
    }
}
