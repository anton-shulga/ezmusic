package by.epam.webpoject.ezmusic.validator;

import by.epam.webpoject.ezmusic.entity.Order;

/**
 * Created by Антон on 21.08.2016.
 */
public class SongParametersValidator {
    public static boolean validateCreateParameters(String[] authorIds, String[] albumIds, String name, String year, String filePath, String cost) {
        try {
            if (authorIds != null) {
                for (String authorId : authorIds) {
                    Long.parseLong(authorId);
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

        if(cost == null){
            return false;
        }else {
            try {
                Double.parseDouble(cost);
            }catch (NumberFormatException e){
                return false;
            }
        }
        return true;
    }

    public static boolean validateDeleteParameters(String songId) {
        if(songId != null){
            try {
                Long.parseLong(songId);
                return true;
            }catch (NumberFormatException e){
                return false;
            }
        }else {
            return false;
        }
    }

    public static boolean validateFindJsonParameters(String[] albumIds) {
        if(albumIds != null){
            try{
                for (String albumId : albumIds) {
                    Long.parseLong(albumId);
                }
            }catch (NumberFormatException e){
                return false;
            }
        }
        return true;
    }

    public static boolean validateFindParameters(String songId) {
        if(songId != null){
            try {
                Long.parseLong(songId);
                return true;
            }catch (NumberFormatException e){
                return false;
            }
        }else {
            return false;
        }
    }

    public static boolean validateUpdateParameters(String[] albumIds, String[] authorIds, String songId, String name, String year, String filePath,  String cost) {
        if(songId == null){
            return false;
        }else {
            try {
                Long.parseLong(songId);
            }catch (NumberFormatException e){
                return false;
            }
        }
        try {
            if (authorIds != null) {
                for (String authorId : authorIds) {
                    Long.parseLong(authorId);
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

        if(cost == null){
            return false;
        }else {
            try {
                Double.parseDouble(cost);
            }catch (NumberFormatException e){
                return false;
            }
        }
        return true;
    }

    public static boolean validateDeleteFromCartParameters(String songId, Order cart) {
        if(songId != null) {
            try {
                Long.parseLong(songId);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if(cart != null){
            return true;
        }else {
            return false;
        }
    }
}
