package tictactoe.ai;

import tictactoe.Board;
import tictactoe.Coordinate;

public interface ComputerPlayer {
	public Coordinate getMove(Board board);
}
