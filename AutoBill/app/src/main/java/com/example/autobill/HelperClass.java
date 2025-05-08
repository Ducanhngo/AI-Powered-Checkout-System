package com.example.autobill;

public class HelperClass {

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    String email;
    String username;

    public HelperClass(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    String password;

    public HelperClass() {
    }
}
