package maze;

public enum MazeDirection {

	NORTH, EAST, SOUTH, WEST, NULL;
	
	public MazeDirection opposite() {
		if(this.equals(NORTH)) {
			return SOUTH;
		}
		else if(this.equals(SOUTH)) {
			return NORTH;
		}
		else if(this.equals(EAST)) {
			return WEST;
		}
		else if(this.equals(WEST)) {
			return EAST;
		}
		
		return NULL;
	}
}
