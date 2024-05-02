import java.util.*;

public class WordLadderUtils {
    // Metode untuk mendapatkan semua tetangga dari suatu kata
    public static List<String> getNeighbors(String word) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();

        // Untuk setiap karakter dalam kata
        for (int i = 0; i < chars.length; i++) {
            char old = chars[i];
            // Coba ganti dengan setiap huruf lain dalam abjad
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == old) {
                    continue;
                }
                chars[i] = c;
                neighbors.add(new String(chars));
            }
            // Kembalikan karakter asli
            chars[i] = old;
        }

        return neighbors;
    }

    // Metode untuk mencetak word ladder dari suatu node
    public static void printWordLadder(WordNode node, int visitedNodes) {
        Deque<String> words = new ArrayDeque<>();
        int distance = node.numSteps; // Simpan jarak sebelum loop
        // Membangun word ladder dengan mengikuti predecessor dari setiap node
        while (node != null) {
            words.push(node.word);
            node = node.pre;
        }
        // Mencetak word ladder dan jarak
        System.out.println("Word ladder: " + String.join(" -> ", words));
        System.out.println("Jarak: " + distance);
        System.out.println("Banyaknya node yang dikunjungi: " + visitedNodes);
    }

    // Metode untuk menghitung heuristik antara dua kata
    public static int heuristic(String word, String target) {
        int mismatches = 0;
        // Menghitung jumlah karakter yang tidak cocok
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != target.charAt(i)) {
                mismatches++;
            }
        }
        return mismatches;
    }

    // Metode untuk mencetak word ladder dari suatu node (versi A*)
    public static void printWordLadderA(WordNode node, int visitedNodes) {
        Deque<String> words = new ArrayDeque<>();
        int distance = node.numSteps - 1; // Simpan jarak sebelum loop
        // Membangun word ladder dengan mengikuti predecessor dari setiap node
        while (node != null) {
            words.push(node.word);
            node = node.pre;
        }
        // Mencetak word ladder dan jarak
        System.out.println("Word ladder: " + String.join(" -> ", words));
        System.out.println("Jarak: " + distance);
        System.out.println("Banyaknya node yang dikunjungi: " + visitedNodes);
    }

    public static String printWordLadderGUI(WordLadderResult result) {
        WordNode node = result.node;
        Deque<String> words = new ArrayDeque<>();
        int distance = node.numSteps;
        while (node != null) {
            words.push(node.word);
            node = node.pre;
        }
        String resultStr = "Word ladder: " + String.join(" -> ", words) + "\n" + "Jarak: " + distance;
        resultStr += "\nBanyaknya node yang dikunjungi: " + result.visitedNodes;
        return resultStr;
    }

    public static String printWordLadderGUIA(WordLadderResult result) {
        WordNode node = result.node;
        Deque<String> words = new ArrayDeque<>();
        int distance = node.numSteps - 1;
        while (node != null) {
            words.push(node.word);
            node = node.pre;
        }
        String resultStr = "Word ladder: " + String.join(" -> ", words) + "\n" + "Jarak: " + distance;
        resultStr += "\nBanyaknya node yang dikunjungi: " + result.visitedNodes;
        return resultStr;
    }
}