package servelet;

import Entity.ExerciseEntity;
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
import java.util.List;

/**
 * Created by Gennadiy on 19.12.2017.
 */
@WebServlet(name = "GetExercises", value= {"/GetExercises"})
public class GetExercises extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();
            HttpSession s = request.getSession(true);
            Integer userId = Integer.parseInt(s.getAttribute("id").toString());
            Integer levelId = WorkWithObjects.getUser(userId).getLId().getLId();
            List<ExerciseEntity> listExercises = WorkWithObjects.getListExercise();
            List<ExerciseEntity> listExerciseCompleted = WorkWithObjects.getListExerciseCompleted(userId);

            String exerciseCompletedId = "";
            String exercisesId = "";
            String levelsId = "";
            for (ExerciseEntity element: listExercises) {
                exercisesId += element.getEId() + ";";
                levelsId += element.getLId().getLId() + ";";
            }

            for (ExerciseEntity element: listExerciseCompleted) {
                exerciseCompletedId += element.getEId() + ";";
            }
            jsonEnt.put("id", levelId);
            jsonEnt.put("levelsId", levelsId);
            jsonEnt.put("exercisesId", exercisesId);
            jsonEnt.put("exerciseCompletedId", exerciseCompletedId);
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
