package it.unisa.dottorato.phdCourse;

import it.unisa.dottorato.account.Professor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;

/** Servlet incaricata ad effettuare la richiesta di aggiunta di una lezione
 *
 * @author Giuseppe Picciocchi
 */
@WebServlet(name = "AddLessonServlet", urlPatterns = {"/AddLessonServlet"})
public class AddLessonServlet extends HttpServlet {

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

        JSONObject result = new JSONObject();
        PrintWriter out = response.getWriter();

        try {
            response.setContentType("text/html;charset=UTF-8");
            
            //conserviamo gli attributi da settare nelle variabili
            String date = request.getParameter("data");
            String starttime = request.getParameter("starttime");
            String endtime = request.getParameter("endtime");
            String name = request.getParameter("name");
            String classroom = request.getParameter("classroom");
            String description = request.getParameter("description");
            String cycle = request.getParameter("cycle");
            String curriculum = request.getParameter("curriculum");
            String course = request.getParameter("course");
            
            HttpSession session = request.getSession();
            Professor loggedPerson = (Professor) session.getAttribute("professor");
            
            Lesson lesson = new Lesson();
            
            //inseriamo nell'oggetto corso i valori passati come parametri precedentemente
            lesson.setDate(java.sql.Date.valueOf(date));
            lesson.setStartTime(Integer.parseInt(starttime));
            lesson.setEndTime(Integer.parseInt(endtime));
            lesson.setName(name);
            lesson.setClassroom(classroom);
            lesson.setDescription(description);
            lesson.setCycle(Integer.parseInt(cycle));
            lesson.setCurriculum(curriculum);
            lesson.setFK_course(Integer.parseInt(course));
            
            //inseriamo l'oggetto nella gestione calendario
            CalendarManager.getInstance().insert_lesson(lesson);
            
            out.println("<script type=\"text/javascript\">");
            out.println("alert('La lezione è stata inserita');");
            out.println("location='collaborationActivity.jsp';"); // da modificare
            out.println("</script>");
            
        } catch (SQLException ex) {
            Logger.getLogger(AddLessonServlet.class.getName()).log(Level.SEVERE, null, ex);
            
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