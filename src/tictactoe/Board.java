package tictactoe;

import java.util.HashSet;

public class Board {
	private Cell[][] cells;
	
	public static final int ROWS_COLUMNS = 3;
	
	public Board() {
		cells = new Cell[ROWS_COLUMNS][ROWS_COLUMNS];
		
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				cells[i][j] = new Cell();
			}
		}
	}
	
	public Cell[][] getCells() {
		return cells;
	}
	
	public boolean canMakeMark(int x, int y) {
		boolean outOfBoundsX = x < 0 || x > cells.length - 1;
		if(outOfBoundsX) {
			return false;
		}
		
		boolean outOfBoundsY = y < 0 || y > cells[x].length - 1;
		if(outOfBoundsY) {
			return false;
		}
		
		boolean hasMark = cells[x][y].getMark() != Mark.NONE;
		if(hasMark) {
			return false;
		}
		
		return true;
	}
	
	public boolean makeMark(int x, int y, Mark mark) {
		if(!canMakeMark(x, y) || mark == Mark.NONE || getGameState() != GameState.IN_PROGRESS) {
			return false;
		}
		
		cells[x][y].setMark(mark);
		return true;
	}
	
	public GameState getGameState() {
		HashSet<GameState> states = new HashSet<GameState>();
		states.add(getColumnGameState());
		states.add(getRowGameState());
		states.add(getLeftRightDiagonalGameState());
		states.add(getRightLeftDiagonalGameState());
		
		if(states.contains(GameState.GAME_OVER_PLAYER_1_WINS)) {
			return GameState.GAME_OVER_PLAYER_1_WINS;
		}
		if(states.contains(GameState.GAME_OVER_PLAYER_2_WINS)) {
			return GameState.GAME_OVER_PLAYER_2_WINS;
		}
		if(states.contains(GameState.IN_PROGRESS)) {
			return GameState.IN_PROGRESS;
		}
		return GameState.GAME_OVER_TIE;
	}
	
	private GameState getColumnGameState() {
		boolean inProgress = false;
		
		for(int x = 0; x < cells.length; x++) {
			HashSet<Mark> marksInCurrentColumn = new HashSet<Mark>();
			
			for(int y = 0; y < cells[x].length; y++) {
				Mark mark = cells[x][y].getMark();
				
				marksInCurrentColumn.add(mark);
			}
			
			if(marksInCurrentColumn.size() == 1 && marksInCurrentColumn.contains(Mark.PLAYER_1)) {
				return GameState.GAME_OVER_PLAYER_1_WINS;
			}
			
			if(marksInCurrentColumn.size() == 1 && marksInCurrentColumn.contains(Mark.PLAYER_2)) {
				return GameState.GAME_OVER_PLAYER_2_WINS;
			}
			
			if(marksInCurrentColumn.contains(Mark.NONE)) {
				inProgress = true;
			}
		}
		
		if(inProgress) {
			return GameState.IN_PROGRESS;
		}
		
		return GameState.GAME_OVER_TIE;
	}
	
	private GameState getRowGameState() {
		boolean inProgress = false;
		
		for(int y = 0; y < ROWS_COLUMNS; y++) {
			HashSet<Mark> marksInCurrentRow = new HashSet<Mark>();
			
			for(int x = 0; x < cells.length; x++) {
				Mark mark = cells[x][y].getMark();

				marksInCurrentRow.add(mark);
			}
			
			if(marksInCurrentRow.size() == 1 && marksInCurrentRow.contains(Mark.PLAYER_1)) {
				return GameState.GAME_OVER_PLAYER_1_WINS;
			}
			
			if(marksInCurrentRow.size() == 1 && marksInCurrentRow.contains(Mark.PLAYER_2)) {
				return GameState.GAME_OVER_PLAYER_2_WINS;
			}
			
			if(marksInCurrentRow.contains(Mark.NONE)) {
				inProgress = true;
			}
		}
		
		if(inProgress) {
			return GameState.IN_PROGRESS;
		}
		
		return GameState.GAME_OVER_TIE;
	}
	
	private GameState getLeftRightDiagonalGameState() {
		HashSet<Mark> marksInDiagonal = new HashSet<Mark>();
		
		for(int i = 0; i < ROWS_COLUMNS; i++) {
			Mark mark = cells[i][i].getMark();

			marksInDiagonal.add(mark);
		}
		
		if(marksInDiagonal.size() == 1 && marksInDiagonal.contains(Mark.PLAYER_1)) {
			return GameState.GAME_OVER_PLAYER_1_WINS;
		}
		
		if(marksInDiagonal.size() == 1 && marksInDiagonal.contains(Mark.PLAYER_2)) {
			return GameState.GAME_OVER_PLAYER_2_WINS;
		}
		
		if(marksInDiagonal.contains(Mark.NONE)) {
			return GameState.IN_PROGRESS;
		}
		
		return GameState.GAME_OVER_TIE;
	}
	
	private GameState getRightLeftDiagonalGameState() {
		HashSet<Mark> marksInDiagonal = new HashSet<Mark>();
		int y = 0;
		
		for(int x = ROWS_COLUMNS - 1; x >= 0; x--) {
			Mark mark = cells[x][y].getMark();

			marksInDiagonal.add(mark);
			
			y++;
		}
		
		if(marksInDiagonal.size() == 1 && marksInDiagonal.contains(Mark.PLAYER_1)) {
			return GameState.GAME_OVER_PLAYER_1_WINS;
		}
		
		if(marksInDiagonal.size() == 1 && marksInDiagonal.contains(Mark.PLAYER_2)) {
			return GameState.GAME_OVER_PLAYER_2_WINS;
		}
		
		if(marksInDiagonal.contains(Mark.NONE)) {
			return GameState.IN_PROGRESS;
		}
		
		return GameState.GAME_OVER_TIE;
	}
}
