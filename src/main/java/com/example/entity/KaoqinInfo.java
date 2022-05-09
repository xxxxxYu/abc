package com.example.entity;

import javax.persistence.*;

@Table(name = "kaoqin_info")
public class KaoqinInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "shangban")
    private String shangban;
    @Column(name = "xiaban")
    private String xiaban;
    @Column(name = "shangbanStatus")
    private String shangbanStatus;
    @Column(name = "xiabanStatus")
    private String xiabanStatus;
    @Column(name = "userId")
    private Long userId;

    private int status;

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

    public String getShangban() {
        return shangban;
    }

    public void setShangban(String shangban) {
        this.shangban = shangban;
    }

    public String getXiaban() {
        return xiaban;
    }

    public void setXiaban(String xiaban) {
        this.xiaban = xiaban;
    }

    public String getShangbanStatus() {
        return shangbanStatus;
    }

    public void setShangbanStatus(String shangbanStatus) {
        this.shangbanStatus = shangbanStatus;
    }

    public String getXiabanStatus() {
        return xiabanStatus;
    }

    public void setXiabanStatus(String xiabanStatus) {
        this.xiabanStatus = xiabanStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

}
