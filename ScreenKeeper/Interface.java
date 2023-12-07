package ScreenKeeper;

import java.awt.*;
import hsa2.*;

public class Interface{
	/***** Constants *****/
	static final int SLEEPTIME = 100;
	static final int WindowWidth = 1200;
	static final int WindowHeight = 800;
	
	private static boolean keepPlaying = true;
	private static GraphicsConsole gc = new GraphicsConsole (WindowWidth, WindowWidth);
	
	private static void startScreen() {
		gc.setAntiAlias(true);
		gc.setFont(new Font("Comic Sans", Font.PLAIN, 20));
		gc.setBackgroundColor(Color.CYAN);
		gc.clear();
		gc.drawString("Welcome to The Screensaver Experience", WindowWidth / 20, WindowHeight/2-350);
		gc.drawString("There are 3 screensavers in total:", WindowWidth/20, (WindowHeight / 2) - 300);
		gc.drawString("Flower Mandalas, Cannon Beam Blast, Ball Accelerator", WindowWidth/20, (WindowHeight / 2) - 250);
		gc.drawString("Hope you enjoy this presentation!", WindowWidth/20, (WindowHeight / 2));
		gc.drawString("Starting in 5 seconds...", WindowWidth/20, (WindowHeight) - 300);
		gc.sleep(5000);
	} 
	public static void main (String[] args) {
		startScreen();
		int looperIndex = 0;
		while(keepPlaying) {
			switch(looperIndex) {
				case 0:
					Mandala.mandalaMaker(gc);
					break;
				case 1:
					BeamStruggle.beamStruggle(gc);
					break;
				case 2:
					BallAccelerator.Accelerate(gc);
					break;
				case 3:
					looperIndex = -1;	break;
			}
			looperIndex++;
			gc.sleep(1000);
			gc.clear();
		}
	}
}

class Mandala{
	static final int SLEEPTIME = 100;
	static final int WindowWidth = 1200;
	static final int WindowHeight = 800;
	
	public static void mandalaMaker(GraphicsConsole gc) {		
		gc.clear();
		int x,y, count = 3;
		
		y = WindowHeight/2;
		x = WindowWidth/4; 
		for(int i = 0; i<5; i++) {
			floralDraw(x*i, WindowHeight, count, Color.magenta, gc);
			floralDraw(x*i, y, count, Color.magenta, gc);
			floralDraw(x*i, 0, count, Color.magenta, gc);
		}
		
		count+= 3;
		y = WindowHeight/4;
		x = WindowWidth/3; 
		for(int i = 0; i<4; i++) {
			floralDraw(x*i, y, count, Color.ORANGE, gc);
			floralDraw(x*i, 3*y, count, Color.ORANGE, gc);
		}
		
		count+= 5;
		y = WindowHeight/2;
		x = WindowWidth/6; 
		floralDraw(x, y, count, Color.red, gc);
		floralDraw(x*5, y, count, Color.red, gc);

		count+= 2;
		x = WindowWidth/2; 
		floralDraw(x, y, count, Color.GREEN, gc);
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
	private static void floralDraw(int centerX, int centerY, int numCircles, Color firstRingColor, GraphicsConsole gc) {
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
}

class BeamStruggle{
	
	static final int WindowWidth = 1200;
	static final int WindowHeight = 800;
	
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
	
    public static void beamStruggle(GraphicsConsole gc) {		   		 
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
    		beamDraw(gc);
		 }
	}
    private static void beamDraw(GraphicsConsole gc) {
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
}

class BallAccelerator{
	static final int WindowWidth = 1200;
	static final int WindowHeight = 800;
	
	private static int ballX, ballY; // Initial x and y position of the ball
	private static int dx, dy; // Velocity of the logo
	private static int ballRadius; // Width of the logo
	private static int speedIncrease;
	private static int backgroundRGB;
	
	private static Color[] colours = {new Color(0, 89, 83), new Color(228, 116, 32), new Color(90, 64, 111), new Color(110, 230, 167), new Color(110, 106, 167)};
	private static int currentColor = 0;
	
	private static Line[] lines;
	private static int lineIndex; // Index to track the next available position in the array
	    
	public static void Accelerate(GraphicsConsole gc) {
		ballX = WindowWidth / 2 - 200; // Initial x position of the ball
		ballY = WindowHeight / 2 - 200; // Initial y position of the ball
		ballRadius = 225; // Width of the logo
		backgroundRGB = 225;
		currentColor = 0;
		lines = new Line[200];
		lineIndex = 0; // Index to track the next available position in the array
		 
		gc.setBackgroundColor(new Color(backgroundRGB, backgroundRGB, backgroundRGB));
		dx = dy = 1;
		speedIncrease = 5;
		while (true) {
			ballX += dx * speedIncrease;
			ballY += dy * speedIncrease;
			
	        if (ballX <= 0)	
	        	collision(ballX, ballY, ballX, ballY + (ballRadius/2), colours[currentColor], true, gc); // Create a vertical line at the collision	      
	        else if (ballX >= WindowWidth - ballRadius)	
	        	collision(ballX, ballY, ballX + ballRadius, ballY + (ballRadius/2), colours[currentColor], true, gc); // Create a vertical line at the collision	       
	        
	        if (ballY <= 0)	
	        	collision(ballX, ballY, ballX + (ballRadius/2), ballY, colours[currentColor], false, gc); // Create a horizontal line at the collision	       
	        else if (ballY >= WindowHeight - ballRadius)	
	        	collision(ballX, ballY, ballX + (ballRadius / 2), ballY + ballRadius, colours[currentColor], false, gc); // Create a horizontal line at the collision
	                
	        drawer(gc);
	        gc.sleep(50);	
	        
	    	if(lines[lines.length - 1] != null) {
	    		gc.setBackgroundColor(Color.black);
	    		gc.clear();
	            break;
	        }
		}
	} 
	private static void drawer(GraphicsConsole gc) {
		gc.clear();
	    for (int i = 0; i < lines.length; i++) {        	
	        if (lines[i] != null) {
	        	lines[i].x1 = ballX +(ballRadius/2);
	        	lines[i].y1 = ballY + (ballRadius/2);
	            gc.setColor(lines[i].color);
	            gc.drawLine(lines[i].x1, lines[i].y1, lines[i].x2, lines[i].y2);
	        }
	    }
	    gc.setColor(colours[currentColor]);
	    gc.fillOval(ballX, ballY, ballRadius, ballRadius);
	}
	private static void collision(int x1, int y1, int x2, int y2, Color color, boolean heightOrWidthBounding, GraphicsConsole gc) {
		if (heightOrWidthBounding)	dx = -dx;
		else dy = -dy;
		speedIncrease++;
	    currentColor = (currentColor + 1) % colours.length;
	    ballRadius--;
	    backgroundRGB--;
		gc.setBackgroundColor(new Color(backgroundRGB, backgroundRGB, backgroundRGB));
		
	    lines[lineIndex] = new Line(x1, y1, x2, y2, color);
	    lineIndex = (lineIndex + 1) % lines.length; // Move to the next position, wrapping around if necessary
	}
}

class Line {
int x1, y1, x2, y2;
Color color;
public Line(int x1, int y1, int x2, int y2, Color color) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
    this.color = color;
}
}
