package by.epam.webpoject.ezmusic.validator;

/**
 * Created by Антон on 12.08.2016.
 */
public class FindSongReuqestValidator {
    public static boolean validate(String songId) {
        if (songId != null && !songId.isEmpty()) {
            try {
                Long longSongId = Long.parseLong(songId);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }
}
