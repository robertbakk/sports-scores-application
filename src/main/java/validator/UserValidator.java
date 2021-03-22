package validator;

import entity.User;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    public static void validateCreateNewUserFlow(User user) {
        if (user.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is empty/null.");
        }
        if (user.getUsername().length() < 5) {
            throw new IllegalArgumentException("Username must be at least 5 characters.");
        }

        if (user.getVarsta() < 0) {
            throw new IllegalArgumentException("Age must be >0.");
        }

        if (user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is empty/null.");
        }
        if (user.getMail().isEmpty()) {
            throw new IllegalArgumentException("Mail is empty/null.");
        }

        if (!Pattern.compile("^.+@.+\\..+$").matcher(user.getMail()).find()){
            throw new IllegalArgumentException("Mail is invalid.");
        }
    }

}

