/***************************************************************
 * 
 * file: Credits.java
 * author: Team Ji-Jo
 * class: CS 245 - Programming Graphical User Interfaces
 *
 * assignment: Hangman V1.0
 * date last modified: 10/10/17
 *
 * purpose: This class is the credits page that will show the names and ID numbers of the team members.  
 *
 ****************************************************************/

package hangman;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;


public class Credits extends JPanel implements ActionListener
{
    //Variable to iniziate the entire Game.
    private GameIntro gameStart;
    
    //Back Buttton declaration.
    private JButton backBttn;

    //Constructor
    public Credits(GameIntro gameStrt)
    {
        
        this.gameStart = gameStrt;

        //Creates back button and waits for command.
        backBttn = new JButton("Back");
        backBttn.addActionListener(this);

        //Create Container
        Container container = new Container();

        //This will make the container for the credits. they will scroll from top-bottom
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

        //Moves the other container down so they wont interfere with eachother.
        container.add(Box.createRigidArea(new Dimension(0, 50)));
        
        //Creates the "Credits" title
        container.add(new JLabel("Credits"));

        //Moves the next label a little bit down below the "Credits" label
        container.add(Box.createRigidArea(new Dimension(0, 40)));

        //List all of the team members
        container.add(new JLabel("Subong Jeon, 010918466"));
        container.add(new JLabel("Ismail Abbas, 010173826"));
        container.add(new JLabel("Oscar Alcaraz, 009911733"));
        container.add(new JLabel("Josue Ruiz, 0099660457"));
        container.add(new JLabel("Justin Galloway, 009610068"));
        container.add(Box.createRigidArea(new Dimension(0, 50)));
        container.add(backBttn);

        //Adds the container to the panel
        this.add(container);
        
    }

    //override to go back to menu
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