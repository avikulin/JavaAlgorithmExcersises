// import testable class
package ProblemG;

// import test utilities

import ProblemG.SolutionG;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
   public static void main(String[] args){
       YTestRunner<String, Integer> tr = new YTestRunner<String, Integer>(
               SolutionG::toBooleanString,
               String::equals,
               (String s)-> s,
               200);

       // add test case definitions
       tr.Append(new YTestCase<String, Integer>(
               0,
               "0"));

       tr.Append(new YTestCase<String, Integer>(
               1,
               "1"));

       tr.Append(new YTestCase<String, Integer>(
               2,
               "10"));

       tr.Append(new YTestCase<String, Integer>(
               3,
               "11"));

       tr.Append(new YTestCase<String, Integer>(
               5,
               "101"));

       tr.Append(new YTestCase<String, Integer>(
               8,
               "1000"));
       tr.Append(new YTestCase<String, Integer>(
               10,
               "1010"));

       tr.Append(new YTestCase<String, Integer>(
               14,
               "1110"));
       tr.Append(new YTestCase<String, Integer>(
               255,
               "11111111"));

       // run all tests & print results to console
       tr.run();
    }
}
