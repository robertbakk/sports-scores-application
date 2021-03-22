package controller;

import repository.BookmarkRepo;
import repository.GameRepo;
import repository.UserRepo;
import service.BookmarkService;
import service.GameService;
import service.UserService;
import javax.swing.*;
import java.awt.*;

public class MainView {

    private final MainController mainController;
    private final SignupController signupController;
    private final LoginController loginController;

    public JFrame frame;
    public JPanel panel;
    public JLabel label = new JLabel("Choose an action:");
    public JButton btnLogin = new JButton("Login");
    public JButton btnSignUp = new JButton("Sign up");
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public MainView(UserService userService, GameService gameService, BookmarkService bokmarkService) {
        this.mainController = new MainController();
        this.loginController = new LoginController(userService,gameService,bokmarkService);
        this.signupController = new SignupController(userService);
        frame = new JFrame("Flashscore");
        initialize();
        frame.setVisible(true);
    }

    private void initialize() {

        panel = new JPanel(new GridLayout(2,1));
        JPanel panel1 = new JPanel(new GridBagLayout());
        JPanel panel2 = new JPanel(new FlowLayout());

        panel1.add(label);
        panel2.add(btnLogin);
        panel2.add(btnSignUp);
        panel.add(panel1);
        panel.add(panel2);
        frame.add(panel);
        frame.setSize(300,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        btnLogin.addActionListener( e -> mainController.loginInit(loginController));
        btnSignUp.addActionListener( e -> mainController.signUpInit(signupController, loginController));

        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
    }


}
