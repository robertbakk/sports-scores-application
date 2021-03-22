package controller;

import javax.swing.*;
import java.awt.*;

public class SignupView {

    private SignupController signupController;
    private LoginController loginController;

    public JFrame frame;
    private JTextField username;
    private JTextField name;
    private JTextField mail;
    private JTextField varsta;
    private JPasswordField password;
    public JButton btnBack = new JButton("Back");
    public JButton btnSignUp = new JButton("Sign Up");
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public SignupView(SignupController signupController, LoginController loginController) {
        this.signupController = signupController;
        this.loginController = loginController;
        frame = new JFrame("Flashscore - Sign up");
        initialize();
        frame.setVisible(true);
    }

    private void initialize() {

        JLabel lblUsername = new JLabel("Username");
        JLabel lblName = new JLabel("Full name");
        JLabel lblPassword = new JLabel("Password");
        JLabel lblMail = new JLabel("E-mail address");
        JLabel lblVarsta = new JLabel("Age");

        username = new JTextField(10);
        name = new JTextField(10);
        password = new JPasswordField(10);
        mail = new JTextField(10);
        varsta = new JTextField(10);


        JLabel lblNewLabel = new JLabel("Fill out your information");
        lblNewLabel.setFont(new Font("Franklin Gothic Demi", Font.BOLD | Font.ITALIC, 16));


        JPanel panel = new JPanel(new GridLayout(7,1));
        JPanel panel1 = new JPanel(new GridBagLayout());
        JPanel panel2 = new JPanel(new FlowLayout());
        JPanel panel3 = new JPanel(new FlowLayout());
        JPanel panel4 = new JPanel(new FlowLayout());
        JPanel panel5 = new JPanel(new FlowLayout());
        JPanel panel6 = new JPanel(new FlowLayout());
        JPanel panel7 = new JPanel(new FlowLayout());

        panel1.add(lblNewLabel);
        panel2.add(lblUsername);
        panel2.add(username);
        panel3.add(lblPassword);
        panel3.add(password);
        panel4.add(lblName);
        panel4.add(name);
        panel5.add(lblMail);
        panel5.add(mail);
        panel6.add(lblVarsta);
        panel6.add(varsta);
        panel7.add(btnBack);
        panel7.add(btnSignUp);
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        panel.add(panel5);
        panel.add(panel6);
        panel.add(panel7);
        frame.add(panel);

        frame.setSize(300,350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);


        btnSignUp.addActionListener( e -> {
            signupController.signUp(name.getText(), username.getText(), String.valueOf(password.getPassword()), mail.getText(), varsta.getText(), loginController);
            name.setText("");
            username.setText("");
            password.setText("");
            mail.setText("");
            varsta.setText("");
        });

        btnBack.addActionListener( e -> {
            frame.setVisible(false);
            frame.dispose();
        });
    }

}
