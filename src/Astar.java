import java.util.*;

public class Astar {
    public static void findWordLadder(String startWord, String targetWord, Set<String> wordSet) {
        /*  Membuat PriorityQueue untuk menyimpan WordNode, dengan Comparator yang 
            membandingkan jumlah langkah dan heuristik
        */
        
        PriorityQueue<WordNode> wordQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.numSteps + WordLadderUtils.heuristic(node.word, targetWord)));
        
        // Menambahkan startWord ke queue dengan jumlah langkah 1 dan tanpa predecessor
        wordQueue.add(new WordNode(startWord, 1, null));

        // Menambahkan targetWord ke wordSet
        wordSet.add(targetWord);

        // Melakukan loop selama queue tidak kosong
        while (!wordQueue.isEmpty()) {
            // Mengambil dan menghapus WordNode dengan prioritas tertinggi dari queue
            WordNode currentNode = wordQueue.poll();
            String currentWord = currentNode.word;

            // Jika currentWord sama dengan targetWord, mencetak word ladder dan mengakhiri metode
            if (currentWord.equals(targetWord)) {
                WordLadderUtils.printWordLadderA(currentNode);
                return;
            }

            // Mengubah currentWord menjadi array of char untuk manipulasi karakter
            char[] wordChars = currentWord.toCharArray();
            for (int i = 0; i < wordChars.length; i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    char originalChar = wordChars[i];
                    // Mengganti karakter di posisi i dengan c jika mereka berbeda
                    if (wordChars[i] != c) {
                        wordChars[i] = c;
                    }

                    // Membuat string baru dari array of char
                    String newWord = new String(wordChars);
                    // Jika wordSet mengandung newWord, menambahkannya ke queue dan menghapusnya dari wordSet
                    if (wordSet.contains(newWord)) {
                        wordQueue.add(new WordNode(newWord, currentNode.numSteps + 1, currentNode));
                        wordSet.remove(newWord);
                    }

                    // Mengembalikan karakter asli ke posisi i
                    wordChars[i] = originalChar;
                }
            }
        }
    }

    public static WordNode findWordLadderGUI(String startWord, String targetWord, Set<String> wordSet) {
        PriorityQueue<WordNode> wordQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.numSteps + WordLadderUtils.heuristic(node.word, targetWord)));
        wordQueue.add(new WordNode(startWord, 1, null));
        wordSet.add(targetWord);
    
        while (!wordQueue.isEmpty()) {
            WordNode currentNode = wordQueue.poll();
            String currentWord = currentNode.word;
    
            if (currentWord.equals(targetWord)) {
                return currentNode; // Return the final node instead of printing
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
    
        return null; // Return null if no word ladder is found
    }
}