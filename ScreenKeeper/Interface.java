package ScreenKeeper;

import java.awt.*;
import hsa2.*;

public class Interface{
	/***** Constants *****/
	static final int SLEEPTIME = 100;
	static final int WindowWidth = 1200;
	static final int WindowHeight = 800;
	static final int changeTime = 25000;
	
	/***** Global (instance) Variables ******/
	private static boolean keepPlaying = true;
	private static GraphicsConsole gc = new GraphicsConsole (WindowWidth, WindowWidth);
	
	private static void startScreen() {
		gc.setAntiAlias(true);
		gc.setFont(new Font("Comic Sans", Font.PLAIN, 20));
		gc.setBackgroundColor(Color.CYAN);
		gc.clear();
		gc.drawString("Welcome to The Screensaver Experience", WindowWidth / 20, WindowHeight/2-350);
		gc.drawString("There are 3 screensavers in total:", WindowWidth/20, (WindowHeight / 2) - 300);
		gc.drawString("Mandalas, Cannon Beam Blast, INSERT WHATEVER THE THIS ONE IS HERE", WindowWidth/20, (WindowHeight / 2) - 250);
		gc.drawString("Hope you enjoy this presentation!", WindowWidth/20, (WindowHeight / 2));
		gc.drawString("Starting in 5 seconds...", WindowWidth/20, (WindowHeight) - 300);
		gc.sleep(5000);
	}
	
	// Mandala Maker
	public static void mandala() {		
		gc.clear();
		int x,y, count = 3;
		
		y = WindowHeight/2;
		x = WindowWidth/4; 
		for(int i = 0; i<5; i++) {
			floralDraw(x*i, WindowHeight, count, Color.magenta);
			floralDraw(x*i, y, count, Color.magenta);
			floralDraw(x*i, 0, count, Color.magenta);
		}
		
		count+= 3;
		y = WindowHeight/4;
		x = WindowWidth/3; 
		for(int i = 0; i<4; i++) {
			floralDraw(x*i, y, count, Color.ORANGE);
			floralDraw(x*i, 3*y, count, Color.ORANGE);
		}
		
		count+= 5;
		y = WindowHeight/2;
		x = WindowWidth/6; 
		floralDraw(x, y, count, Color.red);
		floralDraw(x*5, y, count, Color.red);

		count+= 2;
		x = WindowWidth/2; 
		floralDraw(x, y, count, Color.GREEN);
	}
	private static Color colorChange(Color previousColor, int randomIncrease) {
		Color newColor;
		switch(randomIncrease) {
			case 1:
				newColor = new Color(previousColor.getRed()-15, previousColor.getBlue(), previousColor.getGreen());
				break;
			case 2:
				newColor = new Color(previousColor.getRed(), previousColor.getBlue()-15, previousColor.getGreen());
				break;
			case 3:
				newColor = new Color(previousColor.getRed(), previousColor.getBlue(), previousColor.getGreen()-15);
				break;
			default:
				newColor = previousColor;		
				break;
		}
		return newColor;
	}
	private static void floralDraw(int centerX, int centerY, int numCircles, Color firstRingColor) {
		int x, y, 
		radius = 10 + 20*numCircles,
		numLines = 12,
		lineLength = 15*numCircles,
		randomColorChanger = (int)(3*Math.random())+1;
		double angle;
		Color innerCircleColor = new Color(225,225,225);
		
		
		for (int i = 0; i < numLines; i++) {
            gc.setColor(firstRingColor);
    		gc.sleep(SLEEPTIME);
            angle = Math.toRadians(360.0 / numLines * i);
            x = (int) (centerX + Math.cos(angle) * lineLength);
            y = (int) (centerY + Math.sin(angle) * lineLength);
            gc.fillOval(x-radius, y-radius, radius*2, radius*2);
        }
        for (int i = 0; i < numLines; i++) {
    		gc.sleep(SLEEPTIME/2);
            angle = Math.toRadians(360.0 / numLines * i);
        	x = (int) (centerX + Math.cos(angle) * lineLength);
            y = (int) (centerY + Math.sin(angle) * lineLength);
	        gc.setColor(Color.black);
	        gc.drawOval(x - radius, y - radius, radius * 2, radius * 2);
        }
        
        
        // Draw concentric circles
        for (int i = numCircles; i > 0; i--) {
    		gc.sleep(SLEEPTIME);
        	gc.setColor(innerCircleColor);
            gc.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
	        gc.setColor(Color.black);
            gc.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
            radius -= 20;
    		innerCircleColor = colorChange(innerCircleColor, randomColorChanger);
        }
	}
	
	// Beam Struggle
	private static int cannonWidth = 150;
	private static int cannonHeight = 150;
	private static int impactSize = 150;
    private static boolean beamsMeeting = false; // Flag to indicate if beams have met 
	
    private static int cannon1X = 100; // character 1    
    private static int[] cannon1XBase = {cannon1X, cannon1X+75, cannon1X+150};
    private static int[] cannon1YBase = {500, 400, 500};
    private static Polygon cannon1Base = new Polygon(cannon1XBase, cannon1YBase, 3); 
    private static Rectangle beam1 = new Rectangle(cannon1X + 75, 365, 50, 20);
   
