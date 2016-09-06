package by.epam.webpoject.ezmusic.validator;

/**
 * Created by Антон on 06.09.2016.
 */
public class UserParametersValidator {
    public static boolean validateAddFundsParameters(String moneyAmount) {
        if (moneyAmount != null) {
            try {
                Double.parseDouble(moneyAmount);
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
}
