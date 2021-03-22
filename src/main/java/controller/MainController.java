package controller;



public class MainController { ;

    public void loginInit(LoginController loginController) {
        new LoginView(loginController);
    }
    public void signUpInit(SignupController signupController, LoginController loginController) {
        new SignupView(signupController, loginController);
    }

}
