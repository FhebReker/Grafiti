package ScreenKeeper;

import java.awt.*;
import hsa2.*;

public class Mandala{
	
	
	static final int SLEEPTIME = 100;
	static final int WindowWidth = 1200;
	static final int WindowHeight = 800;
	static final int changeTime = 25000;
	
	/***** Global (instance) Variables ******/
	private static GraphicsConsole gc = new GraphicsConsole (WindowWidth, WindowWidth);
	
	public static void MandalaDraw() {
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
	
}
