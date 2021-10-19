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
        /*

         */

        tr.append(new YTestCase<String, String>(
                        "TEST-7",
                        "",
                        ""
                )
        );

        tr.append(new YTestCase<String, String>(
                        "TEST-8",
                        "2[a]2[ab]",
                        "aaabab"
                )
        );

        tr.append(new YTestCase<String, String>(
                        "TEST-9",
                        "3[a]2[r2[t]]",
                        "aaarttrtt"
                )
        );

        tr.append(new YTestCase<String, String>(
                        "TEST-10",
                        "a2[aa3[b]]",
                        "aaabbbaabbb"
                )
        );

        tr.append(new YTestCase<String, String>(
                        "TEST-11",
                        "abacabaca",
                        "abacabaca"
                )
        );

        tr.append(new YTestCase<String, String>(
                        "TEST-12",
                        "2[abac]a",
                        "abacabaca"
                )
        );

        tr.append(new YTestCase<String, String>(
                        "TEST-13",
                        "3[aba]",
                        "abaabaaba"
                )
        );

        tr.append(new YTestCase<String, String>(
                        "TEST-14",
                        "",
                        ""
                )
        );

        tr.append(new YTestCase<String, String>(
                        "TEST-15",
                        "a3[b]c2[d]e2[f3[g]h3[k]lm]no",
                        "abbbcddefggghkkklmfggghkkklmno"
                )
        );

        tr.append(new YTestCase<String, String>(
                        "TEST-16",
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

        tr.append(new YTestCase<String, String>(
                        "YANDEX-TEST-38",
                        "2[aba]",
                        "abaaba"
                )
        );

        tr.append(new YTestCase<String, String>(
                        "YANDEX-TEST-39",
                        "a2[qwer2[ty]]x",
                        "aqwertytyqwertytyx"
                )
        );

        tr.append(new YTestCase<String, String>(
                        "YANDEX-TEST-40a",
                        "abc3[d2[q]]2[q2[w]]p",
                        "abcdqqdqqdqqqwwqwwp"
                )
        );

        tr.append(new YTestCase<String, String>(
                        "YANDEX-TEST-40b",
                        "abc3[dqq]2[qww]p",
                        "abcdqqdqqdqqqwwqwwp"
                )
        );

        tr.append(new YTestCase<String, String>(
                        "YANDEX-TEST-40c",
                        "abcdqqdqqdqqqwwqwwp",
                        "abcdqqdqqdqqqwwqwwp"
                )
        );

        tr.append(new YTestCase<String, String>(
                        "YANDEX-TEST-36",
                        "3[kex]2[2[2[1[m]2[h]2[qyo]3[daia]1[dkq]]3[2[cf]2[vsdx]3[mz]3[argb]2[s]]1[szv]" +
                                 "3[3[b]]3[3[a]2[sm]3[f]1[i]3[v]3[jf]]2[ke]]2[2[ltq]1[isum]3[1[ow]2[kvwl]1[niv]" +
                                 "1[pk]]1[3[uwnp]]3[2[xor]]3[2[e]3[ouq]2[fu]2[yham]]]2[jbd]]2[wrm]2[1[3[v]3[q]" +
                                 "2[r]]3[2[epwt]3[wyu]2[ovae]2[jxl]1[qpw]3[gz]]]2[mhf]2[3[2[ps]3[1[ls]]1[2[sg]" +
                                 "1[f]3[c]1[lfnk]2[x]]]1[2[sgb]1[wn]2[y]]]2[2[q]1[3[y]1[3[wdtl]2[va]1[j]3[wrp]" +
                                 "3[lppl]]]1[1[jvx]1[eix]3[z]2[j]2[js]2[emx]]3[2[paib]2[fdw]1[w]1[rem]]2[divg]" +
                                 "3[2[fvdo]1[e]2[qv]]]1[1[pj]2[2[v]2[f]3[tkjo]2[esps]3[v]]1[3[a]1[eo]3[fyl]]]",
                     "kexkexkexmhhqyoqyodaiadaiadaiadkqmhhqyoqyodaiadaiadaiadkqcfcfvsdxvsdxmzmzmza" +
                                 "rgbargbargbsscfcfvsdxvsdxmzmzmzargbargbargbsscfcfvsdxvsdxmzmzmzargbargbargbs" +
                                 "sszvbbbbbbbbbaaasmsmfffivvvjfjfjfaaasmsmfffivvvjfjfjfaaasmsmfffivvvjfjfjfkek" +
                                 "emhhqyoqyodaiadaiadaiadkqmhhqyoqyodaiadaiadaiadkqcfcfvsdxvsdxmzmzmzargbargba" +
                                 "rgbsscfcfvsdxvsdxmzmzmzargbargbargbsscfcfvsdxvsdxmzmzmzargbargbargbssszvbbbb" +
                                 "bbbbbaaasmsmfffivvvjfjfjfaaasmsmfffivvvjfjfjfaaasmsmfffivvvjfjfjfkekeltqltqi" +
                                 "sumowkvwlkvwlnivpkowkvwlkvwlnivpkowkvwlkvwlnivpkuwnpuwnpuwnpxorxorxorxorxorx" +
                                 "oreeouqouqouqfufuyhamyhameeouqouqouqfufuyhamyhameeouqouqouqfufuyhamyhamltqlt" +
                                 "qisumowkvwlkvwlnivpkowkvwlkvwlnivpkowkvwlkvwlnivpkuwnpuwnpuwnpxorxorxorxorxo" +
                                 "rxoreeouqouqouqfufuyhamyhameeouqouqouqfufuyhamyhameeouqouqouqfufuyhamyhamjbd" +
                                 "jbdmhhqyoqyodaiadaiadaiadkqmhhqyoqyodaiadaiadaiadkqcfcfvsdxvsdxmzmzmzargbarg" +
                                 "bargbsscfcfvsdxvsdxmzmzmzargbargbargbsscfcfvsdxvsdxmzmzmzargbargbargbssszvbb" +
                                 "bbbbbbbaaasmsmfffivvvjfjfjfaaasmsmfffivvvjfjfjfaaasmsmfffivvvjfjfjfkekemhhqy" +
                                 "oqyodaiadaiadaiadkqmhhqyoqyodaiadaiadaiadkqcfcfvsdxvsdxmzmzmzargbargbargbssc" +
                                 "fcfvsdxvsdxmzmzmzargbargbargbsscfcfvsdxvsdxmzmzmzargbargbargbssszvbbbbbbbbba" +
                                 "aasmsmfffivvvjfjfjfaaasmsmfffivvvjfjfjfaaasmsmfffivvvjfjfjfkekeltqltqisumowk" +
                                 "vwlkvwlnivpkowkvwlkvwlnivpkowkvwlkvwlnivpkuwnpuwnpuwnpxorxorxorxorxorxoreeou" +
                                 "qouqouqfufuyhamyhameeouqouqouqfufuyhamyhameeouqouqouqfufuyhamyhamltqltqisumo" +
                                 "wkvwlkvwlnivpkowkvwlkvwlnivpkowkvwlkvwlnivpkuwnpuwnpuwnpxorxorxorxorxorxoree" +
                                 "ouqouqouqfufuyhamyhameeouqouqouqfufuyhamyhameeouqouqouqfufuyhamyhamjbdjbdwrm" +
                                 "wrmvvvqqqrrepwtepwtwyuwyuwyuovaeovaejxljxlqpwgzgzgzepwtepwtwyuwyuwyuovaeovae" +
                                 "jxljxlqpwgzgzgzepwtepwtwyuwyuwyuovaeovaejxljxlqpwgzgzgzvvvqqqrrepwtepwtwyuwy" +
                                 "uwyuovaeovaejxljxlqpwgzgzgzepwtepwtwyuwyuwyuovaeovaejxljxlqpwgzgzgzepwtepwtw" +
                                 "yuwyuwyuovaeovaejxljxlqpwgzgzgzmhfmhfpspslslslssgsgfccclfnkxxpspslslslssgsgf" +
                                 "ccclfnkxxpspslslslssgsgfccclfnkxxsgbsgbwnyypspslslslssgsgfccclfnkxxpspslslsl" +
                                 "ssgsgfccclfnkxxpspslslslssgsgfccclfnkxxsgbsgbwnyyqqyyywdtlwdtlwdtlvavajwrpwr" +
                                 "pwrplppllppllppljvxeixzzzjjjsjsemxemxpaibpaibfdwfdwwrempaibpaibfdwfdwwrempai" +
                                 "bpaibfdwfdwwremdivgdivgfvdofvdoeqvqvfvdofvdoeqvqvfvdofvdoeqvqvqqyyywdtlwdtlw" +
                                 "dtlvavajwrpwrpwrplppllppllppljvxeixzzzjjjsjsemxemxpaibpaibfdwfdwwrempaibpaib" +
                                 "fdwfdwwrempaibpaibfdwfdwwremdivgdivgfvdofvdoeqvqvfvdofvdoeqvqvfvdofvdoeqvqvp" +
                                 "jvvfftkjotkjotkjoespsespsvvvvvfftkjotkjotkjoespsespsvvvaaaeofylfylfyl"
                )
        );
        // run all tests & print results to console
        tr.run(/*new String[]{"TEST-16"}*/);
    }
}
