/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.integrazione.news;

import it.unisa.dottorato.phdClass.PhdClass;
import it.unisa.dottorato.phdClass.PhdClassManager;
import it.unisa.integrazione.database.NewsManager;
import it.unisa.integrazione.database.exception.MissingDataEccezione;
import it.unisa.integrazione.model.News;
import it.unisa.integrazione.model.Person;
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
 *
 * @author Rembor
 */
@WebServlet(name = "InsertNews", urlPatterns = {"/amministratore/InsertNews"})
public class InsertNewsServlet extends HttpServlet {

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
            throws ServletException, IOException, MissingDataEccezione {
        
        
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        try {

            JSONObject result = new JSONObject();
         String id = request.getParameter("idnews");
         
        String title = request.getParameter("title");
        String description =request.getParameter("description");
         HttpSession session = request.getSession();
            Person loggedPerson = (Person) session.getAttribute("person");
            
        News avviso =new News();
        
       avviso.setId(/*Integer.parseInt(id)*/);
        avviso.setTitle(title);
        avviso.setDescription(description);
        
        result.put("result", true);

        try {
           NewsManager .getInstance().add(avviso);
            out.println("<script type=\"text/javascript\">");
            out.println("alert('L'avviso è stato inserito');");
            out.println("location='NewsActivity.jsp';"); // da creare
            out.println("</script>");
        } catch (SQLException ex) {
            result.put("result", false);
            Logger.getLogger(InsertNewsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        out.write(result.toString());

        } catch (JSONException ex) {
            Logger.getLogger(InsertNewsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (MissingDataEccezione ex) {
            Logger.getLogger(InsertNewsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (MissingDataEccezione ex) {
            Logger.getLogger(InsertNewsServlet.class.getName()).log(Level.SEVERE, null, ex);
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