package com.zzxx.exam.bean;

public class User {
    private String id;
    private String name;
    private String password;
    private String tel;
    private String mail;

    public User(){

    }

    public User(String id, String name, String password, String tel, String mail) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.tel = tel;
        this.mail = mail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name=" + name +
                ", password=" + password +
                ", tel=" + tel +
                ", mail=" + mail +
                '}';
    }
}
