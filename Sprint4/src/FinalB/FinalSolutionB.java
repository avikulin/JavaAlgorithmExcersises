package FinalB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            HashPair hashPair = (HashPair) o;
            return key == hashPair.key &&
                    value == hashPair.value;
        }
    }

    class HashBucket {
        private final List<HashPair> hashPairsStorage;

        HashBucket() {
            hashPairsStorage = new LinkedList<>();
        }
        private HashPair findReference(int key) {
            Iterator<HashPair> pairsIterator = hashPairsStorage.iterator();
            while (pairsIterator.hasNext()) {
                HashPair kv = pairsIterator.next();
                if (key == kv.key) return kv;
            }
            return null;
        }

        public int getValue(int key) {
            HashPair res = findReference(key);
            return (res == null) ? NOT_FOUND : res.value;
        }

        public void putValue(HashPair element) {
            HashPair kv = findReference(element.key);

            if (kv == null)
                hashPairsStorage.add(element);
            else
                kv.value = element.value;
        }

        public int deleteValue(int key) {
            HashPair element = findReference(key);
            if (element == null) return NOT_FOUND;

            int previousValue = element.value;
            hashPairsStorage.remove(element);
            return previousValue;
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
        String[] buffer = new String[numOfRequests + 1];

        buffer[0] = String.valueOf(numOfRequests);
        for (int i = 1; i < numOfRequests + 1; i++)
            buffer[i] = reader.readLine();

        System.out.println(processRequests(buffer));
    }
}
