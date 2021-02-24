// import testable class
package ProblemA;

// import test utilities
import YTester.YTestRunner;
import YTester.YTestCase;

public class RunTests {
   public static void main(String[] args){
         // set reference to testable function & setting time limit (time barrier)
        YTestRunner<Integer, String> tr = new YTestRunner<Integer, String>(
                SolutionA::Calculate,
                Integer::equals,
                String::valueOf,
                200);

        // add test case definitions
        tr.Append(new YTestCase<Integer, String>("0 1 2 3", 5));
        tr.Append(new YTestCase<Integer, String>("1 1 0 3", 4));
        tr.Append(new YTestCase<Integer, String>("0 1 0 3", 3));
        tr.Append(new YTestCase<Integer, String>("0 1 2 0", 2));
        tr.Append(new YTestCase<Integer, String>("1 0 2 1", 1));
        tr.Append(new YTestCase<Integer, String>("1 0 2 0", 0));
        tr.Append(new YTestCase<Integer, String>("-8 -5 -2 7", -183));
        tr.Append(new YTestCase<Integer, String>("8 2 9 -10", 40));

        // run all tests & print results to console
        tr.run();
    }
}
