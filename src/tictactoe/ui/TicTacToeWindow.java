package tictactoe.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;

import tictactoe.Board;
import tictactoe.Coordinate;
import tictactoe.GameState;
import tictactoe.GameStatePayload;
import tictactoe.Mark;
import tictactoe.ai.ComputerPlayer;
import tictactoe.ai.EasyComputerPlayer;
import tictactoe.ai.HardComputerPlayer;

public class TicTacToeWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1183116272159743334L;
	
	private Board board;
	private BoardDisplay boardDisplay;
	private List<Player> players;
	private int currentPlayerIndex;
	private JPanel bottomLayout;
	private JLabel statusMessage;
	private JButton newGame;
	
	private Timer computerPlayerDelay;
	private int COMPUTER_THINKING_DELAY_MILLISECONDS = 750;

	public TicTacToeWindow() {
		super("Tic Tac Toe");
		
		setLookAndFeel();
		
		setLayout(new BorderLayout());
		
		computerPlayerDelay = new Timer(COMPUTER_THINKING_DELAY_MILLISECONDS, this);
		computerPlayerDelay.setRepeats(false);
		
		initializeNewGame();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(640, 480);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void initializeNewGame() {
		board = new Board();
		initializePlayers();
		
		if(boardDisplay != null) {
			remove(boardDisplay);
		}
		if(bottomLayout != null) {
			remove(bottomLayout);
		}
		boardDisplay = new BoardDisplay(board);
		add(boardDisplay, BorderLayout.CENTER);
		boardDisplay.addCellClickedActionListener(this);
		
		bottomLayout = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
		
		statusMessage = new JLabel();
		statusMessage.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		bottomLayout.add(statusMessage);
		
		newGame = new JButton("New Game");
		newGame.addActionListener(this);
		bottomLayout.add(newGame);
		
		add(bottomLayout, BorderLayout.PAGE_END);
		
		setDescription();
		
		if(isCurrentPlayerComputer()) {
			queueComputerPlayerMove();
		}
	}
	
	private void initializePlayers() {
		
		Object[] playerOptions = { "Human", "Easy Computer", "Hard Computer" };
		
		int result = JOptionPane.showOptionDialog(this,
					"Who would you like to play against?",
					"Tic Tac Toe",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE,
					null,
					playerOptions,
					null);

		Player player1 = new Player("Player 1", Mark.PLAYER_1);
		Player player2 = null;
		
		if(result == JOptionPane.YES_OPTION) {
			player2 = new Player("Player 2", Mark.PLAYER_2);
			
		} else if (result == JOptionPane.NO_OPTION) {
			player2 = new Player("Easy Computer", Mark.PLAYER_2, new EasyComputerPlayer());
			
		} else if (result == JOptionPane.CANCEL_OPTION) {
			player2 = new Player("Hard Computer", Mark.PLAYER_2, new HardComputerPlayer());
			
		} else {
			System.exit(0);
			
		}
		
		players = new ArrayList<Player>();
		
		players.add(player1);
		players.add(player2);
		
		Collections.shuffle(players);
		
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
			
		} else if (e.getSource() == newGame) {
			int result = JOptionPane.showConfirmDialog(this,
					"Do you want to start a new game?",
					"Start a New Game?",
					JOptionPane.YES_NO_OPTION);
			
			if(result == JOptionPane.YES_OPTION) {
				 initializeNewGame();
			}
			
		} if(e.getActionCommand() != null && e.getActionCommand().equals("Cell") && !isCurrentPlayerComputer()) {
			CellDisplay cell = (CellDisplay)e.getSource();
			makeMark(cell.getCoordinate());
		}
	}
	
	private void makeMark(Coordinate coordinate) {
		if(board.makeMark(coordinate, getCurrentPlayer().getMark())) {
			moveToNextPlayer();
			
			setDescription();
			boardDisplay.updateCellDisplays();
			
			GameStatePayload gameStatePayload = board.getGameStatePayload();
			
			if(gameStatePayload.getGameState() == GameState.IN_PROGRESS && isCurrentPlayerComputer()) {
				queueComputerPlayerMove();
			}
		}
	}
	
	private void setDescription() {
		GameStatePayload gameStatePayload = board.getGameStatePayload();
		
		String description = "";
		switch(gameStatePayload.getGameState()) {
			case GAME_OVER_WINNER:
				description = getPlayerNameFromMark(gameStatePayload.getWinner()) + " wins!";
				break;
			case GAME_OVER_TIE:
				description = "Both players tied.  Game over.";
				break;
			case IN_PROGRESS:
				description = players.get(currentPlayerIndex).getName();
				
				if(isCurrentPlayerComputer()) {
					description += " is thinking...";
				} else {
					description += ", please make a move.";	
				}
				
				break;
		}
		
		statusMessage.setText(description);
	}
	
	private String getPlayerNameFromMark(Mark mark) {
		for(int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			if(player.getMark() == mark) {
				return player.getName();
			}
		}
		
		return null;
	}
	
	private Player getCurrentPlayer() {
		return players.get(currentPlayerIndex);
	}
	
	private void moveToNextPlayer() {
		currentPlayerIndex++;
		if(currentPlayerIndex > players.size() - 1) {
			currentPlayerIndex = 0;
		}
	}
	
	private void queueComputerPlayerMove() {
		computerPlayerDelay.start();
		newGame.setEnabled(false);
	}
	
	private void makeComputerPlayerMove() {
		Player player = getCurrentPlayer();
		ComputerPlayer ai = player.getLogic();
		Mark mark = player.getMark();
		
		Coordinate move = ai.getMove(board, mark);
		
		makeMark(move);
		newGame.setEnabled(true);
	}
	
	private boolean isCurrentPlayerComputer() {
		return getCurrentPlayer().getLogic() != null;
	}
}
