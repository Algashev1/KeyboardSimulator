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
 * Created by Gennadiy on 19.12.2017.
 */
@WebServlet(name = "SetLevel", value= {"/SetLevel"})
public class SetLevel extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();

            int levelId;
            int min;
            int max;
            int maxError;
            int time;
            int zone;
            boolean flag = true;

            try {
                levelId = Integer.parseInt(request.getParameter("levelId"));
                min = Integer.parseInt(request.getParameter("min"));
                max = Integer.parseInt(request.getParameter("max"));
                maxError = Integer.parseInt(request.getParameter("maxError"));
                time = Integer.parseInt(request.getParameter("time"));
                zone = Integer.parseInt(request.getParameter("zone"));

                if (min < 50) {
                    jsonEnt.put("result", -3);
                }
                else if (min > 500) {
                    jsonEnt.put("result", -4);
                }
                else if (max < 50) {
                    jsonEnt.put("result", -5);
                }
                else if (max > 500) {
                    jsonEnt.put("result", -6);
                }
                else if (maxError < 1) {
                    jsonEnt.put("result", -7);
                }
                else if (maxError > 10) {
                    jsonEnt.put("result", -8);
                }
                else if (levelId != 0 && zone != 0 && (time > 0) && (min > 0 && min <= 500) && (max > 0 && max <= 500)) {
                    if (min >= max) {
                        jsonEnt.put("result", -2);
                    }
                    else {
                        WorkWithObjects.editLevel(levelId, min, max, maxError, time, zone);
                        jsonEnt.put("result", 0);
                    }
                } else {
                    jsonEnt.put("result", -1);
                }
                flag = false;
            }
            finally {
                if (flag)  jsonEnt.put("result", -1);
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