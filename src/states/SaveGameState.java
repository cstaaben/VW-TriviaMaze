package states;

import java.io.*;
import java.util.*;

public class SaveGameState implements TriviaMazeState, Serializable{

	private TriviaMaze triviaMaze;
    private static final String SAVE_PATH = "./src/savedGames/";
	
	public SaveGameState(TriviaMaze triviaMaze) {
		this.triviaMaze = triviaMaze;
	}
	
	@Override
	public void saveGame() {
	 //Save each object that changes state to a data file that can be read in when the game is loaded
     //Enter a save name for the game, when we load we display all the save files located in the save folder
      
      String saveName = "";
      
      saveName = getSaveName();
      
      //send all required objects to serializable storage file
      // Write to disk with FileOutputStream
      try{
    	  FileOutputStream f_out = new FileOutputStream(SAVE_PATH+saveName+".data");

    	  // Write object with ObjectOutputStream
    	  ObjectOutputStream obj_out = new ObjectOutputStream (f_out);

    	  // Write object out to disk
    	  obj_out.writeObject (triviaMaze);
    	  //obj_out.close();
      }
      
      catch(FileNotFoundException e){
    	  System.out.println(e);
      }
      
      catch(IOException ioe){
    	  System.out.println(ioe);
      }
      
      System.out.println("Your game has been saved as "+saveName);
      
      triviaMaze.setState(triviaMaze.getMainMenuState());
	}
	
	private String getSaveName(){
		String temp = "";
		System.out.println("Name this game, and press enter");
	      
	      while (!triviaMaze.KB.hasNext("[A-Za-z]+")) {
	        System.out.println("Please use only alphabetical characters to name your game");
	        triviaMaze.KB.next();
	      }
	      temp = triviaMaze.KB.next();
	      return temp;
	}

	@Override
	public void newGame() {
		// TODO Auto-generated method stub

	}

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
