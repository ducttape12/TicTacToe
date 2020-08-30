package tictactoe.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import tictactoe.Board;
import tictactoe.Coordinate;
import tictactoe.Mark;
import tictactoe.ai.ComputerPlayer;
import tictactoe.ai.HardComputerPlayer;

public class HardComputerPlayerTests {

	@Test
	public void GivenEmptyBoard_WhenGetMoveIsCalled_ThenReturnsCenterCoordinates() {
		// Arrange
		Board board = BoardGenerator.generateBoard(
				0, 0, 0,
				0, 0, 0,
				0, 0, 0
				);
		ComputerPlayer cpu = new HardComputerPlayer();
		
		// Act
		Coordinate move = cpu.getMove(board, Mark.PLAYER_1);
		
		// Assert
		assertEquals(1, move.getX());
		assertEquals(1, move.getY());
	}

	@Test
	public void GivenPossiblityOfWinningOnARow_WhenGetMoveIsCalled_ThenReturnsWinningMove() {
		// Arrange
		Board board = BoardGenerator.generateBoard(
				0, 2, 0,
				1, 1, 0,
				0, 2, 0
				);
		ComputerPlayer cpu = new HardComputerPlayer();
		
		// Act
		Coordinate move = cpu.getMove(board, Mark.PLAYER_1);
		
		// Assert
		assertEquals(2, move.getX());
		assertEquals(1, move.getY());
	}
	
	@Test
	public void GivenPossiblityOfWinningOnAColumn_WhenGetMoveIsCalled_ThenReturnsWinningMove() {
		// Arrange
		Board board = BoardGenerator.generateBoard(
				2, 1, 2,
				1, 1, 2,
				0, 0, 0
				);
		ComputerPlayer cpu = new HardComputerPlayer();
		
		// Act
		Coordinate move = cpu.getMove(board, Mark.PLAYER_1);
		
		// Assert
		assertEquals(1, move.getX());
		assertEquals(2, move.getY());
	}
	
	@Test
	public void GivenPossiblityOfWinningOnALeftRightDiagonal_WhenGetMoveIsCalled_ThenReturnsWinningMove() {
		// Arrange
		Board board = BoardGenerator.generateBoard(
				1, 2, 2,
				2, 1, 1,
				0, 0, 0
				);
		ComputerPlayer cpu = new HardComputerPlayer();
		
		// Act
		Coordinate move = cpu.getMove(board, Mark.PLAYER_1);
		
		// Assert
		assertEquals(2, move.getX());
		assertEquals(2, move.getY());
	}
	
	@Test
	public void GivenPossiblityOfWinningOnARightLeftDiagonal_WhenGetMoveIsCalled_ThenReturnsWinningMove() {
		// Arrange
		Board board = BoardGenerator.generateBoard(
				0, 2, 1,
				2, 1, 1,
				0, 0, 2
				);
		ComputerPlayer cpu = new HardComputerPlayer();
		
		// Act
		Coordinate move = cpu.getMove(board, Mark.PLAYER_1);
		
		// Assert
		assertEquals(0, move.getX());
		assertEquals(2, move.getY());
		
	}

	@Test
	public void GivenPossibilityOfWinningAndLosing_WhenGetMoveIsCalled_ThenReturnsWinningMove() {
		// Arrange
		Board board = BoardGenerator.generateBoard(
				1, 2, 0,
				1, 2, 0,
				0, 0, 0
				);
		ComputerPlayer cpu = new HardComputerPlayer();
		
		// Act
		Coordinate move = cpu.getMove(board, Mark.PLAYER_1);
		
		// Assert
		assertEquals(0, move.getX());
		assertEquals(2, move.getY());
	}

	@Test
	public void GivenPossibilityOfLosing_WhenGetMoveIsCalled_ThenReturnsBlockingMove() {
		// Arrange
		Board board = BoardGenerator.generateBoard(
				2, 1, 0,
				1, 0, 0,
				0, 0, 2
				);
		ComputerPlayer cpu = new HardComputerPlayer();
		
		// Act
		Coordinate move = cpu.getMove(board, Mark.PLAYER_1);
		
		// Assert
		assertEquals(1, move.getX());
		assertEquals(1, move.getY());
	}

	@Test
	public void GivenPossibilityOfAMomentMove_WhenGetMoveIsCalled_ThenReturnsMomentMove() {
		// Arrange
		Board board = BoardGenerator.generateBoard(
				2, 0, 0,
				1, 0, 0,
				0, 0, 0
				);
		ComputerPlayer cpu = new HardComputerPlayer();
		
		// Act
		Coordinate move = cpu.getMove(board, Mark.PLAYER_1);
		
		// Assert
		assertTrue(move.getX() == 1 || move.getX() == 2);
		assertEquals(1, move.getY());
	}

	@Test
	public void GivenNoGoodOptions_WhenGetMoveIsCalled_ThenReturnsAMove() {
		// Arrange
		Board board = BoardGenerator.generateBoard(
				1, 2, 1,
				2, 2, 1,
				0, 1, 2
				);
		ComputerPlayer cpu = new HardComputerPlayer();
		
		// Act
		Coordinate move = cpu.getMove(board, Mark.PLAYER_1);
		
		// Assert
		assertEquals(0, move.getX());
		assertEquals(2, move.getY());
		
	}
}
