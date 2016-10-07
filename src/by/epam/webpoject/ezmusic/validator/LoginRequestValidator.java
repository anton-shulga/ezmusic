package by.epam.webpoject.ezmusic.validator;

/**
 * Created by Антон on 02.08.2016.
 */
public class LoginRequestValidator {
    public static boolean validate(String login, String password) {
        return login != null && password != null;
    }
}
