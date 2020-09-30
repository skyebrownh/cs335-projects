import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MemoryGame extends JFrame implements ActionListener
{
    // Core game play objects
    private final Board gameBoard;
    private FlippableCard prevCard1, prevCard2;

    // Labels to display game info
    private final JLabel guessesMadeLabel, matchesMadeLabel;

    // layout objects: Views of the board and the label area
    private final JPanel boardView, labelView;

    // Record keeping
    private int guessesMade = 0;
    private int matchesMade = 0;
    private Timer cardTimer;

    public MemoryGame()
    {
        // Call the base class constructor
        super("Hubble Memory Game");

        // Allocate the interface elements
        JButton restart = new JButton("Restart");
        JButton quit = new JButton("Quit");
        matchesMadeLabel = new JLabel("Matches: 0");
        guessesMadeLabel = new JLabel("Guesses: 0");

        // setup the interface objects (restart, quit) and any event handling they need to perform
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Allocate two major panels to hold interface
        labelView = new JPanel();  // used to hold labels
        boardView = new JPanel();  // used to hold game board

        // get the content pane, onto which everything is eventually added
        Container c = getContentPane();

        // Setup the game board with cards
        gameBoard = new Board(25, this);
        // set cards to back and randomize
        gameBoard.resetBoard();

        // Add the game board to the board layout area
        boardView.setLayout(new GridLayout(5, 5, 2, 0));
        gameBoard.fillBoardView(boardView);

        // Add required interface elements to the "label" JPanel
        labelView.setLayout(new GridLayout(1, 4, 2, 2));
        labelView.add(quit);
        labelView.add(restart);
        labelView.add(matchesMadeLabel);
        labelView.add(guessesMadeLabel);

        // Both panels should now be individually laid out
        // Add both panels to the container
        c.add(labelView, BorderLayout.NORTH);
        c.add(boardView, BorderLayout.SOUTH);

        setSize(745, 550);
        setVisible(true);
    }

    /* Handle anything that gets clicked and that uses MemoryGame as an
     * ActionListener */
    public void actionPerformed(ActionEvent e)
    {
        // Get the currently clicked card from a click event
        FlippableCard currCard = (FlippableCard)e.getSource();

        // reveal the card
        currCard.showFront();

        cardTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // first card logic
                if (prevCard1 == null) {
                    prevCard1 = currCard;

                    // happy face has no effect on guesses and has no match
                    if (prevCard1.imagePath().equals("res/happy-face.jpg")) {
                        prevCard1.setEnabled(false);
                        prevCard1 = null;
                    }
                    // non happy face needs a second guess
                }

                // second card logic
                else {
                    prevCard2 = currCard;

                    if (prevCard1.imagePath().equals(prevCard2.imagePath())) {
                        // MATCH
                        prevCard1.setEnabled(false);
                        prevCard2.setEnabled(false);
                        matchesMade++;
                        matchesMadeLabel.setText(String.format("Matches: %d", matchesMade));
                    }
                    else {
                        // NO MATCH
                        prevCard1.hideFront();
                        prevCard2.hideFront();
                        guessesMade++;
                        guessesMadeLabel.setText(String.format("Guesses: %d", guessesMade));
                    }

                    // reset prevCard1 and prevCard2
                    prevCard1 = null;
                    prevCard2 = null;
                }

                // check for termination state
                if (matchesMade == 12) {
                    JOptionPane.showMessageDialog(null, "YOU WIN!");
                }
            }
        });
        cardTimer.setRepeats(false);
        cardTimer.start();
    }

    private void restartGame()
    {
        guessesMade = 0;
        matchesMade = 0;
        matchesMadeLabel.setText("Matches: 0");
        guessesMadeLabel.setText("Guesses: 0");

        // Clear the boardView and have the gameBoard generate a new layout
        boardView.removeAll();
        gameBoard.resetBoard();
        gameBoard.fillBoardView(boardView);
    }

    public static void main(String[] args)
    {
        MemoryGame M = new MemoryGame();
        M.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });
    }
}
