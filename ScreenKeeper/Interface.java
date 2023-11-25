package ScreenKeeper;

import java.awt.*;
import hsa2.*;

public class Interface{
	public static void main (String[] args) {
		startScreen();
		
	}
	/***** Constants *****/
	static final int SLEEPTIME = 10;
	static final int WindowWidth = 1200;
	static final int WindowHeight = 1000;

	
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
		gc.draw3DRect(100, 100, 200, 100, true);
	}
}
