package by.epam.webpoject.ezmusic.entity;

import java.util.ArrayList;

/**
 * Created by Антон on 28.07.2016.
 */
public class Song {
    private long songId;
    private String name;
    private int year;
    private Album album;
    private ArrayList<Comment> commentList;
    private double cost;
    private String filePath;

}
