package states;

import java.io.*;
import java.util.Scanner;

public class LoadGameState implements TriviaMazeState, Serializable {

	private TriviaMaze triviaMaze;
	private final String SAVE_PATH = "./src/savedGames/";
	private TriviaMaze game;
	
	public LoadGameState(TriviaMaze triviaMaze) {
		this.triviaMaze = triviaMaze;
	}
	
	@Override
	public void loadGame() {
		File f = getFile();
		//load file
		
		if(!f.exists()){
			triviaMaze.getMainMenuState();
		}
		try{
			FileInputStream f_in = new FileInputStream(f);
	    	ObjectInputStream obj_in = new ObjectInputStream (f_in);
	    	game=(TriviaMaze)obj_in.readObject();
	    	//obj_in.close();
		}
		catch(FileNotFoundException e){
			System.out.println(e);
		}
		catch(IOException ioe){
			//System.out.println(ioe);
		}
		catch(ClassNotFoundException cnfe){
			System.out.println(cnfe);
		}

		
		triviaMaze.setMaze(game.getMaze());
		triviaMaze.setPlayer(game.getPlayer());
		
		System.out.println("Your game was loaded successfully!");	
		
		triviaMaze.setState(triviaMaze.getNavigateMazeState());
	}
	
	private File getFile(){
		int fileCount = showFiles();
		File uFile = chooseFile(fileCount);
		return uFile;
	}
	private int showFiles(){
		File inFile = new File(SAVE_PATH);
		File[] files = inFile.listFiles();
		int count = 0;
		for(File f: files){
			count++;
			System.out.println(count+": "+f.getName());
			
		}
		
		return count;
		
	}
	
	private File chooseFile(int fc){
		Scanner kb = new Scanner(System.in);
		int choice = 0;
		File uFile;
		
		System.out.println("Which file would you like to load?");
		choice = kb.nextInt();
//		try{
			while(choice > fc && choice < 1){
					System.out.println("Invalid Selection, please try again");
				
				choice = kb.nextInt();
			}
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//		
		//choice represents the index of the file we want when it's in a file list like the one we created in showfile
		//recreate that list and grab the "choice" file
		
		uFile = grabFile(choice);
		return uFile;
		
	}
	
	private File grabFile(int n){
		File inFile = new File(SAVE_PATH);
		File[] files = inFile.listFiles();
		File uFile = new File("");
		
		uFile = files[n+1] ;
		
		return uFile;
		
	}
	
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
