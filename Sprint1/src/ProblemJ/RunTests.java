// import testable class
package ProblemJ;

// import test utilities
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
   public static void main(String[] args){
       YTestRunner<String, Integer> tr = new YTestRunner<String, Integer>(
               SolutionJ::Factorize,
               String::equals,
               (String s)-> s,
               200);

       // add test case definitions
       tr.append(new YTestCase<String, Integer>(1,"1"));
       tr.append(new YTestCase<String, Integer>(2,"2"));
       tr.append(new YTestCase<String, Integer>(3,"3"));
       tr.append(new YTestCase<String, Integer>(4,"2 2"));
       tr.append(new YTestCase<String, Integer>(6,"2 3"));
       tr.append(new YTestCase<String, Integer>(8,"2 2 2"));
       tr.append(new YTestCase<String, Integer>(9,"3 3"));
       tr.append(new YTestCase<String, Integer>(11,"11"));
       tr.append(new YTestCase<String, Integer>(33,"3 11"));
       tr.append(new YTestCase<String, Integer>(54,"2 3 3 3"));
       // run all tests & print results to console
       tr.run();
    }
}
