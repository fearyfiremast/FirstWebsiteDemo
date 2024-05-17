package com.example.demo.models;

public class User {
    private String name;
    private String password;
    private int size;
    public User() {
    }
    public User(String name, String password, int size) {
        this.name = name;
        this.password = password;
        this.size = size;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    

}
