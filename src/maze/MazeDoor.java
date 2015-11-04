package maze;

public class MazeDoor implements MazeObject {
	
	private String direction;
	
	public MazeDoor(String direction) {
		this.direction = direction;
	}
	
	public String getDirection() { return direction; }

	@Override
	public String display() {
		// Display question
		return "Question";
	}

}
