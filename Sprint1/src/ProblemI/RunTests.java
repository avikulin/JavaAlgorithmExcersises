// import testable class
package ProblemI;

// import test utilities
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
   public static void main(String[] args){
       YTestRunner<Boolean, Integer> tr = new YTestRunner<Boolean, Integer>(
               SolutionI::checkPower4,
               Boolean::equals,
               (Boolean b)-> String.valueOf(b),
               200);

       // add test case definitions
       tr.Append(new YTestCase<Boolean, Integer>(1,true));
       tr.Append(new YTestCase<Boolean, Integer>(2,false));
       tr.Append(new YTestCase<Boolean, Integer>(4,true));     //+
       tr.Append(new YTestCase<Boolean, Integer>(8,false));
       tr.Append(new YTestCase<Boolean, Integer>(12,false));
       tr.Append(new YTestCase<Boolean, Integer>(15,false));
       tr.Append(new YTestCase<Boolean, Integer>(16,true));    //+
       tr.Append(new YTestCase<Boolean, Integer>(24,false));
       tr.Append(new YTestCase<Boolean, Integer>(64,true));    //+
       tr.Append(new YTestCase<Boolean, Integer>(1024,true));  //+
       tr.Append(new YTestCase<Boolean, Integer>(4096,true));  //+
       tr.Append(new YTestCase<Boolean, Integer>(10000,false));
       // run all tests & print results to console
       tr.run();
    }
}
