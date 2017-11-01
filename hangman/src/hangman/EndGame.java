/**
 * *************************************************************
 *
 * file: EndGame.java
 * author: Team Ji-Jo
 * class: CS 245 - Programming Graphical User Interfaces
 *
 * assignment: Hangman V1.0
 * date last modified: 10/10/17
 *
 * purpose: This class displays the high score for the game
 *
 ***************************************************************
 */
package hangman;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * This class is responsible for displaying the score of what the player has as game progresses
 */
public class EndGame extends JPanel implements ActionListener {

    // This variable will contain the score for the player, updated as the game progresses
    private int score;
    // This variable will contain the main Game object
    private GameIntro game;
    // This variable will contain the JButton that allows the player(s) to go back from whatever screen they are on
    private JButton backButton;

    /*
    This constructor is the default constructor, initializing the Game object itself, and the score that wil be running for the game
     */
    public EndGame(GameIntro g, int s) {
        backButton = new JButton("End");
        backButton.addActionListener(this);
        this.game = g;
        this.score = s;

        //Create a new container
        Container c = new Container();
        c.setLayout(new BoxLayout(c, BoxLayout.PAGE_AXIS));
        c.add(Box.createRigidArea(new Dimension(0, 50)));

        //Create the title and add it to the box container
        c.add(new JLabel("The game has ended...now let's fire up Runescape!"));

        // Shifts the label under 'Credits'
        c.add(Box.createRigidArea(new Dimension(0, 40)));

        // Label of contributors
        c.add(new JLabel("Your score, as impressive as it is, was " + s));
        checkScores();
        c.add(backButton);

        // Adding containers to a panel is necessary
        this.add(c);
    }

    /*
     * This method will listen for any actions performed, taking in an input of an ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        JButton butt = null;

        if (o instanceof JButton) {
            butt = (JButton) o;
        }

        // If b doesn't equal null, then the back button has been pressed and we need to leaaaave
        if (butt != null) {
            if (butt.getText().equalsIgnoreCase("end")) {
                // The first step is to make the game frame not visible anymore
                game.frame.getContentPane().setVisible(false);
                // Removing it is the only way to do it, can't draw over it
                game.frame.getContentPane().remove(this);
                game.frame.add(new GameMenu(game));
                game.frame.getContentPane().setVisible(true);
            }
        }
    }

    private void checkScores() {
    
    }
}
