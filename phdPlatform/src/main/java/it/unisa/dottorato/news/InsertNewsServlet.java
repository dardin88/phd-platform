package it.unisa.dottorato.news;



import it.unisa.dottorato.exception.DescriptionException;
import it.unisa.dottorato.exception.IdException;
import it.unisa.dottorato.exception.TitleException;
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
import org.json.JSONException;
import org.json.JSONObject;
/**Servlet incaricata ad effettuare la richiesta di inserimento di una news
 *
 * @author Giuseppe Picciocchi 
 */
@WebServlet(name = "InsertNews", urlPatterns = {"/InsertNews"})
public class InsertNewsServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request oggetto request per accedere ai parametri inviati attraverso
     * il metodo getParameter per ottenere il titolo della news title e la 
     * descrizione della news description per effettuare la richiesta di 
     * cancellazione della news
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, IdException, TitleException, DescriptionException {

         try {
            response.setContentType("text/html;charset=UTF-8");
            
            PrintWriter out = response.getWriter();
            JSONObject result = new JSONObject();
            int number = NewsManager.getInstance().nextNumber();
            String  title = request.getParameter("title");
            String description= request.getParameter("description");
            String recipients= request.getParameter("recipients");

         
            
            News anews = new News();
            anews.setId(number);
            anews.setTitle(title);
            anews.setDescription(description);
            anews.setRecipients(recipients);
           
            
            
            result.put("result", true);
            
            try {
                NewsManager.getInstance().insertNews(anews);
            } catch (SQLException ex) {
                result.put("result", false);
                Logger.getLogger(InsertNewsServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            out.write(result.toString());
            
        } catch (JSONException ex) {
            Logger.getLogger(InsertNewsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(InsertNewsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (IdException ex) {
            Logger.getLogger(InsertNewsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TitleException ex) {
            Logger.getLogger(InsertNewsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DescriptionException ex) {
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
        } catch (IdException ex) {
            Logger.getLogger(InsertNewsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TitleException ex) {
            Logger.getLogger(InsertNewsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DescriptionException ex) {
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