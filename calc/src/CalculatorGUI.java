import javax.swing.*;
import java.awt.*;

public class CalculatorGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");

        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        frame.setLayout(grid);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(new JButton("7"), gbc);
        gbc.gridx = 1;
        frame.add(new JButton("8"), gbc);
        gbc.gridx = 2;
        frame.add(new JButton("9"), gbc);
        gbc.gridx = 3;
        frame.add(new JButton("/"), gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        frame.add(new JButton("4"), gbc);
        gbc.gridx = 1;
        frame.add(new JButton("5"), gbc);
        gbc.gridx = 2;
        frame.add(new JButton("6"), gbc);
        gbc.gridx = 3;
        frame.add(new JButton("*"), gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        frame.add(new JButton("1"), gbc);
        gbc.gridx = 1;
        frame.add(new JButton("2"), gbc);
        gbc.gridx = 2;
        frame.add(new JButton("3"), gbc);
        gbc.gridx = 3;
        frame.add(new JButton("-"), gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        frame.add(new JButton("0"), gbc);
        gbc.gridx = 1;
        frame.add(new JButton("."), gbc);
        gbc.gridx = 2;
        frame.add(new JButton("="), gbc);
        gbc.gridx = 3;
        frame.add(new JButton("+"), gbc);

        gbc.gridy = 0;
        gbc.gridx = 3;
        frame.add(new JLabel("calc output"), gbc);

        frame.setSize(300, 300);
        frame.setPreferredSize(frame.getSize());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    }
}
