/**
 * SaveGameState.java
 * Author: Corbin Staaben
 * Description: SaveGameState is a piece of the State pattern implemented to manage game flow. It allows the user to
 * save the game at the current point in the game using the Java API's Serializable interface.
 */
package states;

import java.io.*;
import java.util.Calendar;

public class SaveGameState implements TriviaMazeState, Serializable {

	private TriviaMaze triviaMaze;
	private transient FileOutputStream fos;
	private transient ObjectOutputStream oos;

	private static final String SAVEPATH = "./src/savedGames/"; // contains the path to the directory containing saved games

	/**
	 * SaveGameState saves the reference that manages game flow and all pertinent information for this "state" to function
	 * @param triviaMaze - the TriviaMaze object that contains, or holds references to, all the information necessary
	 *                   for the game, and this state, to function.
	 */

	public SaveGameState(TriviaMaze triviaMaze) {
		this.triviaMaze = triviaMaze;
	}

	/**
	 * (description of saveGame) It will then automatically transition the user into the main menu state of the program.
	 */
	@Override
	public void saveGame() {
		String filename = buildTimestamp();

		try {
			fos = new FileOutputStream(SAVEPATH + filename);
			oos = new ObjectOutputStream(fos);

			oos.writeObject(triviaMaze);
		}
		catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}

		System.out.println("Saved Game!");
		triviaMaze.setState(triviaMaze.getMainMenuState());
	}

	/**
	 * buildTimestamp() is a helper method to build a timestamp of the current time for use in naming saved game files
	 * @return - the String representation of the current timestamp (month-day-year_hour:minute:second).
	 */
	private String buildTimestamp() {
		String result = "";

		result += Calendar.getInstance().get(Calendar.MONTH)+1;
		result += "-" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		result += "-" + Calendar.getInstance().get(Calendar.YEAR);
		result += "_" + Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		result += "-" + Calendar.getInstance().get(Calendar.MINUTE);
		result += "-" + Calendar.getInstance().get(Calendar.SECOND);

		return result;
	}

	/**
	 * All methods beyond this point are stubbed out because they are a part of the TriviaMazeState interface.
	 * They are not necessary for this "state" to function and they are not, nor should they ever be, implemented here.
	 */

	@Override
	public void newGame() {}
	@Override
	public void loadGame() {}
	@Override
	public void adminDB() {}
	@Override
	public void navigateMaze() {}
	@Override
	public void answerQuestion(String direction) {}
	@Override
	public void mainMenu() {}
	@Override
	public void exitMaze() {}
	@Override
	public void endGame() {}

}
