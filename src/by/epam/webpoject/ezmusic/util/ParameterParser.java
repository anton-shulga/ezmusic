package by.epam.webpoject.ezmusic.util;

/**
 * Created by Антон on 17.08.2016.
 */
public class ParameterParser {
    public static Double parseDouble(String value) {
        return Double.parseDouble(value);
    }

    public static Long parseLong(String value) {
        return Long.parseLong(value);
    }

    public static Long[] parseLongArray(String[] selectedAlbumIds) {
        if (selectedAlbumIds != null) {
            Long[] longIds = new Long[selectedAlbumIds.length];

            int i = 0;

            for (String str : selectedAlbumIds) {
                longIds[i] = Long.parseLong(str.trim());
                i++;
            }
            return longIds;
        } else {
            return null;
        }
    }

    public static int parseInt(String value) {
        return Integer.parseInt(value);
    }
}
