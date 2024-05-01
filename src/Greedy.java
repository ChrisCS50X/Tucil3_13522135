import java.util.*;


public class Greedy {
    public static void findWordLadder(String startWord, String targetWord, Set<String> wordSet) {
        PriorityQueue<WordNode> queue = new PriorityQueue<>(Comparator.comparingInt(node -> WordLadderUtils.heuristic(node.word, targetWord)));
        queue.add(new WordNode(startWord, 0, null));
        Set<String> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            WordNode node = queue.poll();
            String word = node.word;

            if (word.equals(targetWord)) {
                WordLadderUtils.printWordLadder(node);
                return;
            }

            if (!visited.add(word)) {
                continue;
            }

            for (String neighbor : WordLadderUtils.getNeighbors(word)) {
                if (wordSet.contains(neighbor) && !visited.contains(neighbor)) {
                    queue.add(new WordNode(neighbor, node.numSteps + 1, node));
                }
            }
        }

        System.out.println("No word ladder found.");
    }
}