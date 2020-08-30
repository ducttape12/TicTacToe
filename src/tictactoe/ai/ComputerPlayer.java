package tictactoe.ai;

import tictactoe.Board;
import tictactoe.Coordinate;
import tictactoe.Mark;

public interface ComputerPlayer {
	public Coordinate getMove(Board board, Mark ownMark);
}
