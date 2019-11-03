package com.example.topicosespecialesproyecto;

public class SaveRegister {

    // variables del registro a usar
    private String UserId;
    private String Email;
    private String UserName;
    private String password;

    // creacion de getters y setter


    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString(){
     return UserName + password;
    }

}
