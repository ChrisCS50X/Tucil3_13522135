public class WordNode {
    String word;  // Kata yang diwakili oleh node ini
    int numSteps; // Jumlah langkah dari kata awal ke kata ini
    WordNode pre; // Node sebelumnya dalam word ladder, digunakan untuk melacak jalur

    // Konstruktor untuk WordNode
    public WordNode(String word, int numSteps, WordNode pre) {
        this.word = word;
        this.numSteps = numSteps;
        this.pre = pre;
    }
}