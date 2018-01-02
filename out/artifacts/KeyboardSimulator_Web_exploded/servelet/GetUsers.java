package servelet;

import Entity.UserEntity;
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
import java.util.List;

/**
 * Created by Gennadiy on 15.12.2017.
 */
@WebServlet(name = "GetUsers", value= {"/GetUsers"})
public class GetUsers extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {
        response.setContentType("application/json");    //Отправляем от сервера данные в JSON -формате
        response.setCharacterEncoding("utf-8");         //Кодировка отправляемых данных
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();
            List<UserEntity> users = WorkWithObjects.getListUsers();
            int count = users.size();
            jsonEnt.put("count", count);
            String logins = "";
            String passwords = "";
            String levels = "";
            String roles = "";
            String ids = "";
            for (int i = 0; i < count; i++) {
                logins += users.get(i).getULogin() + ";";
                passwords += users.get(i).getUPassword() + ";";
                levels += users.get(i).getLId().getLId() + ";";
                roles += users.get(i).getURole() + ";";
                ids += users.get(i).getUId() + ";";
            }
            jsonEnt.put("login", logins);
            jsonEnt.put("password", passwords);
            jsonEnt.put("level", levels);
            jsonEnt.put("role", roles);
            jsonEnt.put("id", ids);
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
    }//

}