    private static int cannon2X = WindowWidth-200; // character 2
    private static int[] cannon2XBase = {cannon2X, cannon2X+75, cannon2X+150};
    private static int[] cannon2YBase = {500, 400, 500};
    private static Polygon cannon2Base = new Polygon(cannon2XBase, cannon2YBase, 3); 
    private static Rectangle beam2 = new Rectangle(cannon2X - 25, 365, 50, 20);
	
    public static void beamStruggle() {		   		 
		 gc.setBackgroundColor(Color.black);
		 gc.clear();
		 resetBeams();
		 while (true) {
			 gc.clear();
			 if (!beamsMeeting) {
				 // Move the beams towards each other
				 beam2.x -= 32;

				 // Update the width of the beams as they move
				 beam1.width += 32;
				 beam2.width += 32;

				 // increasing height of beams to show power
				 beam1.height+=4;
				 beam2.height+=4;
				 beam1.y-=2;
				 beam2.y-=2;

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
					 beam1.width -= 12;
					 beam2.width += 12;
					 beam2.x -= 12;
		
					 beam1.height+=2;
					 beam2.height-=2;
					 beam1.y-=1;
					 beam2.y+=1;
        
					 if((beam2.x - (impactSize / 2)) > (WindowWidth / 2))	impactSize-=2;
					 else impactSize+=2;
				 }
				 if(leftOrRight == 2) {
					 beam1.width += 12;
					 beam2.width -= 12;
					 beam2.x += 12;
		
					 beam1.height-=2;
					 beam2.height+=2;
					 beam1.y+=1;
					 beam2.y-=1;  
        
					 if((beam2.x - (impactSize / 2)) < (WindowWidth / 2))	impactSize-=2;
					 else impactSize+=2;
				 }
			 }
		
			 if (beam1.height <= 0 || beam2.height <= 0)	break;
    		beamDraw();
		 }
	}
    private static void beamDraw() {
		 // Draw beam 1
		 gc.setColor(Color.MAGENTA);
		 gc.fillRect(beam1.x, beam1.y, beam1.width, beam1.height);

		 // Draw beam 2
		 gc.setColor(Color.WHITE);
		 gc.fillRect(beam2.x, beam2.y, beam2.width, beam2.height);

		 // Draw character 1
		 gc.setColor(new Color(243, 169, 3));
		 gc.fillOval(cannon1X, 300, cannonWidth, cannonHeight);
		 gc.setColor(new Color(24, 20, 99));
		 gc.fillPolygon(cannon1Base);

		 // Draw character 2
		 gc.setColor(new Color(199, 199, 109));
		 gc.fillOval(cannon2X, 300, cannonWidth, cannonHeight);
		 gc.setColor(new Color(6, 163, 104));
		 gc.fillPolygon(cannon2Base);

		 if (beamsMeeting) {
			 gc.setColor(Color.MAGENTA);
			 gc.fillStar(beam2.x - (impactSize / 2) - 55, beam1.y + (int)(beam1.height/2) - (impactSize/2) - 25, impactSize + 40, impactSize + 40);
			 gc.setColor(Color.WHITE);
			 gc.fillStar(beam2.x - (impactSize / 2) + 5, beam1.y + (int)(beam1.height/2) - (impactSize/2) - 25, impactSize + 40, impactSize + 40);
			 
			 // Draw character 1
			 gc.setColor(new Color(243, 169, 3));
			 gc.fillOval(cannon1X, 300, cannonWidth, cannonHeight);
			 gc.setColor(new Color(24, 20, 99));
			 gc.fillPolygon(cannon1Base);

			 // Draw character 2
			 gc.setColor(new Color(199, 199, 109));
			 gc.fillOval(cannon2X, 300, cannonWidth, cannonHeight);
			 gc.setColor(new Color(6, 163, 104));
			 gc.fillPolygon(cannon2Base);
			 
			 gc.setColor(Color.CYAN);
			 gc.fillOval(beam2.x - (impactSize / 2), beam1.y + (int)(beam1.height/2) - (impactSize/2), impactSize, impactSize);
		 }            
		 gc.sleep(20);
    }
    private static void resetBeams() {
    	impactSize = 150;
        beam1 = new Rectangle(cannon1X + 75, 365, 50, 20); // Beam 1 rectangle
        beam2 = new Rectangle(cannon2X - 25, 365, 50, 20); // Beam 2 rectangle
        beamsMeeting = false; // Flag to indicate if beams have met 
    }
	
    
    
	public static void main (String[] args) {
		//startScreen();
		int looperIndex = 0;
		while(keepPlaying) {
			switch(looperIndex) {
				case 0:
					//mandala();		break;
				case 1:
					//beamStruggle();	break;
				case 2:
				case 3:
					looperIndex = -1;	break;
			}
			looperIndex++;
			gc.sleep(1000);
			gc.clear();
		}
	}
}
