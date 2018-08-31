
import java.io.IOException;
import java.util.Random;

//Created by Nathan Yung & Cade Yamamoto
public class Player {
	EZImage playerImg; // Image of player
	String fileName; // stores player image filename
	String direction; // stores direction player is facing
	int x; // stores x position
	int y; // stores y position
	char eKey;
	char qKey;
	int health; // stores health level
	int width;
	int height;
	int moveSpeed; // stores movement speed
	int heldGunDamage;
	int heldGunProjSpeed;
	char shoot; // stores char to shoot weapon
	char up, down, left, right; // stores chars to move up, down, left, and right
	boolean isHolding = false;
	boolean active = false;
	String newDirection = null; // Direction storage variable
	String gunSound;

	Player(String fileName, int x, int y, int health, int moveSpeed, char eKey, char qKey, char shoot, char up,
			char down, char left, char right) {
		// constructor for Player
		this.fileName = fileName; // stores filename string
		this.x = x; // stores x position
		this.y = y; // stores y position
		this.eKey = eKey;
		this.qKey = qKey;
		this.up = up; // stores char to move up
		this.down = down; // store char to move down
		this.left = left; // stores char to move left
		this.right = right; // stores char to move right
		this.shoot = shoot; // stores char to shoot weapon
		this.health = health; // stores int of player health level
		this.moveSpeed = moveSpeed; // stores int of player's movement speed
		randomLocation(EZ.getWindowWidth() - 20, EZ.getWindowHeight() - 20);
		playerImg = EZ.addImage(fileName, this.x, this.y); // adds image of player
		playerImg.scaleBy(0.4); // scale player image
		width = Map.picSize - 10;
		height = Map.picSize - 10;
	}

	public String getDirection() { // return player's direction as string
		return direction;
	}

	public int getX() {
		return playerImg.getXCenter(); // return player's x position
	}

	public int getY() {
		return playerImg.getYCenter(); // return player's y position
	}

	public void move() {
		if (EZInteraction.isKeyDown(up)) { // if up key is being pressed...
			y -= moveSpeed; // decrement y by move speed
			direction = "UP"; // direction is now up
			if (Map.isObjectTouching(x, y - height) == true) {
				y += moveSpeed;

			}
		}
		if (EZInteraction.isKeyDown(left)) { // if left key is being pressed...
			x -= moveSpeed; // decrement x by move speed
			direction = "LEFT"; // direction is now left
			if (Map.isObjectTouching(x - width, y) == true) {
				x += moveSpeed;
			}
		}
		if (EZInteraction.isKeyDown(down)) { // if down key is being pressed...
			y += moveSpeed; // increment y by move speed
			direction = "DOWN"; // direction is now down
			if (Map.isObjectTouching(x, y + height) == true) {
				y -= moveSpeed;
			}
		}
		if (EZInteraction.isKeyDown(right)) { // if right key is being pressed...
			x += moveSpeed; // increment x by move speed
			direction = "RIGHT"; // direction is now right
			if (Map.isObjectTouching(x + width, y) == true) {
				x -= moveSpeed;
			}
		}
		playerImg.translateTo(x, y); // move player image to x,y position

	}

	public void randomLocation(int locX, int locY) { // Takes x and y bounds as input and finds random x and y.
		Random randNum = new Random(); // new random generator
		x = randNum.nextInt(locX); // x is a random number up to x
		y = randNum.nextInt(locY); // y is a random number up to y
		if (Map.isObjectTouching(x, y) == true) { // if the weapon is touching a wall...
			randomLocation(locX, locY); // find a new weapon location
			System.out.println("finding new location...");
		}
	}

	public boolean isObjectTouching(int x, int y) { // takes x and y positions as input
		if (playerImg.isPointInElement(x, y)) { // if an element is at the x and y position...
			System.out.println("touching!");
			return true; // isObjectTouching is true
		}
		return false; // isObjectTouching is false
	}

	public void changeStats(String weaponType) {
		if (weaponType == "ak47.png") { //if type ak47
			heldGunDamage = 8; //set stats
			heldGunProjSpeed = 20;
			gunSound = "akshot.wav";
		}
		if (weaponType == "uzi.png") { //if type uzi
			heldGunDamage = 3; //set stats
			heldGunProjSpeed = 40;
			gunSound = "uzishot.wav";
		}
	}

	public void go() {
		move(); // call on move function to move player
		EZ.refreshScreen(); // refresh screen
	}
}
