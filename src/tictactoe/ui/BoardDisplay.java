package tictactoe.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import tictactoe.Board;
import tictactoe.Cell;
import tictactoe.Coordinate;

public class BoardDisplay extends JPanel implements ActionListener {

	private static final long serialVersionUID = -7920128182767517429L;
	
	private ArrayList<ActionListener> cellClickedActionListeners = new ArrayList<ActionListener>();
	private ArrayList<CellDisplay> displayCells = new ArrayList<CellDisplay>(Board.ROWS_COLUMNS * Board.ROWS_COLUMNS);
	
	public BoardDisplay(Board board) {
		setLayout(new GridLayout(Board.ROWS_COLUMNS, Board.ROWS_COLUMNS));
		
		for(int y = 0; y < Board.ROWS_COLUMNS; y++) {
			for(int x = 0; x < Board.ROWS_COLUMNS; x++) {
				Cell cell = board.getCells()[x][y];
				CellDisplay cellDisplay = new CellDisplay(new Coordinate(x, y), cell);
				
				add(cellDisplay);
				displayCells.add(cellDisplay);
				cellDisplay.addActionListener(this);
			}
		}
		
		updateCellDisplays();
	}

	public void addCellClickedActionListener(ActionListener actionListener) {
		cellClickedActionListeners.add(actionListener);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CellDisplay cell = (CellDisplay)e.getSource();
		
		for(int i = 0; i < cellClickedActionListeners.size(); i++) {
			ActionEvent event = new ActionEvent(cell, ActionEvent.ACTION_PERFORMED, "Cell");
			cellClickedActionListeners.get(i).actionPerformed(event);
		}
	}
	
	public void updateCellDisplays() {
		for(int i = 0; i < displayCells.size(); i++) {
			displayCells.get(i).updateCellDisplay();
		}
	}
}
