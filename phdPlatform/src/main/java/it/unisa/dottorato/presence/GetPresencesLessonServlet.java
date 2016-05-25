/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.presence;

import it.unisa.dottorato.exception.IdException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Raffaele Costantino
 */
@WebServlet(name = "GetPresencesLesson", urlPatterns = {"/GetPresencesLesson"})
public class GetPresencesLessonServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws it.unisa.dottorato.presence.PhdStudentexception
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, PhdStudentexception {
    
            PrintWriter out = response.getWriter();

            try 
            {
                JSONObject result = new JSONObject();
                int fkLesson = Integer.parseInt(request.getParameter("idLesson"));
                ArrayList<Presence> presence = PresenceManager.getInstance().getPresencesLesson(fkLesson);
                JSONArray resultArray = new JSONArray(presence);
                result.put("presence", resultArray);
                out.write(result.toString());
            } 
            catch (SQLException | JSONException | IdException | ClassNotFoundException ex)
            {
                Logger.getLogger(GetPresenceToLessonServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (PhdStudentexception ex) {
            Logger.getLogger(GetPresencesLessonServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (PhdStudentexception ex) {
            Logger.getLogger(GetPresencesLessonServlet.class.getName()).log(Level.SEVERE, null, ex);
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
