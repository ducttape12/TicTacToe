package tictactoe;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class CellDisplay extends JButton {

	private static final long serialVersionUID = -3237773521573653419L;
	
	private Coordinate coordinate;
	private Cell cell;
	
	private static final String PLAYER_1_DISPLAY = "X";
	private static final String PLAYER_2_DISPLAY = "O";
	private static final String NONE_DISPLAY = "";
	
	public CellDisplay(Coordinate coordinate, Cell cell) {
		super();
		
		this.coordinate = coordinate;
		this.cell = cell;
		
		Font currentFont = getFont();
		Font biggerFont = new Font(currentFont.getFontName(), Font.PLAIN, 48);
		setFont(biggerFont);
	}
	
	public Coordinate getCoordinate() {
		return coordinate;
	}
	
	public void updateCellDisplay() {
		switch(cell.getMark()) {
			case PLAYER_1:
				setText(PLAYER_1_DISPLAY);
				setBackground(Color.CYAN);
				setOpaque(true);
				setBorderPainted(false);
				break;
			case PLAYER_2:
				setText(PLAYER_2_DISPLAY);
				setBackground(Color.YELLOW);
				setOpaque(true);
				setBorderPainted(false);
				break;
			case NONE:
				setText(NONE_DISPLAY);
				break;
		}
	}
}
