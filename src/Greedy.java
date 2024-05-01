import java.util.*;

public class Greedy {
    public static void findWordLadder(String startWord, String targetWord, Set<String> wordSet) {
        // Membuat PriorityQueue untuk menyimpan WordNode, dengan Comparator yang membandingkan heuristik saja
        PriorityQueue<WordNode> queue = new PriorityQueue<>(Comparator.comparingInt(node -> WordLadderUtils.heuristic(node.word, targetWord)));
        
        // Menambahkan startWord ke queue dengan jumlah langkah 0 dan tanpa predecessor
        queue.add(new WordNode(startWord, 0, null));
        
        // Membuat HashSet untuk menyimpan kata-kata yang sudah dikunjungi
        Set<String> visited = new HashSet<>();

        // Melakukan loop selama queue tidak kosong
        while (!queue.isEmpty()) {
            // Mengambil dan menghapus WordNode dengan prioritas tertinggi dari queue
            WordNode node = queue.poll();
            String word = node.word;

            // Jika word sama dengan targetWord, mencetak word ladder dan mengakhiri metode
            if (word.equals(targetWord)) {
                WordLadderUtils.printWordLadder(node);
                return;
            }

            // Jika word sudah dikunjungi, melanjutkan ke iterasi berikutnya
            if (!visited.add(word)) {
                continue;
            }

            // Untuk setiap tetangga dari word
            for (String neighbor : WordLadderUtils.getNeighbors(word)) {
                // Jika wordSet mengandung neighbor dan neighbor belum dikunjungi, menambahkannya ke queue
                if (wordSet.contains(neighbor) && !visited.contains(neighbor)) {
                    queue.add(new WordNode(neighbor, node.numSteps + 1, node));
                }
            }
        }

        // Jika tidak ada word ladder yang ditemukan, mencetak pesan
        System.out.println("No word ladder found.");
    }

    public static WordNode findWordLadderGUI(String startWord, String targetWord, Set<String> wordSet) {
        PriorityQueue<WordNode> queue = new PriorityQueue<>(Comparator.comparingInt(node -> WordLadderUtils.heuristic(node.word, targetWord)));
        queue.add(new WordNode(startWord, 0, null));
        Set<String> visited = new HashSet<>();
    
        while (!queue.isEmpty()) {
            WordNode node = queue.poll();
            String word = node.word;
    
            if (word.equals(targetWord)) {
                return node; // Return the final node instead of printing
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
    
        return null; // Return null if no word ladder is found
    }
}