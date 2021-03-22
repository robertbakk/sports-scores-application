package controller;

import dto.UserDto;
import javax.swing.*;
import java.awt.*;


public class SettingsView {
    private final UserController userController;
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private UserDto userDto;

    public JFrame frame;

    public JButton btnConfirm = new JButton("Confirm");
    public JButton btnBack = new JButton("Back");
    private JTextField name;
    private JTextField age;
    private JTextField mail;
    private JPasswordField password;


    public SettingsView(UserDto userDto, UserController userController) {
        this.userController = userController;
        this.userDto = userDto;
        frame = new JFrame("Flashscore - Settings");
        initialize();
        frame.setVisible(true);
    }

    private void initialize () {
        JLabel lblName = new JLabel("Full name");
        JLabel lblMail = new JLabel("E-mail address");
        JLabel lblVarsta = new JLabel("Age");
        JLabel lblPassword = new JLabel("Password");

        name = new JTextField(10);
        age = new JTextField(10);
        mail = new JTextField(10);
        password = new JPasswordField(10);
        name.setText(userDto.getName());
        age.setText(userDto.getVarsta()+"");
        mail.setText(userDto.getMail());
        password.setText(userDto.getPassword());

        JPanel panel = new JPanel(new GridLayout(5,1));
        JPanel panel1 = new JPanel(new FlowLayout());
        JPanel panel2 = new JPanel(new FlowLayout());
        JPanel panel3 = new JPanel(new FlowLayout());
        JPanel panel4 = new JPanel(new FlowLayout());
        JPanel panel5 = new JPanel(new FlowLayout());

        panel1.add(lblName);
        panel1.add(name);
        panel2.add(lblMail);
        panel2.add(mail);
        panel3.add(lblVarsta);
        panel3.add(age);
        panel4.add(lblPassword);
        panel4.add(password);
        panel5.add(btnBack);
        panel5.add(btnConfirm);
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        panel.add(panel5);
        frame.add(panel);

        frame.setSize(300,350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

        btnConfirm.addActionListener( e -> {
            userController.updateUser(userDto, name.getText(), mail.getText(), Integer.parseInt(age.getText()),  String.valueOf(password.getPassword()));
            frame.setVisible(false);
            frame.dispose();
        });

        btnBack.addActionListener( e -> {
            frame.setVisible(false);
            frame.dispose();
        });

    }
}
