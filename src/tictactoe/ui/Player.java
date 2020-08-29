package tictactoe.ui;

import tictactoe.Mark;
import tictactoe.ai.ComputerPlayer;

public class Player {
	private Mark mark;
	private ComputerPlayer logic;
	private String name;
	
	public Player(String name, Mark mark) {
		this(name, mark, null);
	}
	
	public Player(String name, Mark mark, ComputerPlayer logic) {
		this.name = name;
		this.mark = mark;
		this.logic = logic;
	}
	
	public Mark getMark() {
		return mark;
	}
	
	public ComputerPlayer getLogic() {
		return logic;
	}
	
	public String getName() {
		return name;
	}
}
