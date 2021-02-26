// import testable class
package ProblemH;

// import test utilities

import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
   public static void main(String[] args){
       YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
               SolutionH::binaryAdd,
               String::equals,
               (String s)-> s,
               200);

       // add test case definitions
       tr.append(new YTestCase<String, String[]>(
               new String[]{"0","0"},
               "0"));

       tr.append(new YTestCase<String, String[]>(
               new String[]{"1","0"},
               "1"));

       tr.append(new YTestCase<String, String[]>(
               new String[]{"0","1"},
               "1"));

       tr.append(new YTestCase<String, String[]>(
               new String[]{"1","1"},
               "10"));

       tr.append(new YTestCase<String, String[]>(
               new String[]{"10","1"},
               "11"));

       tr.append(new YTestCase<String, String[]>(
               new String[]{"111","111"},
               "1110"));

       tr.append(new YTestCase<String, String[]>(
               new String[]{"111","0"},
               "111"));

       tr.append(new YTestCase<String, String[]>(
               new String[]{"1000","10000"},
               "11000"));

       tr.append(new YTestCase<String, String[]>(
               new String[]{"111","1"},
               "1000"));

       // run all tests & print results to console
       tr.run();
    }
}
