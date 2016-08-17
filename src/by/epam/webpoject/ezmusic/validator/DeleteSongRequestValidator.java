package by.epam.webpoject.ezmusic.validator;

/**
 * Created by Антон on 14.08.2016.
 */
public class DeleteSongRequestValidator {
    public static boolean validate(String songId){
        if(songId != null && !songId.isEmpty()){
            try{
                Long.parseLong(songId);
            }catch (NumberFormatException e){
                return false;
            }
            return true;
        }else {
            return false;
        }
    }
}
