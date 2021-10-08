package YTester;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.function.Function;
/**
 * Base class for bath test-case execution/
 *
 * @param <T> type of result dataset.
 * @param <R> type of input dataset.
 */
public class YTestRunner<T, R> implements Runnable {
    private final List<YTestCase<T, R>> testCaseList;
    private final int timeLimitMs;
    private final ThrowableFunction<R, T> funcToInvoke; // reference to testable function
    private final Comparer<T> funcCompare; // reference to function to compare obtained result with expected value
    private final Function<T, String> funcCastResToString; // reference to function for serialization obtained result to string

    /**
     * Constructor for YTestRunner
     *
     * @param func                  static function of testable object to invoke.
     *                              T - type of result, R - type of input dataset.
     * @param comparator            delegate to function, that compares result with the right answer.
     *                              T - type of result
     * @param castToStringFunc      delegate to perform casting result to string for printing in logs.
     *                              T - type of result.
     * @param timeLimitMilliseconds time limit for executing testable function. The test will stop if it will be exceeded.
     */
    public YTestRunner(ThrowableFunction<R, T> func, Comparer<T> comparator, Function<T, String> castToStringFunc, int timeLimitMilliseconds) {
        this.testCaseList = new ArrayList<YTestCase<T, R>>(10);
        funcToInvoke = func;
        funcCompare = comparator;
        funcCastResToString = castToStringFunc;
        timeLimitMs = timeLimitMilliseconds;
    }

    /**
     * Append new test case to batch plan
     *
     * @param testCase Reference to test-case instance.
     */
    public void append(YTestCase<T, R> testCase) {
        testCaseList.add(testCase);
    }

    private List<YTestCase<T, R>> createTestPlan(Set<String> testCaseSubSet){
        if (testCaseSubSet == null) return testCaseList;

        List<YTestCase<T, R>> res = new ArrayList<>();
        for (YTestCase<T, R> testCase: testCaseList){
            if (testCaseSubSet.contains(testCase.Id))
                res.add(testCase);
        }
        return res;
    }

    /**
     * Run test-plan in batch execution.
     */
    public void executeTestPlan(Set<String> testCaseSubSet) {
        StringBuilder sb = new StringBuilder();
        String templateOK = "#%d\tresult - Ok\t(time spent: %d ms)\r\n";
        String templateErr = "#%d\tresult - Error\tyour answer - %s\tright answer - %s\t(time spent: %d ms)\r\n";
        String templateErrWithID = "#%d [%s]\tresult - Error\tyour answer - %s\tright answer - %s\t(time spent: %d ms)\r\n";
        String templateExceptionWithID = "#%d [%s]\tRuntime exception: [ %s ]\n\tStack trace: %s\r\n";
        int counter = 0;

        List<YTestCase<T, R>> testPlan = createTestPlan(testCaseSubSet);
        for (YTestCase<T, R> testCase : testPlan) {
            counter++;

            long startTime = System.nanoTime();

            try {
                T  res = funcToInvoke.tryApply(testCase.input);

                long endTime = System.nanoTime();
                long timeSpan = (endTime - startTime)/1_000_000; //in milliseconds

                String logItem;
                if (!this.funcCompare.compare(testCase.expectedResult, res)) {
                    if (testCase.hasId) {
                        logItem = String.format(templateErrWithID,
                                counter,
                                testCase.Id,
                                funcCastResToString.apply(res),
                                funcCastResToString.apply(testCase.expectedResult),
                                timeSpan);
                    } else {
                        logItem = String.format(templateErr,
                                counter,
                                funcCastResToString.apply(res),
                                funcCastResToString.apply(testCase.expectedResult),
                                timeSpan);
                    }
                } else {
                    logItem = String.format(templateOK, counter, timeSpan);
                }

                sb.append(logItem);
                if (timeSpan > this.timeLimitMs) {
                    sb.append("TIME LIMIT EXCEEDED!\t");
                    //break;
                }
            } catch (Exception e){
                String errorMessage = e.getMessage();
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));

                String stackTrace = sw.toString();
                String msg = String.format(templateExceptionWithID, counter, testCase.Id,errorMessage, stackTrace);

                sb.append(msg);
            }
        }

        System.out.println(sb.toString());
    }

    public void run(String[] testCaseIDs){
        Set<String> IDs = new HashSet<>(Arrays.asList(testCaseIDs));
        executeTestPlan(IDs);
    }

    public void run(){
        executeTestPlan(null);
    }
}