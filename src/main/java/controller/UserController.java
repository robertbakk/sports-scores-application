package controller;

import dto.BookmarkDto;
import dto.GameDto;
import dto.UserDto;
import service.BookmarkService;
import service.GameService;
import service.UserService;
import utils.ControllerUtils;
import java.util.ArrayList;
import java.util.UUID;

public class UserController {

    private final GameService gameService;
    private final UserService userService;
    private final BookmarkService bookmarkService;

    public UserController(UserService userService, GameService gameService, BookmarkService bookmarkService) {
        this.userService = userService;
        this.gameService = gameService;
        this. bookmarkService = bookmarkService;
    }

    public ArrayList<GameDto> getGames() {
        try {
            return gameService.getGames();

        } catch (Exception e) {
            ControllerUtils.createSwingErrorMessage(e.getMessage());
            return null;
        }
    }

    public ArrayList<GameDto> getBookmarkedGames(UserDto userDto) {
        try {
            ArrayList<BookmarkDto> bookmarks = bookmarkService.getBookmarkedGames(userDto);
            ArrayList<GameDto> games = new ArrayList<>();
            for (BookmarkDto b : bookmarks)
                games.add(gameService.getGame(b.getGameID()));
            return games;

        } catch (Exception e) {
            ControllerUtils.createSwingErrorMessage(e.getMessage());
            return null;
        }
    }

    public void addBookmark(UserDto userDto, String team1, String team2){
        try {
            if (!bookmarkService.bookmarkExists(userDto.getId(), gameService.getGame(team1, team2).getId())) {
                BookmarkDto bookmarkDto = new BookmarkDto(UUID.randomUUID().toString(), userDto.getId(), gameService.getGame(team1, team2).getId());
                bookmarkService.addBookmark(bookmarkDto);
            }


        } catch (Exception e) {
            ControllerUtils.createSwingErrorMessage(e.getMessage());
        }
    }

    public void removeBookmark(UserDto userDto, String team1, String team2){
        try {
            bookmarkService.removeBookmark(userDto.getId(), gameService.getGame(team1, team2));

        } catch (Exception e) {
            ControllerUtils.createSwingErrorMessage(e.getMessage());
        }
    }

    public void updateUser(UserDto userDto, String name, String mail, int age, String password) {
        try {
            userService.updateUser(userDto, name, mail, age, password);

        } catch (Exception e) {
            ControllerUtils.createSwingErrorMessage(e.getMessage());
        }
    }
}
