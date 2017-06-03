import com.springapp.mvc.ManagementSystem;
import com.springapp.mvc.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/")
public class WelcomeUserFrame extends HttpServlet {

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
        UserType user = new UserType("nan", "nan", "nan");
        if (answer == 1) {
            try {
                user.setStatus("reg");
                resp.sendRedirect("http://localhost:8080/registration");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String status = (String) req.getParameter("status");
        if (req.getParameter("username").equals("")){
            req.setAttribute("logIn","Enter username!");
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }else {
            if (req.getParameter("password").equals("")) {
                req.setAttribute("logIn", "Enter password!");
                getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            } else {
                try {
                    ArrayList<UserType> users = (ArrayList<UserType>) ManagementSystem.getInstance().getUsers();
                    ArrayList<UserType> moderators = (ArrayList<UserType>) ManagementSystem.getInstance().getModerators();
                    ArrayList<UserType> admins = (ArrayList<UserType>) ManagementSystem.getInstance().getAdmins();
                    String p = req.getParameter("password");
                    String p2 = md5Custom(p);
                    if (status.equals("user")) {
                        for (UserType u : users) {
                            if ((u.getUsername().equals(req.getParameter("username"))) && (u.getPassword().equals(p2))) {
                                user.setUsername(req.getParameter("username"));
                                user.setPassword(p2);
                                user.setStatus(status);
                                ManagementSystem.getInstance().insertUserType(user);
                                resp.sendRedirect("http://localhost:8080/main");
                                break;
                            }
                        }
                    }
                    if (status.equals("moderator")) {
                        for (UserType u : moderators) {
                            if ((u.getUsername().equals(req.getParameter("username"))) && (u.getPassword().equals(p2))) {
                                user.setUsername(req.getParameter("username"));
                                user.setPassword(p2);
                                user.setStatus(status);
                                ManagementSystem.getInstance().insertUserType(user);
                                resp.sendRedirect("http://localhost:8080/mainmoderator");
                                break;
                            }
                        }
                    }
                    if (status.equals("admin")) {
                        for (UserType u : admins) {
                            if ((u.getUsername().equals(req.getParameter("username"))) && (u.getPassword().equals(p2))) {
                                user.setUsername(req.getParameter("username"));
                                user.setPassword(p2);
                                user.setStatus(status);
                                ManagementSystem.getInstance().insertUserType(user);
                                resp.sendRedirect("http://localhost:8080/mainadmin");
                                break;
                            }
                        }
                    }
                    if (user.getStatus().equals("nan")) {
                        req.setAttribute("logIn", "User does not exist!");
                        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String md5Custom(String st) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);
        while( md5Hex.length() < 32 ){
            md5Hex = "0" + md5Hex;
        }
        return md5Hex;
    }

    private int checkAction(HttpServletRequest req) throws Exception {
        if (req.getParameter("aReg") != null) {
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
