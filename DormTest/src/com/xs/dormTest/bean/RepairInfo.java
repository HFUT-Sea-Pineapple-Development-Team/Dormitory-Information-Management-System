package com.xs.dormTest.bean;

import java.sql.Date;

public class RepairInfo {
    private int id ;
    private String stu_id ;
    private String name ;
    private int build_id ;
    private int room_id ;
    private Date report_time ;
    private String reason ;
    private Date repair_time;
    private String idea ;
    private String is_satisfied ;
    private String tel ;
    private String is_finished ;
    private String repair_report ;

    public RepairInfo() {
        super();
    }

    public RepairInfo(int id, String stu_id, String name, int build_id, int room_id,String tel ,
                      Date report_time, String reason, Date repair_time, String idea,
                      String is_satisfied,String is_finished,String repair_report) {
        this.id = id;
        this.stu_id = stu_id;
        this.name = name;
        this.build_id = build_id;
        this.room_id = room_id;
        this.report_time = report_time;
        this.reason = reason;
        this.repair_time = repair_time;
        this.idea = idea;
        this.is_satisfied = is_satisfied;
        this.tel = tel ;
        this.is_finished = is_finished ;
        this.repair_report = repair_report ;
    }

    public String getIs_finished() {
        return is_finished;
    }

    public void setIs_finished(String is_finished) {
        this.is_finished = is_finished;
    }

    public String getRepair_report() {
        return repair_report;
    }

    public void setRepair_report(String repair_report) {
        this.repair_report = repair_report;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBuild_id() {
        return build_id;
    }

    public void setBuild_id(int build_id) {
        this.build_id = build_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public Date getReport_time() {
        return report_time;
    }

    public void setReport_time(Date report_time) {
        this.report_time = report_time;
    }

    public void setReason(String reason) {
        this.reason = reason ;
    }

    public String getReason () {
        return reason ;
    }

    public Date getRepair_time() {
        return repair_time;
    }

    public void setRepair_time(Date repair_time) {
        this.repair_time = repair_time;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public String getIs_satisfied() {
        return is_satisfied;
    }

    public void setIs_satisfied(String is_satisfied) {
        this.is_satisfied = is_satisfied;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "RepairInfo{" +
                "id=" + id +
                ", stu_id='" + stu_id + '\'' +
                ", name='" + name + '\'' +
                ", build_id=" + build_id +
                ", room_id=" + room_id +
                ", report_time=" + report_time +
                ", reason='" + reason + '\'' +
                ", repair_time=" + repair_time +
                ", idea='" + idea + '\'' +
                ", is_satisfied='" + is_satisfied + '\'' +
                ", tel='" + tel + '\'' +
                ", is_finished='" + is_finished + '\'' +
                ", repair_report='" + repair_report + '\'' +
                '}';
    }
}
