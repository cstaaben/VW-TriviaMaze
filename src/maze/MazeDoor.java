package maze;

public class MazeDoor implements MazeObject {
	
	private MazeDirection lesserDirection, greaterDirection;
	private boolean open;
	private boolean locked;
	private MockQuestion question;
	
	private MazeRoom lesserRoom, greaterRoom;
	
	public MazeDoor() {
		this.open = false;
		this.locked = false;
	}

	public MazeDirection getLesserDirection() { return lesserDirection; }
	public MazeDirection getGreaterDirection() { return greaterDirection; }
	public boolean isOpen() { return open; }
	public boolean isLocked() { return locked; }
	public MazeRoom getLesserRoom() { return lesserRoom; }
	public MazeRoom getGreaterRoom() { return greaterRoom; }
	
	public void setLesserDirection(MazeDirection lesserDirection) {
		this.lesserDirection = lesserDirection;
	}

	public void setGreaterDirection(MazeDirection greaterDirection) {
		this.greaterDirection = greaterDirection;
	}

	@Override
	public String display() {
		// Display question
		return question.display();
	}
	
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	public boolean isCorrectAnswer(String input) {
		return input.toLowerCase().equals(question.getAnswer().toLowerCase());
	}
	
	public void setLesserRoom(MazeRoom room) {
		this.lesserRoom = room;
	}
	
	public void setGreaterRoom(MazeRoom room) {
		this.greaterRoom = room;
	}
	
	public void setQuestion(MockQuestion question) {
		this.question = question;
	}
	
	public int getDoorPoints() {
		return this.question.getPoints();
	}
}
