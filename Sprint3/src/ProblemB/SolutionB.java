package ProblemB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

class Combinator{
    String[] codesStorage;
    List<String> combinationStorage;
    Combinator(String alphabetCodes){
        codesStorage = new String[alphabetCodes.length()];
        combinationStorage = new ArrayList<>();
        for(int i=0; i< alphabetCodes.length(); i++){
            switch (alphabetCodes.charAt(i)){
                case '2': codesStorage[i] = "abc" ; break;
                case '3': codesStorage[i] = "def" ; break;
                case '4': codesStorage[i] = "ghi" ; break;
                case '5': codesStorage[i] = "jkl" ; break;
                case '6': codesStorage[i] = "mno" ; break;
                case '7': codesStorage[i] = "pqrs"; break;
                case '8': codesStorage[i] = "tuv" ; break;
                case '9': codesStorage[i] = "wxyz"; break;
            }
        }
    }

    private void generate(String input, int level){
        if (level == codesStorage.length){
            combinationStorage.add(input);
            return;
        }

        String codes = codesStorage[level];
        for (int i=0; i< codes.length(); i++){
            generate(input + codes.charAt(i), level + 1);
        }
    }

    public String getCombinations(){
        generate("", 0);
        StringJoiner joiner = new StringJoiner(" ");
        for (String str: combinationStorage){
            joiner.add(str);
        }
        return joiner.toString();
    }
}

public class SolutionB {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Combinator obj = new Combinator(reader.readLine());
        System.out.println(obj.getCombinations());
    }
}
