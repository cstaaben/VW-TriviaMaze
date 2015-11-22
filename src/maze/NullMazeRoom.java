package maze;

public class NullMazeRoom implements MazeObject {

	private MazeDoor door;
	
	public void setDoor(MazeDoor door) { 
		this.door = door;
		
		door.setLocked(true);
	}
	
	public MazeDoor getDoor() { return this.door; }
	
	@Override
	public String display() {
		return "";
	}

}
