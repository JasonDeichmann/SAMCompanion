package com.example.kayletiu.samcompanion;

/**
 * Created by Jayvee Gabriel on 08/07/2018.
 */

public class Reply {

    private int postID;
    private String content;

    public Reply(int postID, String content) {
        this.postID = postID;
        this.content = content;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
