package Problems;

import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children;
    Map<String, Integer> counts;

    public TrieNode() {
        children = new HashMap<>();
        counts = new HashMap<>();
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String sentence, int count) {
        TrieNode node = root;
        for (char c : sentence.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
            node.counts.put(sentence, node.counts.getOrDefault(sentence, 0) + count);
        }
    }

    public List<String> search(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (!node.children.containsKey(c)) {
                return new ArrayList<>();
            }
            node = node.children.get(c);
        }

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
            (a, b) -> a.getValue().equals(b.getValue()) ?
                      a.getKey().compareTo(b.getKey()) :
                      b.getValue() - a.getValue()
        );

        pq.addAll(node.counts.entrySet());
        List<String> result = new ArrayList<>();
        int k = 3;
        while (!pq.isEmpty() && k > 0) {
            result.add(pq.poll().getKey());
            k--;
        }
        return result;
    }
}

public class AutocompleteSystem {
    private Trie trie;
    private String currentInput;

    public AutocompleteSystem(String[] sentences, int[] times) {
        trie = new Trie();
        currentInput = "";
        for (int i = 0; i < sentences.length; i++) {
            trie.insert(sentences[i], times[i]);
        }
    }

    public List<String> input(String c) {
        if (c == "#") {
            trie.insert(currentInput, 1);
            currentInput = "";
            return new ArrayList<>();
        } else {
            currentInput += c;
            return trie.search(currentInput);
        }
    }

    public static void main(String[] args) {
        String[] sentences = {"i love you", "island", "iroman", "i love leetcode"};
        int[] times = {5, 3, 2, 2};
        AutocompleteSystem system = new AutocompleteSystem(sentences, times);

        System.out.println(system.input("i ")); // ["i love you", "island","i love leetcode"]
        System.out.println(system.input(" ")); // ["i love you","i love leetcode"]
        System.out.println(system.input(" ")); // []
        System.out.println(system.input("#")); // []
        
        System.out.println(system.input("i")); // ["i love you", "island","i love leetcode"]
        System.out.println(system.input(" ")); // ["i love you","i love leetcode"]
        System.out.println(system.input("l")); // ["i love you","i love leetcode"]
    }
}
