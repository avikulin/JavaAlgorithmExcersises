// import testable class
package ProblemE;

// import test utilities

import YTester.YTestCase;
import YTester.YTestRunner;

import java.util.Arrays;

public class RunTests {
   public static void main(String[] args){
         // set reference to testable function & setting time limit (time barrier)
        YTestRunner<String[],String[]> tr = new YTestRunner<String[], String[]>(
                SolutionE::GetLongestWord,
                Arrays::equals,
                (String[] s) -> s[0],
                200);

        // add test case definitions
        tr.append(new YTestCase<String[],String[]>(new String[]{
                "19",
                "i love segment tree"
        }, new String[]{"segment","7"}));

       tr.append(new YTestCase<String[],String[]>(new String[]{
               "19",
               "i love segment tree s0gm1nt"
       }, new String[]{"segment","7"}));


       tr.append(new YTestCase<String[],String[]>(new String[]{
               "21",
               "frog jumps from river"
       }, new String[]{"jumps", "5"}));

       tr.append(new YTestCase<String[],String[]>(new String[]{
               "17",
               " sybtbv jxqwbu cj"
       }, new String[]{"sybtbv", "6"}));

        // run all tests & print results to console
        tr.run();
    }
}
