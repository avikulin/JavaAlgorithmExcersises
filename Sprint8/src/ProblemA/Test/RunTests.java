package ProblemA.Test;

// import test utilities
        import ProblemA.SolutionA;
        import YTester.YTestCase;
        import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                SolutionA::process,
                String::equals,
                i -> i,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                        "MIN",
                        new String[]{"abcab",
                                     "defde"},
                        "YES"
                )
        );

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-1",
                        new String[]{"mxyskaoghi",
                                     "qodfrgmslc"},
                        "YES"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-2",
                        new String[]{"agg",
                                     "xdd"},
                        "YES"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-3",
                        new String[]{"agg",
                                     "xda"},
                        "NO"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "MAX",
                        new String[]{"rmbwpruzwccozcifoqilozwlafvpbtwarcyiiujhvggettedsgwnoaqjzvorgdgehssuhubvqyneziaalnakhamoddmzlhmsklhkdkmdwpgtnwpcxltouhqnhjsoddkpmazabcxaxgeifvqqrfafowcdotellymywoanltpptaepsfjsvwyunlcorinsnyqnkhfecvcxqwwkcaadogfpufpzcsqbvazaqakzwppywbskyihdxipujocwesawzprrqpnykbfcmeuaifhcixlhjfktoxyafmxxcsewxaqcgnbm",
                                    "ukslqumylaahyagphegvhylvipbqsjliuaxggmdzbwwojjontwlfhiedybhuwnwozttmzmsbexfoygiivficzikhnnkyvzktcvzcncknlqwjflqarvjhmzefzdthnncqkiyisarirwogpbeeupiphlanhjovvxkxlhifvjqqjioqtpdtblxmfvahugftfxefczpoabarellcaiinhwpqmpqyatesbiyieicylqqxlstcxgznrgqmdhalotilyquueqfxcspakomigpzagrvzdpcjhrxipkrratolrieawfsk"},
                        "YES"
                )
        );
        // run all tests & print results to console
        tr.run();
    }
}
