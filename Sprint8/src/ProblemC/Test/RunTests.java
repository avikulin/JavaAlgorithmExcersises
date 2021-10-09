package ProblemC.Test;

// import test utilities

import ProblemC.SolutionC;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                SolutionC::process,
                String::equals,
                i -> i,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-1",
                        new String[]{"abcdefg",
                                     "abdefg"},
                        "OK"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-2",
                        new String[]{"helo",
                                     "hello"},
                        "OK"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-3",
                        new String[]{"dog",
                                     "fog"},
                        "OK"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-4",
                        new String[]{"mama",
                                     "papa"},
                        "FAIL"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "BIG",
                        new String[]{"ibffkqadjqdmraigdietkqbmqrixntugkxnquthqjizfkwbphomgajmszlcepgywkqnwmphbagsutjwxxjbzklupbzisgo" +
                                "gfnrkldrxalxqwbxadvesnkgzhhhzrhhlthvrcvxyrasqtywpcpilwtmburmzkicompbjurzndblsgnbafxrclfjmdzmem" +
                                "gsqztdwowlxixuqvfrolovzaojxizvfvmupokwimfcnhmavbpbaaqhnkhmfwflliccajfhjbfavsrrbtyhmyfmxsptvuno" +
                                "iiboerhwpfqafewtcdzizgnkgwvebhrmmkqkvfgthsqzhzhqzsknkbmocpzwtkanglxzdjzdwxzfgatgvgvcgyjixldytw" +
                                "sqrltihgqwsrdsprsdecdsbossidxkapdmdozqeajrffpdeqqiyceuooueiizjzcdeczzbuxocoyhbpjjkbtzptwhozssb" +
                                "peblmcedhbdcydoqgavyymdqkjccrdlhuovuobkpttzgfmzbzqeppgzuwktniuiaazvzlbihuutxitvnfihzxbqldfaflk" +
                                "fmxxvjotimfywkyqfufyvjmzvccmfonzntibnidfnolhdnsoekuxekxsaunqjhvjatqzfingambjzkmmgmcnqrdroaahee" +
                                "ukheovetjunvthimwsfsvidsxyjbxxvptvjvjonbvhyobieyvdeiqdghjgdkocjnlncfkcwfokplwlofwrgryhihaxubjy" +
                                "osnguqdkbswuevtkfsplmvywcioucvvxzhqgrqblrfowtluliyvfdvtqzzcddyeashpdszrkfnqmahstgzjplhxitxarua" +
                                "eeqvrjajygcsxuhdoqaeldohetkmcdyywpzzlhycgbnglbgkurjqzykfqzfgxoulbmrbinhnspswognokkvlntcwywwkury" +
                                "flvuxljtzrkdkqbvsxewrbpfkexierknpemxwcgmlxawinxlhuuwifmnfpllqbbrweycusjianvjvcwcnjc",
                                "ibffkqadjqdmraigdietkqbmqrixntugkxnquthqjizfkwbphomgajmszlcepgywkqnwmphbagsutjwxxjbzklupbzisgo" +
                                "gfnrkldrxalxqwbxadvesnkgzhhhzrhhlthvrcvxyrasqtywpcpilwtmburmzkicompbjurzndblsgnbafxrclfjmdzmem" +
                                "gsqztdwowlxixuqvfrolovzaojxizvfvmupokwimfcnhmavbpbaaqhnkhmfwflliccajfhjbfavsrrbtyhmyfmxsptvuno" +
                                "iiboerhwpfqafewtcdzizgnkgwvebhrmmkqkvfgthsqzhzhqzsknkbmocpzwtkanglxzdjzdwxzfgatgvgvcgyjixldytw" +
                                "sqrltihgqwsrdsprsdecdsbossidxkapdmdozqeajrffpdeqqiyceuooueiizjzcdeczzbuxocoyhbpjjkbtzptwhozssb" +
                                "peblmcedhbdcydoqgavyymdqkjccrdlhuovuobkpttzgfmzbzqeppgzuwktniuiaazvzlbihuutxitvnfihzxbqldfaflk" +
                                "fmxxvjotimfywkyqfufyvjmzvccmfonzntibnidfnolhdnsoekuxekxsaunqjhvjatqzfingambjzkmmgmcnqrdroaahee" +
                                "ukheovetjunvthimwsfsvidsxyjbxxvptvjvjonbvhyobieyvdeiqdghjgdkocjnlncfkcwfokplwlofwrgryhihaxubjy" +
                                "osnguqdkbswuevtkfsplmvywcioucvvxzhqgrqblrfowtluliyvfdvtqzzcddyeashpdszrkfnqmahstgzjplhxitxarua" +
                                "eeqvrjajygcsxuhdoqaeldohetkmcdyywpzzlhycgbnglbgkurjqzykfqzfgxoulbmrbinhnspswognokkvlntcwywwkury" +
                                "flvuxljtzrkdkqbvsxewrbpfkexierknpemxwcgmlxawinxlhuuwifmnfpllqbbrweycusjianvjvcwcnjc"},
                        "OK"
                )
        );
        // run all tests & print results to console
        tr.run(/*new String[]{"EXAMPLE-4"}*/);
    }
}
