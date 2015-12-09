/**
 * DoorJamMain.java
 * Author: Corbin Staaben
 * Description: DoorJamMain provides the main method to execute the program.
 */
public class DoorJamMain {

	public static void main(String[] args) {
		DoorJam game;

		if(args.length > 0)
			game = new DoorJam(args[0]);
		else
			game = new DoorJam("");

		game.run();
	}
}
