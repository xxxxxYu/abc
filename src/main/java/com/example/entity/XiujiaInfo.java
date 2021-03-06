package com.example.entity;

import javax.persistence.*;

@Table(name = "xiujia_info")
public class XiujiaInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "time")
    private String time;
    @Column(name = "tianshu")
    private String tianshu;
    @Column(name = "liyou")
    private String liyou;
    @Column(name = "userName")
    private String userName;
    @Column(name = "level")
    private Integer level;
    @Column(name = "status")
    private String status;
    @Column(name = "reason")
    private String reason;
    @Column(name = "verifyName")
    private String verifyName;





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTianshu() {
        return tianshu;
    }

    public void setTianshu(String tianshu) {
        this.tianshu = tianshu;
    }

    public String getLiyou() {
        return liyou;
    }

    public void setLiyou(String liyou) {
        this.liyou = liyou;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getVerifyName() {
        return verifyName;
    }

    public void setVerifyName(String verifyName) {
        this.verifyName = verifyName;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }


    @Override
    public String toString() {
        return "XiujiaInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", tianshu='" + tianshu + '\'' +
                ", liyou='" + liyou + '\'' +
                ", userName='" + userName + '\'' +
                ", level=" + level +
                ", status='" + status + '\'' +
                ", reason='" + reason + '\'' +
                ", verifyName='" + verifyName + '\'' +

                '}';
    }
}
