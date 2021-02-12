// import testable class
package ProblemB;

// import test utilities

import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
   public static void main(String[] args){
         // set reference to testable function & setting time limit (time barrier)
        YTestRunner<Boolean, String> tr = new YTestRunner<Boolean, String>(
                (String s)-> SolutionB.Calculate(s),
                (Boolean a, Boolean b) -> a == b,
                (Boolean b)->String.valueOf(b),
                200);

        // add test case definitions
        tr.Append(new YTestCase<Boolean, String>("1 2 -3", false));
        tr.Append(new YTestCase<Boolean, String>("7 11 7", true));
        tr.Append(new YTestCase<Boolean, String>("6 -2 0", true));

        // run all tests & print results to console
        tr.run();
    }
}
