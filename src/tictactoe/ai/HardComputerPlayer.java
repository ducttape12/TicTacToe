package tictactoe.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tictactoe.Board;
import tictactoe.Cell;
import tictactoe.Coordinate;
import tictactoe.Mark;
import tictactoe.shared.DirectionGenerator;

/**
 * Hard difficulty computer player.  Moves fall in the following order:
 * 
 * 1. If the board is blank and there's a perfect center, go for the center move.
 * 2. If the AI can win with one move, that make move.
 * 3. If an opponent can win in one move, block that move.
 * 4. If there's a row, column, or diagonal with only this AI's
 *    marks, make a move in that row, column, or diagonal.
 * 5. Just make a random move from the available choices.
 */
public class HardComputerPlayer implements ComputerPlayer {	
	
	@Override
	public Coordinate getMove(Board board, Mark ownMark) {
		Cell[][] cells = board.getCells();
		
		return getNextMove(cells, ownMark);
	}
	
	private Mark getMark(Cell[][] cells, Coordinate coordinate) {
		Cell cell = cells[coordinate.getX()][coordinate.getY()];
		
		return cell.getMark();
	}
	
	private Coordinate getNextMove(Cell[][] cells, Mark ownMark) {
		
		List<Coordinate> opponentCanWinNextMoveList = new ArrayList<Coordinate>(Board.ROWS_COLUMNS * Board.ROWS_COLUMNS);
		List<Coordinate> momentumMoveList = new ArrayList<Coordinate>(Board.ROWS_COLUMNS * Board.ROWS_COLUMNS);
		List<Coordinate> possibleMoveList = new ArrayList<Coordinate>(Board.ROWS_COLUMNS & Board.ROWS_COLUMNS);
		boolean blankBoard = true;
		
		for(int allDirectionsIndex = 0; allDirectionsIndex < DirectionGenerator.getAllDirections().size(); allDirectionsIndex++) {
			List<Coordinate> direction = DirectionGenerator.getAllDirections().get(allDirectionsIndex);
			
			List<Coordinate> myCoordinates = new ArrayList<Coordinate>(Board.ROWS_COLUMNS);
			List<Coordinate> opponentCoordinates = new ArrayList<Coordinate>(Board.ROWS_COLUMNS);
			List<Coordinate> blankCoordinates = new ArrayList<Coordinate>(Board.ROWS_COLUMNS);
			
			for(int currentDirectionIndex = 0; currentDirectionIndex < direction.size(); currentDirectionIndex++) {
				Coordinate coordinate = direction.get(currentDirectionIndex);
				
				Mark mark = getMark(cells, coordinate);
				
				if(mark == ownMark) {
					blankBoard = false;
					myCoordinates.add(coordinate);
				} else if (mark == Mark.NONE) {
					blankCoordinates.add(coordinate);
				} else {
					blankBoard = false;
					opponentCoordinates.add(coordinate);
				}
			}
			
			boolean iCanWinNextMove = blankCoordinates.size() == 1 && opponentCoordinates.size() == 0;
			if(iCanWinNextMove) {
				// This is the only move we want to short circuit because winning is always the best move;
				// no need to look for additional moves
				return blankCoordinates.get(0);
			}
			
			boolean opponentCanWinNextMove = blankCoordinates.size() == 1 && myCoordinates.size() == 0;
			if(opponentCanWinNextMove) {
				opponentCanWinNextMoveList.addAll(blankCoordinates);
			}

			boolean directionWithOnlyMyMoves = myCoordinates.size() > 0 && opponentCoordinates.size() == 0;
			if(directionWithOnlyMyMoves) {
				momentumMoveList.addAll(blankCoordinates);
			}

			possibleMoveList.addAll(blankCoordinates);
		}
		
		
		if(opponentCanWinNextMoveList.size() > 0) {
			return randomCoordinate(opponentCanWinNextMoveList);
		}
		
		if(momentumMoveList.size() > 0) {
			return randomCoordinate(momentumMoveList);
		}
		
		boolean perfectCenter = (Board.ROWS_COLUMNS - 1) % 2 == 0;
		if(blankBoard && perfectCenter) {
			return new Coordinate((Board.ROWS_COLUMNS - 1) / 2, (Board.ROWS_COLUMNS - 1) / 2);
		}
		
		return randomCoordinate(possibleMoveList);
	}
	
	private Coordinate randomCoordinate(List<Coordinate> coordinates) {
		Random generator = new Random();
		
		int index = generator.nextInt(coordinates.size());
		
		return coordinates.get(index);
	}

}
