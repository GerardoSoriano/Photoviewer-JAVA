/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photoviewer.model;

import java.sql.Date;

/**
 *
 * @author Gerardo Soriano
 */
public class User {
    private int id;
    private String fullname;
    private String username;
    private String email;
    private String pass;
    private String description;
    private Date birthday;
    private boolean gender;
    private String country;
    private String city;
    private String avatar;
    private String cover;
    private boolean privacity;

    public User(String fullname, String username, String email, String pass, String description, Date birthday, boolean gender, String country, String city, String avatar, String cover, boolean privacity) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.pass = pass;
        this.description = description;
        this.birthday = birthday;
        this.gender = gender;
        this.country = country;
        this.city = city;
        this.avatar = avatar;
        this.cover = cover;
        this.privacity = privacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public boolean isPrivacity() {
        return privacity;
    }

    public void setPrivacity(boolean privacity) {
        this.privacity = privacity;
    }
    
    
}
