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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Rembor
 */
@WebServlet(name = "ViewNewsListServlet", urlPatterns = {"/ViewNewsListServlet"})
public class ViewNewsListServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ConnectionException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            JSONObject result = new JSONObject();
            try {
                ArrayList<News> avviso = NewsManager.getInstance().getNewsByTypeOfTitle(request.getParameter("title"));
                JSONArray resultArray = new JSONArray(avviso);
                result.put("NewsList", resultArray);
                out.write(result.toString());
            } catch (SQLException | JSONException ex) {
                Logger.getLogger(ViewNewsListServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
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
  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ConnectionException {
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
            throws ServletException, IOException, ConnectionException {
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
       
        
    
    
    
    
    

