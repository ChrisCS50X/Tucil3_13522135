import java.util.*;
import java.nio.file.*;
import java.io.IOException;

public class Astar {
    public static void main(String[] args) throws IOException {
        List<String> wordList = Files.readAllLines(Paths.get("./words_alpha.txt"));
        Set<String> wordSet = new HashSet<>(wordList);
        String startWord = "bolder".toLowerCase();
        String targetWord = "higher".toLowerCase();

        long startTime = System.nanoTime();
        findWordLadder(startWord, targetWord, wordSet);
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1_000_000;  // convert to milliseconds

        System.out.println("Execution time: " + duration + " ms");
    }

    public static void findWordLadder(String startWord, String targetWord, Set<String> wordSet) {
        if (!wordSet.contains(startWord) || !wordSet.contains(targetWord)) {
            System.out.println("Error: Start Word dan End word harus merupakan kata-kata yang berada di kamus bahasa inggris.");
            return;
        }
        
        if (startWord.length() != targetWord.length()) {
            System.out.println("Error: Start Word dan End word harus memiliki panjang yang sama.");
            return;
        }

        PriorityQueue<WordNode> wordQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.numSteps + WordLadderUtils.heuristic(node.word, targetWord)));
        wordQueue.add(new WordNode(startWord, 1, null));

        wordSet.add(targetWord);

        while (!wordQueue.isEmpty()) {
            WordNode currentNode = wordQueue.poll();
            String currentWord = currentNode.word;

            if (currentWord.equals(targetWord)) {
                WordLadderUtils.printWordLadderA(currentNode);
                return;
            }

            char[] wordChars = currentWord.toCharArray();
            for (int i = 0; i < wordChars.length; i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    char originalChar = wordChars[i];
                    if (wordChars[i] != c) {
                        wordChars[i] = c;
                    }

                    String newWord = new String(wordChars);
                    if (wordSet.contains(newWord)) {
                        wordQueue.add(new WordNode(newWord, currentNode.numSteps + 1, currentNode));
                        wordSet.remove(newWord);
                    }

                    wordChars[i] = originalChar;
                }
            }
        }
    }

}