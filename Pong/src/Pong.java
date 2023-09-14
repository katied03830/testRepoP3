import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Pong extends Frame {

	// variable for the ball
	int xBall = 0;
	int yBall = 0;
	int widthBall = 50; // we can change this later
	Color ballColor = new Color(255, 255, 0);

	// movement variables
	int movementY = 3; // control ball movement in Y-dir
	int movementX = 4; // control ball movement in X-dir

	// variable for the left paddle
	int xPaddleLeft = 50;
	int yPaddleLeft = 50;
	int paddleWidth = 20;
	int paddleHeight = 80;
	Color paddleColor = new Color(192, 192, 192);

	// variable for the right paddle
	int xPaddleRight = 700; // change later
	int yPaddleRight = 50;

	/*
	 * SCORING VARIABLES
	 */
	int playerScore1 = 0;
	int playerScore2 = 0;

	/* paint is getting called roughly 60x per second */
	public void paint(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 600);

		// drawing TEXT on the frame
		Font f = new Font("serif", Font.PLAIN, 30);
		g.setFont(f);
		g.setColor(Color.white);
		g.drawString("HHEHEHE", xBall, yBall);
		g.drawString("" + playerScore1, 105, 70);
		g.drawString("" + playerScore2, 650, 70);
		g.drawString("Player 1 Score:", 40, 30);
		g.drawString("Player 2 Score:", 580, 30);

		// paint the pong ball
		g.setColor(ballColor);
		g.fillOval(xBall, yBall, widthBall, widthBall);

		// paint left paddle
		g.setColor(paddleColor);
		g.fillRect(xPaddleLeft, yPaddleLeft, paddleWidth, paddleHeight);

		// paint right paddle
		g.setColor(paddleColor);
		g.fillRect(xPaddleRight, yPaddleRight, paddleWidth, paddleHeight);

		// code for updating the POSITION of the ball object
		xBall += movementX; // same as bass x ballx
		yBall += movementY;

		/*
		 * if statements (conditional statements) aka blocking statements if (conditions
		 * { run if condition is true }
		 */

		// teleport the ball to the middle ^^
		// if it hits the side//this is a reset of the game
		// since the player missed

		if (xBall >= 732) { //// switch position and score if hit right side
			// what to do if ball reaches right side of screen?
			// switch direction of movementX!
			playerScore1++;
			xBall = 366;
			yBall = 255;

			// update the speed and direction to be randomized
			// to add more variability
			// random # formula:
			// int randValue = (int)(Math.random()*(range+1)) + min;
			// where range is max - min, and max is the largest val
			// and min is the lowest val

			// [3 5]
			movementX = (int) (Math.random() * ((5 - 3) + 1)) + 3;
			if (Math.random() < .50) {
				// this is gonna happen 50% of the time
				movementX *= -1; // so 50% change of switching direction
			}
		}
		if (xBall <= 0) { //// switch position and score if hit left side
			playerScore2++;
			xBall = 366;
			yBall = 255;
			// [3 5]
			movementX = (int) (Math.random() * ((5 - 3) + 1)) + 3;
			if (Math.random() < .50) {
				// this is gonna happen 50% of the time
				movementX *= -1; // so 50% change of switching direction
			}

		}

		// write the same block of code for moving in the Y
		if (yBall > 510) {
			// switch direction in the y
			movementY *= -1;
		}
		if (yBall < 0) {
			movementY *= -1;
		}

		// ball hits left paddle
		if (xBall <= (xPaddleLeft + paddleWidth) && (yBall >= yPaddleLeft && yBall <= (yPaddleLeft + paddleHeight))) {
			movementX *= -1;
			xBall = xPaddleLeft + paddleWidth + 1;

		}

		// if ball hits right paddle
		if (((xBall + widthBall) >= xPaddleRight)
				&& (yBall >= yPaddleRight && yBall <= (yPaddleRight + paddleHeight))) {
			movementX *= -1;
			xBall = (xPaddleRight - widthBall) - 1;
			System.out.println("here");
		}

	}

	public void keyPressed(KeyEvent arg0) {
		System.out.println(arg0.getKeyCode());

		if (arg0.getKeyCode() == 83) {// 's' key
			// your vars might be slightly different
			yPaddleLeft += 50; // left_paddle y position
			if (yPaddleLeft > 510) {
				yPaddleLeft = 510;
			}

		}

		// check keycode 87 for 'w' key for left paddle
		if (arg0.getKeyCode() == 87) {
			yPaddleLeft -= 50;
			if (yPaddleLeft < 0) {
				yPaddleLeft = 0;
			}
		}
		// down arrow key for right paddle
		if (arg0.getKeyCode() == 40) {
			yPaddleRight += 50;
			if (yPaddleRight > 510) {
				yPaddleRight = 510;
			}
		}
		// up arrow key for right paddle
		if (arg0.getKeyCode() == 38) {
			yPaddleRight -= 50;
			if (yPaddleRight < 0) {
				yPaddleRight = 0;
			}
		}

	}

	public void keyReleased(KeyEvent arg0) {

	}

	public void keyTyped(KeyEvent arg0) {

	}

	public static void main(String[] arg) {
		Pong p = new Pong();
	}

}
