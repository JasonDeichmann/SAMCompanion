package com.example.kayletiu.samcompanion;

/**
 * Created by Jayvee Gabriel on 08/07/2018.
 */

public class Quote {

    private String author;
    private String content;
    private String user;

    public Quote(String user, String content, String author) {
        this.author = author;
        this.user= user;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
}
