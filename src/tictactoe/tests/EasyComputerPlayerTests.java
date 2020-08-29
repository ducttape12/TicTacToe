package tictactoe.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import tictactoe.Board;
import tictactoe.Coordinate;
import tictactoe.ai.ComputerPlayer;
import tictactoe.ai.EasyComputerPlayer;

public class EasyComputerPlayerTests {
	
	@Test
	public void GivenOneValidMove_WhenGetMoveCalled_ThenReturnsValidMove() {
		// Arrange
		Board board = BoardGenerator.generateBoard(
				0, 1, 2,
				1, 2, 1,
				1, 2, 1
				);
		ComputerPlayer cpu = new EasyComputerPlayer();
		
		// Act
		Coordinate move = cpu.getMove(board);
		
		// Assert
		assertEquals(0, move.getX());
		assertEquals(0, move.getY());
	}
	
	@Test
	public void GivenNoValidMoves_WhenGetMoveCalled_ThenReturnsNull() {
		// Arrange
		Board board = BoardGenerator.generateBoard(
				2, 1, 2,
				1, 2, 1,
				1, 2, 1
				);
		ComputerPlayer cpu = new EasyComputerPlayer();
		
		// Act
		Coordinate move = cpu.getMove(board);
		
		// Assert
		assertNull(move);
	}

}
