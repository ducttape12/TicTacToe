package tictactoe;

public class Cell {
	private Mark mark;
	
	public Cell() {
		mark = Mark.NONE;
	}
	
	public void setMark(Mark value) {
		mark = value;
	}
	
	public Mark getMark() {
		return mark;
	}
}
