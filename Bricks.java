package brickbronze;

import java.awt.*;

public class Bricks extends Rectangle {
	//this can be used for setting various types of blocks. (Unbreakable ones, ones that make the paddle smaller or larger, ones that give an extra ball...)
	//They could have different colours too.
	int type = 1;
	Color colour = new Color(255,222,111);
	//is the block displayed on the screen?
	boolean isVisible = true;
	//constructor. Set parameters for all blocks
	Bricks() {
		width = 100;
		height = 20;
	}
}
