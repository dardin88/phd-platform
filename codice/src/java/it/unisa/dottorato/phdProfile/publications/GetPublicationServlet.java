package it.unisa.dottorato.phdProfile.publications;

import it.unisa.dottorato.exception.IdException;
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

/**Servlet incaricata ad effettuare la richiesta di ricercare una pubblicazione 
 * dato il suo id
 * 
 * @author andre
 */

@WebServlet(name = "GetPublication", urlPatterns = {"/GetPublicationServlet"})
public class GetPublicationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request oggetto request per accedere ai parametri inviati attraverso
     * il metodo getParameter per ottenere l'id della pubblicazione idPublication
     * per effettuare la richiesta di ricerca di una pubblicazione
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int pubId = Integer.parseInt(request.getParameter("idPublication"));
            JSONObject result = new JSONObject();
            try {
                Publication p = PublicationManager.getInstance().getPublicationById(pubId);
                result.put("idPublication", p.getIdPublication() );
                result.put("title", p.getTitle() ) ;
                result.put("publicationIssue", p.getPublicationIssue());
                result.put("year", p.getYear());
                result.put("numberPage", p.getNumberPages());
                result.put("link", p.getLink());
                result.put("type", p.getType());
                result.put("otherAuthors", p.getAuthors());
                result.put("abstract", p.getAbstract());
                result.put("fkPhdstudent", p.getFkPhdstudent());
                out.write(result.toString());
            } catch (ClassNotFoundException | SQLException | JSONException ex) {
                Logger.getLogger(GetPublicationServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IdException ex) {
                Logger.getLogger(GetPublicationServlet.class.getName()).log(Level.SEVERE, null, ex);
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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