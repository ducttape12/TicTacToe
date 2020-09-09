package tictactoe;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

import tictactoe.shared.DirectionGenerator;

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
	
	public boolean canMakeMark(Coordinate coordinate) {
		int x = coordinate.getX();
		int y = coordinate.getY();
		
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
	
	public boolean makeMark(Coordinate coordinate, Mark mark) {
		if(!canMakeMark(coordinate) || mark == Mark.NONE || getGameStatePayload().getGameState() != GameState.IN_PROGRESS) {
			return false;
		}
		
		cells[coordinate.getX()][coordinate.getY()].setMark(mark);
		return true;
	}
	
	public GameStatePayload getGameStatePayload() {
		List<List<Coordinate>> allDirections = DirectionGenerator.getAllDirections();
		
		boolean inProgress = false;
		
		for(int allDirectionsIndex = 0; allDirectionsIndex < allDirections.size(); allDirectionsIndex++) {
			List<Coordinate> direction = allDirections.get(allDirectionsIndex);
			HashSet<Mark> marksInDirection = new HashSet<Mark>();
			
			for(int directionIndex = 0; directionIndex < direction.size(); directionIndex++) {
				Coordinate coordinate = direction.get(directionIndex);
				Mark markAtCoordinate = cells[coordinate.getX()][coordinate.getY()].getMark();
				
				marksInDirection.add(markAtCoordinate);	
				
				if(markAtCoordinate == Mark.NONE) {
					inProgress = true;
				}
			}
			
			boolean directionFilledWithSameMark = marksInDirection.size() == 1 && !marksInDirection.contains(Mark.NONE);
			
			if(directionFilledWithSameMark) {
				Mark[] marks = marksInDirection.toArray(new Mark[marksInDirection.size()]);
				return new GameStatePayload(GameState.GAME_OVER_WINNER, marks[0]);
			}
		}
		
		if(inProgress) {
			return new GameStatePayload(GameState.IN_PROGRESS, null);
		}
		
		return new GameStatePayload(GameState.GAME_OVER_TIE, null);
	}
}
