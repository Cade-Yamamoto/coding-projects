
import java.util.Scanner;
import java.awt.Color;
import java.io.FileReader;
import java.io.IOException;

//Created by Levy/Cade

public class MainClass {
	static Weapon[] ak47 = new Weapon[5]; // creates a new weapon object array called ak47
	static Weapon[] uzi = new Weapon[4]; //creates a new weapon object array called uzi
	static Player[] player = new Player[2]; // creates a new Player array called player
	static Bullet[] bullet = new Bullet[2];// creates a new Bullet array called bullet
	static Map gameMap = new Map(50); // Creates map object with picSize of 50 (based on wall size)
	static String winner, loser; //win/loss strings (used at end)
	static EZSound idleMusic = EZ.addSound("megalovania.wav"); //idle music
	public static void main(String[] args) throws IOException { //Main
		gameMap.makeMap();
		player[0] = new Player("frogRed.png", 150, 150, 100, 3, 'e', 'q', 'f', 'w', 's', 'a', 'd'); // initialize //
																										// first player
		player[1] = new Player("frogBlue.png", 350, 150, 100, 3, 'o', 'u', 'p', 'i', 'k', 'j', 'l'); // initialize
		
																								// second player
		for (int i = 0; i < ak47.length; i++) { //for each element in ak47 array...
			ak47[i] = new Weapon(player, "ak47.png"); // create an ak47
		}
		for (int i = 0; i < uzi.length; i++) { //for each element in uzi array...
			uzi[i] = new Weapon(player, "uzi.png"); // create one uzi
		}

		bullet[0] = new Bullet(player[0], player[1]);
		bullet[1] = new Bullet(player[1], player[0]);
		idleMusic.loop();
		while (player[0].health > 0 && player[1].health > 0) { // while the player has health
			player[0].go(); // call on go function for player.
			player[1].go(); // call on go function for player 2.
			bullet[0].go();
			bullet[1].go();
			for (int i = 0; i < ak47.length; i++) { //initialize ak47
				ak47[i].go();
			}
			for (int i = 0; i < uzi.length; i++) { //initialize uzis
				uzi[i].go();
			}

		}

		if (player[0].health <= 0) {
			winner = "Player 2";
			loser = "Player 1";
		}
		if (player[1].health <= 0) {
			winner = "Player 1";
			loser = "Player 2";
		}
		idleMusic.stop();
		EZ.removeAllEZElements();
		EZ.addText("DPCOMIC.TTF", 500, 100, winner + " has  been defeated by " + loser + "!!!", Color.BLUE, 50);
		EZSound winSound = EZ.addSound("victory_fanfare.wav");
		winSound.play();
	}
}
