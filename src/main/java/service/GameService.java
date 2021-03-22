package service;

import dto.GameDto;
import dto.UserDto;
import entity.Game;
import entity.User;
import exception.CustomErrorMessages;
import exception.EntityNotExistException;
import mappers.GameMapper;
import repository.GameRepo;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Logger;

public class GameService {

    private final static Logger log = Logger.getLogger(GameService.class.getName());

    private GameRepo gameRepo;

    public GameService(GameRepo gameRepo) {
        log.info("Game service initialised");
        this.gameRepo = gameRepo;
    }

    public GameDto getGame(String id) {

        if (id == null || id.equals("")) {
            throw new IllegalArgumentException(CustomErrorMessages.INVALID_ID_MESSAGE);
        }
        Game game = gameRepo.findGame(id);
        if (game == null) {
            throw new EntityNotExistException("No game having id " + id + " exists.");
        }
        GameDto gameDto = GameMapper.entityToDto(game);
        return gameDto;
    }

    public ArrayList<GameDto> getGames() {

        List<Game> games = gameRepo.getGames();
        ArrayList<GameDto> gamesDto = new ArrayList<GameDto>();
        for (Game g : games) {
            GameDto gameDto = GameMapper.entityToDto(g);
            gamesDto.add(gameDto);
        }
        return gamesDto;
    }

    public GameDto getGame(String team1, String team2) {
        Game game = gameRepo.getGame(team1, team2);
        GameDto gameDto = GameMapper.entityToDto(game);
        return gameDto;
    }


    public void addGame(GameDto gameDto) {
        if(gameDto==null){
            log.warning("Invalid game");
            log.severe("NULL GAME");
        }

        log.info("Successfully added a new game.");
        log.info(gameDto.toString());

        Game game = new Game();
        game.setId(gameDto.getId());
        game.setTeam1(gameDto.getTeam1());
        game.setTeam2(gameDto.getTeam2());
        game.setCategory(gameDto.getCategory());
        game.setStatus(gameDto.getStatus());
        game.setScoreTeam1(gameDto.getScoreTeam1());
        game.setScoreTeam2(gameDto.getScoreTeam2());

        gameRepo.insertNewGame(game);
    }

    public void deleteGame(String team1, String team2) {
        log.info("Successfully deleted a game.");

        gameRepo.deleteGame(team1, team2);
    }

    public void updateGame(String team1, String team2, String updatedTeam1, String updatedTeam2, String updatedScore1, String updatedScore2, String updatedCategory, String updatedStatus) {
        log.info("Successfully updated a game.");

        gameRepo.updateGame(team1, team2, updatedTeam1, updatedTeam2, updatedScore1, updatedScore2, updatedCategory, updatedStatus);
    }

}
