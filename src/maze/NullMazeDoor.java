package maze;

public class NullMazeDoor extends MazeDoor {
	
	public NullMazeDoor() {
	}
	
	@Override
	public boolean isLocked() { return true; }
	@Override
	public boolean isOpen() { return false; }
	
	@Override
	public String display() {
		return "null";
	}

}
