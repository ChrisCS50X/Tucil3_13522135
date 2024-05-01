import java.util.*;
import java.nio.file.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> wordList = Files.readAllLines(Paths.get("./words_alpha.txt"));
        Set<String> wordSet = new HashSet<>(wordList);
    
        Scanner scanner = new Scanner(System.in);
    
        try {
            System.out.println("Enter the start word:");
            String startWord = scanner.nextLine().toLowerCase();
    
            System.out.println("Enter the target word:");
            String targetWord = scanner.nextLine().toLowerCase();
    
            if (!wordSet.contains(startWord) || !wordSet.contains(targetWord)) {
                System.out.println("Error: Start Word dan End word harus merupakan kata-kata yang berada di kamus bahasa inggris.");
                return;
            }
    
            if (startWord.length() != targetWord.length()) {
                System.out.println("Error: Start Word dan End word harus memiliki panjang yang sama.");
                return;
            }
    
            System.out.println("Choose an algorithm: 1 for Astar, 2 for Greedy, 3 for UCS");
            int choice = scanner.nextInt();
    
            long startTime = System.nanoTime();
    
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
    
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000; 
            System.out.println("Execution time: " + duration + " ms");
        } finally {
            scanner.close();
        }
    }
}