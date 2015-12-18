package it.unisa.dottorato.phdCourse;

import it.unisa.dottorato.exception.IdException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**Servlet incaricata ad effettuare la richiesta di visualizzazione di una lezione
 *
 * @author Giuseppe Picciocchi
 */
@WebServlet(name = "GetLessonServlet", urlPatterns = {"/GetLessonServlet"})
public class GetLessonServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int lessonId = Integer.parseInt(request.getParameter("idLesson"));
            JSONObject result = new JSONObject();
            try {
                Lesson c = CalendarManager.getInstance().getLessonById(lessonId);
                result.put("idLesson", c.getIdLesson());
                result.put("date", c.getData());
                result.put("startTime", c.getStartTime());
                result.put("endTime", c.getEndTime());
                result.put("name", c.getName());
                result.put("classroom", c.getClassroom());
                result.put("description", c.getDescription());
                result.put("cycle", c.getCycle());
                result.put("curriculum", c.getCurriculum());
                result.put("fkCourse", c.getFK_course());
                out.write(result.toString());
            } catch (ClassNotFoundException | SQLException | JSONException | IdException ex) {
                Logger.getLogger(GetLessonServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

