package com.example.kayletiu.samcompanion;

/**
 * Created by Jason Deichmann on 7/15/18.
 */

public class UserModel {

    private Integer userID;
    private String username;
    private String password;
    private Integer userType;

    public UserModel(){}
    public UserModel(Integer userID, String username, String password, Integer userType){
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public Integer getUserID(){return userID;}

    public void setUserID(Integer userID){this.userID = userID;}

    public String getUsername(){return username;}

    public void setUsername(String username){this.username = username;}

    public String getPassword(){return password;}

    public void setPassword(String password){this.password = password;}

    public Integer getUserType(){return userType;}

    public void setUserType(Integer userType){this.userType = userType;}

}