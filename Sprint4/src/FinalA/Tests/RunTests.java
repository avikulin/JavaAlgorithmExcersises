// import testable class
package FinalA.Tests;

// import test utilities
import FinalA.FinalSolutionA;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                FinalSolutionA::processQueries,
                String::equals,
                i -> i,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-1",
                        new String[]{"3",
                                     "i love coffee",
                                     "coffee with milk and sugar",
                                     "free tea for everyone",
                                     "3",
                                     "i like black coffee without milk",
                                     "everyone loves new year",
                                     "mary likes black coffee without milk"},
                        "1 2\n" +
                                    "3\n" +
                                    "2 1"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-2",
                        new String[]{"6",
                                     "buy flat in moscow",
                                     "rent flat in moscow",
                                     "sell flat in moscow",
                                     "want flat in moscow like crazy",
                                     "clean flat in moscow on weekends",
                                     "renovate flat in moscow",
                                     "1",
                                     "flat in moscow for crazy weekends"},
                         "4 5 1 2 3"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SIMPLE-1",
                        new String[]{"1",
                                     "test",
                                     "1",
                                     "test"},
                         "1"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SIMPLE-2",
                        new String[]{"1",
                                     "test",
                                     "1",
                                     "TEST"},
                         ""
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SIMPLE-3",
                        new String[]{"1",
                                    "buy flat in moscow",
                                    "4",
                                    "buy",
                                    "flat",
                                    "in",
                                    "moscow"},
                        "1\n"+
                                    "1\n"+
                                    "1\n"+
                                    "1"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SIMPLE-4",
                        new String[]{"10",
                                     "set test",
                                     "set test",
                                     "set test",
                                     "set test",
                                     "set test",
                                     "set test",
                                     "set test",
                                     "set test",
                                     "set test",
                                     "set test",
                                     "4",
                                     "set",
                                     "test",
                                     "set test",
                                     "test set"},
                        "1 2 3 4 5\n"+
                                    "1 2 3 4 5\n"+
                                    "1 2 3 4 5\n"+
                                    "1 2 3 4 5"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SIMPLE-5",
                        new String[]{"10",
                                "set test",
                                "set test",
                                "set1 test1",
                                "set2 test2",
                                "set3 test3",
                                "set4 test4",
                                "set5 test5",
                                "set6 test6",
                                "set7 test7",
                                "set8 test8",
                                "4",
                                "set",
                                "test",
                                "set test",
                                "test set"},
                    "1 2\n"+
                                "1 2\n"+
                                "1 2\n"+
                                "1 2"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "RELEVANCE-1",
                        new String[]{"10",
                                "set test", //#1 (set:1 ,test:1) = 2
                                "set test set", //#2 (set:2 ,test:1) = 3
                                "set test set set", //#3 (set:3,test:1) = 4
                                "set test set set set", //#4 (set:4,test:1) = 5
                                "set test", //#5 (set:1,test:1) = 2
                                "set test test", //#6 (set:1,test:2) =3
                                "set test test test", //#7 (set:1,test:3) = 4
                                "set test test test test", //#8 (set:1,test:4) = 5
                                "set test1", //#9 (set:1,test:0) =1
                                "set1 test", //#10 (set:0,test:1) =1
                                "4",
                                "set",
                                "test",
                                "set test",
                                "test set"},
                        "4 3 2 1 5\n"+ // 7 8 9 10
                                "8 7 6 1 2\n"+ // 3 4 5 10 9
                                "4 8 3 7 2\n"+
                                "4 8 3 7 2"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "RELEVANCE-2",
                        new String[]{"10",
                                "set test", //#1 (set:1 ,test:1) = 2
                                "set test set", //#2 (set:2 ,test:1) = 3
                                "set test set set", //#3 (set:3,test:1) = 4
                                "set test set set set", //#4 (set:4,test:1) = 5
                                "set test", //#5 (set:1,test:1) = 2
                                "set test test", //#6 (set:1,test:2) =3
                                "set test test test", //#7 (set:1,test:3) = 4
                                "set test test test test", //#8 (set:1,test:4) = 5
                                "set test1", //#9 (set:1,test:0) =1
                                "set1 test", //#10 (set:0,test:1) =1
                                "4",
                                "set set set",
                                "test test test",
                                "set test set test",
                                "test set test set"},
                        "4 3 2 1 5\n"+ // 7 8 9 10
                                "8 7 6 1 2\n"+ // 3 4 5 10 9
                                "4 8 3 7 2\n"+
                                "4 8 3 7 2"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "RELEVANCE-3",
                        new String[]{"5",
                                "set test",
                                "set test set",
                                "set test set set",
                                "set test set set set",
                                "test",
                                "3",
                                "set",
                                "set set",
                                "set set set"},
                    "4 3 2 1\n"+
                                "4 3 2 1\n"+
                                "4 3 2 1"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "RELEVANCE-4",
                        new String[]{"5",
                                "set test",
                                "set test test",
                                "set test test test",
                                "set test test test test",
                                "test",
                                "3",
                                "set",
                                "set set",
                                "set set set"},
                    "1 2 3 4\n"+
                                "1 2 3 4\n"+
                                "1 2 3 4"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "RELEVANCE-5",
                        new String[]{"5",
                                "set test",
                                "set test test",
                                "set test test test",
                                "set test test test test",
                                "test",
                                "3",
                                "test",
                                "test test",
                                "test test test"},
                        "4 3 2 1 5\n"+
                                "4 3 2 1 5\n"+
                                "4 3 2 1 5"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "RELEVANCE-6",
                        new String[]{"5",
                                "set test", //#1
                                "set Test test", //#2
                                "set Test test Test", //#3
                                "set test test test test", //#4
                                "test",//#5
                                "3",
                                "Test",
                                "Test test",
                                "Test test test"},
                        "3 2\n"+
                                "4 3 2 1 5\n"+
                                "4 3 2 1 5"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "NEGATIVE-1",
                        new String[]{"1",
                                "buy flat in moscow",
                                "4",
                                "Buy",
                                "Flat",
                                "In",
                                "Moscow"},
                        ""
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "NEGATIVE-1",
                        new String[]{"1",
                                "buy flat in moscow",
                                "4",
                                "Buy Buy buy Buy Buy Buy Buy",
                                "Flat flat Flat Flat Flat Flat",
                                "In in In In In In In",
                                "Moscow moscow Moscow Moscow Moscow"},
                        "1\n"+
                                    "1\n"+
                                    "1\n"+
                                    "1"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "POSITIVE-1",
                        new String[]{"10",
                                "buy flat in moscow",
                                "buy flat in moscow",
                                "buy flat in moscow",
                                "buy flat in moscow",
                                "buy flat in moscow",
                                "buy flat in moscow",
                                "buy flat in moscow",
                                "buy flat in moscow",
                                "buy flat in moscow",
                                "buy flat in moscow",
                                "1",
                                "buy flat"},
                        "1 2 3 4 5"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "POSITIVE-2",
                        new String[]{"1",
                                "buy flat in moscow",
                                "4",
                                "Buy Buy buy Buy Buy Buy Buy",
                                "Flat flat Flat Flat Flat Flat",
                                "In in In In In In In",
                                "Moscow moscow Moscow Moscow Moscow"},
                        "1\n"+
                                "1\n"+
                                "1\n"+
                                "1"
                )
        );
        tr.append(new YTestCase<String, String[]>(
                        "SORTING-1",
                        new String[]{"10",
                                "buy Flat In Moscow", //1
                                "Buy flat In Moscow", //1
                                "Buy Flat in Moscow", //1
                                "Buy Flat In moscow", //1
                                "buy Flat In Moscow", //1
                                "Buy flat In Moscow", //1
                                "Buy Flat in Moscow", //1
                                "Buy Flat In moscow", //1
                                "buy flat in moscow", //4
                                "buy flat in moscow", //4
                                "1",
                                "buy flat in moscow"},
                        "9 10 1 2 3"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SORTING-2",
                        new String[]{"10",
                                "buy Flat In Moscow", //1
                                "Buy flat In Moscow", //1
                                "Buy Flat in Moscow", //1
                                "Buy Flat In moscow", //1
                                "buy flat In Moscow", //2
                                "Buy flat in Moscow", //2
                                "Buy Flat in moscow", //2
                                "buy Flat In moscow", //2
                                "buy flat in moscow", //4
                                "buy flat in moscow", //4
                                "1",
                                "buy flat in moscow"},
                        "9 10 5 6 7"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SORTING-3",
                        new String[]{"5",
                                "set test", //#1
                                "set set test test", //#2
                                "set set set test test test", //#3
                                "set set set set test test test test", //#4
                                "set set set set set test test test test test", //#5
                                "3",
                                "set",
                                "test",
                                "set test"},
                        "5 4 3 2 1\n"+
                                    "5 4 3 2 1\n"+
                                    "5 4 3 2 1"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SORTING-4",
                        new String[]{"5",
                                "set test", //#1
                                "set test test", //#2
                                "set set test test test", //#3
                                "set set test test test test", //#4
                                "set set set test test test test test", //#5
                                "1",
                                "set test"},
                        "5 4 3 2 1"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SORTING-5",
                        new String[]{"5",
                                "set test", //#1
                                "set test test", //#2
                                "set set test test test", //#3
                                "set set test test test test", //#4
                                "set set set test test test test test", //#5
                                "1",
                                "set"},
                        "5 3 4 1 2"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SORTING-5",
                        new String[]{"5",
                                "set", //#1
                                "set test", //#2
                                "set set test", //#3
                                "set set test ", //#4
                                "set set set test", //#5
                                "1",
                                "set test"},
                        "5 3 4 2 1"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SORTING-6",
                        new String[]{"5",
                                "set", //#1
                                "set test", //#2
                                "set set test", //#3
                                "set set test ", //#4
                                "set set set test", //#5
                                "1",
                                "set test Test"},
                        "5 3 4 2 1"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SORTING-7",
                        new String[]{"2",
                                "set", //#1
                                "test", //#2
                                "6",
                                "set",
                                "test",
                                "set test",
                                "test set",
                                "set set test",
                                "test test set"},
                    "1\n"+
                                "2\n"+
                                "1 2\n"+
                                "1 2\n"+
                                "1 2\n"+
                                "1 2"
                )
        );
        // run all tests & print results to console
        tr.run();
    }
}
