import java.awt.Color;
import java.io.FileReader;
import java.util.Scanner;
import java.io.IOException;

//Created by Levy-Jean Matsuda
public class Map {
	static int width; // declares int variable named width
	static int height; // declares int variable called height
	static int picSize; // declares int variable called picSize; equal to pixel size of picture used to
						// create wall
	static int wallCount = 0; // declares int to keep track of wallCount.
	static String mapName; // declares String variable called mapName
	static EZImage map[] = new EZImage[500]; // creates an array with size 500x500
	private static Scanner fileScan; // create a new scanner

	public Map(int picSize) { // constructor for map class.
		Map.picSize = picSize; // initialize picture size
	}

	public void makeMap() throws IOException {
		EZ.initialize(1000, 700); // creates first window
		startSequence(); //calls on function
		System.out.println(mapName);
		fileScan = new Scanner(new FileReader(mapName)); // create a new filereader
		width = fileScan.nextInt(); // sets value of width to int in text file
		height = fileScan.nextInt(); // sets value of height to next int in text file
		EZ.initialize(width * picSize, height * picSize); // creates window based on height,width, and picSize
		String inputTxt = fileScan.nextLine(); // declares a string variable named inputTxt

		for (int row = 0; row < height; row++) { // goes through each row
			inputTxt = fileScan.nextLine(); // sets value of inputTxt to next line in text file
			System.out.println(inputTxt);// print out inputTxt to check that it scans correctly
			for (int column = 0; column < width; column++) { // goes through each column
				char letter = inputTxt.charAt(column); // create a char called letter that is equal to the character at
														// given column
				switch (letter) {
				case 'G': // if there is a G there...
					map[wallCount] = EZ.addImage("grass.png", column * picSize + 25, row * picSize + 25); // add image
																											// to that
																											// slot
					wallCount++; // increment wall count

					break;
				default: // otherwise do nothing
					break;
				}
			}
		}
	}

	public void startSequence() {
		boolean noSelection = true; //boolean for selection
		//add images/text
		EZ.addText("DPCOMIC.TTF", 500, 50, "Prepare to Fight!!!", Color.RED, 50);
		EZ.addText("DPCOMIC.TTF", 500, 100, "Choose your map:", Color.RED, 50);

		EZImage map1 = EZ.addImage("map1.PNG", 200, 300);
		map1.scaleTo(0.3);
		EZImage map2 = EZ.addImage("map2.PNG", 500, 550);
		map2.scaleTo(0.3);
		EZImage map3 = EZ.addImage("map3.PNG", 800, 300);
		map3.scaleTo(0.3);
		while (noSelection) { //while user hasnt selected
			if (EZInteraction.wasMouseLeftButtonPressed()) { //if user clicks
				if (map1.isPointInElement(EZInteraction.getXMouse(), EZInteraction.getYMouse())) {
					System.out.println("clicked"); 
					mapName = "map.txt";
					noSelection = false; //if they click map1, load map 1
				}
				if (map2.isPointInElement(EZInteraction.getXMouse(), EZInteraction.getYMouse())) {
					System.out.println("clicked");
					mapName = "map2.txt"; //if they choose map2, load map 2
					noSelection = false;
				}
				if (map3.isPointInElement(EZInteraction.getXMouse(), EZInteraction.getYMouse())) {
					System.out.println("clicked");
					mapName = "map3.txt"; //if they choose map 3, load map 3
					noSelection = false;
				}

			}
			EZ.refreshScreen();

		}
		EZ.removeAllEZElements();
	}

	public static boolean isObjectTouching(int x, int y) { // takes x and y positions as input
		for (int i = 0; i < wallCount; i++) { // for each wall...

			if (map[i].isPointInElement(x, y)) { // if a wall is at the x and y position...
				System.out.println("touching!");
				return true; // isObjectTouching is true
			}
		}
		return false; // isObjectTouching is true
	}

}
