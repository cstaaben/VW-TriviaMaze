package maze;

public class Maze implements MazeObject {
	
	public static final int MAX_SIZE = 10;
	private int size;
	private MazeObject[][] maze;
	
	public Maze(int size) {
		this.size = size;
		
		MazeMediator.createReference(this);
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
