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
import java.sql.Timestamp;
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

/**Servlet incaricata a modificare un'attività nel registro delle attività di un dottorando
 *
 * @author Ernesto
 */
@WebServlet(name = "UpdateActivity", urlPatterns = {"/UpdateActivity"})
public class UpdateActivityServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.text.ParseException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, SQLException {
        
        response.setContentType("text/html;charset=UTF-8");
        JSONObject result = new JSONObject();
        PrintWriter out = response.getWriter();
        
        try {
            String name = request.getParameter("name");
            int idActivity = Integer.parseInt(request.getParameter("idActivity"));
            String description =  request.getParameter("description");
            String startDateTime = request.getParameter("startDateTime");
            String endDateTime = request.getParameter("endDateTime");
            String totalTime = request.getParameter("totalTime");
            String typology = request.getParameter("typology");


            HttpSession session = request.getSession();
            PhdStudent loggedPerson = (PhdStudent) session.getAttribute("account");  // da verificare

            Activity activity = new Activity();
            activity.setName(name);
            activity.setDescription(description);
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            java.sql.Timestamp startTime = new Timestamp(formatter.parse(startDateTime).getTime());
            java.sql.Timestamp endTime = new Timestamp(formatter.parse(endDateTime).getTime());
            activity.setStartDateTime(startTime);
            activity.setEndDateTime(endTime);
            activity.setTypology(typology);

            ActivityRegisterManager.getInstance().updateActivity(idActivity, activity);
            result.put("result", true);

            out.write(result.toString());

        } catch (JSONException ex) {
            Logger.getLogger(InsertActivityServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException | SQLException ex) {
            Logger.getLogger(UpdateActivityServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException | SQLException ex) {
            Logger.getLogger(UpdateActivityServlet.class.getName()).log(Level.SEVERE, null, ex);
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
