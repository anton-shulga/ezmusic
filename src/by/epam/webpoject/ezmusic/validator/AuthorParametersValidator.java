package by.epam.webpoject.ezmusic.validator;

/**
 * Created by Антон on 21.08.2016.
 */
public class AuthorParametersValidator {
    public static boolean validateCreateParameters(String[] albumIds, String[] songIds, String name, String country, String photoPath) {
        try {
            if (albumIds != null) {
                for (String albumId : albumIds) {
                    Long.parseLong(albumId);
                }
            }
            if(songIds != null){
                for (String songId : songIds) {
                    Long.parseLong(songId);
                }
            }
        }catch (NumberFormatException e){
            return false;
        }
        if(name == null || name.isEmpty()){
            return false;
        }
        if(country == null || country.isEmpty()){
            return false;
        }
        if(photoPath == null || photoPath.isEmpty()){
            return false;
        }
        return true;
    }

    public static boolean validateDeleteParameters(String authorId) {
        if(authorId != null){
            try {
                Long.parseLong(authorId);
                return true;
            }catch (NumberFormatException e){
                return false;
            }
        }else {
            return false;
        }
    }

    public static boolean validateFindParameters(String authorId) {
        if(authorId != null){
            try {
                Long.parseLong(authorId);
                return true;
            }catch (NumberFormatException e){
                return false;
            }
        }else {
            return false;
        }
    }

    public static boolean validateUpdateParameters(String authorId, String[] albumIds, String[] songIds, String name, String country, String photoPath) {
        if(authorId == null){
            return false;
        }else {
            try {
                Long.parseLong(authorId);
            }catch (NumberFormatException e){
                return false;
            }
        }
        try {
            if (songIds != null) {
                for (String songId : songIds) {
                    Long.parseLong(songId);
                }
            }
            if(albumIds != null){
                for (String albumId : albumIds) {
                    Long.parseLong(albumId);
                }
            }
        }catch (NumberFormatException e){
            return false;
        }
        if(name == null || name.isEmpty()){
            return false;
        }

        if(country == null || country.isEmpty()){
            return false;
        }
        if(photoPath == null || photoPath.isEmpty()){
            return false;
        }
        return true;
    }
}
