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

/**Servlet incaricata a inserire un'attività nel registro delle attività
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
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException{
         
        response.setContentType("text/html;charset=UTF-8");
        JSONObject result = new JSONObject();
        PrintWriter out = response.getWriter();                        
        
        String name = request.getParameter("name");
        String description =  request.getParameter("description");
        String startDateTime = request.getParameter("startDateTime");
        String endDateTime = request.getParameter("endDateTime");
        String typology = request.getParameter("typology");
        String idSeminar = request.getParameter("idSeminar");

        HttpSession session = request.getSession();
        PhdStudent loggedPerson = (PhdStudent) session.getAttribute("account");  // da verificare

        Activity activity = new Activity();

        activity.setName(name);
        activity.setDescription(description);
        activity.setStartDateTime(startDateTime);
        activity.setEndDateTime(endDateTime);
        activity.setTypology(typology);
        activity.setFkPhdStudent(loggedPerson.getfkAccount());
        System.out.println(activity.toString());

        try {
            ActivityRegisterManager.getInstance().insertActivity(activity, idSeminar);
        } catch (SQLException ex) {
            Logger.getLogger(InsertActivityServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            result.put("result", true);
        } catch (JSONException ex) {
            result.put("result", false);
            Logger.getLogger(InsertActivityServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.write(result.toString());
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
        } catch (JSONException ex) {
            Logger.getLogger(InsertActivityServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (JSONException ex) {
            Logger.getLogger(InsertActivityServlet.class.getName()).log(Level.SEVERE, null, ex);
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