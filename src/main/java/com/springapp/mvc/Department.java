package com.springapp.mvc;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Department {
    private int departmentId;
    private String nameDept;
    private String head;

    public Department() {}

    public Department(ResultSet rs)throws SQLException {
        setDepartmentId(rs.getInt(1));
        setNameDept(rs.getString(2));
        setHead(rs.getString(3));
    }

    @Override
    public String toString() {
        return nameDept;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getNameDept() {
        return nameDept;
    }

    public void setNameDept(String nameDept) {
        this.nameDept = nameDept;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
}
