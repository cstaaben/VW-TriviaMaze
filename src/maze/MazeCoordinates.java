package maze;

public class MazeCoordinates implements MazeObject {
	
	private static final long serialVersionUID = 6341209524058039126L;
	private int row;
	private int column;
	
	public MazeCoordinates(int row, int col) {
		this.row = row;
		this.column = col;
	}
	
	public int getRow() { return row; }
	public int getCol() { return column; }
	
	public void setCoordinates(int row, int col) {			
		this.row = row;
		this.column = col;
	}
	
	public void setCoordinates(MazeCoordinates mc) {
		this.row = mc.getRow();
		this.column = mc.getCol();
	}
	
	public boolean equals(MazeCoordinates mc) {
		return mc.getRow() == row && mc.getCol() == column;
	}
	
	public boolean equals(int row, int col) {
		return this.row == row && col == column;
	}
	
	public String display() {
		return "(" + row + ", " + column + ")";
	}
}