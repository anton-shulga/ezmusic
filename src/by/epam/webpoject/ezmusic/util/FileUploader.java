package by.epam.webpoject.ezmusic.util;

import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.util.List;

/**
 * Created by Антон on 14.09.2016.
 */
public class FileUploader {
    public static String upload(String path, List<FileItem> itemList){
        String fileName = null;
        try {
            for (FileItem item : itemList) {
                if (!item.isFormField()) {
                     fileName = new File(item.getName()).getName();
                    item.write(new File(path + File.separator + fileName));
                }
            }
        } catch (Exception e) {

        }
        return fileName;

    }
}
