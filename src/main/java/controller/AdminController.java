package controller;

import dto.GameDto;
import dto.UserDto;

import java.util.Observable;
import service.BookmarkService;
import service.EmailService;
import service.GameService;
import service.UserService;
import utils.ControllerUtils;
import java.util.ArrayList;
import java.util.UUID;

public class AdminController extends Observable {

    private final GameService gameService;
    private final UserService userService;
    private final BookmarkService bookmarkService;

    public AdminController(UserService userService, GameService gameService, BookmarkService bookmarkService) {
        this.userService = userService;
        this.gameService = gameService;
        this.bookmarkService = bookmarkService;
    }

    public void addGame(String team1, String team2, String category) {
        try {
            GameDto gameDto = new GameDto(UUID.randomUUID().toString(),team1,team2,category);
            gameService.addGame(gameDto);
            setChanged();
            notifyObservers(null);
        } catch (Exception e) {
            ControllerUtils.createSwingErrorMessage(e.getMessage());
        }
    }

    public void deleteGame(String team1, String team2) {
        try {
            bookmarkService.removeBookmarks(gameService.getGame(team1,team2));
            gameService.deleteGame(team1, team2);
            setChanged();
            notifyObservers(null);
        } catch (Exception e) {
            ControllerUtils.createSwingErrorMessage(e.getMessage());
        }
    }

    public void sendEmails(String team1, String team2) {
        try {
            GameDto gameDto = gameService.getGame(team1, team2);
            ArrayList<String> userIDs = bookmarkService.getUserIDs(gameDto);
            ArrayList <UserDto> userDtos = new ArrayList<>();

            for (String userID : userIDs)
                userDtos.add(userService.getUser(userID));

            for (UserDto userDto : userDtos)
                EmailService.sendEmail(userDto.getMail(), gameDto.toString());

        } catch (Exception e) {
            ControllerUtils.createSwingErrorMessage(e.getMessage());
        }
    }

    public void updateGame(String team1, String team2, String updatedTeam1, String updatedTeam2, String score1, String score2, String updatedScore1, String updatedScore2, String category, String updatedCategory, String status, String updatedStatus) {
        try {
            gameService.updateGame(team1, team2, updatedTeam1, updatedTeam2, updatedScore1, updatedScore2, updatedCategory, updatedStatus);
            String[] details = new String[] {updatedTeam1,updatedTeam2,score1,score2,updatedScore1,updatedScore2,category,updatedCategory,status,updatedStatus};
            setChanged();
            notifyObservers(details);
        } catch (Exception e) {
            ControllerUtils.createSwingErrorMessage(e.getMessage());
        }
    }

    public ArrayList<GameDto> getGames() {
        try {
            return gameService.getGames();

        } catch (Exception e) {
            ControllerUtils.createSwingErrorMessage(e.getMessage());
            return null;
        }
    }
}
