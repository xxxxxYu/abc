package com.example.entity;

import javax.persistence.*;

@Table(name = "qingjia_info")
public class QingjiaInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "liyou")
    private String liyou;
    @Column(name = "tianshu")
    private String tianshu;
    @Column(name = "time")
    private String time;
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


    private Long startTime;
    private Long endTime;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLiyou() {
        return liyou;
    }

    public void setLiyou(String liyou) {
        this.liyou = liyou;
    }

    public String getTianshu() {
        return tianshu;
    }

    public void setTianshu(String tianshu) {
        this.tianshu = tianshu;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
        return "QingjiaInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", liyou='" + liyou + '\'' +
                ", tianshu='" + tianshu + '\'' +
                ", time='" + time + '\'' +
                ", userName='" + userName + '\'' +
                ", level=" + level +
                ", status='" + status + '\'' +
                ", reason='" + reason + '\'' +
                ", verifyName='" + verifyName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}
