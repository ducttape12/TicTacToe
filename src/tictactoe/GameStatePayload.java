package tictactoe;

public class GameStatePayload {
	private GameState gameState;
	private Mark winner;
	
	public GameStatePayload(GameState gameState, Mark winner) {
		this.gameState = gameState;
		this.winner = winner;
	}
	
	public GameState getGameState() {
		return gameState;
	}
	
	public Mark getWinner() {
		return winner;
	}
}
