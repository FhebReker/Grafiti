package brickbronze;

import java.awt.*;
import hsa2.*;


/*
NEED TO ADD:
1. 	Add a menu that will add difficulty levels to the program, makes the paddle smaller and the ball faster.
2. 	Add a graphic if hit subtracts points
3. 	Have a level 2 enabled – which then is more difficult – ball faster, paddle smaller.
*/

public class AnimatorZone {
	public static void main(String[] args) {
		new AnimatorZone();
	}
	
	/***** Constants *****/
	static final int SLEEPTIME = 10;
	static final int GRWIDTH = 800;
	static final int GRHEIGHT = 600;
	static final Color PADDLECOLOUR = Color.YELLOW; //so it's really easy to find and change when needed
	static int NUMBLOCKS = 10; //set the number of blocks here
	private static int moveAmount = 7;

	
	/***** Global (instance) Variables ******/
	private GraphicsConsole gc = new GraphicsConsole (GRWIDTH, GRHEIGHT);
	private Ball ball = new Ball(GRWIDTH, 300);
	private Rectangle paddle = new Rectangle(0,0,100,16); //set width and height here
	private Bricks[] blocks = new Bricks[NUMBLOCKS]; //this just makes the array, it doesn't create the blocks!
	private int lives, score;
	private boolean isPlaying = true;
	private char challengeLevel = '0';
	
	/****** Constructor ********/
	private AnimatorZone() {
		initialize();
		//main game loop
		
		
		
		while (gc.getKeyCode() != 'Q' && isPlaying) { //press q to quit
			movePaddle_mouse();
			//movePaddle_keys();
			moveBall();
			drawGraphics();
			gc.sleep(SLEEPTIME);
			if (lives <= 0) isPlaying = false;
			
			//check if all of the blocks are gone.
			boolean win = true;
			for (int i=0; i < NUMBLOCKS; i++) {
				if (blocks[i].isVisible) win = false;
			}
			if (win) isPlaying = false;
		}
	
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
	ball.resetXY(); // This is totally unnecessary unless you restart and need to reset the ball position and speed.
	
	
	gc.setColor(Color.cyan);
	gc.drawString("Welcome!", 100, 50);
	gc.drawString("Would you like a challenge?", 100, 100);
	gc.drawString("If you do, listen close.", 100, 150);
	gc.drawString("If you want to play normally, do not press anything.", 100, 400);
	gc.drawString("If you want a light challenge, hold the 1 key", 100, 450);
	gc.drawString("If you want a gruelling challenge, hold the 2 key", 100, 500);
	gc.sleep(3000);
	challengeLevel = gc.getKeyChar();
	Challenger(challengeLevel);
	gc.clear();
	
	//make all blocks. ** I"m only making one row of 6 blocks. You can figure out how to make more.
	if(NUMBLOCKS != 10) {
		blocks = new Bricks[NUMBLOCKS];
	}
	for (int i=0; i < NUMBLOCKS; i++) { //instead of NUMBLOCKS I could use blocks.length
		blocks[i] = new Bricks();
		blocks[i].x = 120*i+30;
		blocks[i].y = 60;
		
		if ((blocks[i].x + blocks[i].width) >= GRWIDTH) {
			for (int j = blocks[i].x + blocks[i].width; j > 800; j --)
			blocks[i].x -= GRWIDTH;
			blocks[i].y += 60;
			System.out.println("Subtracted!");
		}
		System.out.println((blocks[i].x + blocks[i].width));
		System.out.println((blocks[i].y));
		System.out.println();
	}
	gc.sleep(500); // allow a bit of time for the user to move the mouse to the correct position in the game screen
	}
	
	public void Challenger(char difficulty) {
		if (difficulty == '1') {
			paddle.width -= 25;
			ball.xspeed *= 2;
			ball.yspeed *= 2;
			moveAmount *= 1.5;
			NUMBLOCKS *= 2;
		}
		else if (difficulty == '2') {
			paddle.width -= 50;
			ball.xspeed *= 2.5;
			ball.yspeed *= 2.5;
			moveAmount *= 3;
			NUMBLOCKS *= 3;
		}
	}
	
	/**
	* This method moves the ball and handles all collisions where the ball hits something.
	* Don't make a separate method to see if the paddle hits the ball.
	*/
	private void moveBall() {
		ball.x += ball.xspeed;
		ball.y += ball.yspeed;
		//bounce off bottom of screen
		if ((ball.y + ball.diameter) > gc.getDrawHeight()) {
			ball.yspeed *=-1;
			lives--;
			score -= 100;
			ball.colour = new Color(Color.HSBtoRGB((float)Math.random(), 1.0f, 1.0f));
			gc.drawString("HIT!", ball.x, gc.getDrawHeight() - 100);
			if (challengeLevel == '1')
			gc.sleep(SLEEPTIME + 25);
			else if(challengeLevel == '2')
				gc.sleep(SLEEPTIME + 50);
			else
				gc.sleep(SLEEPTIME + 25);
		}
		//right side of screen
		if ((ball.x + ball.diameter) > gc.getDrawWidth()) {
			ball.xspeed *=-1;
		}
		//top of screen
		if (ball.y < 0) {
			ball.yspeed *=-1;
			ball.yspeed++;
		}
		//left side of screen
		if (ball.x < 0) {
			ball.xspeed *=-1;
		}
		//check if ball hits paddle
		if (ball.intersects(paddle)) {
			if (ball.yspeed > 0 ) { //the ball must be moving downwards, not upwards
				ball.yspeed *=-1;
			}
		}
		//see if ball hits block
		for (int i=0; i < blocks.length; i++) {
			if (ball.intersects(blocks[i])) {
				blocks[i].isVisible = false; //don't bother drawing it on the screen
				blocks[i].y = -100; //move it off the screen
				ball.yspeed *= -1;
				score += 200;
			}
		}
	}
	
	private void movePaddle_mouse(){
		paddle.x = gc.getMouseX() - paddle.width/2;
	}
	private void movePaddle_keys(){
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
			gc.setColor(ball.colour);
			gc.fillOval(ball.x, ball.y, ball.width, ball.height);
			//draw the blocks
			for (int i=0; i < blocks.length; i++) {
				if (blocks[i].isVisible) {
					gc.setColor(blocks[i].colour);
					gc.fillRect(blocks[i].x, blocks[i].y, blocks[i].width, blocks[i].height);
				}
			}
			gc.setColor(PADDLECOLOUR);
			gc.fillRoundRect(paddle.x, paddle.y, paddle.width, paddle.height, 10,10);
			gc.drawString("Score: " + score, GRWIDTH / 8, 30);
		}
	}
}
