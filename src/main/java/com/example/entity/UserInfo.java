package com.example.entity;

import javax.persistence.*;

@Table(name = "user_info")
public class UserInfo extends Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "nickName")
    private String nickName;
    @Column(name = "sex")
    private String sex;
    @Column(name = "age")
    private Integer age;
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    private String email;
    @Column(name = "cardId")
    private String cardId;
    @Column(name = "level")
    private Integer level;
    @Column(name = "status")
    private int status;


    private String statusName;

    private int totalNumber;
    private int lateNumber;
    private int earlyNumber;


    private Long firstDay;
    private Long lastDay;

    private int qingJiaNumber;
    private int xiuJiaNumber;

    public String getStatusName() {
        return statusName;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", birthday='" + birthday + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", cardId='" + cardId + '\'' +
                ", level=" + level +
                ", status=" + status +
                ", statusName='" + statusName + '\'' +
                ", totalNumber=" + totalNumber +
                ", lateNumber=" + lateNumber +
                ", earlyNumber=" + earlyNumber +
                ", firstDay=" + firstDay +
                ", lastDay=" + lastDay +
                '}';
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getLateNumber() {
        return lateNumber;
    }

    public void setLateNumber(int lateNumber) {
        this.lateNumber = lateNumber;
    }

    public int getEarlyNumber() {
        return earlyNumber;
    }

    public void setEarlyNumber(int earlyNumber) {
        this.earlyNumber = earlyNumber;
    }

    public Long getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(Long firstDay) {
        this.firstDay = firstDay;
    }

    public Long getLastDay() {
        return lastDay;
    }

    public void setLastDay(Long lastDay) {
        this.lastDay = lastDay;
    }

    public int getQingJiaNumber() {
        return qingJiaNumber;
    }

    public void setQingJiaNumber(int qingJiaNumber) {
        this.qingJiaNumber = qingJiaNumber;
    }

    public int getXiuJiaNumber() {
        return xiuJiaNumber;
    }

    public void setXiuJiaNumber(int xiuJiaNumber) {
        this.xiuJiaNumber = xiuJiaNumber;
    }
}
