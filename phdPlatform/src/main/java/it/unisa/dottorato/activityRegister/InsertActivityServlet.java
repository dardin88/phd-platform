/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.activityRegister;
import it.unisa.dottorato.account.PhdStudent;
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

/**Servlet incaricata di inserire un'attività nel registro delle attività di un dottorando
 *
 * @author Ernesto
 */
@WebServlet(name = "InsertActivity", urlPatterns = {"/InsertActivity"})
public class InsertActivityServlet extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException{
        response.setContentType("text/html;charset=UTF-8");
        JSONObject result = new JSONObject();
        PrintWriter out = response.getWriter();
        
        try {
            response.setContentType("text/html;charset=UTF-8");

            String name = request.getParameter("name");
            String description =  request.getParameter("description");
            String startDateTime = request.getParameter("startDateTime");
            String endDateTime = request.getParameter("endDateTime");
            String totalTime = request.getParameter("totalTime");
            String typology = request.getParameter("typology");
            String fkPhdStudent = request.getParameter("fkPhdStudent");


            HttpSession session = request.getSession();
            PhdStudent loggedPerson = (PhdStudent) session.getAttribute("account");  // da verificare

            Activity activity = new Activity();
            activity.setName(name);
            activity.setDescription(description);
            activity.setStartDateTime(java.sql.Date.valueOf(startDateTime));
            activity.setStartDateTime(java.sql.Date.valueOf(endDateTime));
            activity.setTotalTime(Float.parseFloat(totalTime));
            activity.setTypology(typology);
            activity.setFkPhdStudent(fkPhdStudent);

            ActivityRegisterManager.getInstance().insertActvity(activity);
            result.put("result", true);

            /*out.println("<script type=\"text/javascript\">");
            out.println("alert('L'attività è stata inserita.');");
            out.println("location='profileNuovo.jsp';");
            out.println("</script>");*/

            out.write(result.toString());

        } catch (JSONException ex) {
            Logger.getLogger(InsertActivityServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
