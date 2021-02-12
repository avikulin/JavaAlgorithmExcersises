package YTester;

import java.util.List;
import java.util.function.Function;
import java.util.ArrayList;

import static java.util.Optional.ofNullable;

/**
 *
 * @param <T>   type of result dataset.
 * @param <R>   type of input dataset.
 */
public class YTestRunner<T,R> implements Runnable{
    private List<YTestCase<T, R>> testCaseList;
    private int timeLimitMs = 200;
    private Function<R, T> funcToInvoke;
    private Comparer<T> funcCompare;
    private Function<T, String> funcCastResToString;

    private String templateOK = "#%d\tresult - Ok\t(time spent: %d ms)\r\n";
    private String templateErr = "#%d\tresult - Error\tyour answer - %s\tright answer - %s\t(time spent: %d ms)\r\n";

    /**
     * Constructor of YTestRunner
     * @param func  static function of testable object to invoke.
     *              T - type of result, R - type of input dataset.
     * @param comparator delegate to function, that compares result with the right answer.
     *                   T - type of result
     * @param castToStringFunc delegate to perform casting result to string for printing in logs.
     *                         T - type of result.
     * @param timeLimitMilliseconds timelimit for executing testable funtion. The test will stop if it will be exceeded.
     */
    public YTestRunner(Function<R, T> func, Comparer<T> comparator, Function<T, String> castToStringFunc, int timeLimitMilliseconds) {
        this.testCaseList = new ArrayList<YTestCase<T, R>>(10);
        funcToInvoke = func;
        funcCompare = comparator;
        funcCastResToString = castToStringFunc;
        timeLimitMs = timeLimitMilliseconds;
    }

    public void Append(YTestCase<T, R> testCase){
        testCaseList.add(testCase);
    }

    public void run() {
        StringBuilder sb = new StringBuilder();

        int counter = 0;
        for (YTestCase<T, R> testCase: testCaseList){
            counter++;
            long startTime = System.currentTimeMillis();
            T res = funcToInvoke.apply(testCase.input);
            long endTime = System.currentTimeMillis();
            long timeSpan = endTime - startTime;

            String logItem;
            if (!this.funcCompare.compare(testCase.expectedResult,res)){
                logItem = String.format(templateErr,
                                        counter,
                                        funcCastResToString.apply(res),
                                        funcCastResToString.apply(testCase.expectedResult),
                                        timeSpan);
            } else {
                logItem = String.format(templateOK, counter, timeSpan);
            }
            sb.append(logItem);
            if (timeSpan > this.timeLimitMs){
                sb.append("TIME LIMIT EXCEEDED!");
                break;
            }
        }

        System.out.println(sb.toString());
    }
}