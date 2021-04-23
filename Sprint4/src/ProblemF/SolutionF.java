package ProblemF;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class SolutionF {
    static int getFirstDigitalValue(String str) {
        int res = 0;
        int firstScaceIdx = str.indexOf(' ');
        int firstDigLength = (firstScaceIdx == -1)? str.length(): firstScaceIdx;

        for (int i = 0; i < firstDigLength; i++) {
            char ch =str.charAt(i);
            res += (int)Math.pow(10,firstDigLength-i-1)*((int)ch-48);
        }

        return res;
    }

    static int[] charCodes = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41,
            43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};

    static long getHashInt(String str) {
        long res = 1;
        for (int i = 0; i < str.length(); i++) {
            res *= charCodes[(int) str.charAt(i) - 97];
            res = res % 305235503L;
        }

        return res;
    }

    static String getHashString(String str) {
        String res = str.chars()
                .sorted()
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return res;
    }

    public static String groupAnagrams(String[] input) {
        Map<Long, List<Integer>> groupsStorage = new HashMap<>();
//        Map<String, List<Integer>> groupsStorage = new HashMap<>();
        int numOfTokens = Integer.parseInt(input[0]);
        StringTokenizer tokenizer = new StringTokenizer(input[1]);

        for (int i = 0; i < numOfTokens; i++) {
            String token = tokenizer.nextToken();
            long key = getHashInt(token);
//            String key = getHashString(token);

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
//        for (Map.Entry<String, List<Integer>> pair : groupsStorage.entrySet()) {
            List<Integer> positions = pair.getValue();
//            positions.sort(Comparator.naturalOrder());
            String posInGroup = positions.stream().map(x -> String.valueOf(x)).collect(Collectors.joining(" "));
            res.add(posInGroup);
        }

        res.sort((String o1, String o2) -> {
            int first = getFirstDigitalValue(o1);
            int second = getFirstDigitalValue(o2);
            if (first == second) return 0;
            return (first > second) ? 1 : -1;
        });
//        res.sort(Comparator.naturalOrder());
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
