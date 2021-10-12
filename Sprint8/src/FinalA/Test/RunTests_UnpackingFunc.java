package FinalA.Test;

// import test utilities

import FinalA.FinalSolutionA;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests_UnpackingFunc {
    public static void main(String[] args) {
        YTestRunner<String, String> tr = new YTestRunner<String, String>(
                FinalSolutionA::unpackTestDriverFunc,
                String::equals,
                i -> i,
                200);

        /* TEST-1
           "h!2[aa3[b]c3[d]e]?gh" =

                3[b] = bbb
                aa3[b] = aabbb
                3[d] = ddd
                c3[d] = cddd
                aa3[b]c3[d]e = aabbbcddde
                2[aa3[b]c3[d]e] = aabbbcdddeaabbbcddde
                h!2[aa3[b]c3[d]e]?gh = h!aabbbcdddeaabbbcddde?gh
         */
        tr.append(new YTestCase<String, String>(
                        "TEST-1",
                        "h!2[aa3[b]c3[d]e]?gh",
                        "h!aabbbcdddeaabbbcddde?gh"
                )
        );

        /*
            TEST-2
            "k!2[1<3[2<3[ab]>]>]?f" =
                3[ab] = ababab
                2<3[ab]> = 2<ababab>
                3[2<3[ab]>] = 2<ababab>2<ababab>2<ababab>
                1<3[2<3[ab]>]> = 1<2<ababab>2<ababab>2<ababab>>
                2[1<3[2<3[ab]>]>] = 1<2<ababab>2<ababab>2<ababab>>1<2<ababab>2<ababab>2<ababab>>
                k!2[1<3[2<3[ab]>]>]?f = k!1<2<ababab>2<ababab>2<ababab>>1<2<ababab>2<ababab>2<ababab>>?f
         */
        tr.append(new YTestCase<String, String>(
                        "TEST-2",
                        "k!2[1<3[2<3[ab]>]>]?f",
                        "k!1<2<ababab>2<ababab>2<ababab>>1<2<ababab>2<ababab>2<ababab>>?f"
                )
        );

        /*
            TEST-3
            "y!2[ab2[2[cd2[01]ef2[23]]gh]kl]?x" =
                2[01] = 0101
                cd2[01] = cd0101
                2[23] = 2323
                ef2[23] = ef2323
                cd2[01]ef2[23] = cd0101ef2323
                2[cd2[01]ef2[23]] = cd0101ef2323cd0101ef2323
                cd2[01]ef2[23]]gh = cd0101ef2323cd0101ef2323gh
                2[2[cd2[01]ef2[23]]gh] = cd0101ef2323cd0101ef2323ghcd0101ef2323cd0101ef2323gh
                ab2[2[cd2[01]ef2[23]]gh]kl = abcd0101ef2323cd0101ef2323ghcd0101ef2323cd0101ef2323ghkl
                y!2[ab2[2[cd2[01]ef2[23]]gh]kl]?x = y!abcd0101ef2323cd0101ef2323ghcd0101ef2323cd0101ef2323
                                                    ghklabcd0101ef2323cd0101ef2323ghcd0101ef2323cd0101ef2323ghkl?x

         */
        tr.append(new YTestCase<String, String>(
                        "TEST-3",
                        "y!2[ab2[2[cd2[01]ef2[23]]gh]kl]?x",
                        "y!abcd0101ef2323cd0101ef2323ghcd0101ef2323cd0101ef2323ghklabcd0101ef2323cd0101" +
                                "ef2323ghcd0101ef2323cd0101ef2323ghkl?x"
                )
        );

        /*
            TEST-4
            "y!2[a2[2[b1[01]c2[23]d]e3[45]]f]?x" =

                1[01] = 01
                b1[01]b01
                2[23] = 2323
                c2[23] = c2323
                b1[01]c2[23] = b01c2323
                b1[01]c2[23]d = b01c2323d
                2[b1[01]c2[23]d] = b01c2323db01c2323d
                3[45] = 454545
                e3[45] = e454545
                2[b1[01]c2[23]d]e3[45] = b01c2323db01c2323de454545
                2[2[b1[01]c2[23]d]e3[45]] = b01c2323db01c2323de454545b01c2323db01c2323de454545
                a2[2[b1[01]c2[23]d]e3[45]]f = ab01c2323db01c2323de454545b01c2323db01c2323de454545f
                2[a2[2[b1[01]c2[23]d]e3[45]]f]= ab01c2323db01c2323de454545b01c2323db01c2323de454545fab01c2323db01
                                                c2323de454545b01c2323db01c2323de454545f
                y!2[a2[2[b1[01]c2[23]d]e3[45]]f]?x = y!ab01c2323db01c2323de454545b01c2323db01c2323de454545fab01c2
                                                     323db01c2323de454545b01c2323db01c2323de454545f?x

         */
        tr.append(new YTestCase<String, String>(
                        "TEST-4",
                        "y!2[a2[2[b1[01]c2[23]d]e3[45]]f]?x",
                        "y!ab01c2323db01c2323de454545b01c2323db01c2323de454545fab01c2323db01c2323de45454" +
                                "5b01c2323db01c2323de454545f?x"
                )
        );

        /* TEST-5

         */
        tr.append(new YTestCase<String, String>(
                        "TEST-5",
                        "h!2[aa3[b]c3[d]e]?gh_3[x]_z",
                        "h!aabbbcdddeaabbbcddde?gh_xxx_z"
                )
        );

        /* TEST-6

         */
        tr.append(new YTestCase<String, String>(
                        "TEST-6",
                        "h!3[b]3[b]_z",
                        "h!bbbbbb_z"
                )
        );
        /* TEST-5

         */
        tr.append(new YTestCase<String, String>(
                        "TEST-7",
                        "h!3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]" +
                                 "3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]" +
                                 "3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]" +
                                 "3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]" +
                                 "3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]" +
                                 "3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]" +
                                 "3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]" +
                                 "3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]" +
                                 "3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]" +
                                 "3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]" +
                                 "3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]" +
                                 "3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]" +
                                 "3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]" +
                                 "3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]" +
                                 "3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]" +
                                 "3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]" +
                                 "3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]" +
                                 "3[b]3[b]3[b]3[b]3[b]3[b]3[b]3[b]_z",
                        "h!bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
                                    "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
                                    "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
                                    "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
                                    "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
                                    "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
                                    "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
                                    "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
                                    "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
                                    "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
                                    "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
                                    "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
                                    "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
                                    "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb_z"
                )
        );


        // run all tests & print results to console
        tr.run(/*new String[]{"TEST-6"}*/);
    }
}
