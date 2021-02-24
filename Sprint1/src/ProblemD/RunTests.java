// import testable class
package ProblemD;

// import test utilities

import ProblemD.SolutionD;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
   public static void main(String[] args){
           // set reference to testable function & setting time limit (time barrier)
           YTestRunner<String,String[]> tr = new YTestRunner<String, String[]>(
                   SolutionD::GetStochasticRatio,
                   String::equals,
                   (String s)-> s,
                   200);

           // add test case definitions
           tr.Append(new YTestCase<String,String[]>(new String[]{
                   "7",
                   "-1 -10 -8 0 2 0 5"
           }, "3"));



           tr.Append(new YTestCase<String,String[]>(new String[]{
                   "5",
                   "1 2 5 4 8"
           }, "2"));

           // run all tests & print results to console
           tr.run();
       }
}
