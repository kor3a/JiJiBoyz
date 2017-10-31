/** *************************************************************
 *
 * file: GameIntro.java
 * author: Team Ji-Jo
 * class: CS 245 - Programming Graphical User Interfaces
 *
 * assignment: Hangman V1.0
 * date last modified: 10/10/17
 *
 * purpose: This class is the intro screen that displays the team name.
 *
 *************************************************************** */
package hangman;

import java.awt.*;
import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class GameIntro {

    //Declare all the variables
    static JFrame frame;
    IntroPanel panel;
    Credits credits;
    GameMenu menu;
    HighScores highscores;
    EndGame scores;
    ColorGame colorgame;
    Play game;

    //Constructor
    public GameIntro() {

        frame = new JFrame("Ji-Ji Boyz");
        panel = new IntroPanel();
        highscores = new HighScores(this);
        game = new Play(this);
        menu = new GameMenu(this);
        credits = new Credits(this);

        frame.setPreferredSize(new Dimension(600, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.add(panel);
        frame.setVisible(true);

        //Puts the frame into sleep for 3 (+1 delayed) seconds
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
        }
        frame.getContentPane().setVisible(false);
        frame.getContentPane().remove(panel);
        frame.add(menu);
        frame.getContentPane().setVisible(true);

    }

    //Call itself
    public static void main(String[] args) {
        new GameIntro();
    }
}
