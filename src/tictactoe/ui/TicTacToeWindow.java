package tictactoe.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;

import tictactoe.Board;
import tictactoe.Coordinate;
import tictactoe.GameState;
import tictactoe.Mark;
import tictactoe.ai.ComputerPlayer;
import tictactoe.ai.EasyComputerPlayer;

public class TicTacToeWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1183116272159743334L;
	
	private Board board;
	private BoardDisplay boardDisplay;
	private JLabel statusMessage;
	private List<Player> players;
	private int currentPlayerIndex;
	
	private Timer computerPlayerDelay;
	private int COMPUTER_THINKING_DELAY_MILLISECONDS = 1000;

	public TicTacToeWindow() {
		super("Tic Tac Toe");
		
		setLookAndFeel();
		board = new Board();
		initializePlayers();
		
		computerPlayerDelay = new Timer(COMPUTER_THINKING_DELAY_MILLISECONDS, this);
		computerPlayerDelay.setRepeats(false);
		
		setLayout(new BorderLayout());
		boardDisplay = new BoardDisplay(board);
		add(boardDisplay, BorderLayout.CENTER);
		boardDisplay.addCellClickedActionListener(this);
		
		statusMessage = new JLabel();
		statusMessage.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		statusMessage.setHorizontalAlignment(SwingConstants.CENTER);
		add(statusMessage, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(640, 480);
		setLocationRelativeTo(null);
		setVisible(true);
		
		setDescription();
	}
	
	private void initializePlayers() {
		
		Object[] playerOptions = { "Human", "Computer" };
		
		int result = 0;
		do {
			result = JOptionPane.showOptionDialog(null,
					"Who would you like to play against?",
					"Tic Tac Toe",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.PLAIN_MESSAGE,
					null,
					playerOptions,
					null);

		} while(result != JOptionPane.YES_OPTION && result != JOptionPane.NO_OPTION);

		Player player1 = new Player("Player 1", Mark.PLAYER_1);
		Player player2;
		
		// Human
		if(result == JOptionPane.YES_OPTION) {
			player2 = new Player("Player 2", Mark.PLAYER_2);
			
		// Computer
		} else {
			player2 = new Player("Computer", Mark.PLAYER_2, new EasyComputerPlayer());
		}
		
		players = new ArrayList<Player>();
		
		players.add(player1);
		players.add(player2);
		
		// TODO: This breaks the name display if Player 2 goes first
		//Collections.shuffle(players);
		
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
		if(e.getSource() == computerPlayerDelay) {
			makeComputerPlayerMove();
			
		} else if(e.getActionCommand() != null && e.getActionCommand().equals("Cell")) {
			CellDisplay cell = (CellDisplay)e.getSource();
			makeMark(cell.getCoordinate());
		}
	}
	
	private void makeMark(Coordinate coordinate) {
		if(board.makeMark(coordinate, getCurrentPlayer().getMark())) {
			Player newPlayer = moveToNextPlayer();
			
			setDescription();
			boardDisplay.updateCellDisplays();
			
			if(board.getGameState() == GameState.IN_PROGRESS && newPlayer.getLogic() != null) {
				queueComputerPlayerMove();
			}
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
				
				if(getCurrentPlayer().getLogic() != null) {
					description += " is thinking...";
				} else {
					description += ", please make a move.";	
				}
				
				break;
		}
		
		statusMessage.setText(description);
	}
	
	private Player getCurrentPlayer() {
		return players.get(currentPlayerIndex);
	}
	
	private Player moveToNextPlayer() {
		currentPlayerIndex++;
		if(currentPlayerIndex > players.size() - 1) {
			currentPlayerIndex = 0;
		}
		
		return getCurrentPlayer();
	}
	
	private void queueComputerPlayerMove() {
		computerPlayerDelay.start();
	}
	
	private void makeComputerPlayerMove() {
		ComputerPlayer ai = getCurrentPlayer().getLogic();
		Coordinate move = ai.getMove(board);
		
		makeMark(move);
	}
}
