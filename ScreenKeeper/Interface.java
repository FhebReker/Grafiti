package ScreenKeeper;

import java.awt.*;
import hsa2.*;

public class Interface{
	public static void main (String[] args) {
		startScreen();
		mandala();
	}
	/***** Constants *****/
	static final int SLEEPTIME = 100;
	static final int WindowWidth = 1200;
	static final int WindowHeight = 800;
	static final int changeTime = 25000;
	static boolean goBack = false, goForward = false;

	
	/***** Global (instance) Variables ******/
	private static GraphicsConsole gc = new GraphicsConsole (WindowWidth, WindowWidth);
	
	private static void startScreen() {
		gc.setAntiAlias(true);
		gc.setFont(new Font("Comic Sans", Font.PLAIN, 20));
		gc.setBackgroundColor(Color.CYAN);
		gc.clear();
		gc.drawString("Welcome to The Screensaver Experience", WindowWidth / 20, WindowHeight/2-400);
		gc.drawString("At any time, you may press 'd' or '→' to skip to the next screensaver", WindowWidth/20, (WindowHeight / 2) - 300);
		gc.drawString("Similarly, you may also press 'a' or '←' to return to the previous screensaver", WindowWidth/20, (WindowHeight / 2) - 200);
		gc.drawString("Lastly, if you would like to quit, press 'q' to return", WindowWidth/20, (WindowHeight / 2) - 100);
		gc.drawString("Hope you enjoy this presentation!", WindowWidth/20, (WindowHeight / 2));
		gc.drawString("Starting in 5 seconds...", WindowWidth/20, (WindowHeight) - 300);
		gc.sleep(5000);
	}
	private static void backAndForth() {
		if (gc.getKeyCode() == 37 || gc.getKeyChar() == 'a')
			goBack = true;
		if (gc.getKeyCode() == 39 || gc.getKeyChar() == 'd')
			goForward = true;
	}
	
	private static void mandala() {
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
