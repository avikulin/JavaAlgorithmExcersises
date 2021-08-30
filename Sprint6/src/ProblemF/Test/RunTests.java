package ProblemF.Test;

import ProblemF.SolutionF;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {


    YTestRunner<String, String[]> runner = new YTestRunner<>(
        SolutionF::process,
            String::equals,
            s->s,
            200
    );

    runner.append(new YTestCase<>("EXAMPLE-1",
                                    new String[]{
                                                "5 5" ,
                                                "2 4" ,
                                                "3 5" ,
                                                "2 1" ,
                                                "2 3" ,
                                                "4 5" ,
                                                "1 5"
                                              },
                        "3"));

    runner.append(new YTestCase<>("EXAMPLE-2",
                                    new String[]{
                                                "4 3" ,
                                                "2 3" ,
                                                "4 3" ,
                                                "2 4" ,
                                                "1 3"
                                               },
                        "-1"));

        runner.append(new YTestCase<>("EXAMPLE-3",
                                    new String[]{
                                                "2 1" ,
                                                "2 1" ,
                                                "1 1"
                },
                "0"));

        runner.append(new YTestCase<>("EXAMPLE-11",
                                    new String[]{
                                                    "7 7",
                                                    "1 3",
                                                    "7 6",
                                                    "4 2",
                                                    "7 5",
                                                    "5 2",
                                                    "3 2",
                                                    "1 6",
                                                    "4 1"
                                    },
                "3"));

        runner.append(new YTestCase<>("EXAMPLE-12",
                                    new String[]{
                                            "7 7",
                                            "1 5",
                                            "3 1",
                                            "2 7",
                                            "6 7",
                                            "7 5",
                                            "2 6",
                                            "1 4",
                                            "5 6"
                                    },
                "2"));
        runner.run(/*new String[]{"EXAMPLE-12"}*/);
    }
}
