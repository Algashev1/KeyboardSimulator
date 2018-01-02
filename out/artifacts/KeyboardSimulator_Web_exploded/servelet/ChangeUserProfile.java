package servelet;

import org.json.JSONException;
import org.json.JSONObject;
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
 * Created by Gennadiy on 20.12.2017.
 */
@WebServlet(name = "ChangeUserProfile", value= {"/ChangeUserProfile"})
public class ChangeUserProfile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();
            HttpSession s = request.getSession(true);
            Integer userId = Integer.parseInt(s.getAttribute("id").toString());
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            int levelId = Integer.parseInt(request.getParameter("levelId"));
            int error = WorkWithObjects.editUser(userId, login, password, levelId, 2);
            jsonEnt.put("result",  "");
            jsonEnt.put("error",  error);
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