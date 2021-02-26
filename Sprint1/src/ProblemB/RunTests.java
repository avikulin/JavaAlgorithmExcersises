// import testable class
package ProblemB;

// import test utilities

import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
   public static void main(String[] args){
         // set reference to testable function & setting time limit (time barrier)
        YTestRunner<Boolean, String> tr = new YTestRunner<Boolean, String>(
                SolutionB::Calculate,
                (Boolean a, Boolean b) -> a == b,
                String::valueOf,
                200);

        // add test case definitions
        tr.append(new YTestCase<Boolean, String>("1 2 -3", false));
        tr.append(new YTestCase<Boolean, String>("7 11 7", true));
        tr.append(new YTestCase<Boolean, String>("6 -2 0", true));

        // run all tests & print results to console
        tr.run();
    }
}
