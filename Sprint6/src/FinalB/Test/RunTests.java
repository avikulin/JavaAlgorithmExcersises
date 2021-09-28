package FinalB.Test;

import FinalB.FinalSolutionB;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {


    YTestRunner<String, String[]> runner = new YTestRunner<>(
            FinalSolutionB::process,
            String::equals,
            s->s,
            200
    );

    runner.append(new YTestCase<>("EXAMPLE-1",
                                    new String[]{
                                                "3",
                                                "RB",
                                                "R"
                                              },
                                    "NO"));

    runner.append(new YTestCase<>("EXAMPLE-2",
                                    new String[]{
                                                "4",
                                                "BBB",
                                                "RB",
                                                "B"
                                               },
                                    "YES"));

    runner.append(new YTestCase<>("EXAMPLE-3",
                                    new String[]{
                                            "5",
                                            "RRRB",
                                            "BRR",
                                            "BR",
                                            "R"
                                    },
                                    "NO"));

        runner.run(/*new String[]{"EXAMPLE-3"}*/);
    }
}
