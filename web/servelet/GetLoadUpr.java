package servelet;

import DAO.Impl.LevelDAOImpl;
import org.json.JSONException;
import org.json.JSONObject;
import server.CreatingExercises;

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
@WebServlet(name = "GetLoadUpr", value= {"/GetLoadUpr"})
public class GetLoadUpr extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();
            String path = request.getParameter("path");
            int levelId = Integer.parseInt(request.getParameter("id"));
            String zone = new LevelDAOImpl().getLevel(levelId).getZId().getZValue();
            path = "D:\\" + path;
            String result = CreatingExercises.loadExercise(path);
            jsonEnt.put("result",  result);
            jsonEnt.put("zone",  zone);
            out.print(jsonEnt.toString());
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {

        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {

        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
