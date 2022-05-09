package com.example.entity;

import javax.persistence.*;

@Table(name = "canshu_info")
public class CanshuInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "shangban")
    private String shangban;
    @Column(name = "xiaban")
    private String xiaban;

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


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

}
