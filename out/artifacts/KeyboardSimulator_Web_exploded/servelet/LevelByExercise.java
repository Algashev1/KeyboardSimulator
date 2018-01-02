package servelet;

import Entity.ExerciseEntity;
import Entity.LevelEntity;
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
@WebServlet(name = "LevelByExercise", value= {"/LevelByExercise"})
public class LevelByExercise extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {
        response.setContentType("application/json");    //Отправляем от сервера данные в JSON -формате
        response.setCharacterEncoding("utf-8");         //Кодировка отправляемых данных
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();
            int exerciseId = Integer.parseInt(request.getParameter("exerciseId"));
            HttpSession s = request.getSession(true);
            s.setAttribute("exerciseId", exerciseId);
            ExerciseEntity exerciseEntity = WorkWithObjects.getExercise(exerciseId);
            LevelEntity levelEntity = exerciseEntity.getLId();
            jsonEnt.put("text", exerciseEntity.getEText());
            jsonEnt.put("maxError", levelEntity.getLMaxerrors());
            jsonEnt.put("time", levelEntity.getLTime());
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
