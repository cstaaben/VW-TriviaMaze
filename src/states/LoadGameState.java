package states;

import java.io.*;
import java.util.Arrays;

public class LoadGameState implements TriviaMazeState {

	private TriviaMaze triviaMaze;
	private FileInputStream fis;
	private ObjectInputStream ois;

	private static final String LOADPATH = "./src/savedGames/";
	
	public LoadGameState(TriviaMaze triviaMaze) {
		this.triviaMaze = triviaMaze;
	}
	
	@Override
	public void newGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadGame() {/*
		File file = getFileChoice();
		TriviaMaze loaded = null;

		try {
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);

			loaded = (TriviaMaze)ois.readObject();
		}
		catch(FileNotFoundException fnfe) {
			System.out.println(fnfe.getMessage());
		}
		catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		catch(ClassNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

		triviaMaze.setPlayer(loaded.getPlayer());
		triviaMaze.setMaze(loaded.getMaze());*/
		System.out.println("Loaded Game!");
		triviaMaze.setState(triviaMaze.getNavigateMazeState());
	}

	private File getFileChoice() {
		String input = "";
		int choice = -1;
		File result = null;
		String[] files = new File(LOADPATH).list();
		Arrays.sort(files);

		while(!isValidFile(choice, files)) {
			System.out.println("Please select one of the following files to load (type \"exit\" to exit):");
			for(int i = 0; i < files.length; i++) {
				System.out.printf("%5d.) %s%n", (i + 1), files[i]);
			} // end for i

			try {
				input = TriviaMaze.KB.nextLine();
				if(!input.equalsIgnoreCase("exit"))
					choice = Integer.parseInt(input) - 1;

				result = new File(LOADPATH + input);
				if(!isValidFile(choice, files) && !result.exists()) {
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
				System.out.println("Invalid entry. Please try again.");
			}
		} // end while input

		return result;
	}

	private boolean isValidFile(int s, String[] files) {
		if(s < 0)
			return false;

		return Arrays.binarySearch(files, files[s]) >= 0;
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
