import java.awt.event.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.swing.*;

public class Board
{
    // Array to hold board cards
    private final FlippableCard[] cards;

    public Board(int size, ActionListener AL)
    {
        // Allocate and configure the game board: an array of cards
        // Leave one extra space for the "happy face", added at the end
        cards = new FlippableCard[size];

        // Fill the Cards array
        int imageIdx = 1;

        // Resource loader
        ClassLoader loader = getClass().getClassLoader();

        for (int i = 0; i < (size-1); i += 2) {
            // Load the front image from the resources folder
            String imgPath = "res/hub" + imageIdx + ".jpg";
            ImageIcon img = new ImageIcon(Objects.requireNonNull(loader.getResource(imgPath)));
            imageIdx++;  // get ready for the next pair of cards

            // Setup two cards at a time
            FlippableCard c1 = new FlippableCard(img);
            c1.setImagePath(imgPath);
            FlippableCard c2 = new FlippableCard(img);
            c2.setImagePath(imgPath);

            // Add action listener to cards
            c1.addActionListener(AL);
            c2.addActionListener(AL);

            // Add them to the array
            cards[i] = c1;
            cards[i + 1] = c2;
        }
        // Add the "happy face" image
        String imgPath = "res/happy-face.jpg";
        ImageIcon img = new ImageIcon(Objects.requireNonNull(loader.getResource(imgPath)));
        FlippableCard c1 = new FlippableCard(img);
        c1.addActionListener(AL);
        c1.setImagePath(imgPath);
        cards[size-1] = c1;

        // randomize card positions
        randomize();
    }

    public void fillBoardView(JPanel view)
    {
        for (FlippableCard c : cards) {
            view.add(c);
        }
    }

    public void randomize() {
        List<FlippableCard> cardList = Arrays.asList(cards);
        Collections.shuffle(cardList);
        cardList.toArray(cards);
    }

    public void resetBoard()
    {
        // reset the flipped cards and randomize the card positions
        for (FlippableCard c : cards) {
            c.setEnabled(true);
            c.hideFront();
        }
        randomize();
    }
}
