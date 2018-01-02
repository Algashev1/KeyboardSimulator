package servelet;

import Entity.LevelEntity;
import server.WorkWithObjects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gennadiy on 11.12.2017.
 */

@WebServlet(name = "GetLevel", value= {"/GetLevel"})
public class GetLevel extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {
        response.setContentType("application/json");//Отправляем от сервера данные в JSON -формате
        response.setCharacterEncoding("utf-8");//Кодировка отправляемых данных
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();
            int levelId = Integer.parseInt(request.getParameter("id"));
            LevelEntity level = WorkWithObjects.getLevel(levelId);
            jsonEnt.put("min",level.getLMinlength());
            jsonEnt.put("max",level.getLMaxlength());
            jsonEnt.put("maxErrors",level.getLMaxerrors());
            jsonEnt.put("time", level.getLTime());
            jsonEnt.put("zone",level.getZId().getZId());
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


