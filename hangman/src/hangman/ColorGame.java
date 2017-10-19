/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;
import java.awt.*;
import static java.lang.Thread.sleep;
import java.util.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.lang.reflect.Field;
import javax.swing.*;
/**
 *
 * @author josue
 */
public class ColorGame extends JPanel implements ActionListener {
    
    private Random rng = new Random();
    private JLabel clockDisplay;
    private JLabel scoreDisplay;
    private JButton skip;
    private GameIntro g;
    private int score;
    private int counter;
    private boolean h_ball1;
    private boolean h_ball2;
    private boolean h_ball3;
    private boolean h_ball4;
    private boolean h_ball5;
    private  Ellipse2D ball1;
    private  Ellipse2D ball2;
    private  Ellipse2D ball3;
    private  Ellipse2D ball4;
    private  Ellipse2D ball5;
    private Graphics2D title;
    private Graphics2D g_ball1;
    private Graphics2D g_ball2;
    private Graphics2D g_ball3;
    private Graphics2D g_ball4;
    private Graphics2D g_ball5;
    private Graphics2D highlight;
    
    
    private final String[] COLORS = {"GREEN", "RED", "BLUE",
        "YELLOW", "MAGENTA"
    };
    private String currentColor;
    private String currentColorTitle;
    
    public ColorGame(GameIntro g, int score){
      this.g = g;
      this.score = score;
      randomizeColor();
      loadGUI();
      clock();
      counter = 5;
      
    }

