package it.unisa.dottorato.phdCourse;

import it.unisa.dottorato.account.Professor;
import it.unisa.dottorato.exception.DateException;
import it.unisa.dottorato.exception.DescriptionException;
import it.unisa.dottorato.exception.IdException;
import it.unisa.dottorato.exception.NameException;
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
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;

/**Servlet incaricata ad effettuare la richiesta di aggiornamento di una lezione
 *
 * @author Giuseppe Picciocchi
 */
@WebServlet(name = "UpdateLessonServlet", urlPatterns = {"/UpdateLessonServlet"})
public class UpdateLessonServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request oggetto request per accedere ai parametri inviati attraverso
     * il metodo getParameter per ottenere la data, l'ora di inizio e di fine,
     * il nome, la classe e la descrizione della lezione, il ciclo , il curriculum
     * e il corso a cui la lezione e' associata per effettuare la richiesta di
     * modifica di una lezione
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException,IdException , NameException , ClassroomException , DescriptionException , DateException {

        JSONObject result = new JSONObject();
        PrintWriter out = response.getWriter();

       
            try {
                response.setContentType("text/html;charset=UTF-8");

                int oldlessonID = Integer.parseInt("" + request.getSession().getAttribute("oldidLesson"));
            
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

                lesson.setDate(java.sql.Date.valueOf(date));
                lesson.setStartTime((starttime));
                lesson.setEndTime((endtime));
                lesson.setName(name);
                lesson.setClassroom(classroom);
                lesson.setDescription(description);
                lesson.setFK_course(Integer.parseInt(course));

                result.put("result", true);

            try {
                CalendarManager.getInstance().update_lesson(oldlessonID, lesson);

            } catch (ClassNotFoundException | SQLException ex) {
                result.put("result", false);
                Logger.getLogger(UpdateLessonServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            out.write(result.toString());

        } catch (JSONException ex) {
            Logger.getLogger(UpdateLessonServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
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
       try {
            processRequest(request, response);
        } catch (IdException | NameException | DateException ex) {
            Logger.getLogger(AddLessonServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (DescriptionException ex) {
            Logger.getLogger(AddCourseServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassroomException ex) {
            Logger.getLogger(AddCourseServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       
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
        try {
            processRequest(request, response);
        } catch (IdException | NameException | DateException ex) {
            Logger.getLogger(AddLessonServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (DescriptionException ex) {
            Logger.getLogger(AddCourseServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassroomException ex) {
            Logger.getLogger(AddCourseServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
