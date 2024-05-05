import java.util.*;
import java.nio.file.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Membaca semua baris dari file dan menyimpannya dalam List
        List<String> wordList = Files.readAllLines(Paths.get("./words_alpha.txt"));
        // Mengubah List menjadi Set untuk pencarian yang lebih efisien
        Set<String> wordSet = new HashSet<>(wordList);
    
        // Membuat Scanner untuk membaca input dari terminal
        Scanner scanner = new Scanner(System.in);
    
        try {
            // Meminta pengguna untuk memasukkan kata awal
            System.out.println("Enter the start word:");
            String startWord = scanner.nextLine().toLowerCase();
    
            // Meminta pengguna untuk memasukkan kata target
            System.out.println("Enter the target word:");
            String targetWord = scanner.nextLine().toLowerCase();
    
            // Memeriksa apakah kata awal dan kata target ada dalam set kata
            if (!wordSet.contains(startWord) || !wordSet.contains(targetWord)) {
                System.out.println("Error: Start Word dan End word harus merupakan kata-kata yang berada di kamus bahasa inggris.");
                return;
            }
    
            // Memeriksa apakah kata awal dan kata target memiliki panjang yang sama
            if (startWord.length() != targetWord.length()) {
                System.out.println("Error: Start Word dan End word harus memiliki panjang yang sama.");
                return;
            }
    
            // Meminta pengguna untuk memilih algoritma
            System.out.println("Choose an algorithm: 1 for A*, 2 for Greedy Best First Search, 3 for Uniform Cost Search (UCS)");
            int choice = scanner.nextInt();
    
            // Mengambil waktu awal
            long startTime = System.nanoTime();
    
            // Menjalankan algoritma yang dipilih oleh pengguna
            switch (choice) {
                case 1:
                    Astar.findWordLadder(startWord, targetWord, wordSet);
                    break;
                case 2:
                    Greedy.findWordLadder(startWord, targetWord, wordSet);
                    break;
                case 3:
                    UCS.findWordLadder(startWord, targetWord, wordSet);
                    break;
            }
    
            // Mengambil waktu akhir dan menghitung durasi
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000; 
            // Mencetak durasi eksekusi
            System.out.println("Execution time: " + duration + " ms");
        } finally {
            // Menutup scanner
            scanner.close();
        }
    }
}