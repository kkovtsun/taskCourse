import com.springapp.mvc.Department;
import com.springapp.mvc.Group;
import com.springapp.mvc.MainFrameForm;
import com.springapp.mvc.ManagementSystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

@WebServlet("/main")
public class MainUserFrame extends HttpServlet{

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int answer = 0;
        try {
            answer = checkAction(req);
        } catch (SQLException sql_e) {
            throw new IOException(sql_e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (answer == 1) {
            try {
                ManagementSystem.getInstance().logOut();
                getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        MainFrameForm form = new MainFrameForm();
        try {
            String ds = req.getParameter("departmentId");
            String ys = req.getParameter("year");
            int departmentId = -1;
            if (ds != null) {
                departmentId = Integer.parseInt(ds);
            }
            int year = Calendar.getInstance().get(Calendar.YEAR);
            if (ys != null) {
                year = Integer.parseInt(ys);
            }
            Collection departments = ManagementSystem.getInstance().getDepartments();
            Department d = new Department();
            d.setDepartmentId(departmentId);
            if (departmentId == -1) {
                Iterator i = departments.iterator();
                d = (Department) i.next();
            }
            Collection groups = ManagementSystem.getInstance().getGroupsFromDepartment(d);
            form.setDepartmentId(d.getDepartmentId());
            form.setDepartments(departments);
            form.setGroups(groups);
            String gs = req.getParameter("groupId");
            int groupId = -1;
            if (gs != null) {
                groupId = Integer.parseInt(gs);
            }
            Group g = new Group();
            g.setGroupId(groupId);
            if (groupId == -1) {
                Iterator i = groups.iterator();
                g = (Group) i.next();
            }
            form.setGroupId(g.getGroupId());
            form.setYear(year);
            Collection students = ManagementSystem.getInstance().getStudentsFromGroup(g, year);
            form.setStudents(students);
        } catch (SQLException sql_e) {
            throw new IOException(sql_e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute("form", form);
        getServletContext().getRequestDispatcher("/MainUserFrame.jsp").forward(req, resp);
    }

    private int checkAction(HttpServletRequest req) throws Exception {
        if (req.getParameter("logOut") != null) {
            return 1;
        }
        return 0;
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
