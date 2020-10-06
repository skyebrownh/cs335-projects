import javax.swing.*;
import java.awt.*;

public class GridCell extends JButton {
    // x, y position in Grid
    private Point position;
    // number of adjacent mines to this cell
    private int adjacentMines;
    // True if this cell is a mine, False otherwise
    private boolean isMine;

    // default constructor
    public GridCell() { super(); }

    // custom constructor
    public GridCell(Point position, int adjacentMines, boolean isMine) {
        super();
        setPosition(position);
        setAdjacentMines(adjacentMines);
        setIsMine(isMine);
    }

    // reveal cell
    public void reveal() {
        // show M! for mine or adjacentMines
        if (!isMine && adjacentMines > 0) {
            setText(String.format("%d", this.adjacentMines));
        } else {
            setText("M!");
        }
        // adjacentMines == 0 does not show anything
        // disable this button cell
        setEnabled(false);
    }

    // reset cell
    public void reset() {
        setAdjacentMines(0);
        setIsMine(false);
        setText(null);
        // enable this button again
        setEnabled(true);
    }

    // Private Point: position
    public Point getPosition() { return position; }
    public void setPosition(Point position) { this.position = position; }

    // Private int: adjacentMines
    public int getAdjacentMines() { return adjacentMines; }
    public void setAdjacentMines(int adjacentMines) { this.adjacentMines = adjacentMines; }

    // Private boolean: isMine
    public boolean isMine() { return isMine; }
    public void setIsMine(boolean isMine) { this.isMine = isMine; }
}
