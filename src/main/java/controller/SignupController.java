package controller;

import dto.UserDto;
import exception.CustomErrorMessages;
import service.UserService;
import utils.ControllerUtils;
import java.util.UUID;

public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    public void signUp(String name, String username, String password, String mail, String varsta, LoginController loginController) {
        if (varsta.isEmpty())
            ControllerUtils.createSwingErrorMessage(CustomErrorMessages.AGE_NOT_SET);
        else {
            try {
                int age;
                age = Integer.parseInt(varsta);
                try {
                    if (!userService.userExists(username)) {
                        UserDto userDto = new UserDto(UUID.randomUUID().toString(), name, username, mail, age, password, false);
                        userService.addUser(userDto);
                        loginController.login(username, password);

                    } else ControllerUtils.createSwingErrorMessage(CustomErrorMessages.USERNAME_TAKEN);


                } catch (Exception e) {
                    ControllerUtils.createSwingErrorMessage(e.getMessage());
                }
            } catch (Exception e) {
                ControllerUtils.createSwingErrorMessage(CustomErrorMessages.INVALID_AGE);
            }
        }
    }
}
