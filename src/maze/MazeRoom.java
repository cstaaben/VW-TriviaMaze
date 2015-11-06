package maze;

public class MazeRoom implements MazeObject {
	
	private final int NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;
	private final int NUM_DOORS = 4;
	private int availDoors;
	private MazeObject[] doors;
	
	private MazeMediator mediator;
	
	public MazeRoom(int row, int col) {
		this.doors = new MazeDoor[NUM_DOORS];
		
		this.availDoors = 0;
		
		initializeDoors(row, col);
		
		mediator = MazeMediator.getReference();
	}
	
	public int getDoorNum() { return this.availDoors; }
	
	public void initializeDoors(int row, int col) {
		
		if(row == 0) {
			this.doors[NORTH] = new NullMazeDoor();
			this.doors[SOUTH] = new MazeDoor("South");
			this.availDoors++;
		}
		else if(row == mediator.getMazeSize()-1) {
			this.doors[SOUTH] = new NullMazeDoor();
			this.doors[NORTH] = new MazeDoor("North");
			this.availDoors++;
		}
		else {
			this.doors[NORTH] = new MazeDoor("North");
			this.doors[SOUTH] = new MazeDoor("South");
			this.availDoors += 2;
		}
		
		if(col == mediator.getMazeSize()-1) {
			this.doors[EAST] = new NullMazeDoor();
			this.doors[WEST] = new MazeDoor("West");
			this.availDoors++;
		}
		else if(col == 0) {
			this.doors[WEST] = new NullMazeDoor();
			this.doors[EAST] = new MazeDoor("East");
			this.availDoors++;
		}
		else {
			this.doors[EAST] = new MazeDoor("East");
			this.doors[WEST] = new MazeDoor("West");
			this.availDoors += 2;
		}
	}

	@Override
	public String display() {
		String result = "There are " + availDoors + " doors. You can move: \n";
		
		for(int i = 0; i < NUM_DOORS; i++) {
			if(!((MazeDoor)doors[i]).getDirection().equalsIgnoreCase("null")) {
				result += "\t-" + ((MazeDoor)doors[i]).getDirection() + "\n";
			}
		}
		
		return result;
	}

}
