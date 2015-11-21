package maze;

public class MazeDoor implements MazeObject {
	
	private MazeDirection direction;
	private boolean open;
	private boolean locked;
	private MockQuestion question;
	
	public MazeDoor(MazeDirection direction) {
		this.direction = direction;
		this.question = new MockQuestion();
		this.open = false;
		this.locked = false;
	}
	
	public String getDirection() { return direction.toString(); }

	@Override
	public String display() {
		// Display question
		return question.display();
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
