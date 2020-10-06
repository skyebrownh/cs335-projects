import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.ArrayList;

public class Grid {
    private static final Random rand = new Random();
    private GridCell[][] cells;
    private int numMines = 0;

    // generate random number of mines based on the grid size (min: 2, max: 1/2 grid size)
    public static int generateMineCount(int gridSize) {
        // rand.nextInt(max - min + 1) + min
        if (gridSize == 3)
            return rand.nextInt((gridSize / 2) - 2 + 2) + 2;
        return rand.nextInt((gridSize / 2) - 2 + 1) + 2;
    }

    // default constructor
    public Grid() { super(); }

    // custom constructor 1
    public Grid(int size, ActionListener AL) {
        cells = new GridCell[size][size];

        // generate cells
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                GridCell newCell = new GridCell(new Point(i, j), 0, false);
                newCell.addActionListener(AL);
                cells[i][j] = newCell;
            }
        }

        // assign mines based on size
        int mines = Grid.generateMineCount(cells.length);
        setNumMines(mines);
        assignMines(mines);
    }

    // custom constructor 2
    public Grid(int size, int mineCount, ActionListener AL) {
        cells = new GridCell[size][size];

        // generate cells
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                GridCell newCell = new GridCell(new Point(i, j), 0, false);
                newCell.addActionListener(AL);
                cells[i][j] = newCell;
            }
        }

        // assign mines based on size
        setNumMines(mineCount);
        assignMines(mineCount);
    }

    // fill a JPanel GridLayout with cells
    public void fillBoardView(JPanel view) {
        // add cells to view
        for (GridCell[] cell : cells) {
            for (GridCell gc : cell) {
                view.add(gc);
            }
        }
    }

    // reveal all cells
    public void revealAll() {
        for (GridCell[] cell : cells) {
            for (GridCell gc : cell) {
                gc.reveal();
            }
        }
    }

    // count number of revealed cells
    public int numRevealed() {
        int count = 0;
        for (GridCell[] cell : cells) {
            for (GridCell gc : cell) {
                if (!gc.getText().equals("")) {
                    count++;
                }
            }
        }
        return count;
    }

    // move a mine to a different location
    public void moveMine(GridCell cell) {
        int gridSize = cells.length;

        // remove mine from this cell
        cell.setIsMine(false);

        // generate new mine
        int x = rand.nextInt(gridSize);
        int y = rand.nextInt(gridSize);

        // continually generate new positions until a non-mine position is found
        while (cells[x][y].isMine()) {
            x = rand.nextInt(gridSize);
            y = rand.nextInt(gridSize);
        }

        // assign this cell as a mine
        cells[x][y].setIsMine(true);
        cells[x][y].setAdjacentMines(0);

        // calculate adjacent mines for first cell
        // count # of neighbors that are mines
        int cellMineCount = 0;

        // get neighbors of each cell
        GridCell[] neighbors = getNeighbors(cell);
        for (GridCell neighbor : neighbors) {
            if (neighbor.isMine())
                cellMineCount++;
        }

        // assign value to cell.adjacentMines
        cell.setAdjacentMines(cellMineCount);
    }

    // assign random mines based on a given mine count
    private void assignMines(int mineCount) {
        int gridSize = cells.length;

        for (int i = 0; i < mineCount; i++) {
            int x = rand.nextInt(gridSize);
            int y = rand.nextInt(gridSize);

            // continually generate new positions until a non-mine position is found
            while (cells[x][y].isMine()) {
                x = rand.nextInt(gridSize);
                y = rand.nextInt(gridSize);
            }

            // assign this cell as a mine
            cells[x][y].setIsMine(true);
        }

        // set adjacent mines based on previous assignment
        for (GridCell[] cell : cells) {
            for (GridCell gc : cell) {
                // count # of neighbors that are mines
                int cellMineCount = 0;

                // get neighbors of each cell
                GridCell[] neighbors = getNeighbors(gc);
                for (GridCell neighbor : neighbors) {
                    if (neighbor.isMine())
                        cellMineCount++;
                }

                // assign value to cell.adjacentMines
                gc.setAdjacentMines(cellMineCount);
            }
        }
    }

    // get all adjacent cells of a given cell
    public GridCell[] getNeighbors(GridCell cell) {
        ArrayList<GridCell> neighbors = new ArrayList<>();
        int x = cell.getPosition().x;
        int y = cell.getPosition().y;

        // there are 8 possible neighbors for each cell (corners have 3, edges have 5, all else have 8)
        GridCell topCell = getCellFromPosition(x-1, y);
        GridCell rightCell = getCellFromPosition(x, y+1);
        GridCell bottomCell = getCellFromPosition(x+1, y);
        GridCell leftCell = getCellFromPosition(x, y-1);
        GridCell topRightCell = getCellFromPosition(x-1, y+1);
        GridCell bottomRightCell = getCellFromPosition(x+1, y+1);
        GridCell bottomLeftCell = getCellFromPosition(x+1, y-1);
        GridCell topLeftCell = getCellFromPosition(x-1, y-1);

        GridCell[] possibleNeighbors = {
                topCell, rightCell, bottomCell, leftCell,
                topRightCell, bottomRightCell, bottomLeftCell, topLeftCell
        };

        for (GridCell pNeighbor : possibleNeighbors) {
            if (pNeighbor != null) {
                neighbors.add(pNeighbor);
            }
        }

        return neighbors.toArray(new GridCell[neighbors.size()]);
    }

    // return a GridCell (or null) from a given x, y position
    private GridCell getCellFromPosition(int x, int y) {
        for (GridCell[] cell : cells) {
            for (GridCell gc : cell) {
                if (gc.getPosition().x == x && gc.getPosition().y == y) {
                    return gc;
                }
            }
        }
        return null;
    }

    // reset cells and randomize their positions
    public void resetBoard() {
        // reset all cells
        for (GridCell[] cell: cells) {
            for (GridCell gc: cell) {
                gc.reset();
            }
        }
        // randomize cell positions
        int mines = Grid.generateMineCount(cells.length);
        setNumMines(mines);
        assignMines(mines);
    }

    // Private int: numMines
    public int getNumMines() { return numMines; }
    public void setNumMines(int numMines) { this.numMines = numMines; }
}
