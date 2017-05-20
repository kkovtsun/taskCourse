import com.springapp.mvc.ManagementSystem;
import com.springapp.mvc.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/registration")
public class RegistrationUser extends HttpServlet{
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int answer = 0;
        try {
            answer = checkAction(req);
        } catch (SQLException sql_e) {
            try {
                throw new IOException(sql_e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (answer == 1) {
            Pattern patternName = Pattern.compile("^[a-z]([a-z]{3,20})$");
            String nV =  (String) req.getParameter("usernameR");
            String pV =  (String) req.getParameter("passwordR");
            Matcher matcherName = patternName.matcher(nV);
            Matcher matcherPass = patternName.matcher(pV);
            boolean resultN = matcherName.matches();
            boolean resultP = matcherPass.matches();
            if (req.getParameter("usernameR").equals("")) {
                req.setAttribute("logIn", "Enter username!");
            } else {
                if (resultN == false){
                    req.setAttribute("logIn", "Invalid username!");
                    getServletContext().getRequestDispatcher("/RegistrationUser.jsp").forward(req, resp);

                } else {
                    if (req.getParameter("passwordR").equals("")) {
                        req.setAttribute("logIn", "Enter password!");
                        getServletContext().getRequestDispatcher("/RegistrationUser.jsp").forward(req, resp);
                    } else {
                        if (resultP == false) {
                            req.setAttribute("logIn", "Invalid password!");
                            getServletContext().getRequestDispatcher("/RegistrationUser.jsp").forward(req, resp);
                        } else {
                            try {
                                String p = req.getParameter("passwordR");
                                String p2 = md5Custom(p);
                                UserType user = new UserType(req.getParameter("usernameR"), p2, "User");
                                ManagementSystem.getInstance().insertUser(user);
                                ManagementSystem.getInstance().insertUserType(user);
                                getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        getServletContext().getRequestDispatcher("/RegistrationUser.jsp").forward(req, resp);
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
        if (req.getParameter("signIn") != null) {
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

