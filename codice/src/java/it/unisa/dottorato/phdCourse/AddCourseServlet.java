package it.unisa.dottorato.phdCourse;

import it.unisa.dottorato.Curriculum.CurriculumException;
import it.unisa.dottorato.account.Professor;
import it.unisa.dottorato.exception.DateException;
import it.unisa.dottorato.exception.DescriptionException;
import it.unisa.dottorato.exception.IdException;
import it.unisa.dottorato.exception.NameException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
/**Servlet incaricata ad effettuare la richiesta di aggiunta di un corso
 *
 * @author Giuseppe Picciocchi
 */
@WebServlet(name = "AddCourseServlet", urlPatterns = {"/AddCourseServlet"})
public class AddCourseServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request oggetto request per accedere ai parametri inviati attraverso
     * il metodo getParameter per ottenere il nome del curriculum, il numero del
     * ciclo, il nome, la descrizione, la data di inizio e di fine del corso per 
     * effettuare la richiesta di aggiunta di un nuovo corso
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException ,IdException,DescriptionException,NameException,DateException, CourseException, ParseException{

        JSONObject result = new JSONObject();
        PrintWriter out = response.getWriter();

        try {
            response.setContentType("text/html;charset=UTF-8");
            
            //conserviamo gli attributi da settare nelle variabili
            
            String curriculum = request.getParameter("curriculum");
            String cycle = request.getParameter("cycle");
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String startDate = request.getParameter("starttime");
            String endDate = request.getParameter("endtime");
          //  DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            //df.setLenient(false);
            //String start = df.format(startDate);
            //Date startdate = df.parse(start);
           // String end = df.format(endDate);
           // Date enddate = df.parse(end);
            HttpSession session = request.getSession();
            Professor loggedPerson = (Professor) session.getAttribute("professor");
            
            Course course = new Course();
            
            //inseriamo nell'oggetto corso i valori passati come parametri precedentemente
            
            course.setFkCurriculum(curriculum);
            course.setFkCycle(Integer.parseInt(cycle));
            course.setName(name);
            course.setDescription(description);
            course.setStartDate(java.sql.Date.valueOf(startDate));
            course.setEndDate(java.sql.Date.valueOf(endDate));
            
            result.put("result", true);

        try {
            CalendarManager.getInstance().insert_course(course);
        } catch (SQLException ex)  {
            result.put("result", false);
            Logger.getLogger(AddCourseServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        out.write(result.toString());

        } catch (JSONException ex) {
            Logger.getLogger(AddCourseServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (IdException | NameException | DateException | DescriptionException ex) {
            Logger.getLogger(AddCourseServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CourseException ex) {
            Logger.getLogger(AddCourseServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
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
        } catch (IdException | NameException | DateException | DescriptionException ex) {
            Logger.getLogger(AddCourseServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CourseException ex) {
            Logger.getLogger(AddCourseServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
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