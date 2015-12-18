package it.unisa.dottorato.phdProfile.collaborations;

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

/**Servlet incaricata ad effettuare la richiesta di ricerca di una collaborazione
 * dato il suo id
 * @author andre
 */

@WebServlet(name = "GetCollaboration", urlPatterns = {"/dottorato/GetCollaborationServlet"})
public class GetCollaborationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request oggetto request per accedere ai parametri inviati attraverso
     * il metodo getParameter per ottenere l'id della collaborazione idCollaboration
     * per effettuare la richiesta di ricerca e visualizzazione di una servlet
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int collId = Integer.parseInt(request.getParameter("idCollaboration"));
            JSONObject result = new JSONObject();
            try {
                Collaboration c = CollaborationManager.getInstance().getCollaborationById(collId);
                result.put("idCollaboration", c.getIdCollaboration());
                result.put("description", c.getDescription()) ;
                result.put("startDate", c.getStartDate());
                result.put("endDate", c.getEndDate());
                result.put("istitution", c.getIstitution());
                result.put("fkPhdstudent", c.getFkPhdstudent());
                out.write(result.toString());
            } catch (ClassNotFoundException | SQLException | JSONException ex) {
                Logger.getLogger(GetCollaborationServlet.class.getName()).log(Level.SEVERE, null, ex);
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
