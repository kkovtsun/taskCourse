package com.springapp.mvc;

import java.util.Collection;

public class MainFrameForm{
    private int year;
    private int groupId;
    private int departmentId;
    private Collection departments;
    private Collection groups;
    private Collection students;

    public int getDepartmentId() {return departmentId;}

    public void setDepartmentId(int departmentId) {this.departmentId = departmentId;}

    public Collection getDepartments() {return departments;}

    public void setDepartments(Collection departments) {this.departments = departments;}

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroups(Collection groups) {
        this.groups = groups;
    }

    public Collection getGroups() {
        return groups;
    }

    public void setStudents(Collection students) {
        this.students = students;
    }

    public Collection getStudents() {
        return students;
    }
}
