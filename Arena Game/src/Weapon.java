import java.util.Random;
import java.awt.Color;

//created by Cade Yamamoto
public class Weapon {
	int x = 0;
	int y = 0;
	Player[] player = new Player[2];
	EZSound pickGun = EZ.addSound("pickupak.wav");
	EZSound dropSound = EZ.addSound("drop.wav");
	EZImage weaponImg; // Image of weapon
	String filename;

	Weapon(Player[] player, String filename) { // constructor for weapon
		this.player[0] = player[0];
		this.player[1] = player[1];
		this.filename = filename;
		addWeapon(filename); // calls on add weapon function to add a weapon onto the map.
	}

	// Section 1: Add weapon to Map
	public void addWeapon(String fileName) { // Takes weapon image filename as input
		randomLocation(EZ.getWindowWidth() - 20, EZ.getWindowHeight() - 20); // calls on random location function to
																				// find a random x and y.
		weaponImg = EZ.addImage(fileName, x, y); // adds image at random location.

		weaponImg.scaleBy(0.15); // make images smaller
		weaponImg.pullToFront(); // pull to front
	}

	public void randomLocation(int locX, int locY) { // Takes x and y bounds as input and finds random x and y.
		Random randNum = new Random(); // new random generator
		x = randNum.nextInt(locX); // x is a random number up to x
		y = randNum.nextInt(locY); // y is a random number up to y
		if (player[0].playerImg.isPointInElement(x, y) || player[1].playerImg.isPointInElement(x, y)) { // player is
																										// there...
			randomLocation(locX, locY); // recursively find a new location.
		}
		if (Map.isObjectTouching(x, y) == true) { // if the weapon is touching a wall...
			randomLocation(locX, locY); // find a new weapon location
			System.out.println("finding new location...");
		}
	}
	// End of section 1

	// Section 2: Control weapon pick up/drop
	public void drop(int index) { // takes given key as input
		if (EZInteraction.wasKeyPressed(player[index].qKey)) { // if user presses that key...
			if (player[index].isHolding == true) { // if the player is holding a weapon...
				weaponImg.translateTo(player[index].getX(), player[index].getY()); // Move to weapon to player's //
																					// position
				weaponImg.show(); // show the weapon
				dropSound.play(); // play the sound of the weapon dropping
				player[index].isHolding = false; // player is no longer holding this weapon.

			} else {
				System.out.println("not holding");
			}
		}
	}

	public void pickUp(int index) { // takes key as input
		if (EZInteraction.wasKeyPressed(player[index].eKey)) { // if user presses that key...
			if (player[index].isHolding == false && weaponImg.isShowing) { // if use is not holding a weapon and the
																			// weapon is showing
				int a = player[index].getX(); // gets player's x position
				int b = player[index].getY(); // get player's y position
				if (weaponImg.isPointInElement(a, b)) { // if the weapon is at the player's position...
					weaponImg.hide(); // hide the weapon
					player[index].isHolding = true; // player is holding the weapon
					player[index].changeStats(filename);
					pickGun.play(); // play pickup sound
					System.out.println(weaponImg.getXCenter());
					System.out.println(player[index].isHolding);
				}
			}
		}
	}
	// End of section 2

	// Initialization Function
	public void go() { 
		for (int i = 0; i < 2; i++) {
			pickUp(i);
			drop(i);

		}
	}

}
