package service;


import dto.UserDto;
import entity.User;
import exception.CustomErrorMessages;
import exception.EntityNotExistException;
import mappers.UserMapper;
import repository.UserRepo;
import java.util.logging.Logger;

import static validator.UserValidator.validateCreateNewUserFlow;

public class UserService {

    private final static Logger log = Logger.getLogger(UserService.class.getName());

    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        log.info("User service initialised");
        this.userRepo = userRepo;
    }

    public UserDto getUser(String id) {

        if (id == null || id.equals("")) {
            throw new IllegalArgumentException(CustomErrorMessages.INVALID_ID_MESSAGE);
        }
        User user = userRepo.findUser(id);
        if (user == null) {
            //throw new EntityNotExistException("No user having id " + id + " exists.");
            return null;
        }
        UserDto userDto = UserMapper.entityToDto(user);
        return userDto;
    }


    public UserDto getUserByUsername(String username) {

        if (username == null || username.equals("")) {
            throw new IllegalArgumentException(CustomErrorMessages.INVALID_ID_MESSAGE);
        }

        User user = userRepo.findUserByUsername(username);
        if (user == null) {
            throw new EntityNotExistException("No user having name " + username + " exists.");
        }
        UserDto userDto = UserMapper.entityToDto(user);
        return userDto;
    }


    public boolean userExists(String username) {

        if (username == null || username.equals("")) {
            throw new IllegalArgumentException(CustomErrorMessages.INVALID_USERNAME_MESSAGE);
        }

        User user = userRepo.findUserByUsername(username);
        return user != null;
    }

    public void updateUser(UserDto userDto, String name, String mail, int age, String password) {
        log.info("Successfully updated an user.");
        userDto.setName(name);
        userDto.setMail(mail);
        userDto.setVarsta(age);
        userDto.setPassword(password);

        User user = new User();
        user.setId(userDto.getId());;
        user.setName(userDto.getName());
        user.setMail(userDto.getMail());
        user.setVarsta(userDto.getVarsta());
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());
        user.setAdmin(userDto.isAdmin());
        validateCreateNewUserFlow(user);
        userRepo.updateUser(userDto.getId(), name, mail, age, password);
    }

    public void addUser(UserDto userDto) {
        if(userDto == null) {
            log.warning("Invalid user");
            log.severe("NULL USER");
        }

        User user = new User();
        user.setId(userDto.getId());;
        user.setName(userDto.getName());
        user.setMail(userDto.getMail());
        user.setVarsta(userDto.getVarsta());
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());
        user.setAdmin(userDto.isAdmin());
        validateCreateNewUserFlow(user);
        log.info("Successfully added a new user.");
        log.info(userDto.toString());
        userRepo.insertNewUser(user);
    }
}
