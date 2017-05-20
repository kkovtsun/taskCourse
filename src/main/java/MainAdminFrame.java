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
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/mainadmin")
public class MainAdminFrame extends HttpServlet {
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
        if (answer == 2) {
            Pattern patternName = Pattern.compile("^[a-z]([a-z]{3,20})$");
            String nV =  (String) req.getParameter("lblN");
            String pV =  (String) req.getParameter("lblP");
            Matcher matcherName = patternName.matcher(nV);
            Matcher matcherPass = patternName.matcher(pV);
            boolean resultN = matcherName.matches();
            boolean resultP = matcherPass.matches();
            if (req.getParameter("lblN").equals("")) {
                req.setAttribute("logIn", "Enter username!");
            } else {
                if (resultN == false){
                    req.setAttribute("logIn", "Invalid username!");
                } else {
                    if (req.getParameter("lblP").equals("")) {
                        req.setAttribute("logIn", "Enter password!");
                    } else {
                        if (resultP == false) {
                            req.setAttribute("logIn", "Invalid password!");
                        } else {
                            try {
                                String s = (String) req.getParameter("lblS");
                                String status = s.toLowerCase();
                                String p = req.getParameter("lblP");
                                String p2 = md5Custom(p);
                                UserType user = new UserType();
                                user.setUsername(req.getParameter("lblN"));
                                user.setPassword(p2);
                                user.setStatus(status);
                                if (status.equals("moderator")){
                                    ManagementSystem.getInstance().insertModerator(user);
                                }else{
                                    ManagementSystem.getInstance().insertAdmin(user);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        if (answer == 3) {
            if (req.getParameter("id") != null) {
                UserType user = new UserType();
                user.setId(Integer.parseInt(req.getParameter("id")));
                try {
                    ManagementSystem.getInstance().deleteModerator(user);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
        if (answer == 4) {
            if (req.getParameter("id") != null) {
                UserType user = new UserType();
                user.setId(Integer.parseInt(req.getParameter("id")));
                try {
                    ManagementSystem.getInstance().deleteAdmin(user);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
        try {
            Collection moderators = ManagementSystem.getInstance().getModerators();
            Collection admins = ManagementSystem.getInstance().getAdmins();
            req.setAttribute("moderators", moderators);
            req.setAttribute("admins", admins);
        } catch (Exception e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/MainAdminFrame.jsp").forward(req, resp);
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
        if (req.getParameter("logOut") != null) {
            return 1;
        }
        if (req.getParameter("Add") != null) {
            return 2;
        }
        if (req.getParameter("Delete") != null) {
            return 3;
        }
        if (req.getParameter("DeleteA") != null) {
            return 4;
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
