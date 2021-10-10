package ProblemF;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Token implements Comparable<Token> {
    private int position;
    private String content;

    public Token(int pos, String content) {
        this.position = pos;
        this.content = content;
    }

    public int getPosition() {
        return position;
    }

    public String getContent() {
        return content;
    }

    @Override
    public int compareTo(Token o) {
        if (this.equals(o)) return 0;
        return (this.position > o.position) ? 1 : -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Token token = (Token) o;
        return position == token.position;
    }
}

public class SolutionF {
    public static String process(String[] input) {
        String s = input[0];
        int num = Integer.parseInt(input[1]);
        PriorityQueue<Token> tokens = new PriorityQueue<>(num);
        for (int i = 2; i < input.length; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input[i], " ", true);
            String c = tokenizer.nextToken();
            tokenizer.nextToken();
            int p = Integer.parseInt(tokenizer.nextToken());
            tokens.add(new Token(p, c));
        }

        StringBuilder res = new StringBuilder();

        int currentPos = 0;
        while (!tokens.isEmpty()) {
            Token t = tokens.poll();
            int interval = t.getPosition() - currentPos;
            if (interval > 0) {
                res.append(s, currentPos, currentPos + interval);
                currentPos += interval;
            }
            res.append(t.getContent());
        }
        if (currentPos < s.length()) {
            res.append(s, currentPos, s.length());
        }

        return res.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        String num = reader.readLine();
        int count = Integer.parseInt(num);
        String[] buffer = new String[count + 2];
        buffer[0] = s;
        buffer[1] = num;
        for (int i = 2; i < buffer.length; i++) {
            buffer[i] = reader.readLine();
        }
        System.out.println(process(buffer));
    }
}
