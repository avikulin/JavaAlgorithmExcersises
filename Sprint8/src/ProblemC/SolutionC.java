package ProblemC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SolutionC {
    public static String process(String[] input) {
        String s1 = input[0];
        String s2 = input[1];

        int xDimension = s1.length();
        int yDimension = s2.length();

        if (Math.abs(xDimension - yDimension) > 1) return "FAIL";
        if ((xDimension == 0) || (yDimension == 0)) return "OK";

        //инициализация матрицы динамического программирования
        int[] dpCache = new int[xDimension + 1];
        int[] dpResult = new int[xDimension + 1];
        int[] temp;


        //выполнение расчета
        int dpValue;
        int dpPrevious;

        dpPrevious = 1;
        char s2FirstChar = s2.charAt(0);
        for (int columnIdx = 1; columnIdx <= xDimension; columnIdx++) {
            boolean charsEqual = (s1.charAt(columnIdx - 1) == s2FirstChar);
            if (charsEqual) {
                dpValue = columnIdx - 1;
            } else {
                int minValueUp = columnIdx - 1;
                int minValueTotal = Math.min(minValueUp, dpPrevious);
                dpValue = minValueTotal + 1;
            }
            dpResult[columnIdx] = dpValue;
            dpPrevious = dpValue;
        }
        //rotate
        temp = dpCache;
        dpCache = dpResult;
        dpResult = temp;

        for (int rowIdx = 2; rowIdx <= yDimension; rowIdx++) {
            dpPrevious = rowIdx;
            char s2TestChar = s2.charAt(rowIdx - 1);
            for (int columnIdx = 1; columnIdx <= xDimension; columnIdx++) {
                boolean charsEqual = (s1.charAt(columnIdx - 1) == s2TestChar);
                if (charsEqual) {
                    dpValue = dpCache[columnIdx - 1];
                } else {
                    int minValueUp = Math.min(dpCache[columnIdx],dpCache[columnIdx - 1]);
                    int minValueTotal = Math.min(minValueUp, dpPrevious);
                    dpValue = minValueTotal + 1;
                }
                dpResult[columnIdx] = dpValue;
                dpPrevious = dpValue;
            }
            //rotate
            temp = dpCache;
            dpCache = dpResult;
            dpResult = temp;
        }
        return (dpCache[xDimension] > 1) ? "FAIL" : "OK";
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
