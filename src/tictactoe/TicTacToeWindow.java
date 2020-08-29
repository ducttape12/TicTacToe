package tictactoe;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class TicTacToeWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1183116272159743334L;
	
	private Board board;
	private BoardDisplay boardDisplay;
	private JLabel statusMessage;
	private Mark currentPlayer;

	public TicTacToeWindow() {
		super("Tic Tac Toe");
		
		setLookAndFeel();
		board = new Board();
		currentPlayer = Mark.PLAYER_1;
		
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
		if(board.makeMark(coordinate, currentPlayer)) {
			currentPlayer = currentPlayer == Mark.PLAYER_1 ? Mark.PLAYER_2 : Mark.PLAYER_1;
			
			setDescription();
			boardDisplay.updateCellDisplays();
		}
	}
	
	private void setDescription() {
		GameState state = board.getGameState();
		
		String description = "";
		switch(state) {
			case GAME_OVER_PLAYER_1_WINS:
				description = "Player 1 wins!";
				break;
			case GAME_OVER_PLAYER_2_WINS:
				description = "Player 2 wins!";
				break;
			case GAME_OVER_TIE:
				description = "Both players tied.  Game over.";
				break;
			case IN_PROGRESS:
				description = currentPlayer == Mark.PLAYER_1 ? "Player 1" : "Player 2";
				description += ", please make a move.";
				break;
		}
		
		statusMessage.setText(description);
	}
}
