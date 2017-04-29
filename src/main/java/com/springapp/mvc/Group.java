package com.springapp.mvc;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Group{
    private int groupId;
    private String nameGroup;
    private String curator;
    private String speciality;
    private String speHead;
    private int departmentId;

    public Group() {}

    public Group(ResultSet rs)throws SQLException {
        setGroupId(rs.getInt(1));
        setNameGroup(rs.getString(2));
        setCurator(rs.getString(3));
        setSpeciality(rs.getString(4));
        setSpeHead(rs.getString(5));
        setDepartmentId(rs.getInt(6));
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getSpeHead() {
        return speHead;
    }

    public void setSpeHead(String speHead) {
        this.speHead = speHead;
    }

    public String getCurator() {
        return curator;
    }

    public void setCurator(String curator) {
        this.curator = curator;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String toString() {
        return nameGroup;
    }
}
