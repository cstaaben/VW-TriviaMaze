import java.util.EnumMap;

import maze.MazeDirection;
import maze.MazeDoor;

public class SnippetTests {

	public static void main(String[] args) {
		EnumMap<MazeDirection, MazeDoor> map = new EnumMap<MazeDirection, MazeDoor>(MazeDirection.class);
		
		map.put(MazeDirection.NORTH, new MazeDoor());
		map.put(MazeDirection.SOUTH, new MazeDoor());
		map.put(MazeDirection.EAST, new MazeDoor());
		map.put(MazeDirection.WEST, new MazeDoor());
		
		System.out.println(map.get(MazeDirection.NORTH));
		System.out.println(map.get(MazeDirection.SOUTH));
		System.out.println(map.get(MazeDirection.EAST));
		System.out.println(map.get(MazeDirection.WEST));
	}

}
