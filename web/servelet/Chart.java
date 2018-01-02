package servelet;

import Entity.StatisticsEntity;
import org.json.JSONException;
import org.json.JSONObject;
import server.StatisticsFormation;

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
 * Created by Gennadiy on 25.12.2017.
 */
@WebServlet(name = "Chart", value= {"/Chart"})
public class Chart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();
            HttpSession s = request.getSession(true);
            Integer userId = Integer.parseInt(s.getAttribute("id").toString());
            int date = Integer.parseInt(request.getParameter("date"));
            List<Integer> result = StatisticsFormation.chart(userId, date);
            String errors = "";
            String labels = "";

            for (int i = 0; i < result.size(); i++) {

                if ((i + 1) == result.size()) {
                    errors += result.get(i);
                    labels += "\"" + i + "\"";
                }
                else {
                    errors += result.get(i) + ",";
                    labels += "\"" + i + "\"" + ",";
                }
            }
            //errors += "]";
            //labels += "]";
            jsonEnt.put("labels", labels);
            jsonEnt.put("errors", errors);
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