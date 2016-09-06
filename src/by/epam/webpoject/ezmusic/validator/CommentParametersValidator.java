package by.epam.webpoject.ezmusic.validator;

/**
 * Created by Антон on 06.09.2016.
 */
public class CommentParametersValidator {
    public static boolean validateCreateParameters(String songId, String text, String rating) {
        if(songId == null){
            return false;
        }else {
            try{
                Long.parseLong(songId);
            }catch (NumberFormatException e) {
                return false;
            }
        }
        if(text == null || text.isEmpty()){
            return false;
        }
        if(rating == null){
            return false;
        }else {
            try{
                Integer.parseInt(songId);
            }catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
}
