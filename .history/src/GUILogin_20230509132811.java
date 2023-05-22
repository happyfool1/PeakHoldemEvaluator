import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;

public class GUILogin {
    private JFrame frame;
    private JTextField userIdField;
    private JPasswordField passwordField;
    private HashMap<String, UserAccount> accounts;

    public GUILogin() {
        accounts = new HashMap<>();
        initialize();
    }

    public void createAccount(String userId, String password) {
        UserAccount newUser = new UserAccount(userId, password);
        accounts.put(userId, newUser);
    }

    public boolean authenticate(String userId, String password) {
        UserAccount user = accounts.get(userId);
        return user != null && user.getPassword().equals(password);
    }

    private void initialize() {
        frame = new JFrame("Login");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(4, 2));

        frame.getContentPane().add(new JLabel("User ID: "));
        userIdField = new JTextField();
        frame.getContentPane().add(userIdField);

        frame.getContentPane().add(new JLabel("Password: "));
        passwordField = new JPasswordField();
        frame.getContentPane().add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userId = userIdField.getText();
                String password = new String(passwordField.getPassword());

                if (authenticate(userId, password)) {
                    JOptionPane.showMessageDialog(frame, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid credentials.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.getContentPane().add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userId = userIdField.getText();
                String password = new String(passwordField.getPassword());

                createAccount(userId, password);
                JOptionPane.showMessageDialog(frame, "Account created successfully!");
            }
        });
        frame.getContentPane().add(registerButton);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public voG();
            }
        });
    }
}