package com.example.kayletiu.samcompanion;

/**
 * Created by Jason Deichmann on 7/15/18.
 */

public class UserModel {

    private Integer userID;
    private String username;
    private String password;
    private Integer userType;
    private Integer searching;
    private Integer chatting;
    private Integer chatRoom;

    public UserModel(){}
    public UserModel(Integer userID, String username, String password, Integer userType, Integer searching, Integer chatting, Integer chatRoom){
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.searching = searching;
        this.chatting = chatting;
        this.chatRoom = chatRoom;
    }

    public Integer getUserID(){return userID;}

    public void setUserID(Integer userID){this.userID = userID;}

    public String getUsername(){return username;}

    public void setUsername(String username){this.username = username;}

    public String getPassword(){return password;}

    public void setPassword(String password){this.password = password;}

    public Integer getUserType(){return userType;}

    public void setUserType(Integer userType){this.userType = userType;}

    public Integer getSearching() {return searching;}

    public void setSearching(Integer searching) {this.searching = searching;}

    public Integer getChatting() {return chatting;}

    public void setChatting(Integer chatting) {this.chatting = chatting;}

    public Integer getChatRoom() {return chatRoom;}

    public void setChatRoom(Integer chatRoom) {this.chatRoom = chatRoom;}

}
