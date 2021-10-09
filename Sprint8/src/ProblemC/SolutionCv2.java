//package ProblemC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SolutionCv2 {
    public static String process(String[] input) {
        String s1 = input[0];
        String s2 = input[1];

        int s1Lenght = s1.length();
        int s2Length = s2.length();

        if (Math.abs(s1Lenght - s2Length) > 1) return "FAIL";
        if ((s1Lenght == 0) && (s2Length == 0)) return "OK";

        if (s1Lenght < s2Length){
            String tmp = s1;
            s1 = s2;
            s2 = tmp;

            int t = s1Lenght;
            s1Lenght = s2Length;
            s2Length = t;
        }

        int pointerOne = 0;
        int pointerTwo = 0;
        boolean oneTimeInconsistency = false;

        while ((pointerOne < s1Lenght)&&(pointerTwo < s2Length)){
            char charA = s1.charAt(pointerOne);
            char charB = s2.charAt(pointerTwo);
            if(charA == charB){
                pointerTwo++;
            } else {
                if (oneTimeInconsistency) return "FAIL";
                oneTimeInconsistency = true;
                if (s1Lenght == s2Length) {
                    pointerTwo++;
                }
            }
            pointerOne++;
        }

        return "OK";
    }

    /*public static void profile(){
        String s1 = "ibffkqadjqdmraigdietkqbmqrixntugkxnquthqjizfkwbphomgajmszlcepgywkqnwmphbagsutjwxxjbzklupbzisgo" +
                    "gfnrkldrxalxqwbxadvesnkgzhhhzrhhlthvrcvxyrasqtywpcpilwtmburmzkicompbjurzndblsgnbafxrclfjmdzmem" +
                    "gsqztdwowlxixuqvfrolovzaojxizvfvmupokwimfcnhmavbpbaaqhnkhmfwflliccajfhjbfavsrrbtyhmyfmxsptvuno" +
                    "iiboerhwpfqafewtcdzizgnkgwvebhrmmkqkvfgthsqzhzhqzsknkbmocpzwtkanglxzdjzdwxzfgatgvgvcgyjixldytw" +
                    "sqrltihgqwsrdsprsdecdsbossidxkapdmdozqeajrffpdeqqiyceuooueiizjzcdeczzbuxocoyhbpjjkbtzptwhozssb" +
                    "peblmcedhbdcydoqgavyymdqkjccrdlhuovuobkpttzgfmzbzqeppgzuwktniuiaazvzlbihuutxitvnfihzxbqldfaflk" +
                    "fmxxvjotimfywkyqfufyvjmzvccmfonzntibnidfnolhdnsoekuxekxsaunqjhvjatqzfingambjzkmmgmcnqrdroaahee" +
                    "ukheovetjunvthimwsfsvidsxyjbxxvptvjvjonbvhyobieyvdeiqdghjgdkocjnlncfkcwfokplwlofwrgryhihaxubjy" +
                    "osnguqdkbswuevtkfsplmvywcioucvvxzhqgrqblrfowtluliyvfdvtqzzcddyeashpdszrkfnqmahstgzjplhxitxarua" +
                    "eeqvrjajygcsxuhdoqaeldohetkmcdyywpzzlhycgbnglbgkurjqzykfqzfgxoulbmrbinhnspswognokkvlntcwywwkury" +
                    "flvuxljtzrkdkqbvsxewrbpfkexierknpemxwcgmlxawinxlhuuwifmnfpllqbbrweycusjianvjvcwcnjc";
        String s2 = "ibffkqadjqdmraigdietkqbmqrixntugkxnquthqjizfkwbphomgajmszlcepgywkqnwmphbagsutjwxxjbzklupbzisgo" +
                    "gfnrkldrxalxqwbxadvesnkgzhhhzrhhlthvrcvxyrasqtywpcpilwtmburmzkicompbjurzndblsgnbafxrclfjmdzmem" +
                    "gsqztdwowlxixuqvfrolovzaojxizvfvmupokwimfcnhmavbpbaaqhnkhmfwflliccajfhjbfavsrrbtyhmyfmxsptvuno" +
                    "iiboerhwpfqafewtcdzizgnkgwvebhrmmkqkvfgthsqzhzhqzsknkbmocpzwtkanglxzdjzdwxzfgatgvgvcgyjixldytw" +
                    "sqrltihgqwsrdsprsdecdsbossidxkapdmdozqeajrffpdeqqiyceuooueiizjzcdeczzbuxocoyhbpjjkbtzptwhozssb" +
                    "peblmcedhbdcydoqgavyymdqkjccrdlhuovuobkpttzgfmzbzqeppgzuwktniuiaazvzlbihuutxitvnfihzxbqldfaflk" +
                    "fmxxvjotimfywkyqfufyvjmzvccmfonzntibnidfnolhdnsoekuxekxsaunqjhvjatqzfingambjzkmmgmcnqrdroaahee" +
                    "ukheovetjunvthimwsfsvidsxyjbxxvptvjvjonbvhyobieyvdeiqdghjgdkocjnlncfkcwfokplwlofwrgryhihaxubjy" +
                    "osnguqdkbswuevtkfspdfzdfzdfzdfzsdfzsdfzdsawererdfdfzsdfsdfzzsdfzdsfzswerawerawstgzjplhxitxarua" +
                    "eeqvrjajygcsxuhdoqaeldohetkmcdyywpzzlhycgbnglbgkurjqzykfqzfgxoulbmrbinhnspswognokkvlntcwywwkury" +
                    "flvuxljtzrkdkqbvsxewrbpfkexierknpemxwcgmlxawinxlhuuwifmnfpllqbbrweycusjianvjvcwcnjc";
                    "flvuxljtzrkdkqbvsxewrbpfkexierknpemxwcgmlxawinxlhuuwifmnfpllqbbrweycusjianvjvcwcnjc";
        String[] buffer = new String[]{s1, s2};
        while (true){
            process(buffer);
        }
    }*/

    public static void main(String[] args) throws IOException {
        /*profile();*/
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] buffer = new String[2];
        buffer[0] = reader.readLine();
        buffer[1] = reader.readLine();
        System.out.println(process(buffer));
    }
}
