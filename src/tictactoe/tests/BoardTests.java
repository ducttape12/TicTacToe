package tictactoe.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import tictactoe.Board;
import tictactoe.Cell;
import tictactoe.GameState;
import tictactoe.Mark;

public class BoardTests {

	@Test
	public void WhenEmptyBoard_ThenInProgress() {
		Board board = generateBoard(
				0, 0, 0,
				0, 0, 0,
				0, 0, 0
				);
		
		assertEquals(GameState.IN_PROGRESS, board.getGameState());
	}
	
	@Test
	public void WhenNoPossibleMoves_ThenTie() {
		Board board = generateBoard(
				2, 1, 1,
				1, 2, 2,
				2, 1, 1
				);
		
		assertEquals(GameState.GAME_OVER_TIE, board.getGameState());
	}
	
	@Test
	public void GivenWinInRow1_WhenWinIsPlayer1_ThenFindWin() {
		Board board = generateBoard(
				1, 1, 1,
				0, 2, 0,
				0, 0, 2
				);
		
		assertEquals(GameState.GAME_OVER_PLAYER_1_WINS, board.getGameState());
	}
	
	@Test
	public void GivenWinInRow2_WhenWinIsPlayer1_ThenFindWin() {
		Board board = generateBoard(
				0, 2, 0,
				1, 1, 1,
				0, 0, 2
				);
		
		assertEquals(GameState.GAME_OVER_PLAYER_1_WINS, board.getGameState());
	}
	
	@Test
	public void GivenWinInRow3_WhenWinIsPlayer1_ThenFindWin() {
		Board board = generateBoard(
				0, 2, 0,
				0, 0, 2,
				1, 1, 1
				);
		
		assertEquals(GameState.GAME_OVER_PLAYER_1_WINS, board.getGameState());
	}
	
	@Test
	public void GivenWinInRow1_WhenWinIsPlayer2_ThenFindWin() {
		Board board = generateBoard(
				2, 2, 2,
				1, 1, 0,
				0, 0, 1
				);
		
		assertEquals(GameState.GAME_OVER_PLAYER_2_WINS, board.getGameState());
	}
	
	@Test
	public void GivenWinInRow2_WhenWinIsPlayer2_ThenFindWin() {
		Board board = generateBoard(
				0, 1, 0,
				2, 2, 2,
				0, 1, 1
				);
		
		assertEquals(GameState.GAME_OVER_PLAYER_2_WINS, board.getGameState());
	}
	
	@Test
	public void GivenWinInRow3_WhenWinIsPlayer2_ThenFindWin() {
		Board board = generateBoard(
				0, 1, 0,
				0, 1, 1,
				2, 2, 2
				);
		
		assertEquals(GameState.GAME_OVER_PLAYER_2_WINS, board.getGameState());
	}
	
	@Test
	public void GivenWinInColumn1_WhenWinIsPlayer1_ThenFindWin() {
		Board board = generateBoard(
				1, 0, 2,
				1, 2, 0,
				1, 0, 0
				);
		
		assertEquals(GameState.GAME_OVER_PLAYER_1_WINS, board.getGameState());
	}
	
	@Test
	public void GivenWinInColumn2_WhenWinIsPlayer1_ThenFindWin() {
		Board board = generateBoard(
				0, 1, 2,
				2, 1, 0,
				0, 1, 0
				);
		
		assertEquals(GameState.GAME_OVER_PLAYER_1_WINS, board.getGameState());
	}
	
	@Test
	public void GivenWinInColumn3_WhenWinIsPlayer1_ThenFindWin() {
		Board board = generateBoard(
				2, 0, 1,
				0, 2, 1,
				0, 0, 1
				);
		
		assertEquals(GameState.GAME_OVER_PLAYER_1_WINS, board.getGameState());
	}
	
	@Test
	public void GivenWinInColumn1_WhenWinIsPlayer2_ThenFindWin() {
		Board board = generateBoard(
				2, 0, 1,
				2, 1, 0,
				2, 1, 0
				);
		
		assertEquals(GameState.GAME_OVER_PLAYER_2_WINS, board.getGameState());
	}
	
	@Test
	public void GivenWinInColumn2_WhenWinIsPlayer2_ThenFindWin() {
		Board board = generateBoard(
				1, 2, 1,
				1, 2, 0,
				0, 2, 0
				);
		
		assertEquals(GameState.GAME_OVER_PLAYER_2_WINS, board.getGameState());
	}
	
	@Test
	public void GivenWinInColumn3_WhenWinIsPlayer2_ThenFindWin() {
		Board board = generateBoard(
				1, 1, 2,
				0, 1, 2,
				0, 0, 2
				);
		
		assertEquals(GameState.GAME_OVER_PLAYER_2_WINS, board.getGameState());
	}
	
	@Test
	public void GivenWinLeftRightDiagonal_WhenWinIsPlayer1_ThenFindWin() {
		Board board = generateBoard(
				1, 0, 2,
				0, 1, 2,
				0, 0, 1
				);
		
		assertEquals(GameState.GAME_OVER_PLAYER_1_WINS, board.getGameState());
	}
	
	@Test
	public void GivenWinLeftRightDiagonal_WhenWinIsPlayer2_ThenFindWin() {
		Board board = generateBoard(
				2, 1, 1,
				0, 2, 1,
				0, 0, 2
				);
		
		assertEquals(GameState.GAME_OVER_PLAYER_2_WINS, board.getGameState());
	}
	
	@Test
	public void GivenWinRightLeftDiagonal_WhenWinIsPlayer1_ThenFindWin() {
		Board board = generateBoard(
				2, 0, 1,
				0, 1, 2,
				1, 0, 0
				);
		
		assertEquals(GameState.GAME_OVER_PLAYER_1_WINS, board.getGameState());
	}
	
	@Test
	public void GivenWinRightLeftDiagonal_WhenWinIsPlayer2_ThenFindWin() {
		Board board = generateBoard(
				1, 1, 2,
				0, 2, 1,
				2, 0, 0
				);
		
		assertEquals(GameState.GAME_OVER_PLAYER_2_WINS, board.getGameState());
	}
	
	@Test
	public void GivenTwoEntriesInLeftToRightDiagonal_WhenPlayerIsBlocked_ThenFindNoWin() {
		Board board = generateBoard(
				0, 0, 1,
				0, 1, 0,
				2, 0, 0
				);
		
		assertEquals(GameState.IN_PROGRESS, board.getGameState());
	}
	
	private Board generateBoard(
			int _00, int _10, int _20,
			int _01, int _11, int _21,
			int _02, int _12, int _22) {
		Board board = new Board();
		
		Cell[][] cells = board.getCells();
		cells[0][0].setMark(translateMark(_00));
		cells[1][0].setMark(translateMark(_10));
		cells[2][0].setMark(translateMark(_20));
		cells[0][1].setMark(translateMark(_01));
		cells[1][1].setMark(translateMark(_11));
		cells[2][1].setMark(translateMark(_21));
		cells[0][2].setMark(translateMark(_02));
		cells[1][2].setMark(translateMark(_12));
		cells[2][2].setMark(translateMark(_22));
		
		return board;
	}
	
	private Mark translateMark(int value) {
		switch(value) {
			case 0:
				return Mark.NONE;
			case 1:
				return Mark.PLAYER_1;
			case 2:
				return Mark.PLAYER_2;
			default:
				return Mark.NONE;
		}
	}
	

}
