package com.xs.dormTest.bean;

import java.sql.Timestamp;

public class Visiter {
    private int id ;
    private String name ;
    private String tel ;
    private String reason ;
    private Timestamp time ;
    private String showTime ;

    public Visiter() {
        super();
    }

    public Visiter(int id, String name, String tel, String reason, Timestamp time, String showTime) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.reason = reason;
        this.time = time;
        this.showTime = showTime;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Visiter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", reason='" + reason + '\'' +
                ", time=" + time +
                '}';
    }
}
