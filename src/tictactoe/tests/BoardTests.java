package tictactoe.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import tictactoe.Board;
import tictactoe.GameState;
import tictactoe.GameStatePayload;
import tictactoe.Mark;

public class BoardTests {

	@Test
	public void WhenEmptyBoard_ThenInProgress() {
		Board board = BoardGenerator.generateBoard(
				0, 0, 0,
				0, 0, 0,
				0, 0, 0
				);
		
		GameStatePayload gameStatePayload = board.getGameStatePayload();
		
		assertEquals(GameState.IN_PROGRESS, gameStatePayload.getGameState());
		assertNull(gameStatePayload.getWinner());
	}
	
	@Test
	public void WhenNoPossibleMoves_ThenTie() {
		Board board = BoardGenerator.generateBoard(
				2, 1, 1,
				1, 2, 2,
				2, 1, 1
				);
		
		GameStatePayload gameStatePayload = board.getGameStatePayload();
		
		assertEquals(GameState.GAME_OVER_TIE, gameStatePayload.getGameState());
		assertNull(gameStatePayload.getWinner());
	}
	
	@Test
	public void GivenWinInRow1_WhenWinIsPlayer1_ThenFindWin() {
		Board board = BoardGenerator.generateBoard(
				1, 1, 1,
				0, 2, 0,
				0, 0, 2
				);
		
		GameStatePayload gameStatePayload = board.getGameStatePayload();
		
		assertEquals(GameState.GAME_OVER_WINNER, gameStatePayload.getGameState());
		assertEquals(Mark.PLAYER_1, gameStatePayload.getWinner());
	}
	
	@Test
	public void GivenWinInRow2_WhenWinIsPlayer1_ThenFindWin() {
		Board board = BoardGenerator.generateBoard(
				0, 2, 0,
				1, 1, 1,
				0, 0, 2
				);
		
		GameStatePayload gameStatePayload = board.getGameStatePayload();
		
		assertEquals(GameState.GAME_OVER_WINNER, gameStatePayload.getGameState());
		assertEquals(Mark.PLAYER_1, gameStatePayload.getWinner());
	}
	
	@Test
	public void GivenWinInRow3_WhenWinIsPlayer1_ThenFindWin() {
		Board board = BoardGenerator.generateBoard(
				0, 2, 0,
				0, 0, 2,
				1, 1, 1
				);
		
		GameStatePayload gameStatePayload = board.getGameStatePayload();
		
		assertEquals(GameState.GAME_OVER_WINNER, gameStatePayload.getGameState());
		assertEquals(Mark.PLAYER_1, gameStatePayload.getWinner());
	}
	
	@Test
	public void GivenWinInRow1_WhenWinIsPlayer2_ThenFindWin() {
		Board board = BoardGenerator.generateBoard(
				2, 2, 2,
				1, 1, 0,
				0, 0, 1
				);
		
		GameStatePayload gameStatePayload = board.getGameStatePayload();
		
		assertEquals(GameState.GAME_OVER_WINNER, gameStatePayload.getGameState());
		assertEquals(Mark.PLAYER_2, gameStatePayload.getWinner());
	}
	
	@Test
	public void GivenWinInRow2_WhenWinIsPlayer2_ThenFindWin() {
		Board board = BoardGenerator.generateBoard(
				0, 1, 0,
				2, 2, 2,
				0, 1, 1
				);
		
		GameStatePayload gameStatePayload = board.getGameStatePayload();
		
		assertEquals(GameState.GAME_OVER_WINNER, gameStatePayload.getGameState());
		assertEquals(Mark.PLAYER_2, gameStatePayload.getWinner());
	}
	
	@Test
	public void GivenWinInRow3_WhenWinIsPlayer2_ThenFindWin() {
		Board board = BoardGenerator.generateBoard(
				0, 1, 0,
				0, 1, 1,
				2, 2, 2
				);
		
		GameStatePayload gameStatePayload = board.getGameStatePayload();
		
		assertEquals(GameState.GAME_OVER_WINNER, gameStatePayload.getGameState());
		assertEquals(Mark.PLAYER_2, gameStatePayload.getWinner());
	}
	
	@Test
	public void GivenWinInColumn1_WhenWinIsPlayer1_ThenFindWin() {
		Board board = BoardGenerator.generateBoard(
				1, 0, 2,
				1, 2, 0,
				1, 0, 0
				);
		
		GameStatePayload gameStatePayload = board.getGameStatePayload();
		
		assertEquals(GameState.GAME_OVER_WINNER, gameStatePayload.getGameState());
		assertEquals(Mark.PLAYER_1, gameStatePayload.getWinner());
	}
	
