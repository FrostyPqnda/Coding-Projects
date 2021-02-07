import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

public class GUIApp implements ActionListener {
    private static JLabel label;
    private static JTextField userText;
    private static JButton button;
    private static JLabel success;

    static void createGUIApp()
    {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setTitle("Password Generator");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);
        panel.setBackground(new Color(31, 31, 31));

        label = new JLabel("Password Length [8, 20]");
        label.setForeground(new Color(55, 169, 250));
        label.setBounds(10, 20, 250, 50);
        label.setFont(new Font("Oswald", Font.PLAIN, 25));
        panel.add(label);

        userText = new JTextField(100);
        userText.setBounds(250, 20, 165, 40);
        panel.add(userText);

        button = new JButton("Generate password");
        button.setBounds(10, 80, 150, 25);
        button.addActionListener(new GUIApp());
        panel.add(button);

        success = new JLabel("");
        success.setBounds(10, 150, 400, 50);
        success.setForeground(new Color(55, 169, 250));
        success.setFont(new Font("Oswald", Font.PLAIN, 25));
        panel.add(success);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //int passwordLength = Integer.parseInt(userText.getText());
        success.setText("Generated password: ");
    }
}
