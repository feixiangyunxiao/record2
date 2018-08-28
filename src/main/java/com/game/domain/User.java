package com.game.domain;

public class User {
    private Integer id;

    private String name;

    private String password;

    private String email;

    private Integer flag;

    private String photo;

    private Integer qqnumber;

    private String reserve;

    private String activecode;

    private int activeFlag;

    private String resetcode;


    public String getResetcode() {
        return resetcode;
    }

    public void setResetcode(String resetcode) {
        this.resetcode = resetcode;
    }

    public String getActivecode() {
        return activecode;
    }

    public void setActivecode(String activecode) {
        this.activecode = activecode;
    }

    public int getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(int activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public Integer getQqnumber() {
        return qqnumber;
    }

    public void setQqnumber(Integer qqnumber) {
        this.qqnumber = qqnumber;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve == null ? null : reserve.trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", flag=" + flag +
                ", photo='" + photo + '\'' +
                ", qqnumber=" + qqnumber +
                ", reserve='" + reserve + '\'' +
                ", activecode='" + activecode + '\'' +
                ", activeFlag=" + activeFlag +
                ", resetcode='" + resetcode + '\'' +
                '}';
    }
}