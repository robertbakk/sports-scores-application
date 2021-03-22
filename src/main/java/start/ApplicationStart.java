package start;

import controller.MainView;
import dto.UserDto;
import repository.BookmarkRepo;
import repository.GameRepo;
import repository.UserRepo;
import service.BookmarkService;
import service.GameService;
import service.UserService;
import java.io.IOException;


public class ApplicationStart {

    public static void main(String[] args) {

        UserService userService = new UserService(new UserRepo());
        GameService gameService = new GameService(new GameRepo());
        BookmarkService bokmarkService = new BookmarkService(new BookmarkRepo());
        new MainView(userService,gameService,bokmarkService);

       if (userService.getUser("1") == null) {
           userService.addUser(new UserDto ("1", "admin", "admin", "flashscore.adm@gmail.com", 0, "1234", true));
        }
    }
}
