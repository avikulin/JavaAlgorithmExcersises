package ProblemG.Test;

import ProblemG.SolutionG;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {


    YTestRunner<String, String[]> runner = new YTestRunner<>(
        SolutionG::process,
            String::equals,
            s->s,
            200
    );

    runner.append(new YTestCase<>("EXAMPLE-1",
                                    new String[]{
                                                "5 4",
                                                "2 1",
                                                "4 5",
                                                "4 3",
                                                "3 2",
                                                "2"
                                              },
                        "3"));

    runner.append(new YTestCase<>("EXAMPLE-2",
                                    new String[]{
                                                "3 3",
                                                "3 1",
                                                "1 2",
                                                "2 3",
                                                "1"
                                               },
                        "1"));

        runner.append(new YTestCase<>("EXAMPLE-3",
                                    new String[]{
                                                "6 8",
                                                "6 1",
                                                "1 3",
                                                "5 1",
                                                "3 5",
                                                "3 4",
                                                "6 5",
                                                "5 2",
                                                "6 2",
                                                "4"
                                               },
                "3"));


        runner.run(/*new String[]{"EXAMPLE-3"}*/);
    }
}
