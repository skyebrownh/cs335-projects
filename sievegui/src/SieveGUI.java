import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;

public class SieveGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        JLabel label = new JLabel("Enter a number between 1 and 10,000");
        label.setBounds(20, 50, 275, 50);

        JTextField textField = new JTextField();
        textField.setBounds(50,100,150,50);
        JLabel result = new JLabel();
        result.setBounds(250, 100, 100, 50);

        JButton submitButton = new JButton("Check");
        submitButton.setBounds(50, 200, 100, 50);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tfValue = textField.getText();
                if (tfValue.isEmpty()) {
                    result.setText("INVALID VALUE");
                    return;
                }

                int tfInt = Integer.parseInt(tfValue);
                if (tfInt < 1 || tfInt > 10_000) {
                    result.setText("INVALID VALUE");
                    return;
                }

                boolean[] primeArray = sieve();
                if (primeArray[tfInt])
                    result.setText("PRIME");
                else
                    result.setText("NOT PRIME");
            }
        });

        frame.add(label);
        frame.add(textField);
        frame.add(result);
        frame.add(submitButton);

        frame.setLayout(null);
        frame.setSize(400, 300);
        frame.setPreferredSize(frame.getSize());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    }

    static boolean[] sieve() {
        boolean[] primes = new boolean[10_000];
        Arrays.fill(primes, Boolean.TRUE);
        // 0 and 1 are not prime
        primes[0] = false;
        primes[1] = false;

        for (int i = 2; i < 10_000; i++) {
            if (primes[i] && isPrime(i)) {
                // set all indices that are multiples of this prime number to false
                for (int j = i + 1; j < 10_000; j++) {
                    if (j % i == 0)
                        primes[j] = false;
                }
            }
        }

        return primes;
    }

    private static boolean isPrime(int n) {
        if (n <= 1)
            return false;

        for (int i = 2; i < n; i++) {
            if (n % i == 0)
                return false;
        }

        return true;
    }
}
