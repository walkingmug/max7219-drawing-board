import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;

public class ButtonMatrix extends JFrame {
    private static final int SIZE = 8;
    private static final int GAP = 2;

    private JButton[][] buttons;
    private boolean[][] clicked;

    public ButtonMatrix() {
        setTitle("Drawing Board for MAX7219");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel matrixPanel = new JPanel(new GridLayout(SIZE, SIZE, GAP, GAP));
        buttons = new JButton[SIZE][SIZE];
        clicked = new boolean[SIZE][SIZE];
        
        // Create the matrix with the clickable buttons
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                final int x = row;
                final int y = col;
                buttons[row][col] = new JButton();
                buttons[row][col].setPreferredSize(new Dimension(50, 50));
                buttons[row][col].setBackground(Color.WHITE);
                buttons[row][col].setOpaque(true);
                buttons[row][col].setBorderPainted(false);
                buttons[row][col].addActionListener(new ActionListener() {
                    // Color buttons red when clicked; remove when unclicked
                    public void actionPerformed(ActionEvent e) {
                        if (clicked[x][y]) {
                            clicked[x][y] = false;
                            buttons[x][y].setBackground(Color.WHITE);
                        } else {
                            clicked[x][y] = true;
                            buttons[x][y].setBackground(Color.RED);
                        }
                    }
                });
                matrixPanel.add(buttons[row][col]);
            }
        }
        
        // Create a Save button that converts the drawing to Arduino code
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] rowNames = new String[SIZE];
                // Name matrix rows in Arduino format (e.g. B11000100)
                for (int i = 0; i < SIZE; i++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("B");
                    for (int j = 0; j < SIZE; j++) {
                        sb.append(clicked[i][j] ? "1" : "0");
                    }
                    rowNames[i] = sb.toString();
                }
                // Save result to Arduino file
                String matrixResult = Arrays.toString(rowNames).replaceAll("[\\[\\]]","");
                SaveResult.main(new String[] {"MAX7219.ino", matrixResult});
            }
        });

        add(matrixPanel, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ButtonMatrix();
    }
}
