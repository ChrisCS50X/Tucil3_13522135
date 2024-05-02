public class WordLadderResult {
    public final WordNode node;
    public final int visitedNodes;

    public WordLadderResult(WordNode node, int visitedNodes) {
        this.node = node;
        this.visitedNodes = visitedNodes;
    }
}