    private void loadGUI() {
        setLayout(null);
        clockDisplay = new JLabel("");
        scoreDisplay = new JLabel("Score: "+score);
        skip = new JButton("Skip");
        skip.addActionListener(this);
        skip.setBounds(490, 10, 80, 30);
        clockDisplay.setBounds(280, 1, 200, 25);
        scoreDisplay.setBounds(200, 1, 200, 25);
        
        add(clockDisplay);
        add(scoreDisplay);
        add(skip);
        repaint();
       
        addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseMoved(MouseEvent e){
                h_ball1 = ball1.contains(e.getPoint());
                h_ball2 = ball2.contains(e.getPoint());
                h_ball3 = ball3.contains(e.getPoint());
                h_ball4 = ball4.contains(e.getPoint());
                h_ball5 = ball5.contains(e.getPoint());
                repaint();
                
            }
        });
         addMouseListener(new MouseAdapter(){
             @Override
            public void mouseClicked(MouseEvent e){
                
       if((e.getButton()== 1)&&counter == 0){
           exit();
        }
        if((e.getButton() == 1)&& (ball1.contains(e.getX(), e.getY())) && g_ball1.getColor() == title.getColor() ){
            randomizeColor();
            counter--;
            score += 100;
            scoreDisplay.setText("Score: " + Integer.toString(score));
        }
        else if((e.getButton() == 1)&& (ball2.contains(e.getX(), e.getY())) && g_ball2.getColor() == title.getColor() ){
            randomizeColor();
            counter--;
            score += 100;
            scoreDisplay.setText("Score: " + Integer.toString(score));
        }
        else if((e.getButton() == 1)&& (ball2.contains(e.getX(), e.getY())) && g_ball2.getColor() == title.getColor() ){
            randomizeColor();
            counter--;
            score += 100;
            scoreDisplay.setText("Score: " + Integer.toString(score));
        }
        else if((e.getButton() == 1)&& (ball3.contains(e.getX(), e.getY())) && g_ball3.getColor() == title.getColor() ){
            randomizeColor();
            counter--;
            score += 100;
            scoreDisplay.setText("Score: " + Integer.toString(score));
        }
        else if((e.getButton() == 1)&& (ball4.contains(e.getX(), e.getY())) && g_ball4.getColor() == title.getColor() ){
            randomizeColor();
            counter--;
            score += 100;
            scoreDisplay.setText("Score: " + Integer.toString(score));
        }
        else if((e.getButton() == 1)&& (ball5.contains(e.getX(), e.getY())) && g_ball5.getColor() == title.getColor() ){
            randomizeColor();
            counter--;
            score += 100;
            scoreDisplay.setText("Score: " + Integer.toString(score));
        }
        else if((e.getButton() == 1) && ((ball1.contains(e.getX(), e.getY())) || (ball2.contains(e.getX(), e.getY())) || (ball3.contains(e.getX(), e.getY()))
                || (ball4.contains(e.getX(), e.getY())) || (ball5.contains(e.getX(), e.getY())) )){
            randomizeColor();
            counter--;
            
            }
         
     }
       
            
         });   
        
    }
    public void exit(){
        g.frame.getContentPane().setVisible(false);
        g.frame.getContentPane().remove(this);
        g.frame.add(new HighScores(g, score));
        g.frame.getContentPane().setVisible(true);
            
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
                                + "         Date " + month + "/" + day + "/" + year);
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

        if (button != null) {
            String skip = button.getText().toLowerCase();

            if (skip.equals("skip")) {
                score = 0;
                g.frame.getContentPane().setVisible(false);
                g.frame.getContentPane().remove(this);
                g.frame.add(new HighScores(g, score));
                g.frame.getContentPane().setVisible(true);
            }
        }
    
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        title = (Graphics2D)g;
        Font titleFont = new Font("Arial Black", Font.BOLD, 20); 
        title.setFont(titleFont);
        title.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        title.setColor(getColorValue(currentColor));
        title.drawString(currentColorTitle, 280, 50);
        
        //ball 1
        g_ball1 = (Graphics2D) g;
        ball1 = new Ellipse2D.Double(15, 100, 100, 100);
        ballStats(ball1, g_ball1, "GREEN");
        //ball 2
        g_ball2 = (Graphics2D) g;
        ball2 = new Ellipse2D.Double(100, 200, 100, 100);
        ballStats(ball2, g_ball2, "RED");
        //ball 3
        g_ball3 = (Graphics2D) g;
        ball3 = new Ellipse2D.Double(250, 200, 100, 100);
        ballStats(ball3, g_ball3, "BLUE");
        //ball 4
        g_ball4 = (Graphics2D) g;
        ball4 = new Ellipse2D.Double(350, 100, 100, 100);
        ballStats(ball4, g_ball4, "YELLOW");
        //ball 5
        g_ball5 = (Graphics2D) g;
        ball5 = new Ellipse2D.Double(450, 180, 100, 100);
        ballStats(ball5, g_ball5, "MAGENTA");
       
        highlight = (Graphics2D) g;
        highlight.setColor(Color.BLACK);
        if(h_ball1){
            highlight.drawOval(15, 100, 102, 102); 
        }
        else if(h_ball2){
            highlight.drawOval(100, 200, 102, 102); ;
        }
        else if(h_ball3){
            highlight.drawOval(250, 200, 102, 102); ;
        }
        else if(h_ball4){
            highlight.drawOval(350, 100, 102, 102); ;
        }
        else if(h_ball5){
            highlight.drawOval(450, 180, 102, 102); ;
        }
        
        
    }
   private void ballStats (Ellipse2D shape, Graphics2D ball, String color){
       ball.setColor(getColorValue(color));
       ball.fill(shape);
        
   }
   private Color getColorValue(String color) {
        try{
           final Field f = Color.class.getField(color);
           return (Color)f.get(null);
       }catch(Exception e){
           return Color.BLACK;
       }
    }
   
   //makes sure they don't have the same color
    private void randomizeColor() {
      int first_num = rng.nextInt(5);
      int next_num = rng.nextInt(5);
      currentColor = COLORS[first_num];
      while(first_num == next_num){
          next_num = rng.nextInt(5);
      }
      currentColorTitle = COLORS[next_num];
    }
   
   
  
    
}
