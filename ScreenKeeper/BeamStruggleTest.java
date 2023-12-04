package ScreenKeeper;

import java.awt.*;
import hsa2.*;

public class BeamStruggle {
	private static int cannonWidth = 100;
	private static int cannonHeight = 100;
	private static int impactSize = 150;
	
    private static int cannon1X = 50; // X position of character 1    
    private static int[] cannon1XBase = {cannon1X, cannon1X+50, cannon1X+100};
    private static int[] cannon1YBase = {425, 350, 425};
    private static Polygon cannon1Base = new Polygon(cannon1XBase, cannon1YBase, 3); 
    private static Rectangle beam1 = new Rectangle(cannon1X + 75, 340, 50, 20); // Beam 1 rectangle
   
    private static int cannon2X = 650; // X position of character 2
    private static int[] cannon2XBase = {cannon2X, cannon2X+50, cannon2X+100};
    private static int[] cannon2YBase = {425, 350, 425};
    private static Polygon cannon2 = new Polygon(cannon2XBase, cannon2YBase, 3); 
    private static Rectangle beam2 = new Rectangle(cannon2X - 25, 340, 50, 20); // Beam 2 rectangle

    private static boolean beamsMeeting = false; // Flag to indicate if beams have met 
    
	static final int WindowWidth = 800;
	static final int WindowHeight = 600;
	private static GraphicsConsole gc = new GraphicsConsole (WindowWidth, WindowWidth);

	/* Things to work on:
	 	- Beam Impact needs to move by itself
	 * */
	
    public static void BeamBoom() {
		gc.setBackgroundColor(Color.black);
    	gc.clear();
    	pain();
    	while (true) {
    		gc.clear();
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
                        beam1.width -= 15;
                        beam2.x += 15;
                        beam2.width += 15;
                    }
                }
                
                else {
                	int leftOrRight = (int)(Math.random()*2) + 1;
                	if(leftOrRight == 1) {
                		beam1.width -= 8;
                		beam2.width += 8;
                		beam2.x -= 8;
                		
                        beam1.height+=2;
                        beam2.height-=2;
                        beam1.y-=1;
                        beam2.y+=1;
                        
                    	if((beam2.x - (impactSize / 2)) > 400)	impactSize-=2;
                    	else impactSize+=2;
                	}
                	if(leftOrRight == 2) {
                		beam1.width += 8;
                		beam2.width -= 8;
                		beam2.x += 8;
                		
                        beam1.height-=2;
                        beam2.height+=2;
                        beam1.y+=1;
                        beam2.y-=1;  
                        
                    	if((beam2.x - (impactSize / 2)) < 400)	impactSize-=2;
                    	else impactSize+=2;
                	}
                }
                pain(); // Update the animation                
                if (beam1.height <= 0 || beam2.height <= 0) break;                
    		gc.sleep(10);    		
    	}
    }
        
    public static void pain() {        
        // Draw beam 1
        gc.setColor(Color.ORANGE);
        gc.fillRect(beam1.x, beam1.y, beam1.width, beam1.height);

        // Draw beam 2
        gc.setColor(Color.ORANGE);
        gc.fillRect(beam2.x, beam2.y, beam2.width, beam2.height);
        
        // Draw character 1
        gc.setColor(new Color(243, 169, 3));
        gc.fillOval(cannon1X, 300, cannonWidth, cannonHeight);
        gc.setColor(new Color(41, 85, 220));
        gc.fillPolygon(cannon1Base);
        
        // Draw character 2
        gc.setColor(Color.BLUE);
        gc.fillOval(cannon2X, 300, cannonWidth, cannonHeight);
        gc.setColor(Color.ORANGE);
        gc.fillPolygon(cannon2);
        
        if (beamsMeeting) {
        	gc.setColor(Color.CYAN);
        	gc.fillOval(beam2.x - (impactSize / 2), beam1.y + (int)(beam1.height/2) - (impactSize/2), impactSize, impactSize);
        	//gc.fillStar(beam2.x - (impactSize / 2) - 25, beam1.y + (int)(beam1.height/2) - (impactSize/2) - 25, impactSize+50, impactSize+50);
        }
    }

    public static void main(String[] args) {
	    BeamBoom();
    }
}
