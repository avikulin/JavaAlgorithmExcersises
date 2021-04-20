package ProblemC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import java.util.StringTokenizer;

class HashManager {
    private final int baseValue;
    private final int modValue;
    private final char[] strCharsStorage;
    private final long[] hashStorage;

    HashManager(int base, int modulo, String str) {
        baseValue = base % modulo;
        modValue = modulo;
        strCharsStorage = str.toCharArray();
        hashStorage = new long[strCharsStorage.length];
    }

    void preprocessHashes() {
        for (int i = 0; i < strCharsStorage.length; i++)
            hashStorage[i] = getHash(strCharsStorage, i);
    }

    private long getHash(char[] sequence, int rightBound) {
        if (sequence.length == 0) return 0;
        if (modValue == 1) return 0;
        if (rightBound == 0) return sequence[0] % modValue;

        long prevHash = hashStorage[rightBound - 1] % modValue;
        return ((int) sequence[rightBound] % modValue + ((prevHash * baseValue) % modValue)) % modValue;
    }

    public long modPower(long base, int power, long modulo) {
        long res = 1L;
        while (power > 0) {
            if ((power & 1) != 0)
                res = (res * base) % modulo;

            power = power >> 1; // power = power/2
            base = (base * base) % modulo;
        }
        return res;
    }

    public long getSubstringHash(int left, int right) {
        if (left == 0) return hashStorage[right];
        long powerFactor = modPower(baseValue, right - left + 1, modValue);
        long hashLeftBound = hashStorage[left - 1];
        long hashRightBound = hashStorage[right];
        long res = Math.floorMod(hashRightBound - (hashLeftBound * powerFactor), modValue);
        return res;
    }
}

public class SolutionC {
    public static String process(String[] input) {
        StringJoiner joiner = new StringJoiner("\n");
        int baseParam = Integer.parseInt(input[0]);
        int modParam = Integer.parseInt(input[1]);
        String strParam = input[2];

        HashManager hashManager = new HashManager(baseParam, modParam, strParam);
        hashManager.preprocessHashes();

        int countOfQueries = Integer.parseInt(input[3]);
        for (int i = 0; i < countOfQueries; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input[4 + i]);
            int leftBound = Integer.parseInt(tokenizer.nextToken());
            int rightBound = Integer.parseInt(tokenizer.nextToken());
            joiner.add(String.valueOf(hashManager.getSubstringHash(leftBound - 1, rightBound - 1)));
        }
        return joiner.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

//        ---FOR TESTING PURPOSES---
//
//        String strBase = reader.readLine();
//        String strModulo = reader.readLine();
//        String strValue = reader.readLine();
//        int numberOfQueries = Integer.parseInt(reader.readLine());
//
//        String[] buffer = new String[numberOfQueries + 4];
//        buffer[0] = strBase;
//        buffer[1] = strModulo;
//        buffer[2] = strValue;
//        buffer[3] = String.valueOf(numberOfQueries);
//        for (int i = 0; i < numberOfQueries; i++)
//            buffer[4 + i] = reader.readLine();
//
//        System.out.println(process(buffer));
//        -----------------------------

        StringJoiner joiner = new StringJoiner("\n");
        int baseParam = Integer.parseInt(reader.readLine());
        int modParam = Integer.parseInt(reader.readLine());
        String strParam = reader.readLine();

        HashManager hashManager = new HashManager(baseParam, modParam, strParam);
        hashManager.preprocessHashes();

        int countOfQueries = Integer.parseInt(reader.readLine());
        for (int i = 0; i < countOfQueries; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int leftBound = Integer.parseInt(tokenizer.nextToken());
            int rightBound = Integer.parseInt(tokenizer.nextToken());
            joiner.add(String.valueOf(hashManager.getSubstringHash(leftBound - 1, rightBound - 1)));
        }

        System.out.println(joiner.toString());
    }
}
