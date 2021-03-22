package mappers;

import dto.GameDto;
import entity.Game;

public class GameMapper {

    public static GameDto entityToDto(Game game) {
        GameDto gameDto = new GameDto();
        gameDto.setId(game.getId());
        gameDto.setTeam1(game.getTeam1());
        gameDto.setTeam2(game.getTeam2());
        gameDto.setCategory(game.getCategory());
        gameDto.setStatus(game.getStatus());
        gameDto.setScoreTeam1(game.getScoreTeam1());
        gameDto.setScoreTeam2(game.getScoreTeam2());
        return gameDto;
    }

}
