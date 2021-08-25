package ProblemC.Test;

import YTester.YTestCase;
import YTester.YTestRunner;
import ProblemC.SolutionC;

public class RunTests {
    public static void main(String[] args) {


    YTestRunner<String, String[]> runner = new YTestRunner<>(
        SolutionC::process,
            String::equals,
            s->s,
            200
    );

    runner.append(new YTestCase<>("EXAMPLE-1",
                                    new String[]{
                                                "4 4",
                                                "3 2",
                                                "4 3",
                                                "1 4",
                                                "1 2",
                                                "3"
                                              },
                        "3 2 1 4"));

        runner.append(new YTestCase<>("EXAMPLE-2",
                                        new String[]{
                                                        "2 1",
                                                        "1 2",
                                                        "1"
                                                   },
                            "1 2"));

        runner.append(new YTestCase<>(  "TEST-9",
                                        new String[]{
                                                "6 7",
                                                "1 2",
                                                "3 1",
                                                "1 4",
                                                "3 6",
                                                "1 5",
                                                "4 2",
                                                "4 3",
                                                "1"
                                        },
                            "1 2 4 3 6 5"));

        runner.run();
    }
}
