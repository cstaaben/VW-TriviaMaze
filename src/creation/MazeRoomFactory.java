package creation;

import maze.MazeRoom;

public class MazeRoomFactory {

	private static MazeRoomFactory reference = null;
	
	public static MazeRoomFactory getReference() {
		if(reference == null) {
			reference = new MazeRoomFactory();
		}
		
		return reference;
	}
	
	public MazeRoom getRoom(int row, int col) {
		return null;
	}
}
