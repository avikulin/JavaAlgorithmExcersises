package FinalB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class TrieNode {
    private int CHAR_SIZE = 26;
    private boolean isTerminal;
    private TrieNode[] childNodes;

    TrieNode() {
        childNodes = new TrieNode[CHAR_SIZE];
        isTerminal = false;
    }

    public TrieNode addChildNode(char symbol) {
        int offset = symbol - 'a';
        TrieNode node = childNodes[offset];
        if (node == null) {
            node = new TrieNode();
            childNodes[offset] = node;
        }
        return node;
    }

    TrieNode getChildNodeBySymbol(char symbol) {
        return childNodes[symbol - 'a'];
    }

    public void setTerminalState() {
        isTerminal = true;
    }

    boolean getTerminalState() {
        return isTerminal;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insertPattern(String searchPattern) {
        TrieNode trieNode = root;
        for (int i = 0; i < searchPattern.length(); i++) {
            char symbol = searchPattern.charAt(i);
            trieNode = trieNode.addChildNode(symbol);
        }
        trieNode.setTerminalState();
    }

    public TrieNode getRoot() {
        return root;
    }
}

public class FinalSolutionB {
    private static boolean traverseTrieCoveringDP(Trie trie, String strInput) {
        boolean[] storeDP = new boolean[strInput.length() + 1];
        storeDP[0] = true;

        for (int i = 0; i < strInput.length(); i++) {
            if (storeDP[i]) {
                TrieNode trieNode = trie.getRoot();
                for (int j = i; j < strInput.length(); j++) {
                    if (trieNode != null) {
                        trieNode = trieNode.getChildNodeBySymbol(strInput.charAt(j));
                    } else {
                        break;
                    }
                    if (trieNode != null && trieNode.getTerminalState()) {
                        storeDP[j + 1] = true;
                    }
                }
            }
        }
        return storeDP[strInput.length()];
    }

    public static String process(String[] input) {
        Trie trie = new Trie();
        String strContext = input[0];
        for (int i = 2; i < input.length; i++) {
            trie.insertPattern(input[i]);
        }
        boolean res = traverseTrieCoveringDP(trie, strContext);
        return res ? "YES" : "NO";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String strToSearchIn = reader.readLine();
        String strNumberOfPatterns = reader.readLine();
        int numberOfPatterns = Integer.parseInt(strNumberOfPatterns);
        String[] buffer = new String[numberOfPatterns + 2];
        buffer[0] = strToSearchIn;
        buffer[1] = strNumberOfPatterns;
        int lowerBound = numberOfPatterns + 2;
        for (int i = 2; i < lowerBound; i++) {
            buffer[i] = reader.readLine();
        }
        System.out.println(process(buffer));
    }
}
