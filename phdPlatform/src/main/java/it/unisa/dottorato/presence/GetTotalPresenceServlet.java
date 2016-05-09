/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.presence;

import it.unisa.dottorato.account.PhdStudent;
import it.unisa.dottorato.exception.IdException;
import it.unisa.dottorato.phdCourse.Course;
import it.unisa.dottorato.utility.Utility;
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
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *Servlet incaricata di effettuare la richiesta delle lezioni, delle presenze e delle assenze dello studente 
 * @author cadav
 */
@WebServlet(name = "GetTotalPresenceServlet", urlPatterns = {"/GetTotalPresenceServlet"})
public class GetTotalPresenceServlet extends HttpServlet {

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
                  throws ServletException, IOException, PhdStudentexception {
       response.setContentType("text/html;charset=UTF-8");
PrintWriter out = response.getWriter();

        try {
              HttpSession session = request.getSession();
             JSONObject result = new JSONObject();
               PhdStudent student = (PhdStudent) session.getAttribute("account");
 
 String dottorando = student.getfkAccount();
       String coursename=request.getParameter("Coursename");
     
     
 
                ArrayList<Presence> presenze = PresenceManager.getInstance().getTotalLesson((Utility.AppendQuote(dottorando)), (Utility.AppendQuote(coursename)));
                JSONArray resultArray = new JSONArray(presenze);
                
                result.put("presenze", presenze.get(0).getTotalPresence());
                result.put("presenzeEff", presenze.get(0).getPresenzeEff()); 
                result.put("Assenze", presenze.get(0).getAssenze()); 
                out.write(result.toString());
            } catch (SQLException | JSONException ex) {
                Logger.getLogger(GetPresenceToLessonServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IdException ex) {
             Logger.getLogger(GetPresenceToLessonServlet.class.getName()).log(Level.SEVERE, null, ex);
         } catch (ClassNotFoundException ex) {
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
           Logger.getLogger(GetTotalPresenceServlet.class.getName()).log(Level.SEVERE, null, ex);
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
           Logger.getLogger(GetTotalPresenceServlet.class.getName()).log(Level.SEVERE, null, ex);
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
