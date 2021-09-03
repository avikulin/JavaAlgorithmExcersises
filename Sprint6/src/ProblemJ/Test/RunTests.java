package ProblemJ.Test;

import ProblemJ.SolutionJ;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {


    YTestRunner<String, String[]> runner = new YTestRunner<>(
        SolutionJ::process,
            String::equals,
            s->s,
            200
    );

    runner.append(new YTestCase<>("EXAMPLE-1",
                                    new String[]{
                                                "5 3",
                                                "3 2",
                                                "3 4",
                                                "2 5"
                                              },
                        "1 3 2 4 5"));

    runner.append(new YTestCase<>("EXAMPLE-2",
                                    new String[]{
                                                "6 3",
                                                "6 4",
                                                "4 1",
                                                "5 1"
                                               },
                        "2 3 5 6 4 1"));

    runner.append(new YTestCase<>("EXAMPLE-3",
                                    new String[]{
                                            "4 0"
                                    },
                                    "1 2 3 4"));

        runner.run(new String[]{"EXAMPLE-1"});
    }
}
