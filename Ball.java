package brickbronze;

import java.awt.*;

public class Ball extends Rectangle{
	//do not put superclass variables here
	int xspeed = 3;
	int yspeed = 3;
	int diameter = 30;
	Color colour = Color.RED;
	private int n1,n2;
	/**
	* The ball will be placed randomly within the rectangle specified by the parameters below.
	* The ball will be at least 75 pixels from the edge of this rectangle. (errr... only if maxX and maxY
	are > 150)
	* @param maxX The range of X values for the ball to be placed
	* @param maxY The range of Y values for the ball to be placed
	*/
	Ball(int maxX, int maxY) {
		width=height=diameter;
		x = (int)(Math.random() * (maxX-150))+75;
		y = (int)(Math.random() * (maxY-150))+75;
		// uncomment the following line if you want each ball to have a different random colour
		colour = new Color(Color.HSBtoRGB((float)Math.random(), 1.0f, 1.0f));
		n1 = maxX; n2 = maxY;
	}
	void resetXY() {
		x = (int)(Math.random() * (n1 - 150))+75;
		y = (int)(Math.random() * (n2 - 150))+75;
		xspeed = yspeed = 3;
		width=height=diameter;
	}
}
