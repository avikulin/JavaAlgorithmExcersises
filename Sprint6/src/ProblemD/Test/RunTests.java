package ProblemD.Test;

import ProblemD.SolutionD;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {


    YTestRunner<String, String[]> runner = new YTestRunner<>(
        SolutionD::process,
            String::equals,
            s->s,
            200
    );

    runner.append(new YTestCase<>("EXAMPLE-1",
                                    new String[]{
                                                "4 4",
                                                "1 2",
                                                "2 3",
                                                "3 4",
                                                "1 4",
                                                "3"
                                              },
                        "3 2 4 1"));

        runner.append(new YTestCase<>("EXAMPLE-2",
                                        new String[]{
                                                        "2 1",
                                                        "1 2",
                                                        "1"
                                                   },
                            "1 2"));

        runner.run();
    }
}
