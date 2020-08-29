package tictactoe.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import tictactoe.Board;
import tictactoe.Coordinate;
import tictactoe.GameState;
import tictactoe.Mark;

public class TicTacToeWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1183116272159743334L;
	
	private Board board;
	private BoardDisplay boardDisplay;
	private JLabel statusMessage;
	private List<Player> players;
	private int currentPlayerIndex;

	public TicTacToeWindow() {
		super("Tic Tac Toe");
		
		setLookAndFeel();
		board = new Board();
		initializePlayers();
		
		setLayout(new BorderLayout());
		boardDisplay = new BoardDisplay(board);
		add(boardDisplay, BorderLayout.CENTER);
		boardDisplay.addCellClickedActionListener(this);
		
		statusMessage = new JLabel();
		statusMessage.setHorizontalAlignment(SwingConstants.CENTER);
		add(statusMessage, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(640, 480);
		setLocationRelativeTo(null);
		setVisible(true);
		
		setDescription();
	}
	
	private void initializePlayers() {
		players = new ArrayList<Player>();
		
		Player player1 = new Player("Player 1", Mark.PLAYER_1);
		Player player2 = new Player("Player 2", Mark.PLAYER_2);
		players.add(player1);
		players.add(player2);
		
		currentPlayerIndex = 0;
	}

	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
		}
	}
	
	public static void main(String[] arguments) {
		new TicTacToeWindow();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Cell")) {
			CellDisplay cell = (CellDisplay)e.getSource();
			makeMark(cell.getCoordinate());
		}
		
	}
	
	private void makeMark(Coordinate coordinate) {
		if(board.makeMark(coordinate, players.get(currentPlayerIndex).getMark())) {
			currentPlayerIndex++;
			if(currentPlayerIndex > players.size() - 1) {
				currentPlayerIndex = 0;
			}
			
			setDescription();
			boardDisplay.updateCellDisplays();
		}
	}
	
	private void setDescription() {
		GameState state = board.getGameState();
		
		String description = "";
		switch(state) {
			case GAME_OVER_PLAYER_1_WINS:
				description = players.get(0).getName() + " wins!";
				break;
			case GAME_OVER_PLAYER_2_WINS:
				description = players.get(1).getName() + " wins!";
				break;
			case GAME_OVER_TIE:
				description = "Both players tied.  Game over.";
				break;
			case IN_PROGRESS:
				description = players.get(currentPlayerIndex).getName();
				description += ", please make a move.";
				break;
		}
		
		statusMessage.setText(description);
	}
}
