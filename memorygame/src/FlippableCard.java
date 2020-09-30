import javax.swing.*;
import java.util.Objects;

public class FlippableCard extends JButton
{
    // Resource loader
    private final ClassLoader loader = getClass().getClassLoader();

    // Card front icon
    private Icon front;
    // Card back image
    private final Icon back = new ImageIcon(Objects.requireNonNull(loader.getResource("res/Back.jpg")));

    // ID + Name + ImagePath
    private int id;
    private String customName;
    private String imagePath;

    // Default constructor
    public FlippableCard() { super(); }

    // Constructor with card front initialization
    public FlippableCard(ImageIcon frontImage)
    {
        super();
        front = frontImage;
        super.setIcon(front);
    }

    // Set the image used as the front of the card
    public void setFrontImage(ImageIcon frontImage) { front = frontImage; }

    // Card flipping functions
    public void showFront() { setIcon(front); }
    public void hideFront() { setIcon(back); }

    public boolean isHidden() { return getIcon() == back; }

    // Metadata: ID number
    public int id() { return id; }
    public void setID(int i) { id = i; }

    // Metadata: Custom name
    public String customName() { return customName; }
    public void setCustomName(String s) { customName = s; }

    // Metadata: Image path
    public String imagePath() { return imagePath; }
    public void setImagePath(String s) { imagePath = s; }
}
