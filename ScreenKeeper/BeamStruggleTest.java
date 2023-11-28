package ScreenKeeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BeamStruggleTest extends JFrame {
	private int cannonWidth = 100;
	private int cannonHeight = 100;	
	
    private int cannon1X = 50; // X position of character 1    
    private int[] cannon1XBase = {cannon1X, cannon1X+50, cannon1X+100};
    private int[] cannon1YBase = {425, 350, 425};
    private Polygon cannon1Base = new Polygon(cannon1XBase, cannon1YBase, 3); 
    private Rectangle beam1 = new Rectangle(cannon1X + 75, 340, 50, 20); // Beam 1 rectangle
   
    private int cannon2X = 650; // X position of character 2
    private int[] cannon2XBase = {cannon2X, cannon2X+50, cannon2X+100};
    private int[] cannon2YBase = {425, 350, 425};
    private Polygon cannon2 = new Polygon(cannon2XBase, cannon2YBase, 3); 
    private Rectangle beam2 = new Rectangle(cannon2X - 25, 340, 50, 20); // Beam 2 rectangle

    private boolean beamsMeeting = false; // Flag to indicate if beams have met

    
    /*Curent Problems:
     	- Need to increase cannon size so its not so awkward
     	- Need to increase beam impact size when moving left/right
     	- Need to move beam impact left or right
     	- 
     * */
    
    
    public BeamStruggleTest() {
        setTitle("Beam Struggle Animation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (!beamsMeeting) {
                        // Move the beams towards each other
                        beam2.x -= 16;

                        // Update the width of the beams as they move
                        beam1.width += 16;
                        beam2.width += 16;
                        
                        // increasing height of beams to show power
                        beam1.height+=2;
                        beam2.height+=2;
                        beam1.y-=1;
                        beam2.y-=1;

                        // Check if beams have met
                        if (beam1.intersects(beam2)) {
                            beamsMeeting = true;
                        }
                    }
                    
                    /*else {
                    	int leftOrRight = (int)(Math.random()*2) + 1;
                    	System.out.println(leftOrRight);
                    	if(leftOrRight == 1) {
                    		beam1.width += 32;
                    		beam2.width -= 32;
                    		beam2.x -= 32;
                    		
                            beam1.height+=2;
                            beam2.height-=2;
                            beam1.y-=1;
                            beam2.y+=1;
                    	}
                    	if(leftOrRight == 2) {
                    		beam1.width -= 32;
                    		beam2.width += 32;
                    		beam2.x += 32;
                    		
                            beam1.height-=2;
                            beam2.height+=2;
                            beam1.y+=1;
                            beam2.y-=1;                    		
                    	}
                    }*/

                    repaint(); // Update the animation
                }
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw beam 1
        g.setColor(Color.ORANGE);
        g.fillRect(beam1.x, beam1.y, beam1.width, beam1.height);

        // Draw beam 2
        g.fillRect(beam2.x, beam2.y, beam2.width, beam2.height);
        
        // Draw character 1
        g.setColor(Color.RED);
        g.fillOval(cannon1X, 300, cannonWidth, cannonHeight);
        g.setColor(Color.cyan);
        g.fillPolygon(cannon1Base);
        
        // Draw character 2
        g.setColor(Color.BLUE);
        g.fillOval(cannon2X, 300, cannonWidth, cannonHeight);
        g.setColor(Color.ORANGE);
        g.fillPolygon(cannon2);
        
        if (beamsMeeting) {
        	g.setColor(Color.CYAN);
        	g.fillOval(beam2.x - 75, beam1.y + (int)(beam1.height/2) - 75, 150, 150);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BeamStruggleTest animation = new BeamStruggleTest();
            animation.setVisible(true);
        });
    }
}
