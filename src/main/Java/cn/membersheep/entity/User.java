package cn.membersheep.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

public class User  extends BaseEntity{

    private static final long serialVersionUID = -8085521094887078430L;
    private String username;
    private String password;
    private Integer id;
    private String avatar;
    private Integer gender;
    private String phone;
    private String email;
    private String salt;
    private Integer isdelete;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", avatar='" + avatar + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", salt='" + salt + '\'' +
                ", isDelete=" + isdelete +
                '}';
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getIsDelete() {
        return isdelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isdelete = isDelete;
    }
}
