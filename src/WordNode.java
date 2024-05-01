public class WordNode {
    // Kata yang diwakili oleh node ini
    String word;
    // Jumlah langkah dari kata awal ke kata ini
    int numSteps;
    // Node sebelumnya dalam word ladder, digunakan untuk melacak jalur
    WordNode pre;

    // Konstruktor untuk WordNode
    public WordNode(String word, int numSteps, WordNode pre) {
        // Menetapkan kata untuk node ini
        this.word = word;
        // Menetapkan jumlah langkah dari kata awal ke kata ini
        this.numSteps = numSteps;
        // Menetapkan node sebelumnya dalam word ladder
        this.pre = pre;
    }
}