package by.epam.webpoject.ezmusic.validator;

/**
 * Created by Антон on 02.08.2016.
 */
public class RegisterRequestValidator {
    public static boolean validate(String login, String password, String username, String surname, String email, String phone){
       return login.isEmpty() && password.isEmpty() && username.isEmpty() && surname.isEmpty() && email.isEmpty() && phone.isEmpty();
    }
}
