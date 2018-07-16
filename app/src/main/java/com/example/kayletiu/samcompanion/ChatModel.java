package com.example.kayletiu.samcompanion;

/**
 * Created by Jason Deichmann on 7/7/18.
 */

public class ChatModel {

    private String message;
    private Integer userID;
    private Integer chatRoom;


    public ChatModel(){}

    public ChatModel(String message, Integer userID, Integer chatRoom){
        this.message = message;
        this.userID = userID;
        this.chatRoom = chatRoom;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public Integer getUserID(){return userID;}

    public void setUserID(Integer userID){this.userID = userID;}

    public Integer getChatRoom(){return chatRoom;}

    public void setChatRoom(Integer chatRoom){this.chatRoom = chatRoom;}
}
