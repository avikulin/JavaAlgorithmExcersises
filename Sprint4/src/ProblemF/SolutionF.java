package ProblemF;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class SolutionF {
    static int[] charCodes = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41,
            43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};

    static long getHash(String str) {
        long res = 1;
        for (int i = 0; i < str.length(); i++) {
            res *= charCodes[(int) str.charAt(i) - 97];
            res = res % 305235503L;
        }

        return res;
    }

    public static String groupAnagrams(String[] input) {
        Map<Long, List<Integer>> groupsStorage = new HashMap<>();

        int numOfTokens = Integer.parseInt(input[0]);
        StringTokenizer tokenizer = new StringTokenizer(input[1]);

        for (int i = 0; i < numOfTokens; i++) {
            String token = tokenizer.nextToken();
            long key = getHash(token);
            if (groupsStorage.containsKey(key)) {
                groupsStorage.get(key).add(i);
            } else {
                List<Integer> posList = new ArrayList<>();
                posList.add(i);
                groupsStorage.put(key, posList);
            }
        }

        List<String> res = new ArrayList<>();
        for (Map.Entry<Long, List<Integer>> pair : groupsStorage.entrySet()) {
            List<Integer> positions = pair.getValue();
            positions.sort(Comparator.naturalOrder());
            String posInGroup = positions.stream().map(x -> String.valueOf(x)).collect(Collectors.joining(" "));
            res.add(posInGroup);
        }

        res.sort((o1, o2) -> {
                                if (o1.charAt(0) == o2.charAt(0)) return 0;
                                return (o1.charAt(0) > o2.charAt(0)) ? 1 : -1;
                              });

        return res.stream().collect(Collectors.joining("\n"));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] buffer = new String[2];

        buffer[0] = reader.readLine();
        buffer[1] = reader.readLine();

        System.out.println(groupAnagrams(buffer));
    }
}
