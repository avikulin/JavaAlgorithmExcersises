package FinalB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

class HashTable {
    private final int NOT_FOUND = -1;
    private HashBucket[] hashBucketsStorage;

    class HashPair {
        private int key;
        private int value;

        HashPair(int hashKey, int hashValue) {
            key = hashKey;
            value = hashValue;
        }
    }

    class HashBucket {
        private final List<HashPair> hashPairsStorage;

        private int findIndex(int key) {
            for (int i = 0; i < hashPairsStorage.size(); i++)
                if (hashPairsStorage.get(i).key == key) return i;

            return NOT_FOUND;
        }

        HashBucket() {
            hashPairsStorage = new LinkedList<>();
        }

        public int getValue(int key) {
            for (int i = 0; i < hashPairsStorage.size(); i++) {
                HashPair element = hashPairsStorage.get(i);
                if (key == element.key)
                    return element.value;
            }
            return NOT_FOUND;
        }

        public void putValue(HashPair value) {
            hashPairsStorage.add(value);
        }

        public int deleteValue(int key) {
            int idx = findIndex(key);
            if (idx == NOT_FOUND) return NOT_FOUND;
            HashPair element = hashPairsStorage.remove(idx);
            return element.value;
        }
    }


    HashTable(int size) {
        hashBucketsStorage = new HashBucket[size];
    }

    private int computeBucketIdx(int key) {
        return key % hashBucketsStorage.length;
    }

    public String getElement(int key) {
        int idx = computeBucketIdx(key);
        if (hashBucketsStorage[idx] == null) return "None";

        int value = hashBucketsStorage[idx].getValue(key);

        return (value == NOT_FOUND) ? "None" : String.valueOf(value);
    }

    public void putElement(int key, int value) {
        HashPair kv = new HashPair(key, value);

        int idx = computeBucketIdx(key);

        if (hashBucketsStorage[idx] == null)
            hashBucketsStorage[idx] = new HashBucket();

        hashBucketsStorage[idx].putValue(kv);
    }

    public String deleteElement(int key) {
        int idx = computeBucketIdx(key);
        if (hashBucketsStorage[idx] == null) return "None";

        int value = hashBucketsStorage[idx].deleteValue(key);
        return (value == NOT_FOUND) ? "None" : String.valueOf(value);
    }
}

class Commander {
    private final String GET_CMD = "get";
    private final String PUT_CMD = "put";
    private final String DEL_CMD = "delete";

    private HashTable cmdObject;

    Commander(HashTable objRef) {
        cmdObject = objRef;
    }


    public String interpret(String parameters) {
        StringTokenizer tokenizer = new StringTokenizer(parameters);
        String cmdName = tokenizer.nextToken();

        switch (cmdName) {
            case GET_CMD:
                return cmdObject.getElement(Integer.parseInt(tokenizer.nextToken()));
            case PUT_CMD:
                cmdObject.putElement(Integer.parseInt(tokenizer.nextToken()),
                        Integer.parseInt(tokenizer.nextToken()));
                return "";
            case DEL_CMD:
                return cmdObject.deleteElement(Integer.parseInt(tokenizer.nextToken()));
        }

        return "";
    }

}

public class FinalSolutionB {
    private static final int NUMBER_OF_BUCKETS = 33199;

    public static String processRequests(String[] input) {
        HashTable hashTable = new HashTable(NUMBER_OF_BUCKETS);
        Commander commander = new Commander(hashTable);

        int cmdCount = Integer.parseInt(input[0]);
        StringJoiner stringJoiner = new StringJoiner("\n");

        for (int i = 1; i < cmdCount + 1; i++) {
            String cmdOutput = commander.interpret(input[i]);
            if (!cmdOutput.isEmpty())
                stringJoiner.add(cmdOutput);
        }

        return stringJoiner.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numOfRequests = Integer.parseInt(reader.readLine());
        String[] buffer = new String[numOfRequests];

        buffer[0] = String.valueOf(numOfRequests);
        for (int i = 1; i < numOfRequests + 1; i++)
            buffer[i] = reader.readLine();

        System.out.println(processRequests(buffer));
    }
}
