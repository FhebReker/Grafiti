package ScreenKeeper;

import java.awt.*;
import hsa2.*;

public class Interface {
	public static void main(String[] args) {
		new Interface();
	}
	
	/***** Constants *****/
	static final int SLEEPTIME = 10;
	static final int GRWIDTH = 800;
	static final int GRHEIGHT = 600;
	static final Color PADDLECOLOUR = Color.YELLOW; //so it's really easy to find and change when needed
	static final int NUMBLOCKS = 10; //set the number of blocks here
	
	/***** Global (instance) Variables ******/
	private GraphicsConsole gc = new GraphicsConsole (GRWIDTH, GRHEIGHT);
	private Rectangle paddle = new Rectangle(0,0,100,16); //set width and height here
	private int lives, score;
	private boolean isPlaying = true;
	
	/****** Constructor ********/
	private Interface() {
		initialize();
		//main game loop
	
		if (lives > 0)
		gc.drawString("GAME OVER, You win!", gc.getDrawWidth()/3, gc.getDrawHeight()/2);
		else
		gc.drawString("GAME OVER, You lost.", gc.getDrawWidth()/3, gc.getDrawHeight()/2);
	}
	
	/****** Methods for game *******/
	/* These are things that are only done once.
	* They should not be done over and over in a loop as they will either slow the program down or
	screw it up.
	* Putting all of the initialization in a separate method is useful because then it is really easy to
	restart the game.
	*/
	private void initialize() {
	//set up gc
	gc.setFont(new Font("Georgia", Font.PLAIN, 25));
	gc.setAntiAlias(true);
	gc.setBackgroundColor(Color.BLACK);
	gc.enableMouseMotion(); //only needed for mouse (obviously)
	gc.clear();
	
	//set up variables
	lives = 4;
	score = 0;
	isPlaying = true;
	//set up objects
	paddle.x = GRWIDTH/2;
	paddle.y = GRHEIGHT - 100;
	
	//make all blocks. ** I"m only making one row of 6 blocks. You can figure out how to make more.
	
	gc.sleep(500); // allow a bit of time for the user to move the mouse to the correct position in the game screen
	}
	
	/**
	* This method moves the ball and handles all collisions where the ball hits something.
	* Don't make a separate method to see if the paddle hits the ball.
	*/
	
	private void movePaddle_mouse(){
		paddle.x = gc.getMouseX() - paddle.width/2;
	}
	private void movePaddle_keys(){
		int moveAmount = 7;
		//37 and 39 are the keyboard codes for the left and right arrow keys.
		if (gc.getKeyCode() == 37) paddle.x -= moveAmount;
		if (gc.getKeyCode() == 39) paddle.x += moveAmount;
		//check to prevent moving the paddle off the screen
		if (paddle.x < 0) paddle.x = 0;
		//now you need to figure out how to to the same for the right side of the screen (I did the easy one!)
		if (paddle.x > 700) paddle.x = 700;
		//...
	}
	private void drawGraphics() {
		//clear screen and redraw everything
		synchronized(gc) {
			gc.clear();
			gc.setColor(Color.WHITE);
			gc.drawString("LIVES = " + lives, 500, 30);
			gc.setColor(PADDLECOLOUR);
			gc.fillRoundRect(paddle.x, paddle.y, paddle.width, paddle.height, 10,10);
			gc.drawString("Score: " + score, GRWIDTH / 8, 30);
		}
	}
}