package maze;

public class MazeMediator {
	
	private static MazeMediator mediator = null;
	private MazeObject maze;
	
	private MazeMediator(MazeObject maze) {
		this.maze = maze;
	}
	
	public static MazeMediator createReference(MazeObject maze) {
		if(mediator == null) {
			mediator = new MazeMediator(maze);
		}
		
		return mediator;
	}
	
	public static MazeMediator getReference() { return mediator; }
	
	public int getMazeSize() {
		return ((Maze)maze).getSize();
	}

}
