package maze;

public class NullMazeRoom implements MazeObject {

	private MazeDirection validDirection;
	private MazeDoor door;
	
	public NullMazeRoom(MazeDirection validDirection) {
		this.validDirection = validDirection;
	}
	
	public void setDoor(MazeDoor door) { this.door = door; }
	public MazeDirection getValidDirection() { return this.validDirection; }
	public MazeDoor getDoor() { return this.door; }
	
	@Override
	public String display() {
		return "";
	}

}
