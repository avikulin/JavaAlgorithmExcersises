package FinalA.Test;

import FinalA.FinalSolutionA;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {


        YTestRunner<String, String[]> runner = new YTestRunner<>(
            FinalSolutionA::process,
                String::equals,
                s->s,
                200
        );

        runner.append(new YTestCase<>("EXAMPLE-1",
                                        new String[]{
                                                    "4 4",
                                                    "1 2 5",
                                                    "1 3 6",
                                                    "2 4 8",
                                                    "3 4 3"
                                                  },
                            "19"));

        runner.append(new YTestCase<>("EXAMPLE-2",
                                        new String[]{
                                                    "3 3",
                                                    "1 2 1",
                                                    "1 2 2",
                                                    "2 3 1"
                                                   },
                            "3"));

        runner.append(new YTestCase<>("EXAMPLE-3",
                                        new String[]{
                                                "2 0"
                                        },
                                        "Oops! I did it again"));
        runner.append(new YTestCase<>("EXAMPLE-7",
                                        new String[]{
                                                "10 20",
                                                "9 10 4",
                                                "2 2 4",
                                                "4 2 8",
                                                "10 5 3",
                                                "1 10 6",
                                                "7 4 2",
                                                "10 10 6",
                                                "3 7 4",
                                                "8 9 4",
                                                "8 10 7",
                                                "6 10 10",
                                                "2 8 8",
                                                "3 8 1",
                                                "3 10 3",
                                                "9 5 8",
                                                "10 10 2",
                                                "1 8 1",
                                                "10 1 5",
                                                "3 6 10",
                                                "9 10 8"
                                        },
                                        "69"));

        runner.run(/*new String[]{"EXAMPLE-7"}*/);
    }
}
