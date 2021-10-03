// import testable class
package ProblemE.Test;

// import test utilities
import ProblemE.SolutionE;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                SolutionE::process,
                String::equals,
                i -> i,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-1",
                        new String[]{"130",
                                     "4",
                                     "10 3 40 1"},
                        "4"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-2",
                        new String[]{"100",
                                     "2",
                                     "7 5"},
                        "16"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-3",
                        new String[]{"1",
                                     "1",
                                     "1"},
                        "1"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SHORT-1",
                        new String[]{"23",
                                     "3",
                                     "5 10 20"},
                        "-1"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SHORT-2",
                        new String[]{"102",
                                     "2",
                                     "3 10"},
                        "13"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "SHORT-3",
                        new String[]{"100",
                                     "2",
                                     "5 15"},
                        "8"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "BIG-1",
                        new String[]{"5186",
                                     "200",
                                     "6320 8444 8340 5320 4227 6084 5612 9573 9505 1805 2054 6126 5011 2124 5136 7761 250 8097 2828 9327 3449 5608 1089 2201 9548 5184 5952 5072 3139 2817 586 9682 4096 9968 435 5267 9366 7742 6325 2990 943 3638 6554 5683 7162 6522 3539 1015 7195 407 9781 4709 8557 4970 8451 2305 2032 4708 4609 5630 487 1948 5719 5761 1604 9921 7063 3844 2030 3173 2869 9443 9637 5419 8438 1593 4018 4515 7940 9778 1897 2881 2286 2616 5792 6280 4201 1695 3128 7068 5985 7248 5143 6551 1190 571 63 8730 8348 6912 2431 8857 4616 4173 3630 8288 5115 1398 9767 7254 5846 5161 9383 5258 4749 4183 2602 8612 6691 7641 1388 1467 8076 5012 6351 3383 3731 1524 8593 9874 1602 2801 5838 2682 6037 3438 3819 5198 8040 2310 4044 7482 1733 2956 7961 3928 2043 4199 2804 7414 1705 9200 3645 1044 2812 3281 2501 368 4492 3339 3784 1481 2759 7235 5564 6277 1571 6001 8989 5731 6415 6055 7666 9343 1693 2441 1375 7558 8258 7992 4926 766 566 3049 9731 3453 668 7184 3557 3164 263 7827 7168 8287 1397 4496 888 6465 4833 9709"},
                        "2"
                )
        );
        // run all tests & print results to console
        tr.run(new String[]{"BIG-1"});
    }
}
