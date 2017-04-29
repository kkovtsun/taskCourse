package com.springapp.mvc;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ManagementSystem{
    private static Connection con;
    private static ManagementSystem instance;

    private ManagementSystem() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/students";
            con = DriverManager.getConnection(url, "root", "root");
        } catch (ClassNotFoundException e) {
            throw new Exception(e);
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    public static synchronized ManagementSystem getInstance() throws Exception {
        if (instance == null) {
            instance = new ManagementSystem();
        }
        return instance;
    }

    public List getGroups() throws SQLException {
        List groups = new ArrayList();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT group_id, groupName, curator, speciality, speHead FROM groups");
        while (rs.next()) {
            Group gr = new Group();
            gr.setGroupId(rs.getInt(1));
            gr.setNameGroup(rs.getString(2));
            gr.setCurator(rs.getString(3));
            gr.setSpeciality(rs.getString(4));
            gr.setSpeHead(rs.getString(5));
            groups.add(gr);
        }
        rs.close();
        stmt.close();
        return groups;
    }

    public List getDepartments() throws SQLException {
        List departments = new ArrayList();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT department_id, nameDept, head FROM departments");
        while (rs.next()) {
            Department dp = new Department();
            dp.setDepartmentId(rs.getInt(1));
            dp.setNameDept(rs.getString(2));
            dp.setHead(rs.getString(3));
            departments.add(dp);
        }
        rs.close();
        stmt.close();
        return departments;
    }

    public Collection getAllStudents() throws SQLException {
        Collection students = new ArrayList();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT student_id, firstName, patronymic, surName, "
                + "sex, dateOfBirth, group_id, educationYear FROM students ORDER BY surName, firstName, patronymic");
        while (rs.next()) {
            Student st = new Student(rs);
            students.add(st);
        }
        rs.close();
        stmt.close();
        return students;
    }

    public Collection getStudentsFromGroup(Group group, int year) throws SQLException {
        Collection students = new ArrayList();
        PreparedStatement stmt = con.prepareStatement("SELECT student_id, firstName, patronymic, surName, "
                + "sex, dateOfBirth, group_id, educationYear FROM students "
                + "WHERE group_id =  ? AND  educationYear =  ? "
                + "ORDER BY surName, firstName, patronymic");
        stmt.setInt(1, group.getGroupId());
        stmt.setInt(2, year);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Student st = new Student(rs);
            students.add(st);
        }
        rs.close();
        stmt.close();
        return students;
    }

    public Collection getGroupsFromDepartment(Department department) throws SQLException {
        Collection groups = new ArrayList();
        PreparedStatement stmt = con.prepareStatement("SELECT group_id, groupName, curator, speciality, "
                + "speHead, department_id FROM groups "
                + "WHERE department_id =  ?");
        stmt.setInt(1, department.getDepartmentId());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Group gr = new Group(rs);
            groups.add(gr);
        }
        rs.close();
        stmt.close();
        stmt.close();
        return groups;
    }

    public List getUsers() throws SQLException {
        List users = new ArrayList();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id, username, password, status FROM users");
        while (rs.next()) {
            UserType u = new UserType();
            u.setId(rs.getInt(1));
            u.setUsername(rs.getString(2));
            u.setPassword(rs.getString(3));
            u.setStatus(rs.getString(4));
            users.add(u);
        }
        rs.close();
        stmt.close();
        return users;
    }

    public List getModerators() throws SQLException {
        List users = new ArrayList();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id, username, password, status FROM moderators");
        while (rs.next()) {
            UserType u = new UserType();
            u.setId(rs.getInt(1));
            u.setUsername(rs.getString(2));
            u.setPassword(rs.getString(3));
            u.setStatus(rs.getString(4));
            users.add(u);
        }
        rs.close();
        stmt.close();
        return users;
    }

    public List getAdmins() throws SQLException {
        List users = new ArrayList();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id, username, password, status FROM admins");
        while (rs.next()) {
            UserType u = new UserType();
            u.setId(rs.getInt(1));
            u.setUsername(rs.getString(2));
            u.setPassword(rs.getString(3));
            u.setStatus(rs.getString(4));
            users.add(u);
        }
        rs.close();
        stmt.close();
        return users;
    }

    public Student getStudentById(int studentId) throws SQLException {
        Student student = null;
        PreparedStatement stmt = con.prepareStatement("SELECT student_id, firstName, patronymic, surName,"
                + "sex, dateOfBirth, group_id, educationYear FROM students WHERE student_id = ?");
        stmt.setInt(1, studentId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            student = new Student(rs);
        }
        rs.close();
        stmt.close();
        return student;
    }

    public void logOut() throws SQLException {
        PreparedStatement stmt = con.prepareStatement("DELETE FROM active");
        stmt.execute();
    }

    public void moveStudentsToGroup(Group oldGroup, int oldYear, Group newGroup, int newYear) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("UPDATE students SET group_id =  ?, educationYear=? "
                + "WHERE group_id =  ? AND  educationYear = ?");
        stmt.setInt(1, newGroup.getGroupId());
        stmt.setInt(2, newYear);
        stmt.setInt(3, oldGroup.getGroupId());
        stmt.setInt(4, oldYear);
        stmt.execute();
    }

    public void removeStudentsFromGroup(Group group, int year) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("DELETE FROM students WHERE group_id = ? AND educationYear = ?");
        stmt.setInt(1, group.getGroupId());
        stmt.setInt(2, year);
        stmt.execute();
    }

    public void insertUserType(UserType user) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO active "
                + "(username, password, status)"
                + "VALUES( ?,  ?,  ?)");
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, user.getStatus());
        stmt.execute();
    }

    public void insertUser(UserType user) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO users "
                + "(username, password, status)"
                + "VALUES( ?,  ?,  ?)");
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, user.getStatus());
        stmt.execute();
    }

    public void insertDepartment(Department department) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO departments "
                + "(nameDept, head)"
                + "VALUES( ?, ?)");
        stmt.setString(1, department.getNameDept());
        stmt.setString(2, department.getHead());
        stmt.execute();
    }

    public void deleteDepartment(Department department) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("DELETE FROM departments WHERE department_id =  ?");
        stmt.setInt(1, department.getDepartmentId());
        stmt.execute();
    }

    public Department getDepartmentById(int departmentId) throws SQLException {
        Department department = null;
        PreparedStatement stmt = con.prepareStatement("SELECT department_id, nameDept, head FROM departments WHERE department_id = ?");
        stmt.setInt(1, departmentId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            department = new Department(rs);
        }
        rs.close();
        stmt.close();
        return department;
    }

    public void updateDepartment(Department department) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("UPDATE departments "
                + "SET nameDept=?, head=? WHERE department_id=?");
        stmt.setString(1, department.getNameDept());
        stmt.setString(2, department.getHead());
        stmt.setInt(3, department.getDepartmentId());
        stmt.execute();
    }

    public void insertGroup(Group group) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO groups "
                + "(groupName, curator, speciality, speHead, department_id)"
                + "VALUES( ?,  ?,  ?, ?, ?)");
        stmt.setString(1, group.getNameGroup());
        stmt.setString(2, group.getCurator());
        stmt.setString(3, group.getSpeciality());
        stmt.setString(4, group.getSpeHead());
        stmt.setString(5, String.valueOf(group.getDepartmentId()));
        stmt.execute();
    }

    public void updateGroup(Group group) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("UPDATE groups "
                + "SET groupName=?, curator=?, speciality=?, speHead=? WHERE group_id=?");
        stmt.setString(1, group.getNameGroup());
        stmt.setString(2, group.getCurator());
        stmt.setString(3, group.getSpeciality());
        stmt.setString(4, group.getSpeHead());
        stmt.setInt(5, group.getGroupId());
        stmt.execute();
    }

    public Group getGroupById(int groupId) throws SQLException {
        Group group = null;
        PreparedStatement stmt = con.prepareStatement("SELECT group_id, groupName, curator, speciality, speHead, department_id FROM groups WHERE group_id = ?");
        stmt.setInt(1, groupId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            group = new Group(rs);
        }
        rs.close();
        stmt.close();
        return group;
    }

    public void insertModerator(UserType user) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO moderators "
                + "(username, password, status)"
                + "VALUES( ?,  ?,  ?)");
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, user.getStatus());
        stmt.execute();
    }

    public void deleteModerator(UserType user) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("DELETE FROM moderators WHERE id =  ?");
        stmt.setInt(1, user.getId());
        stmt.execute();
    }

    public void deleteAdmin(UserType user) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("DELETE FROM admins WHERE id =  ?");
        stmt.setInt(1, user.getId());
        stmt.execute();
    }

    public void insertAdmin(UserType user) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO admins "
                + "(username, password, status)"
                + "VALUES( ?,  ?,  ?)");
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, user.getStatus());
        stmt.execute();
    }

    public void insertStudent(Student student) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO students "
                + "(firstName, patronymic, surName, sex, dateOfBirth, group_id, educationYear)"
                + "VALUES( ?,  ?,  ?,  ?,  ?,  ?,  ?)");
        stmt.setString(1, student.getFirstName());
        stmt.setString(2, student.getPatronymic());
        stmt.setString(3, student.getSurName());
        stmt.setString(4, student.getSex());
        stmt.setDate(5, new Date(student.getDateOfBirth().getTime()));
        stmt.setInt(6, student.getGroupId());
        stmt.setInt(7, student.getEducationYear());
        stmt.execute();
    }

    public void updateStudent(Student student) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("UPDATE students "
                + "SET firstName=?, patronymic=?, surName=?, sex=?, dateOfBirth=?, group_id=?,"
                + "educationYear=? WHERE student_id=?");
        stmt.setString(1, student.getFirstName());
        stmt.setString(2, student.getPatronymic());
        stmt.setString(3, student.getSurName());
        stmt.setString(4, student.getSex());
        stmt.setDate(5, new Date(student.getDateOfBirth().getTime()));
        stmt.setInt(6, student.getGroupId());
        stmt.setInt(7, student.getEducationYear());
        stmt.setInt(8, student.getStudentId());
        stmt.execute();
    }

    public void deleteStudent(Student student) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("DELETE FROM students WHERE student_id =  ?");
        stmt.setInt(1, student.getStudentId());
        stmt.execute();
    }

    public void deleteGroup(Group group) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("DELETE FROM groups WHERE group_id =  ?");
        stmt.setInt(1, group.getGroupId());
        stmt.execute();
    }

}