	@Test
	public void GivenWinInColumn2_WhenWinIsPlayer1_ThenFindWin() {
		Board board = BoardGenerator.generateBoard(
				0, 1, 2,
				2, 1, 0,
				0, 1, 0
				);
		
		GameStatePayload gameStatePayload = board.getGameStatePayload();
		
		assertEquals(GameState.GAME_OVER_WINNER, gameStatePayload.getGameState());
		assertEquals(Mark.PLAYER_1, gameStatePayload.getWinner());
	}
	
	@Test
	public void GivenWinInColumn3_WhenWinIsPlayer1_ThenFindWin() {
		Board board = BoardGenerator.generateBoard(
				2, 0, 1,
				0, 2, 1,
				0, 0, 1
				);
		
		GameStatePayload gameStatePayload = board.getGameStatePayload();
		
		assertEquals(GameState.GAME_OVER_WINNER, gameStatePayload.getGameState());
		assertEquals(Mark.PLAYER_1, gameStatePayload.getWinner());
	}
	
	@Test
	public void GivenWinInColumn1_WhenWinIsPlayer2_ThenFindWin() {
		Board board = BoardGenerator.generateBoard(
				2, 0, 1,
				2, 1, 0,
				2, 1, 0
				);
		
		GameStatePayload gameStatePayload = board.getGameStatePayload();
		
		assertEquals(GameState.GAME_OVER_WINNER, gameStatePayload.getGameState());
		assertEquals(Mark.PLAYER_2, gameStatePayload.getWinner());
	}
	
	@Test
	public void GivenWinInColumn2_WhenWinIsPlayer2_ThenFindWin() {
		Board board = BoardGenerator.generateBoard(
				1, 2, 1,
				1, 2, 0,
				0, 2, 0
				);
		
		GameStatePayload gameStatePayload = board.getGameStatePayload();
		
		assertEquals(GameState.GAME_OVER_WINNER, gameStatePayload.getGameState());
		assertEquals(Mark.PLAYER_2, gameStatePayload.getWinner());
	}
	
	@Test
	public void GivenWinInColumn3_WhenWinIsPlayer2_ThenFindWin() {
		Board board = BoardGenerator.generateBoard(
				1, 1, 2,
				0, 1, 2,
				0, 0, 2
				);
		
		GameStatePayload gameStatePayload = board.getGameStatePayload();
		
		assertEquals(GameState.GAME_OVER_WINNER, gameStatePayload.getGameState());
		assertEquals(Mark.PLAYER_2, gameStatePayload.getWinner());
	}
	
	@Test
	public void GivenWinLeftRightDiagonal_WhenWinIsPlayer1_ThenFindWin() {
		Board board = BoardGenerator.generateBoard(
				1, 0, 2,
				0, 1, 2,
				0, 0, 1
				);
		
		GameStatePayload gameStatePayload = board.getGameStatePayload();
		
		assertEquals(GameState.GAME_OVER_WINNER, gameStatePayload.getGameState());
		assertEquals(Mark.PLAYER_1, gameStatePayload.getWinner());
	}
	
	@Test
	public void GivenWinLeftRightDiagonal_WhenWinIsPlayer2_ThenFindWin() {
		Board board = BoardGenerator.generateBoard(
				2, 1, 1,
				0, 2, 1,
				0, 0, 2
				);
		
		GameStatePayload gameStatePayload = board.getGameStatePayload();
		
		assertEquals(GameState.GAME_OVER_WINNER, gameStatePayload.getGameState());
		assertEquals(Mark.PLAYER_2, gameStatePayload.getWinner());
	}
	
	@Test
	public void GivenWinRightLeftDiagonal_WhenWinIsPlayer1_ThenFindWin() {
		Board board = BoardGenerator.generateBoard(
				2, 0, 1,
				0, 1, 2,
				1, 0, 0
				);
		
		GameStatePayload gameStatePayload = board.getGameStatePayload();
		
		assertEquals(GameState.GAME_OVER_WINNER, gameStatePayload.getGameState());
		assertEquals(Mark.PLAYER_1, gameStatePayload.getWinner());
	}
	
	@Test
	public void GivenWinRightLeftDiagonal_WhenWinIsPlayer2_ThenFindWin() {
		Board board = BoardGenerator.generateBoard(
				1, 1, 2,
				0, 2, 1,
				2, 0, 0
				);
		
		GameStatePayload gameStatePayload = board.getGameStatePayload();
		
		assertEquals(GameState.GAME_OVER_WINNER, gameStatePayload.getGameState());
		assertEquals(Mark.PLAYER_2, gameStatePayload.getWinner());
	}
	
	@Test
	public void GivenTwoEntriesInLeftToRightDiagonal_WhenPlayerIsBlocked_ThenGameInProgress() {
		Board board = BoardGenerator.generateBoard(
				0, 0, 1,
				0, 1, 0,
				2, 0, 0
				);
		
		GameStatePayload gameStatePayload = board.getGameStatePayload();
		
		assertEquals(GameState.IN_PROGRESS, gameStatePayload.getGameState());
		assertNull(gameStatePayload.getWinner());
	}
	
}
