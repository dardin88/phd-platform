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

/**
 * Servlet incaricata ad effettuare la richiesta di aggiornamento di un
 * seminario
 *
 * @author Giuseppe
 */
@WebServlet(name = "UpdateSeminarServlet", urlPatterns = {"/UpdateSeminarServlet"})
public class UpdateSeminarServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request oggetto request per accedere ai parametri inviati
     * attraverso il metodo getParameter per ottenere la data, l'ora di inizio e
     * di fine, il nome, il nome dello speaker e la descrizione del seminario,
     * il posto e il corso a cui il seminario e' associato per effettuare la
     * richiesta di modifica di un seminario
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, IdException, DescriptionException, NameException, SpeakerException, PlaceException, DateException {

        JSONObject result = new JSONObject();
        PrintWriter out = response.getWriter();

        try {
            response.setContentType("text/html;charset=UTF-8");

            int seminarID = Integer.parseInt(request.getParameter("id"));
            String date = request.getParameter("date");
            String startTime = request.getParameter("startTime");
            String starttime = startTime.substring(0, 5);
            String endTime = request.getParameter("endTime");
            String endtime = endTime.substring(0, 5);
            String name = request.getParameter("name");
            String namespeacker = request.getParameter("nameSpeacker");
            String description = request.getParameter("description");
            String place = request.getParameter("place");
            String course = request.getParameter("fkCourse");

            HttpSession session = request.getSession();
            Professor loggedPerson = (Professor) session.getAttribute("professor");

            Seminar seminar = new Seminar();

            //inseriamo nell'oggetto corso i valori passati come parametri precedentemente
            seminar.setDate(java.sql.Date.valueOf(date));
            seminar.setStartTime((starttime));
            seminar.setEndTime((endtime));
            seminar.setName(name);
            seminar.setNameSpeacker(namespeacker);
            seminar.setDescription(description);
            seminar.setPlace((place));
            seminar.setFK_course(Integer.parseInt(course));

            result.put("result", true);

            try {
                CalendarManager.getInstance().update_seminar(seminarID, seminar);

            } catch (ClassNotFoundException | SQLException ex) {
                result.put("result", false);
                Logger.getLogger(UpdateLessonServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            String redirectCalendar = "<script>\n"
                    + " location.pathname = '/phd-platform/index.jsp/calendario.jsp';\n"
                    + " </script>"; 
            out.write(redirectCalendar);

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
        } catch (DescriptionException ex) {
            Logger.getLogger(AddCourseServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PlaceException ex) {
            Logger.getLogger(AddSeminarServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SpeakerException ex) {
            Logger.getLogger(AddSeminarServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (DescriptionException ex) {
            Logger.getLogger(AddCourseServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PlaceException ex) {
            Logger.getLogger(AddSeminarServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SpeakerException ex) {
            Logger.getLogger(AddSeminarServlet.class.getName()).log(Level.SEVERE, null, ex);
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
