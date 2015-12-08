/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.news;


import it.unisa.integrazione.database.exception.ConnectionException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Rembor
 */
@WebServlet(name = "ViewNewsServlet", urlPatterns = {"/ViewNewsServlet"})
public class ViewNewsServlet {
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, MissingDataEccezione, ConnectionException {
         
              response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int newsID = Integer.parseInt(request.getParameter("idNews"));
            JSONObject result = new JSONObject();
            try {
                News avviso = NewsManager.getInstance().getNewsByNumber(newsID);
                result.put("idNews", avviso.getId());
                result.put("title", avviso.getTitle());
                result.put("description", avviso.getDescription());
                out.write(result.toString());
            } catch (SQLException | JSONException ex) {
                Logger.getLogger(ViewNewsServlet.class.getName()).log(Level.SEVERE, null, ex);
            }     catch (ExceptionErroreIdNews ex) {
                      Logger.getLogger(ViewNewsServlet.class.getName()).log(Level.SEVERE, null, ex);
                  }
        }
     
     
     
     }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ConnectionException, MissingDataEccezione {
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
   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, MissingDataEccezione, ConnectionException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
   
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
     
     
}
