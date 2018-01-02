package servelet;

import org.json.JSONException;
import org.json.JSONObject;
import server.CreatingExercises;
import server.WorkWithObjects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Gennadiy on 17.12.2017.
 */

@WebServlet(name = "ChangeUser", value= {"/ChangeUser"})
public class ChangeUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();
            int userId = Integer.parseInt(request.getParameter("userId"));
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            int levelId = Integer.parseInt(request.getParameter("level"));
            int roleId = Integer.parseInt(request.getParameter("role"));
            int error = WorkWithObjects.editUser(userId, login, password, levelId, roleId);
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