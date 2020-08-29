package tictactoe.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tictactoe.Board;
import tictactoe.Cell;
import tictactoe.Coordinate;

/**
 * An easy computer player.  It randomly makes a move.
 *
 */
public class EasyComputerPlayer implements ComputerPlayer {

	/**
	 * Randomly pick an open cell for a move
	 */
	@Override
	public Coordinate getMove(Board board) {
		List<Coordinate> possibleMoves = openCells(board);
		
		if(possibleMoves.size() == 0) {
			return null;
		}
		
		Coordinate move = pickCoordinate(possibleMoves);
		
		return move;
	}
	
	private List<Coordinate> openCells(Board board) {
		Cell[][] cells = board.getCells();
		ArrayList<Coordinate> openCoordinates = new ArrayList<Coordinate>();
		
		for(int x = 0; x < cells.length; x++) {
			for(int y = 0; y < cells[x].length; y++) {
				Coordinate cellCoordinate = new Coordinate(x, y);
				
				if(board.canMakeMark(cellCoordinate)) {
					openCoordinates.add(cellCoordinate);
				}
			}
		}
		
		return openCoordinates;
	}
	
	private Coordinate pickCoordinate(List<Coordinate> possibleMoves) {
		Random generator = new Random();
		
		int index = generator.nextInt(possibleMoves.size());
		
		return possibleMoves.get(index);
	}

}
