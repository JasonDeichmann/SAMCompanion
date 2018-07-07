package com.example.kayletiu.samcompanion;

/**
 * Created by Jason Deichmann on 7/7/18.
 */

public class ChatModel {

    private String message;


    public ChatModel(){}

    public ChatModel(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }
}
