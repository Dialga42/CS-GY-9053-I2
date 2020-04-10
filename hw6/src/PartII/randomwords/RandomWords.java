package PartII.randomwords;

import java.io.*;
import java.util.*;

import javafx.util.Pair;

public class RandomWords {

    private String filename;
    /* choose ArrayList<String> or String[] to store the words */
    List<String> wordList = new ArrayList<>();

    public RandomWords() {
    }

    /**
     * Read 10 random words from file
     *
     * @param filename filename
     */
    public RandomWords(String filename) {
        List<String> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        try {
            Scanner in = new Scanner(new FileReader(new File(filename)));
            while (in.hasNext()) {
                list.add(in.next());
            }
        } catch (Exception e) {
        }
        for (int i = 0; i < 10; i++) {
            int pos = (int) (Math.random() * 100);
            while (set.contains(pos)) {
                pos = (int) (Math.random() * 100);
            }
            set.add(pos);
            wordList.add(list.get(pos));
        }
    }

    /**
     * Calculate the levenshtein Distance between two words
     *
     * @param word1 The first word
     * @param word2 The second word
     * @return The levenshtein Distance
     */
    public static int levenshteinDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]) + 1, dp[i - 1][j - 1] + (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1));
            }
        }
        return dp[m][n];
    }

    /**
     * Sort the list of 45 pairs using inner class
     *
     * @return The sorted list
     */
    public List<Pair<String, String>> sortedPairsComparator() {
        List<Pair<String, String>> wordPair = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = i + 1; j < 10; j++) {
                wordPair.add(new Pair<>(wordList.get(i), wordList.get(j)));
            }
        }
        class Compare implements Comparator<Pair<String, String>> {
            public int compare(Pair<String, String> p1, Pair<String, String> p2) {
                return RandomWords.levenshteinDistance(p1.getKey(), p1.getValue()) - RandomWords.levenshteinDistance(p2.getKey(), p2.getValue());
            }
        }
        wordPair.sort(new Compare());
        return wordPair;
    }

    /**
     * Sort the list of 45 pairs using Lambda function
     *
     * @return The sorted list
     */
    public List<Pair<String, String>> sortedPairsLambda() {
        List<Pair<String, String>> wordPair = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = i + 1; j < 10; j++) {
                wordPair.add(new Pair<>(wordList.get(i), wordList.get(j)));
            }
        }
        wordPair.sort(Comparator.comparingInt((Pair<String, String> p) -> RandomWords.levenshteinDistance(p.getKey(), p.getValue())));
        return wordPair;
    }

    public static void main(String[] args) {
        RandomWords r = new RandomWords("src/data/words");
        System.out.println("Random selected words:");
        for (String i : r.wordList) {
            System.out.println(i);
        }
        System.out.println();
        System.out.println("levenshteinDistance of kitten and sitting: " + RandomWords.levenshteinDistance("kitten", "sitting"));
        System.out.println("levenshteinDistance of ice and swing: " + RandomWords.levenshteinDistance("ice", "swing"));
        System.out.println();
        System.out.println("sortedPairsComparator:");
        for (Pair<String, String> i : r.sortedPairsComparator()) {
            System.out.println(i.getKey() + " " + i.getValue() + " " + RandomWords.levenshteinDistance(i.getKey(), i.getValue()));
        }
        System.out.println();
        System.out.println("sortedPairsLambda:");
        for (Pair<String, String> i : r.sortedPairsLambda()) {
            System.out.println(i.getKey() + " " + i.getValue() + " " + RandomWords.levenshteinDistance(i.getKey(), i.getValue()));
        }
    }
}
