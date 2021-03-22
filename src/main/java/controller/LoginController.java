package controller;

import exception.CustomErrorMessages;
import repository.BookmarkRepo;
import repository.GameRepo;
import repository.UserRepo;
import service.BookmarkService;
import service.GameService;
import service.UserService;
import utils.ControllerUtils;
import java.util.ArrayList;

public class LoginController {

    private final UserService userService;
    private final GameService gameService;
    private final BookmarkService bookmarkService;
    private ArrayList<AdminView> adminViews = new ArrayList<>();
    private ArrayList<UserView> userViews = new ArrayList<>();

    public LoginController(UserService userService, GameService gameService, BookmarkService bookmarkService) {
        this.userService = userService;
        this.gameService = gameService;
        this.bookmarkService = bookmarkService;
    }

    public void login(String username, String password) {
        if (password.isEmpty()) {
            ControllerUtils.createSwingErrorMessage(CustomErrorMessages.PASSWORD_EMPTY);
        } else {

            try {
                if (password.equals(userService.getUserByUsername(username).getPassword())) {
                    if (userService.getUserByUsername(username).isAdmin()) {
                        adminViews.add(new AdminView(userService,gameService,bookmarkService));
                        for (UserView userView : userViews)
                            adminViews.get(adminViews.size() - 1).addUserView(userView);
                    } else {
                        userViews.add(new UserView(userService.getUserByUsername(username),userService,gameService,bookmarkService));
                        for (AdminView adminView : adminViews)
                            adminView.addUserView(userViews.get(userViews.size() - 1));
                    }
                } else ControllerUtils.createSwingErrorMessage(CustomErrorMessages.WRONG_PASSWORD_MESSAGE);

            } catch (Exception e) {
                ControllerUtils.createSwingErrorMessage(username + ": " + CustomErrorMessages.INVALID_USERNAME_MESSAGE);
            }
        }
    }
}
