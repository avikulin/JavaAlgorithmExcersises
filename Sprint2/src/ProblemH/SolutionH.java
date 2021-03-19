package ProblemH;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class SolutionH {

    public static boolean check(String input){
        if (input.isEmpty()) return true;
        Stack<Integer> sequence = new Stack<>();
        for (int i=0; i< input.length();i++){
            char current = input.charAt(i);
            switch (current){
                case '(': sequence.push(1); break;
                case '[': sequence.push(2); break;
                case '{':sequence.push(3); break;
                case ')': if (!sequence.empty()&&sequence.pop()!=1) return false;break;
                case ']': if (!sequence.empty()&&sequence.pop()!=2) return false;break;
                case '}': if (!sequence.empty()&&sequence.pop()!=3) return false;break;
            }
        }
        return sequence.empty();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(check(reader.readLine())==true?"True":"False");
    }
}
