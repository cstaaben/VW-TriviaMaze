import maze.MazeDirection;

public class SnippetTests {

	public static void main(String[] args) {
		MazeDirection md = MazeDirection.NORTH;
		String direction;

		direction = md.toString().charAt(0) + md.toString().substring(1, md.toString().length()).toLowerCase();
		
		System.out.println(direction);
	}

}
