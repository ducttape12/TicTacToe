package tictactoe.shared;

import java.util.ArrayList;
import java.util.List;

import tictactoe.Board;
import tictactoe.Coordinate;

public class DirectionGenerator {

	private static List<List<Coordinate>> allDirections;
	
	static {
		allDirections = getListOfAllDirections();
	}
	
	public static List<List<Coordinate>> getAllDirections() {
		return allDirections;
	}
	
	private static List<List<Coordinate>> getListOfAllDirections() {
		ArrayList<List<Coordinate>> directions = new ArrayList<List<Coordinate>>();
		
		addRowsToDirections(directions);
		addColumnsToDirections(directions);
		addLeftRightDiagonalDirection(directions);
		addRightLeftDiagonalDirection(directions);
				
		return directions;
	}
	
	private static void addRowsToDirections(List<List<Coordinate>> directions) {
		for(int y = 0; y < Board.ROWS_COLUMNS; y++) {
			ArrayList<Coordinate> row = new ArrayList<Coordinate>();
			
			for(int x = 0; x < Board.ROWS_COLUMNS; x++) {
				row.add(new Coordinate(x, y));
			}
			
			directions.add(row);
		}
	}
	
	private static void addColumnsToDirections(List<List<Coordinate>> directions) {
		for(int x = 0; x < Board.ROWS_COLUMNS; x++) {
			ArrayList<Coordinate> column = new ArrayList<Coordinate>();
			
			for(int y = 0; y < Board.ROWS_COLUMNS; y++) {
				column.add(new Coordinate(x, y));
			}
			
			directions.add(column);
		}
	}
	
	private static void addLeftRightDiagonalDirection(List<List<Coordinate>> directions) {
		ArrayList<Coordinate> diagonal = new ArrayList<Coordinate>();
		
		for(int i = 0; i < Board.ROWS_COLUMNS; i++) {
			diagonal.add(new Coordinate(i, i));
		}
		
		directions.add(diagonal);
	}
	
	private static void addRightLeftDiagonalDirection(List<List<Coordinate>> directions) {
		ArrayList<Coordinate> diagonal = new ArrayList<Coordinate>();
		int y = 0;
		
		for(int x = Board.ROWS_COLUMNS - 1; x >= 0; x--) {
			diagonal.add(new Coordinate(x, y));
			
			y++;
		}
		
		directions.add(diagonal);
	}
}
