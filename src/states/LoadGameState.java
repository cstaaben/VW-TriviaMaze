/**
 * LoadGameState.java
 * Author: Corbin Staaben
 * Description: LoadGameState is a piece of the State pattern implemented to control game flow. It allows the user
 * to pick a file to load and load the necessary objects using the Java API's Serializable interface and resume
 * playing a maze at the point at which they saved it.
 */
package states;

import java.io.*;
import java.util.Arrays;

public class LoadGameState implements TriviaMazeState, Serializable {

	private TriviaMaze triviaMaze;
	private TriviaMaze loaded;
	private FileInputStream fis;
	private ObjectInputStream ois;

	private static final String LOADPATH = "./src/savedGames/";

	/**
	 * LoadGameState saves the reference of the TriviaMaze object that manages game flow.
	 * @param triviaMaze - the TriviaMaze object that instantiates any LoadGameState and contains all relevant
	 *                   game information.
	 */

	public LoadGameState(TriviaMaze triviaMaze) {
		this.triviaMaze = triviaMaze;
	}

	/**
	 * (description of loadGame) It then transitions the user straight into NavigateMazeState where the user can
	 * continue to navigate the maze.
	 */
	@Override
	public void loadGame() {
		File file = getFileChoice();

		if(!file.exists()) {
			triviaMaze.setState(triviaMaze.getMainMenuState());
			return;
		}
		else {
			String input = "";

			System.out.println("Would you like to load this game or delete it?\n" +
					"\t Type \"load\" or \"delete\".");
			input = TriviaMaze.KB.nextLine();

			if(input.equalsIgnoreCase("load")) {
				try {
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);

					loaded = (TriviaMaze)ois.readObject();
				}
				catch(FileNotFoundException fnfe) {
					System.out.println(fnfe.toString());
				}
				catch(IOException ioe) {
					System.out.println(ioe.toString());
				}
				catch(ClassNotFoundException cnfe) {
					System.out.println(cnfe.toString());
				}
				catch(Exception e) {
					System.out.println(e.toString());
				}

				triviaMaze.setMaze(loaded.getMaze());
				triviaMaze.setPlayer(loaded.getPlayer());

				System.out.println("Loaded Game!");

				triviaMaze.setState(triviaMaze.getNavigateMazeState());
			}
			else if(input.equalsIgnoreCase("delete")) {
				boolean result = file.delete();

				if(result) {
					System.out.println("File successfully deleted!");
					triviaMaze.setState(triviaMaze.getLoadGameState());
				}
				else {
					System.out.println("There was a problem deleting the file.");
					triviaMaze.setState(triviaMaze.getLoadGameState());
				}
			}
		}

	}

	/**
	 * getFileChoice() presents the user with a list of files and prompts them to choose one. It checks the input via a
	 * helper method, isValidFile(). All exceptions are handled within the method.
	 * @return returns the File object corresponding to the user's choice.
	 */
	private File getFileChoice() {
		String input = "";
		int choice = -1;
		File result = null;
		String[] files = new File(LOADPATH).list();
		Arrays.sort(files);

		if(files.length < 1) {
			System.out.println("No games to load.");
			return new File("");
		}

		while(!isValidFile(choice, files) && !input.equalsIgnoreCase("exit")) {
			System.out.println("Please select one of the following files to load (type \"exit\" to exit):");
			for(int i = 0; i < files.length; i++) {
				System.out.printf("%5d.) %s%n", (i + 1), files[i]);
			} // end for i

			try {
				input = TriviaMaze.KB.nextLine();
				if(!input.equalsIgnoreCase("exit")) {
					choice = Integer.parseInt(input) - 1;
					result = new File(LOADPATH + files[choice]);
				}
				else {
					result = new File("");
				}

				if(!isValidFile(choice, files) && !result.exists() && !input.equalsIgnoreCase("exit")) {
					throw new FileNotFoundException("Invalid file. Please try again.");
				}
			}
			catch(FileNotFoundException fnfe) {
				System.out.println(fnfe.getMessage());
			}
			catch(NumberFormatException nfe) {
				System.out.println("Invalid file choice. Please enter the number corresponding to the " +
						"file you'd like to load.");
			}
			catch(Exception e) {
				System.out.println("Invalid entry. Please try again.\n" + e.toString());
			}
		} // end while input

		return result;
	}

	/**
	 * isValidFile() uses the static binarySearch() method of Arrays to search for the given index in the given array
	 * of file names and returns the result.
	 * @param s - the index of the file the user has chosen from the array of file names
	 * @param files - the array of file names generated from the directory
	 * @return - true if the index exists in the array, false if (s < 0) or the index does not exist in the array
	 */
	private boolean isValidFile(int s, String[] files) {
		if(s < 0)
			return false;

		return Arrays.binarySearch(files, files[s]) >= 0;
	}

	/**
	 * All methods beyond this point are stubbed out as they are required by the TriviaMazeState interface
	 * and a part of the State pattern implementation. They are not, nor should they ever be, implemented here.
	 */

	@Override
	public void newGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void adminDB() {
		// TODO Auto-generated method stub

	}

	@Override
	public void navigateMaze() {
		// TODO Auto-generated method stub

	}

	@Override
	public void answerQuestion(String direction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mainMenu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitMaze() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void endGame() {
		// TODO Auto-generated method stub
	}

}
