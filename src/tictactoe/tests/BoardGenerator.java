package tictactoe.tests;

import tictactoe.Board;
import tictactoe.Cell;
import tictactoe.Mark;

public class BoardGenerator {
	
	public static Board generateBoard(
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
	
	private static Mark translateMark(int value) {
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
