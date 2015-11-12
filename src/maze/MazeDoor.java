package maze;

public class MazeDoor implements MazeObject {
	
	private String direction;
	private boolean open;
	private boolean locked;
	//private Question question;
	
	public MazeDoor(String direction) {
		this.direction = direction;
	}
	
	public String getDirection() { return direction; }

	@Override
	public String display() {
		// Display question
		return "Question";
	}
	
	public boolean isOpen() { return open; }
	public boolean isLocked() { return locked; }
	
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	public boolean isCorrectAnswer(String input) {
		return input.toLowerCase().equals("a");
	}

}
