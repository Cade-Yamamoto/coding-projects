import java.awt.Color;

//Made By Cade Yamamoto
public class Bullet {
	EZCircle bullet; //storage variables
	int startX;
	int startY;
	int damage;
	int projSpeed;
	Player player1;
	Player player2;
	EZSound gunSound;

	Bullet(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;

		bullet = EZ.addCircle(startX, startY, 10, 10, Color.BLACK, true); // Create bullet circle

	}

	// Section 3: Bullet control/attack sequence
	public void attack(char c) { // takes key as input
		if (player1.isHolding == true) { // if a player is holding a weapon...
			damage = player1.heldGunDamage;
			projSpeed = player1.heldGunProjSpeed;
			gunSound = EZ.addSound(player1.gunSound);
			shootGun(player1.getDirection()); // call on shoot function with the user's direction as input
			if (EZInteraction.wasKeyPressed(c)) { // if the user presses that key...
				player1.active = true; // weapon is now active
				gunSound.play(); // play sound of gun shot
			}

		}
	}

	public void resetBullet() {
		bullet.hide();
		bullet.translateTo(player1.getX(), player1.getY());
		player1.active = false; // weapon is no longer active
	}

	public void shootGun(String direction) { // takes player direction as input
		if (player1.active == false) { // if weapon is not active...
			player1.newDirection = direction; // current direction is stored as the direction player is facing
			startX = player1.getX(); // bullet start x is the player's x position
			startY = player1.getY();// bullet start y is the player's y position
		}

		if (player1.newDirection == "UP") { // if the stored direction is up...
			if (player1.active == true) { // if weapon is active...
				bullet.translateTo(startX, startY - 50);
				bullet.show();
				if (Map.isObjectTouching(bullet.getXCenter(), bullet.getYCenter())) { //if touching bullet
					resetBullet(); //reset bullet
					System.out.println("touching map");
				}
				if (player2.isObjectTouching(bullet.getXCenter(), bullet.getYCenter())) { //if player touching bullet
					resetBullet(); //reset
					player2.health -= damage; //damage check
					System.out.println("hit player" + "health is " + player2.health);
				}
				if (bullet.getYCenter() > 0) { // if bullet is not touching top of screen...
					bullet.translateTo(startX, startY); // move to startX, startY
					startY = startY - projSpeed; // decrement y-value by the projectile speed
				} else {
					resetBullet(); // call of function to reset bullet.

				}
			}
		}

		if (player1.newDirection == "DOWN") { // if stored direction is down...
			if (player1.active == true) { // if weapon is active...
				bullet.translateTo(startX, startY + 50); //move bullet
				bullet.show();
				if (Map.isObjectTouching(bullet.getXCenter(), bullet.getYCenter())) { //if bullet touching map
					resetBullet(); //reset bullet
					System.out.println("touching map");
				}
				if (player2.isObjectTouching(bullet.getXCenter(), bullet.getYCenter())) { //if bullet touching player
					resetBullet(); //reset bullet
					player2.health -= damage; //damage check
					System.out.println("hit player" + "health is " + player2.health); 
				}
				if (bullet.getYCenter() < EZ.getWindowHeight()) { // if bullet is not touching bottom of the
																	// screen...
					bullet.translateTo(startX, startY); // move to startX, startY
					startY = startY + projSpeed; // increment y-value by the projectile speed.
				} else {
					resetBullet(); // call on function to reset bullet
				}
			}
		}

		if (player1.newDirection == "LEFT") { // if stored direction is left...
			if (player1.active == true) { // if weapon is active...
				bullet.translateTo(startX - 50, startY); //move bullet
				bullet.show();

				if (Map.isObjectTouching(bullet.getXCenter(), bullet.getYCenter())) { //if bullet touching map
					resetBullet(); //reset bullet
					System.out.println("touching map");
				}

				if (player2.isObjectTouching(bullet.getXCenter(), bullet.getYCenter())) { //if bullet touching player
					resetBullet(); //reset bullet
					player2.health -= damage; //damage check
					System.out.println("hit player" + "health is " + player2.health);
				}
				if (bullet.getXCenter() > 0) { // if bullet is not touching left side of screen...
					bullet.translateTo(startX, startY); // move to startX, startY
					startX = startX - projSpeed; // decrement x-value by the projectile speed
				} else {
					resetBullet(); // call on function to reset bullet.
				}
			}
		}

		if (player1.newDirection == "RIGHT") { // if stored direction is right...
			if (player1.active == true) { // if weapon is active...
				bullet.translateTo(startX + 50, startY);
				bullet.show();

				if (Map.isObjectTouching(bullet.getXCenter(), bullet.getYCenter())) { //if bullet touching map
					resetBullet(); //reset bullet
					System.out.println("touching map");
				}
				if (player2.isObjectTouching(bullet.getXCenter(), bullet.getYCenter())) { //if bullet touching player
					resetBullet(); //reset bullet
					player2.health -= damage; //damage check
					System.out.println("hit player" + "health is " + player2.health);
				}
				if (bullet.getXCenter() < EZ.getWindowWidth()) { // if bullet is not touching right side of the
																	// screen...
					bullet.translateTo(startX, startY); // move to startX, startY
					startX = startX + projSpeed; // increment x-value by the projectile speed.
				} else {
					resetBullet(); // call on function to reset bullet.
				}
			}
		}

	}

	// End of section 3
	
	//initialization function
	public void go() {
		attack(player1.shoot);
	}
}
