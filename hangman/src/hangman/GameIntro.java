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
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class GameIntro {

    //Declare all the variables
    static JFrame frame;
    static IntroPanel panel;
    static Credits credits;
    static Credits c1;
    static GameMenu menu;
    static HighScores highscores;
    static EndGame scores;
    static ColorGame colorgame;
    static Play game;

    //Constructor
    public GameIntro() {

        frame = new JFrame("Ji-Ji Boyz");
        panel = new IntroPanel();
        highscores = new HighScores(this);
        game = new Play(this);
        menu = new GameMenu(this);
        credits = new Credits(this);
        c1 = new Credits(this);
        c1.removeBack();

        frame.setPreferredSize(new Dimension(600, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.add(panel);
        frame.setVisible(true);
        frame.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
               
                    if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
                        System.exit(0);
                    }
                    if(e.getKeyCode() == KeyEvent.VK_F1){
                        JFrame credit = new JFrame("Ji-Ji Boyz Credits");
                        credit.setPreferredSize(new Dimension(600, 400));
                        credit.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                        credit.setResizable(false);
                        credit.setLocationRelativeTo(null);
                        credit.pack();
                        credit.add(GameIntro.c1);
                        credit.setVisible(true);
                    }
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
             
            }

            
        });
        
        
        
       
        //Puts the frame into sleep for 3 (+1 delayed) seconds
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
        }
       
        frame.getContentPane().setVisible(false);
        frame.getContentPane().remove(panel);
        frame.add(menu);
        frame.getContentPane().setVisible(true);

    }
//      private static void gotocredits() {
//        frame.getContentPane().setVisible(false);
//        frame.getContentPane().remove(panel);
//        frame.add(credits);
//        frame.getContentPane().setVisible(true);
//      }

    //Call itself
    public static void main(String[] args) {
        new GameIntro();
    }
}
