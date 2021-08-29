package ProblemE.Test;

import ProblemE.SolutionE;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {


    YTestRunner<String, String[]> runner = new YTestRunner<>(
        SolutionE::process,
            String::equals,
            s->s,
            200
    );

    runner.append(new YTestCase<>("EXAMPLE-1",
                                    new String[]{
                                                "6 3",
                                                "1 2",
                                                "6 5",
                                                "2 3"
                                              },
                        "3\n" +
                                    "1 2 3\n" +
                                    "4\n" +
                                    "5 6"));

        runner.append(new YTestCase<>("EXAMPLE-2",
                                        new String[]{
                                                        "2 0"
                                                   },
                            "2\n" +
                                        "1\n" +
                                        "2"));

        runner.append(new YTestCase<>("EXAMPLE-3",
                                        new String[]{
                                                "4 3",
                                                "2 3",
                                                "2 1",
                                                "4 3"
                                        },
                            "1\n" +
                                        "1 2 3 4"));

//      runner.run(new String[]{"EXAMPLE-1"});
        runner.run();
    }
}
