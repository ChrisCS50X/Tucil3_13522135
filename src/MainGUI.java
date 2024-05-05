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
        // Membuat frame
        JFrame frame = new JFrame("Word Ladder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Membuat panel
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        // Menampilkan frame
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // Membuat label dan field teks untuk kata awal
        JLabel startLabel = new JLabel("Masukkan Start Word:");
        startLabel.setBounds(10, 20, 200, 25);
        panel.add(startLabel);

        JTextField startText = new JTextField(20);
        startText.setBounds(220, 20, 160, 25);
        panel.add(startText);

        // Membuat label dan field teks untuk kata target
        JLabel targetLabel = new JLabel("Masukkan End Word:");
        targetLabel.setBounds(10, 60, 200, 25);
        panel.add(targetLabel);

        JTextField targetText = new JTextField(20);
        targetText.setBounds(220, 60, 160, 25);
        panel.add(targetText);

        // Membuat label dan combo box untuk pilihan algoritma
        JLabel algorithmLabel = new JLabel("Pilih Algoritma:");
        algorithmLabel.setBounds(10, 100, 200, 25);
        panel.add(algorithmLabel);

        String[] algorithms = { "A*", "Greedy Best First Search", "Uniform Cost Search (UCS)" };
        JComboBox<String> algorithmBox = new JComboBox<>(algorithms);
        algorithmBox.setBounds(220, 100, 160, 25);
        panel.add(algorithmBox);

        // Membuat tombol execute
        JButton executeButton = new JButton("Execute");
        executeButton.setBounds(10, 140, 80, 25);
        panel.add(executeButton);

        // Membuat area output
        JTextArea outputArea = new JTextArea();
        outputArea.setBounds(10, 180, 560, 180);

        // Membuat scroll pane dan menambahkan area output ke dalamnya
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(10, 180, 560, 180);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        // Menambahkan scroll pane ke panel bukan area output
        panel.add(scrollPane);

        // Menambahkan action listener ke tombol execute
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String startWord = startText.getText().toLowerCase();
                String targetWord = targetText.getText().toLowerCase();
                String algorithm = (String) algorithmBox.getSelectedItem();

                // Memuat daftar kata
                List<String> wordList = null;
                try {
                    wordList = Files.readAllLines(Paths.get("./words_alpha.txt"));
                } catch (IOException ioException) {
                    outputArea.setText("Error membaca file daftar kata.");
                    return;
                }
                Set<String> wordSet = new HashSet<>(wordList);

                // Memvalidasi kata
                if (!wordSet.contains(startWord) || !wordSet.contains(targetWord)) {
                    outputArea.setText("Error: Start Word dan End word harus merupakan kata-kata berbahasa inggris.");
                    return;
                }
                if (startWord.length() != targetWord.length()) {
                    outputArea.setText("Error: Start Word dan End word harus mempunyai panjang kata yang sama.");
                    return;
                }

                if (startWord.equals(targetWord)) {
                    outputArea.setText("Start Word dan End Word tidak boleh sama.");
                    return;
                }

                // Menjalankan algoritma yang dipilih
                long startTime = System.nanoTime();
                WordLadderResult result;
                switch (algorithm) {
                    case "A*":
                        result = Astar.findWordLadderGUI(startWord, targetWord, wordSet);
                        break;
                    case "Greedy Best First Search":
                        result = Greedy.findWordLadderGUI(startWord, targetWord, wordSet);
                        break;
                    case "Uniform Cost Search (UCS)":
                        result = UCS.findWordLadderGUI(startWord, targetWord, wordSet);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid algorithm: " + algorithm);
                }
                long endTime = System.nanoTime();
                long duration = (endTime - startTime) / 1_000_000;
                
                // Menampilkan waktu eksekusi dan hasil
                String resultStr;
                if (algorithm.equals("A*")) {
                    resultStr = WordLadderUtils.printWordLadderGUIA(result);
                } else {
                    resultStr = WordLadderUtils.printWordLadderGUI(result);
                }
                outputArea.setText("Waktu Eksekusi: " + duration + " ms\n" + resultStr);
            }
        });
    }
}