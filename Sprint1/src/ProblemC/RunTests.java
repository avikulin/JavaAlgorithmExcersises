// import testable class
package ProblemC;

// import test utilities

import ProblemC.SolutionC;
import YTester.YTestCase;
import YTester.YTestRunner;

import java.lang.reflect.Array;
import java.util.Arrays;

public class RunTests {
   public static void main(String[] args){
         // set reference to testable function & setting time limit (time barrier)
        YTestRunner<String,String[]> tr = new YTestRunner<String, String[]>(
                (String[] s)-> SolutionC.Calculate(s),
                (String a, String b) -> a.equals(b),
                (String s)-> s,
                200);

        // add test case definitions
        tr.Append(new YTestCase<String,String[]>(new String[]{
                "4",
                "3",
                "1 2 3",
                "0 2 6",
                "7 4 1",
                "2 7 0",
                "3",
                "0"
        }, "7 7"));

       tr.Append(new YTestCase<String,String[]>(new String[]{
               "4",
               "3",
               "1 2 3",
               "0 2 6",
               "7 4 1",
               "2 7 0",
               "0",
               "0"
       }, "0 2"));

        // run all tests & print results to console
        tr.run();
    }
}
