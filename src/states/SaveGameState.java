package states;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;

public class SaveGameState implements TriviaMazeState {

	private TriviaMaze triviaMaze;
	private transient FileOutputStream fos;
	private transient ObjectOutputStream oos;

	private static final String SAVEPATH = "./src/savedGames/";
	
	public SaveGameState(TriviaMaze triviaMaze) {
		this.triviaMaze = triviaMaze;
	}

	@Override
	public void saveGame() {/*
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
*/
		System.out.println("Saved Game!");
		triviaMaze.setState(triviaMaze.getMainMenuState());
	}

	private String buildTimestamp() {
		String result = "";

		result += Calendar.getInstance().get(Calendar.MONTH)+1;
		result += "-" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		result += "-" + Calendar.getInstance().get(Calendar.YEAR);
		result += "_" + Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		result += ":" + Calendar.getInstance().get(Calendar.MINUTE);
		result += ":" + Calendar.getInstance().get(Calendar.SECOND);

		return result;
	}

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
