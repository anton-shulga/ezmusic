package by.epam.webpoject.ezmusic.validator;

/**
 * Created by Антон on 06.09.2016.
 */
public class UserParametersValidator {
    public static boolean validateAddFundsParameters(String moneyAmount) {
        if (moneyAmount != null) {
            try {
                Double.parseDouble(moneyAmount);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean validateCheckLoginAvailabilityParameters(String login) {
        if(login != null && !login.isEmpty()){
            return true;
        }else {
            return false;
        }

    }

    public static boolean validateLoginParameters(String login, String password) {
        if(login == null || login.isEmpty()){
            return false;
        }else {
            return true;
        }
    }
}
