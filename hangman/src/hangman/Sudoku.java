/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.lang.Thread.sleep;
import java.util.Calendar;
import java.awt.Color;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Sudoku extends JPanel implements ActionListener {

    private JLabel clockDisplay;
    private JLabel scoreDisplay;
    private JButton quit;
    private JButton submit;
    private GameIntro g;
    private int score;
    private String[][] board;
    private static final String[][] solution = {
        {"8", "3", "5", "4", "1", "6", "9", "2", "7"},
        {"2", "9", "6", "8", "5", "7", "4", "3", "1"},
        {"4", "1", "7", "2", "9", "3", "6", "5", "8"},
        {"5", "6", "9", "1", "3", "4", "7", "8", "2"},
        {"1", "2", "3", "6", "7", "8", "5", "4", "9"},
        {"7", "4", "8", "5", "2", "9", "1", "6", "3"},
        {"6", "5", "2", "7", "8", "1", "3", "9", "4"},
        {"9", "8", "1", "3", "4", "5", "2", "7", "6"},
        {"3", "7", "4", "9", "6", "2", "8", "1", "5"}
    };
    private static final int rows = 9;
    private static final int cluster = 3;
    private static final int gap = 2;
    private static final float fieldPoints = 32f;
    private static final Color background = java.awt.Color.BLUE;
    private Grid[][] grid = new Grid[rows][rows];
    private JPanel[][] panel;

    public Sudoku(GameIntro g, int score) {
        this.g = g;
        this.score = 540 + score;
        board = new String[rows][rows];
        loadGUI();
        clock();
    }

    private void loadGUI() {

        JPanel myPanel = new JPanel(new GridLayout(cluster, cluster));
        myPanel.setBorder(BorderFactory.createEmptyBorder(gap, gap, gap, gap));
        myPanel.setBackground(background);
        panel = new JPanel[cluster][cluster];

        for (int i = 0; i < panel.length; i++) {
            for (int j = 0; j < panel[i].length; j++) {
                panel[i][j] = new JPanel(new GridLayout(cluster, cluster, 1, 1));
                panel[i][j].setBackground(background);
                panel[i][j].setBorder(BorderFactory.createEmptyBorder(gap, gap, gap, gap));
                myPanel.add(panel[i][j]);
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Grid(g);
                int rows = i / 3;
                int cols = j / 3;
                panel[rows][cols].add(grid[i][j].guess);
            }
        }

        grid[0][0].guess.setText("8");
        grid[0][3].guess.setText("4");
        grid[0][5].guess.setText("6");
        grid[0][8].guess.setText("7");
        grid[1][6].guess.setText("4");
        grid[2][1].guess.setText("1");
        grid[2][6].guess.setText("6");
        grid[2][7].guess.setText("5");
        grid[3][0].guess.setText("5");
        grid[3][2].guess.setText("9");
        grid[3][4].guess.setText("3");
        grid[3][6].guess.setText("7");
        grid[3][7].guess.setText("8");
        grid[4][4].guess.setText("7");
        grid[5][1].guess.setText("4");
        grid[5][2].guess.setText("8");
        grid[5][4].guess.setText("2");
        grid[5][6].guess.setText("1");
        grid[5][8].guess.setText("3");
        grid[6][1].guess.setText("5");
        grid[6][2].guess.setText("2");
        grid[6][7].guess.setText("9");
        grid[7][2].guess.setText("1");
        grid[8][0].guess.setText("3");
        grid[8][3].guess.setText("9");
        grid[8][5].guess.setText("2");
        grid[8][8].guess.setText("5");

        grid[0][0].getGuess().setEditable(false);
        grid[0][3].getGuess().setEditable(false);
        grid[0][5].getGuess().setEditable(false);
        grid[0][8].getGuess().setEditable(false);
        grid[1][6].getGuess().setEditable(false);
        grid[2][1].getGuess().setEditable(false);
        grid[2][6].getGuess().setEditable(false);
        grid[2][7].getGuess().setEditable(false);
        grid[3][0].getGuess().setEditable(false);
        grid[3][2].getGuess().setEditable(false);
        grid[3][4].getGuess().setEditable(false);
        grid[3][6].getGuess().setEditable(false);
        grid[3][7].getGuess().setEditable(false);
        grid[4][4].getGuess().setEditable(false);
        grid[5][1].getGuess().setEditable(false);
        grid[5][2].getGuess().setEditable(false);
        grid[5][4].getGuess().setEditable(false);
        grid[5][6].getGuess().setEditable(false);
        grid[5][8].getGuess().setEditable(false);
        grid[6][1].getGuess().setEditable(false);
        grid[6][2].getGuess().setEditable(false);
        grid[6][7].getGuess().setEditable(false);
        grid[7][2].getGuess().setEditable(false);
        grid[8][0].getGuess().setEditable(false);
        grid[8][3].getGuess().setEditable(false);
        grid[8][5].getGuess().setEditable(false);
        grid[8][8].getGuess().setEditable(false);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (!grid[i][j].guess.isEditable()) {
                    grid[i][j].guess.setToolTipText("Help");
                }
            }
        }

        setLayout(null);
        clockDisplay = new JLabel("");
        scoreDisplay = new JLabel("Score: " + score);
        quit = new JButton("Quit");
        quit.setToolTipText("Back to Menu");
        submit = new JButton("Submit");
        submit.setToolTipText("Submit your answer");
        quit.addActionListener(this);
        submit.addActionListener(this);
        clockDisplay.setBounds(250, 1, 250, 25);
        scoreDisplay.setBounds(170, 1, 250, 25);
        quit.setBounds(490, 270, 80, 30);
        submit.setBounds(50, 270, 80, 30);
        myPanel.setBounds(135, 20, 340, 340);
        add(clockDisplay);
        add(scoreDisplay);
        add(quit);
        add(submit);

        add(myPanel);
        repaint();

    }
    
    public void clock() {
        Thread clock = new Thread() {
            public void run() {
                try {
                    for (;;) {
                        Calendar calendar = new GregorianCalendar();
                        int day = calendar.get(Calendar.DAY_OF_MONTH);
                        int month = calendar.get(Calendar.MONTH) + 1;
                        int year = calendar.get(Calendar.YEAR);

                        int second = calendar.get(Calendar.SECOND);
                        int minute = calendar.get(Calendar.MINUTE);
                        int hour = calendar.get(Calendar.HOUR_OF_DAY);

                        clockDisplay.setText("Time " + hour + ":" + minute + ":" + second
                                + "    Date " + month + "/" + day + "/" + year);
                        sleep(1000);
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        clock.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();
        JButton button = null;
        boolean incorrect = false;

        if (object instanceof JButton) {
            button = (JButton) object;
        }
        if (button != null) {
            String input = button.getText().toLowerCase();

            if (input.equals("quit")) {
                score -= 540;
                g.frame.getContentPane().setVisible(false);
                g.frame.getContentPane().remove(this);
                g.frame.add(new EndGame(g, score));
                g.frame.getContentPane().setVisible(true);
            }
            if (input.equals("submit")) {
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[i].length; j++) {
                        board[i][j] = grid[i][j].getGuess().getText();
                    }
                }
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board[i].length; j++) {
                        if (!board[i][j].equals(solution[i][j])) {
                            incorrect = true;
                            //Subtract 10 for every incorrect tiles and mark them as guessed
                            if (!grid[i][j].alreadyGuessed) {
                                score -= 10;
                                grid[i][j].alreadyGuessed = true;
                            }
                        }
                    }
                }

                if (incorrect == true) {
                    JOptionPane.showMessageDialog(g.frame, "Incorrect Answer");
                } else {
                    g.frame.getContentPane().setVisible(false);
                    g.frame.getContentPane().remove(this);
                    g.frame.add(new EndGame(this.g, score));
                    g.frame.getContentPane().setVisible(true);
                }
            }
        }
        
    }
        
        public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font title = new Font("Arial Black", Font.BOLD, 24);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(title);
        g2.drawString("SUDOKU", 5, 20);
        
    }

    private static class Grid {

        private GameIntro g;
        private boolean alreadyGuessed;
        private JTextField guess;

        public Grid(GameIntro g) {
            this.g = g;
            alreadyGuessed = false;
            guess = create();
            guess.setToolTipText("Type in a number 1 - 9");
        }

        public JTextField create() {
            JTextField textField = new JTextField(2);
            textField.setTransferHandler(null);
            textField.addKeyListener(new KeyAdapter() {
                public void keyInput(KeyEvent ke) {
                    if (textField.getText().length() >= 1) {
                        ke.consume();
                    }
                    if (ke.getKeyChar() > 57 || ke.getKeyChar() < 49) {
                        if (ke.getKeyChar() != 8) {
                            JOptionPane.showMessageDialog(g.frame, "Not a valid input");
                            ke.consume();
                        }
                    }
                    if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                        textField.setText("");
                    }
                }

            });
            textField.setHorizontalAlignment(JTextField.CENTER);
            textField.setFont(textField.getFont().deriveFont(fieldPoints));
            return textField;
        }

        public JTextField getGuess() {
            return guess;
        }
    }
}


