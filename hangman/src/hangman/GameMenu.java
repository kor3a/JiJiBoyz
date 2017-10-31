/**
 * *************************************************************
 *
 * file: GameMenu.java
 * author: Team Ji-Jo
 * class: CS 245 - Programming Graphical User Interfaces
 *
 * assignment: Hangman V1.0
 * date last modified: 10/10/17
 *
 * purpose: This class is the game menu, displays the players options (Play, High Scores & Credits).
 *
 ***************************************************************
 */
package hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * This class is responsible for displaying the main 'Hangman' menu
 */
public class GameMenu extends JPanel implements ActionListener {

    // This variable will contain the play button
    private JButton playButton;
    // This variable will contain the highScoreButton
    private JButton highScoreButton;
    // This variable will hold the credits button
    private JButton creditsButton;
    // This variable will hold the actual Game object
    private GameIntro game;
    // This varible will hold the high score object, a combination of multiple high scores
    private HighScores hs;
    //GameBackground
    //ImageIcon arcade = new ImageIcon("resources/background.jpg");
    Image arcade = Toolkit.getDefaultToolkit().createImage("resources/Background.png");

    // This variable will hold the letter choice the player makes
    String choice = "";

    /*
     * This constructor is the default constructor, and will serve to pull all the resources together and create
     * the listeners for the buttons
     */
    //Constructor that initializes buttons and creates listeners for the buttons
    public GameMenu(GameIntro game) {
        this.game = game;
        playButton = new JButton("Play");
        highScoreButton = new JButton("Highscores");
        creditsButton = new JButton("Credits");
        playButton.addActionListener(this);
        highScoreButton.addActionListener(this);
        creditsButton.addActionListener(this);
        setLayout(null);
        playButton.setBounds(500, 210, 100, 40);
        highScoreButton.setBounds(500, 250, 100, 40);
        creditsButton.setBounds(500, 290, 100, 40);
        add(playButton);
        add(highScoreButton);
        add(creditsButton);
//        repaint();
        setBackground(Color.lightGray);
      
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ob = e.getSource();
        JButton butt = null;
        String buttonText = "";

        if (ob instanceof JButton) {
            butt = (JButton) ob;
        }

        if (butt != null) {
            //If "play" is pressed, begin Hangman
            if (butt.getText().equalsIgnoreCase("play")) {

                game.frame.getContentPane().setVisible(false);
                game.frame.getContentPane().remove(this);
                game.frame.add(new Play(game));
                game.frame.getContentPane().setVisible(true);
            }
            //If "highscores" is pressed, display highscores
            if (butt.getText().equalsIgnoreCase("highscores")) {
                game.frame.getContentPane().setVisible(false);
                game.frame.getContentPane().remove(this);
                game.frame.add(game.highscores);
                game.frame.getContentPane().setVisible(true);
            }
            //If "credits" is pressed, display credits
            if (butt.getText().equalsIgnoreCase("credits")) {
                game.frame.getContentPane().setVisible(false);
                game.frame.getContentPane().remove(this);
                game.frame.add(new Credits(game));
                game.frame.getContentPane().setVisible(true);
            }
        }
    }
    
    public final void escapeListener() {
         getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
         KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel");
         getRootPane().getActionMap().put("Cancel", new AbstractAction() {
         @Override
         public void actionPerformed(ActionEvent e) {
            System.exit(0);
            }
         });
     }
    
//    @Override
//    public void paintComponent (Graphics g) {
//        super.paintComponent(g);
//            g.drawImage(arcade, 0, 0, this);
//        
//    }
}
