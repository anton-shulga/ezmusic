package by.epam.webpoject.ezmusic.validator;

/**
 * Created by Антон on 21.08.2016.
 */
public class AlbumParametersValidator {
    public static boolean validateCreateParameters(String[] selectedSongIds, String[] selectedAuthorIds, String name, String year){
        try {
            if (selectedSongIds != null) {
                for (String songId : selectedSongIds) {
                    Long.parseLong(songId);
                }
            }
            if(selectedAuthorIds != null){
                for (String authorId : selectedAuthorIds) {
                    Long.parseLong(authorId);
                }
            }
        }catch (NumberFormatException e){
            return false;
        }
        if(name == null || name.isEmpty()){
            return false;
        }
        if(year == null){
            return false;
        }else {
            try{
                Integer.parseInt(year);
            }catch (NumberFormatException e){
                return false;
            }
        }
        return true;
    }

    public static boolean validateDeleteParameters(String albumId) {
        if(albumId != null){
            try {
                Long.parseLong(albumId);
                return true;
            }catch (NumberFormatException e){
                return false;
            }
        }else {
            return false;
        }
    }

    public static boolean validateFindParameters(String albumId) {
        if(albumId != null){
            try {
                Long.parseLong(albumId);
                return true;
            }catch (NumberFormatException e){
                return false;
            }
        }else {
            return false;
        }
    }

    public static boolean validateFindJsonParameters(String[] authorIds) {
        if(authorIds != null){
            try{
                for (String authorId : authorIds) {
                    Long.parseLong(authorId);
                }
            }catch (NumberFormatException e){
                return false;
            }
        }
        return true;
    }

    public static boolean validateUpdateParameters(String albumId, String[] selectedAuthorIds, String[] selectedSongIds, String name, String year, String filePath) {
        if(albumId == null){
            return false;
        }else {
            try {
                Long.parseLong(albumId);
            }catch (NumberFormatException e){
                return false;
            }
        }
        try {
            if (selectedSongIds != null) {
                for (String songId : selectedSongIds) {
                    Long.parseLong(songId);
                }
            }
            if(selectedAuthorIds != null){
                for (String authorId : selectedAuthorIds) {
                    Long.parseLong(authorId);
                }
            }
        }catch (NumberFormatException e){
            return false;
        }
        if(name == null || name.isEmpty()){
            return false;
        }
        if(year == null){
            return false;
        }else {
            try{
                Integer.parseInt(year);
            }catch (NumberFormatException e){
                return false;
            }
        }
        if(filePath == null || filePath.isEmpty()){
            return false;
        }
        return true;
    }
}
