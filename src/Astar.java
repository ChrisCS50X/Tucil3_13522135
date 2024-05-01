import java.util.*;


public class Astar {
    public static void findWordLadder(String startWord, String targetWord, Set<String> wordSet) {

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