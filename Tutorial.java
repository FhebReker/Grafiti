package brickbronze;

import java.awt.*;
import hsa2.*;

public class Tutorial{
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		GraphicsConsole c = new GraphicsConsole(800,800);
			
		c.drawRoundRect(100, 20, 200, 400, 50, 25);
		Thread.sleep(1000);
		c.drawMapleLeaf(200, 100, 50,100);
		c.drawString("Hold W!", 50, 300);
		Thread.sleep(2000);
		char k = c.getKeyChar();
		while(true) {
			if (k == 'w')
				c.drawString("WOW", 200, 100);
			else
				c.drawString("NOT IT", 100, 200);
		}
	}
}
