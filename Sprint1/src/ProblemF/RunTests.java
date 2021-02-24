// import testable class
package ProblemF;

// import test utilities

import ProblemD.SolutionD;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
   public static void main(String[] args){
       YTestRunner<Boolean,String> tr = new YTestRunner<Boolean, String>(
               (String s)-> SolutionF.isPalindromic(s),
               (Boolean a, Boolean b) -> a == b,
               (Boolean b)-> String.valueOf(b),
               200);

       // add test case definitions
       tr.Append(new YTestCase<Boolean, String>(
               "A man, a plan, a canal: Panama",
               true));

       tr.Append(new YTestCase<Boolean, String>(
               "A manN, a plan, a canal: Panama",
               false));

       tr.Append(new YTestCase<Boolean, String>(
               "A   m  a  n, a    pl   an, a ca   nal: Panama",
               true));

       tr.Append(new YTestCase<Boolean, String>(
               "  A man, a plan, a canal: Panama  ",
               true));

       tr.Append(new YTestCase<Boolean, String>(
               "A man1, a plan, a canal: Pa1nama",
               true));

       tr.Append(new YTestCase<Boolean, String>(
               "A m%an1, a p!lan, a ca#nal: Pa1nam)a",
               true));

       tr.Append(new YTestCase<Boolean, String>(
               "123454321",
               true));

       tr.Append(new YTestCase<Boolean, String>(
               "!a!",
               true));

       tr.Append(new YTestCase<Boolean, String>(
               "{}",
               false));

       // run all tests & print results to console
       tr.run();
    }
}
