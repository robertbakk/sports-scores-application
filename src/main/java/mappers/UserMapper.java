package mappers;

import dto.UserDto;
import entity.User;

public class UserMapper {

    public static UserDto entityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setMail(user.getMail());
        userDto.setVarsta(user.getVarsta());
        userDto.setPassword(user.getPassword());
        userDto.setUsername(user.getUsername());
        userDto.setAdmin(user.isAdmin());
        return userDto;
    }

}
