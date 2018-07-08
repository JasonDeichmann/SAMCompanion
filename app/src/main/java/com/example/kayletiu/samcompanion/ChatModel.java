package com.example.kayletiu.samcompanion;

/**
 * Created by Jason Deichmann on 7/7/18.
 */

public class ChatModel {

    private String message;
    private Integer userID;


    public ChatModel(){}

    public ChatModel(String message, Integer userID){
        this.message = message;
        this.userID = userID;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public Integer getUserID(){return userID;}

    public void setUserID(Integer userID){this.userID = userID;}
}
