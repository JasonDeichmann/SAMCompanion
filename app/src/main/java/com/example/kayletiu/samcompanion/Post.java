package com.example.kayletiu.samcompanion;

import java.util.ArrayList;

/**
 * Created by Jayvee Gabriel on 07/07/2018.
 */

public class Post {

    private int id;
    private String author;
    private String title;
    private String content;
    private ArrayList<Tag> tags ;
    private String datePosted;


    public Post(int id, String author, String title, String content, ArrayList<Tag> tags, String datePosted) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.tags = tags;
        this.title = title;
        this.datePosted = datePosted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }
}
