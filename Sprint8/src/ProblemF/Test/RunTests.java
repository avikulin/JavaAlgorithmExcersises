package ProblemF.Test;

// import test utilities

import ProblemF.SolutionF;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                SolutionF::process,
                String::equals,
                i -> i,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-1",
                        new String[]{"abacaba",
                                "3",
                                "queue 2",
                                "deque 0",
                                "stack 7"},
                        "dequeabqueueacabastack"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-2",
                        new String[]{"kukareku",
                                "2",
                                "p 1",
                                "q 2"},
                        "kpuqkareku"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "TEST-10",
                        new String[]{"hbdhlamludxcueoscrsfwgaijpkecrmsqctokkkd" +
                                "rstrvfvuytcoahtuppvqbdrroyyewnfkwkpgqxhrdckmuy",
                                "5",
                                "gkosfvgcb 71",
                                "dgg 50",
                                "qsegnmw 85",
                                "dubelcmd 59",
                                "xsss 12"},
                        "hbdhlamludxcxsssueoscrsfwgaijpkecrmsqctokkkdrstrvfvuytdggcoahtu" +
                                "ppvdubelcmdqbdrroyyewnfgkosfvgcbkwkpgqxhrdckmuqsegnmwy"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "TEST-15",
                        new String[]{"nhyqzhwisliifwftmyxqdsrbmxpalfcgevduxljmytcrwfnputknktkflshsnfr" +
                                "efimbmpcjhtouobuwsaxzdbgxiktisslwzgddaahtutyjygvnxkgdpqcyrewpjb" +
                                "yoznatsmpggehisbtwojkinnbhwddwxuezpnrhscajgvzxmvczplxjlzyvqquth" +
                                "gpqdekolblxohbqrinvjxjvrfugjbbuzkwgptm",
                                "24",
                                "ioetclw 226",
                                "mceluflwtt 76",
                                "adu 11",
                                "qfv 169",
                                "xjgga 92",
                                "vnsnu 95",
                                "msepdkrqcj 221",
                                "laqodhxhg 179",
                                "pugpxug 161",
                                "hpklpk 187",
                                "ucpz 58",
                                "ajibqxaos 156",
                                "swkv 191",
                                "rvgvg 60",
                                "gvyecnvje 68",
                                "jxjverqb 183",
                                "dkfmtrpdk 94",
                                "inppzxmpf 71",
                                "afwqaiapjz 197",
                                "xzduwm 180",
                                "gwht 190",
                                "iszl 34",
                                "fmqkrephhx 157",
                                "mytmtwx 121"},
                        "nhyqzhwisliaduifwftmyxqdsrbmxpalfcgeviszlduxljmytcrwfnputknktkflsuc" +
                                "pzhsrvgvgnfrefimbgvyecnvjempcinppzxmpfjhtoumceluflwttobuwsaxzdbgxi" +
                                "ktixjggassdkfmtrpdklvnsnuwzgddaahtutyjygvnxkgdpqcyrmytmtwxewpjbyoz" +
                                "natsmpggehisbtwojkinnbhwddwajibqxaosxfmqkrephhxuezppugpxugnrhscajg" +
                                "qfvvzxmvczplxlaqodhxhgjxzduwmlzyjxjverqbvqquhpklpkthggwhtpswkvqdek" +
                                "olafwqaiapjzblxohbqrinvjxjvrfugjbbuzmsepdkrqcjkwgptioetclwm"
                )
        );


        // run all tests & print results to console
        tr.run( /*new String[]{"TEST-10"}*/);
    }
}
