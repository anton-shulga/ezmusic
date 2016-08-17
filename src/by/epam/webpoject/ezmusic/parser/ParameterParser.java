package by.epam.webpoject.ezmusic.parser;

/**
 * Created by Антон on 17.08.2016.
 */
public class ParameterParser {
    public static Long parseLong(String value){
        return Long.parseLong(value);
    }
    public static Long[] parseLongArray(String[] selectedAlbumIds) {
        Long[] longIds = new Long[selectedAlbumIds.length];
        int i=0;
        for(String str : selectedAlbumIds){
            longIds[i]= Long.parseLong(str.trim());
            i++;
        }
        return longIds;
    }
}
