package mazeTests;

import java.util.Scanner;

import maze.Maze;

public class MazeNavigationTest {
	
	public static final Scanner KB = new Scanner(System.in);
	
	public static void main(String[] args) {
		Maze maze = Maze.getMaze(10);
		
		maze.navigate();
	}

}
