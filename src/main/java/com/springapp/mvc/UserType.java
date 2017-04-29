package com.springapp.mvc;

public class UserType {
    private int id;
    private String username;
    private String password;
    private String status;

    public UserType() {}

    public UserType(String username, String password, String status) {
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public UserType(int id, String username, String password, String status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
