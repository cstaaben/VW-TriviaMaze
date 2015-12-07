/**
 * NewGameState.java
 * Author: Corbin Staaben
 * Description: NewGameState is a piece of the State pattern implemented to control game flow. It prompts the user
 * for the size of the square maze they wish to play in, creates that size square maze and passes it to the
 * TriviaMaze object that instantiates this state. It also creates a MazePlayer object to track the user's
 * information in the maze.
 */
package states;

import creation.MazeFactory;
import maze.MazePlayer;

import java.io.Serializable;

public class NewGameState implements TriviaMazeState, Serializable {

	private TriviaMaze triviaMaze;
	private MazeFactory mazeFactory;

	/**
	 * Constructor that assigns the reference to the TriviaMaze object that instantiates this state and gets a
	 * reference to the MazeFactory object
	 * @param triviaMaze - the TriviaMaze object that will be given the Maze and MazePlayer objects
	 */

	public NewGameState(TriviaMaze triviaMaze) {
		this.triviaMaze = triviaMaze;
		this.mazeFactory = MazeFactory.getReference();
	}

	/**
	 * Prompt the user to enter their desired size of maze to play through. All exceptions thrown by any
	 * user input is handled in the method.
	 * @return - the integer size the user entered
	 */
	private int sizePrompt() {
		int size = 0;
		
		while(size == 0) {
			System.out.print("Please enter a number between 2 and 10 for the size of the maze: ");
			
			try {
				size = Integer.parseInt(TriviaMaze.KB.nextLine());
				if(size < mazeFactory.MIN_SIZE || size > mazeFactory.MAX_SIZE) {
					throw new IndexOutOfBoundsException("Value entered is beyond specified range.");
				}
				System.out.println();
				break;
			}
			catch(NumberFormatException e) {
				System.out.println("\nValue entered is not an integer.");
				size = 0;
				continue;
			}
			catch(IndexOutOfBoundsException i) {
				System.out.println("\n" + i.getMessage());
				size = 0;
				continue;
			}
			catch(Exception e) {
				System.out.println("Invalid input: " + e.getMessage());
			}
		}
		
		return size;
	}

	/**
	 * Call method to prompt the user for size, create maze of that size and pass it to the TriviaMaze object.
	 * Pass a new MazePlayer object to TriviaMaze object, created with the Maze object's start coordinates
	 */
	@Override
	public void newGame() {
		triviaMaze.setMaze(mazeFactory.getMaze(sizePrompt()));
		
		triviaMaze.setPlayer(new MazePlayer(triviaMaze.getMaze().getStart()));

		triviaMaze.setState(triviaMaze.getNavigateMazeState());
	}

	/**
	 * All methods beyond this point are stubbed out as they are required by the TriviaMazeState interface
	 * and a part of the State pattern implementation. They are not, nor should they ever be, implemented here.
	 */

	@Override
	public void loadGame() {
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
