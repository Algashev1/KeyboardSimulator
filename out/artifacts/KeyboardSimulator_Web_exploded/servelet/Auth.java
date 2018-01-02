package servelet;

import Entity.UserEntity;
import org.json.JSONException;
import org.json.JSONObject;
import server.AccessUser;
import server.WorkWithObjects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Gennadiy on 28.12.2017.
 */
@WebServlet(name = "Auth", value= {"/Auth"})
public class Auth extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            int id;
            int role = -1;
            if (!login.isEmpty() && !password.isEmpty()) {
                id = AccessUser.authorizationUser(login, password);
                HttpSession s = request.getSession(true);
                s.setAttribute("id", Integer.toString(id));
                if (id != -1) {
                    UserEntity user = WorkWithObjects.getUser(id);
                    role = user.getURole();
                }
            }
            jsonEnt.put("role",  role);
            out.print(jsonEnt.toString());
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            //Logger.getLogger(AuthenticationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            //Logger.getLogger(AuthenticationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}