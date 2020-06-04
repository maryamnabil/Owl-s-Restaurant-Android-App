package com.example.chefrecipes;

public class User {
    private String fullname, email, password, id, phone,confirmPassword;

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public User(String email, String password,String confirmPassword ,String phone , String fullname ){
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
        this.confirmPassword=confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getFullname() {
        return fullname;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
