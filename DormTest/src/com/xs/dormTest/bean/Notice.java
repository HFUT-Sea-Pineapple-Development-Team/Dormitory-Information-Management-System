package com.xs.dormTest.bean;

import java.sql.Date;

public class Notice {
    private int id ;
    private String title ;
    private String content ;
    private String name ;
    private String tel ;
    private int receivers_num ;
    private String stu_id ;
    private Date time ;

    public Notice() {
        super();
    }

    public Notice(int id, String title, String content, String name, String tel, int receivers_num) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.name = name;
        this.tel = tel;
        this.receivers_num = receivers_num;
        this.stu_id = stu_id ;
        this.time = time ;
    }

    public int getId() {
        return id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getReceivers_num() {
        return receivers_num;
    }

    public void setReceivers_num(int receivers_num) {
        this.receivers_num = receivers_num;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", receivers_num=" + receivers_num +
                ", stu_id='" + stu_id + '\'' +
                ", time=" + time +
                '}';
    }
}
