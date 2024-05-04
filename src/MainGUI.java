import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainGUI {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Word Ladder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Create the panel
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        // Show the frame
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // Create the start word label and text field
        JLabel startLabel = new JLabel("Masukkan Start Word:");
        startLabel.setBounds(10, 20, 200, 25);
        panel.add(startLabel);

        JTextField startText = new JTextField(20);
        startText.setBounds(220, 20, 160, 25);
        panel.add(startText);

        // Create the target word label and text field
        JLabel targetLabel = new JLabel("Masukkan End Word:");
        targetLabel.setBounds(10, 60, 200, 25);
        panel.add(targetLabel);

        JTextField targetText = new JTextField(20);
        targetText.setBounds(220, 60, 160, 25);
        panel.add(targetText);

        // Create the algorithm choice label and combo box
        JLabel algorithmLabel = new JLabel("Pilih Algoritma:");
        algorithmLabel.setBounds(10, 100, 200, 25);
        panel.add(algorithmLabel);

        String[] algorithms = { "Astar", "Greedy", "UCS" };
        JComboBox<String> algorithmBox = new JComboBox<>(algorithms);
        algorithmBox.setBounds(220, 100, 160, 25);
        panel.add(algorithmBox);

        // Create the execute button
        JButton executeButton = new JButton("Execute");
        executeButton.setBounds(10, 140, 80, 25);
        panel.add(executeButton);

        // Create the output area
        JTextArea outputArea = new JTextArea();
        outputArea.setBounds(10, 180, 560, 180);

        // Create a scroll pane and add the output area to it
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(10, 180, 560, 180);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        // Add the scroll pane to the panel instead of the output area
        panel.add(scrollPane);

        // Add action listener to the execute button
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String startWord = startText.getText().toLowerCase();
                String targetWord = targetText.getText().toLowerCase();
                String algorithm = (String) algorithmBox.getSelectedItem();

                // Load the word list
                List<String> wordList = null;
                try {
                    wordList = Files.readAllLines(Paths.get("./words_alpha.txt"));
                } catch (IOException ioException) {
                    outputArea.setText("Error reading word list file.");
                    return;
                }
                Set<String> wordSet = new HashSet<>(wordList);

                // Validate the words
                if (!wordSet.contains(startWord) || !wordSet.contains(targetWord)) {
                    outputArea.setText("Error: Start Word and End word harus merupakan kata-kata berbahasa inggris.");
                    return;
                }
                if (startWord.length() != targetWord.length()) {
                    outputArea.setText("Error: Start Word and End word harus mempunyai panjang kata yang sama.");
                    return;
                }

                if (startWord.equals(targetWord)) {
                    outputArea.setText("Start Word dan End Word tidak boleh sama.");
                    return;
                }

                // Run the selected algorithm
                long startTime = System.nanoTime();
                WordLadderResult result;
                switch (algorithm) {
                    case "Astar":
                        result = Astar.findWordLadderGUI(startWord, targetWord, wordSet);
                        break;
                    case "Greedy":
                        result = Greedy.findWordLadderGUI(startWord, targetWord, wordSet);
                        break;
                    case "UCS":
                        result = UCS.findWordLadderGUI(startWord, targetWord, wordSet);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid algorithm: " + algorithm);
                }
                long endTime = System.nanoTime();
                long duration = (endTime - startTime) / 1_000_000;
                
                // Display the execution time and the result
                String resultStr;
                if (algorithm.equals("Astar")) {
                    resultStr = WordLadderUtils.printWordLadderGUIA(result);
                } else {
                    resultStr = WordLadderUtils.printWordLadderGUI(result);
                }
                outputArea.setText("Waktu Eksekusi: " + duration + " ms\n" + resultStr);
            }
        });
    }
}