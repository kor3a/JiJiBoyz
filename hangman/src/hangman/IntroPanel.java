/***************************************************************
 * 
 * file: IntroPanel.java
 * author: Team Ji-Jo
 * class: CS 245 - Programming Graphical User Interfaces
 *
 * assignment: Hangman V1.0
 * date last modified: 10/10/17
 *
 * purpose: This will display the Start Screen.
 *
 ****************************************************************/
package hangman;

import static hangman.GameIntro.panel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


class IntroPanel extends JPanel {

    //Get the Dimensions of the Panel
    private int rectangleXAxis = this.getWidth()/2;
    
    //Get the Dimensions of the Panel
    private int rectangleYAxis = this.getHeight()/2;
    
    //Get the Dimensions of the Panel
    private int rectangleWidth = 32;
    
    //Get the Dimensions of the Panel
    private int rectangleHeight = 32;

    
    public IntroPanel() {

        setBorder(BorderFactory.createLineBorder(Color.blue));

        addMouseListener(new MouseAdapter() {
            
            @Override
            public void mousePressed(MouseEvent mouseE) {
                
                moveSquare(mouseE.getX(),mouseE.getY());
            }
        });
        
        addMouseMotionListener(new MouseAdapter() {
            
            @Override
            public void mouseDragged(MouseEvent mouseE) {
                
                moveSquare(mouseE.getX(),mouseE.getY());
            }
        });
//        this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0),"forward");
//        this.getActionMap().put("forward", new AbstractAction(){
//        @Override
//        public void actionPerformed(ActionEvent e){
//            JFrame credit = new JFrame("Ji-Ji Boyz Credits");
//            credit.setPreferredSize(new Dimension(600, 400));
//            credit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            credit.setResizable(false);
//            credit.setLocationRelativeTo(null);
//            credit.pack();
//            credit.getContentPane().setVisible(false);
//            credit.getContentPane().remove(panel);
//            credit.add(GameIntro.credits);
//            credit.getContentPane().setVisible(true);
//        }
//        });

    }

    //Set the Size of the Entire Panel. Dimensions were given by the instructor.
    public Dimension getPreferredSize() {
        
        return new Dimension(600, 400);
        
    }

    //Method that Draws the Panel
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        Font fontTittle = new Font("Arial Black", Font.BOLD + Font.ITALIC, 21); 
        Font fontTeam = new Font("Arial Black", Font.BOLD + Font.ITALIC, 18);
        
        Graphics2D graphic2 = (Graphics2D)g;
        
        graphic2.setFont(fontTittle);
        graphic2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        
        //Draw the Project Title
        graphic2.drawString("CS 245 Hangman Project", this.getWidth()/4,50);
        
        graphic2.setFont(fontTeam);
        //Draw the Team Title
        graphic2.drawString("Team Ji-Jo",this.getWidth()-350,this.getHeight()-32);

        g.setColor(Color.red);
        g.fillRect(rectangleXAxis,rectangleYAxis,rectangleWidth,rectangleHeight);

    }

    //move the cursor
    public void moveSquare(int x, int y){ //move square if mouse clicks
        
        int OFFSET = 1;

        if (rectangleXAxis != x || rectangleYAxis != y){
            
            repaint(rectangleXAxis,rectangleYAxis,rectangleWidth+OFFSET,rectangleHeight + OFFSET);
            
            rectangleXAxis = x;
            rectangleYAxis = y;
            
            repaint(rectangleXAxis,rectangleYAxis,rectangleWidth+OFFSET,rectangleHeight + OFFSET);
            
        }
    }
}
