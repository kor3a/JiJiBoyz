/***************************************************************
 * 
 * file: HighScores.java
 * author: Team Ji-Jo
 * class: CS 245 - Programming Graphical User Interfaces
 *
 * assignment: Hangman V1.0
 * date last modified: 10/10/17
 *
 * purpose: This class will displays a High Score page.
 *
 ****************************************************************/

package hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class HighScores extends JPanel implements ActionListener{

    //Array to store all the High Scores
    private JLabel[] hsArray = new JLabel[5]; 
    
    //Create Back Button
    private JButton backBttn = new JButton("Back");
    private GameIntro gameStart;
    
    //game score from the rest of the games
    private int score;


    //Constructor
    public HighScores(GameIntro gameStrt){
        
        this.gameStart = gameStrt;
        
        //iterate through the High Scores Array
        for(int i = 0; i <hsArray.length; i++){
            
            hsArray[i] = new JLabel(" ABC ... 000");
            
        }

        //Creates the "High Scores" title
        JLabel highS = new JLabel("High Scores");
        
        //Container for empty lines
        Container cont1 = new Container();

        //list format
        cont1.setLayout(new BoxLayout(cont1,BoxLayout.PAGE_AXIS));

        //Empty Space for visual purposes
        cont1.add(Box.createRigidArea(new Dimension(0,50)));
        cont1.add(highS);

        //Empty Space for visual purposes
        cont1.add(Box.createRigidArea(new Dimension(0,40)));


        //Add all the High Scores
        for(int i = 0 ; i < hsArray.length; i++) {
            
            cont1.add(hsArray[i]);
        }

        //Fonts
        highS.setFont(new Font("Arial", Font.BOLD, 32));
        
        for(int i = 0; i < hsArray.length; i++) {
            
            hsArray[i].setFont(new Font("Arial", Font.BOLD, 16));
        }

        //waits for mouse click on back
        backBttn.addActionListener(this);

        //Empty Space for visual purposes
        cont1.add(Box.createRigidArea(new Dimension(0,40)));
        cont1.add(backBttn);

        this.add(cont1);
        
    }

    HighScores(GameIntro g, int score) {
        this(g);
        this.score = score;
    }

    //override back to menu
    @Override
    public void actionPerformed(ActionEvent e) {
        
        Object o = e.getSource();
        JButton b = null;
        String buttnTxt = "";

        if(o instanceof JButton) {
            
            b = (JButton) o;
        }

        if(b != null){
            
            if(b.getText().equalsIgnoreCase("back")){
                
                gameStart.frame.getContentPane().setVisible(false);
                gameStart.frame.getContentPane().remove(this);
                gameStart.frame.add(new GameMenu(gameStart));
                gameStart.frame.getContentPane().setVisible(true);
                
            }
        }
    }

}
