/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.sleep;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Sudoku extends JPanel implements ActionListener {

    private JLabel clockDisplay;
    private JLabel scoreDisplay;
    private JButton quit;
    private JButton submit;
    private GameIntro g;
    private int score;

    public Sudoku(GameIntro g, int score) {
        this.g = g;
        this.score = score;
        loadGUI();
        clock();
    }

    private void loadGUI() {
        setLayout(null);
        clockDisplay = new JLabel("");
        scoreDisplay = new JLabel("Score: " + score);
        quit = new JButton("Quit");
        submit = new JButton("Submit");
        quit.addActionListener(this);
        submit.addActionListener(this);
        clockDisplay.setBounds(250, 1, 250, 25);
        scoreDisplay.setBounds(170, 1, 250, 25);
        quit.setBounds(490, 270, 80, 30);
        submit.setBounds(50, 270, 80, 30);
        add(clockDisplay);
        add(scoreDisplay);
        add(quit);
        add(submit);
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

        if (object instanceof JButton) {
            button = (JButton) object;
        }
        if(button != null){
            String input = button.getText().toLowerCase();
            
            if(input.equals("quit")){
                score = 0;
                g.frame.getContentPane().setVisible(false);
                g.frame.getContentPane().remove(this);
                g.frame.add(new EndGame(g, score));
                g.frame.getContentPane().setVisible(true);
            }
            if(input.equals("submit")){
                
            }
        }
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Font title = new Font("Arial Black", Font.BOLD, 24);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(title);
        g2.drawString("SUDOKU", 5, 20);
    }
}
