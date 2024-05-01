import java.util.*;

public class WordLadderUtils {
    public static List<String> getNeighbors(String word) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char old = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == old) {
                    continue;
                }
                chars[i] = c;
                neighbors.add(new String(chars));
            }
            chars[i] = old;
        }

        return neighbors;
    }

    public static void printWordLadder(WordNode node) {
        Deque<String> words = new ArrayDeque<>();
        int distance = node.numSteps; // Save the distance before the loop
        while (node != null) {
            words.push(node.word);
            node = node.pre;
        }
        System.out.println("Word ladder: " + String.join(" -> ", words));
        System.out.println("Distance: " + distance);
    }

    public static int heuristic(String word, String target) {
        int mismatches = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != target.charAt(i)) {
                mismatches++;
            }
        }
        return mismatches;
    }

    public static void printWordLadderA(WordNode node) {
        Deque<String> words = new ArrayDeque<>();
        int distance = node.numSteps - 1; // Save the distance before the loop
        while (node != null) {
            words.push(node.word);
            node = node.pre;
        }
        System.out.println("Word ladder: " + String.join(" -> ", words));
        System.out.println("Distance: " + distance);
    }
}