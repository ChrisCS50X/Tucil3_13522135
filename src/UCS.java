import java.util.*;
import java.nio.file.*;
import java.io.IOException;

public class UCS {
    public static void main(String[] args) throws IOException {
        List<String> wordList = Files.readAllLines(Paths.get("./words_alpha.txt"));
        Set<String> wordSet = new HashSet<>(wordList);
        String startWord = "bolder".toLowerCase();
        String targetWord = "higher".toLowerCase();

        long startTime = System.nanoTime();
        findWordLadder(startWord, targetWord, wordSet);
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1_000_000; // convert to milliseconds

        System.out.println("Execution time: " + duration + " ms");
    }

    public static void findWordLadder(String startWord, String targetWord, Set<String> wordSet) {
        PriorityQueue<WordNode> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.numSteps));
        queue.add(new WordNode(startWord, 0, null));
        Set<String> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            WordNode node = queue.poll();
            String word = node.word;

            if (word.equals(targetWord)) {
                printWordLadder(node);
                return;
            }

            if (!visited.add(word)) {
                continue;
            }

            for (String neighbor : getNeighbors(word)) {
                if (wordSet.contains(neighbor) && !visited.contains(neighbor)) {
                    queue.add(new WordNode(neighbor, node.numSteps + 1, node));
                }
            }
        }

        System.out.println("No word ladder found.");
    }

    private static List<String> getNeighbors(String word) {
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

    private static void printWordLadder(WordNode node) {
        Deque<String> words = new ArrayDeque<>();
        int distance = node.numSteps; // Save the distance before the loop
        while (node != null) {
            words.push(node.word);
            node = node.pre;
        }
        System.out.println("Word ladder: " + String.join(" -> ", words));
        System.out.println("Distance: " + distance);
    }
}