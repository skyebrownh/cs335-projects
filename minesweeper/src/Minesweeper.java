import javax.swing.*;
//import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Minesweeper extends JFrame implements ActionListener {
    // Core gameplay objects
    private Grid gameBoard;
    private JPanel boardView;
//    private JLabel timerLabel;
    private final JLabel numMinesLabel = new JLabel("Mines: 0");

    // TODO: Record keeping
//    private Timer gameTimer = new Timer(1000, new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) { }
//    });

    // map indexes to grid sizes
    private final Map<Integer, Integer> gridSizeMap = new HashMap<Integer, Integer>() {
        {
            put(0, 3); put(1, 4); put(2, 5); put(3, 6); put(4, 7);
            put(5, 8); put(6, 9); put(7, 10); put(8, 11); put(9, 12);
        }
    };

    // default constructor
    public Minesweeper() {
        super("Minesweeper");

        // get content pane
        Container c = getContentPane();

        // Allocate interface elements
        // grid size selector
        String[] gridSizes = {"3x3", "4x4", "5x5", "6x6", "7x7", "8x8", "9x9", "10x10", "11x11", "12x12"};
        JComboBox<String> gridSize = new JComboBox<>(gridSizes);
        gridSize.setSelectedIndex(0);
        gridSize.addActionListener((ActionEvent e) -> {
            int selected = gridSize.getSelectedIndex();
            int gridMapValue = gridSizeMap.get(selected);
            // set grid to this size
            boardView.removeAll();
            gameBoard = new Grid(gridMapValue, this);
            numMinesLabel.setText(String.format("Mines: %d", gameBoard.getNumMines()));
            boardView.setLayout(new GridLayout(gridMapValue, gridMapValue));
            gameBoard.fillBoardView(boardView);
            boardView.revalidate();
        });

        // difficulty selector JComboBox
        String[] difficulties = {"Beginner (4x4 - 4M)", "Intermediate (8x8 - 15M)", "Expert (12x12 - 40M)"};
        JComboBox<String> difficulty = new JComboBox<>(difficulties);
        difficulty.setSelectedIndex(0);
        difficulty.addActionListener((ActionEvent e) -> {
            int selected = difficulty.getSelectedIndex();
            boardView.removeAll();

            // set grid size and mine count
            if (selected == 0) {
                // beginner
                gameBoard = new Grid(4, 4, this);
                boardView.setLayout(new GridLayout(4, 4));
            } else if (selected == 1) {
                // intermediate
                gameBoard = new Grid(8, 15, this);
                boardView.setLayout(new GridLayout(8, 8));
            } else if (selected == 2) {
                // expert
                gameBoard = new Grid(12, 40, this);
                boardView.setLayout(new GridLayout(12, 12));
            }

            numMinesLabel.setText(String.format("Mines: %d", gameBoard.getNumMines()));
            gameBoard.fillBoardView(boardView);
            boardView.revalidate();
        });

        // Menu
        // restart
        JButton restart = new JButton("Restart");
        restart.addActionListener((ActionEvent e) -> {
            restartGame();  // FIXME
        });
        // quit
        JButton quit = new JButton("Quit");
        quit.addActionListener((ActionEvent e) -> {
                System.exit(0);
        });
        // help
        JButton help = new JButton("Help");
        help.addActionListener((ActionEvent e) -> {
                JOptionPane.showMessageDialog(c,
                        "Win the game by revealing all the cells that aren't mines (M!)");
        });

        // populate menu
        JPanel menuView = new JPanel();
        menuView.add(numMinesLabel);
        menuView.add(restart);
        menuView.add(gridSize);
        menuView.add(difficulty);
        menuView.add(quit);
        menuView.add(help);

        // populate gameBoard
        boardView = new JPanel();
        gameBoard = new Grid(3, this);
        numMinesLabel.setText(String.format("Mines: %d", gameBoard.getNumMines()));
        boardView.setLayout(new GridLayout(3, 3));
        gameBoard.fillBoardView(boardView);

        // add grid, menu to container
        c.add(menuView, BorderLayout.NORTH);
        c.add(boardView, BorderLayout.CENTER);

        // set frame size and show
        setSize(800, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        Minesweeper M = new Minesweeper();
        M.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });
    }

    public void actionPerformed(ActionEvent e) {
        // get clicked cell
        GridCell currCell = (GridCell) e.getSource();

        // if mine, reveal all, stop timer, end game
        if (currCell.isMine()) {
            // first click should never be mine!
            if (gameBoard.numRevealed() == 0) {
                // move mine if first click
                gameBoard.moveMine(currCell);
                // reveal cell
                currCell.reveal();
            } else {
                gameOver();
            }
        }
//        else if (currCell.getAdjacentMines() == 0) {
//            // TODO: CLEARING
//            ArrayList<GridCell> stack = new ArrayList<>();
//            stack.add(currCell);
//
//            while (!stack.isEmpty()) {
//                // pop stack
//                GridCell cell = stack.get(0);  // FIXME
//                // reveal cell
//                cell.reveal();
//
//                GridCell[] neighbors = gameBoard.getNeighbors(cell);
//                for (GridCell neighbor : neighbors) {
//                    if (!neighbor.isMine()) {
//                        stack.add(neighbor);
//                    }
//                }
//            }
//        }
        else {
            // reveal cell
            currCell.reveal();
        }
    }

    private void restartGame() {
        gameBoard.resetBoard();
        numMinesLabel.setText(String.format("Mines: %d", gameBoard.getNumMines()));
        gameBoard.fillBoardView(boardView);
        boardView.revalidate();
    }

    private void gameOver() {
        gameBoard.revealAll();
        // TODO: stop timer
    }
}
