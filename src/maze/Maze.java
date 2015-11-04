package maze;

public class Maze implements MazeObject {
	
	public static final int MAX_SIZE = 10;
	private int size;
	private MazeRoom[][] maze;
	
	public Maze(int size) {
		this.size = size;
	}
	
	public int getSize() { return this.size; }

	public String display() {
		String result = "";
		
		for(int i = 0; i < 5; i++) {
			for(int j = i; j == i; j++) {
				result += maze[i][j].display();
			}
		}
		
		return result;
	}

}
