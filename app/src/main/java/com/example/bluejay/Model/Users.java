package com.example.bluejay.Model;

public class Users {

    String userName;
    String Name;
    String userEmail;
    String userPassword;

    public Users(String userName, String name, String userEmail, String userPassword) {
        this.userName = userName;
        Name = name;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public Users() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
