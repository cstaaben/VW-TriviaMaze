package maze;

public class NullMazeDoor extends MazeDoor {
	
	private static final long serialVersionUID = 6143575408807179137L;

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
