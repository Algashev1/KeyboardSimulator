package servelet;

import org.json.JSONException;
import org.json.JSONObject;
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
@WebServlet(name = "AddUser", value= {"/AddUser"})
public class AddUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            if (login.isEmpty() || password.isEmpty()) {
                jsonEnt.put("result", -2);
                out.print(jsonEnt.toString());
            }
            else {
                int levelId = Integer.parseInt(request.getParameter("level"));
                int roleId = Integer.parseInt(request.getParameter("role"));
                int result = WorkWithObjects.addUser(login, password, roleId, levelId);
                jsonEnt.put("result", result);
                out.print(jsonEnt.toString());
            }
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
