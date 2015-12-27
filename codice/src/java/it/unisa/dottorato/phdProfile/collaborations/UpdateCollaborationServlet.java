package it.unisa.dottorato.phdProfile.collaborations;

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

/**Servlet incaricata ad effettuare la richiesta di aggiornamento di una
 * collaborazione
 *
 * @author gemmacatolino
 */
@WebServlet(name = "UpdateCollaboration", urlPatterns = {"/UpdateCollaborationServlet"})
public class UpdateCollaborationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request oggetto request per accedere ai parametri inviati attraverso
     * il metodo getParameter per ottenere l'id, l'istituzione, la descrizione, la data
     * di inizio e di fine della collaborazione, per effettuare la richiesta di
     * modifica di una nuova collaborazione
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        JSONObject result = new JSONObject();
        PrintWriter out = response.getWriter();

        try {
            try {
                response.setContentType("text/html;charset=UTF-8");

                int collaborationID = Integer.parseInt("" + request.getParameter("id"));  
                String description = request.getParameter("description");
                String startDate = request.getParameter("startDate");
                String endDate = request.getParameter("endDate");
                String istitution = request.getParameter("istitution");

                HttpSession session = request.getSession();
                PhdStudent loggedPerson = (PhdStudent) session.getAttribute("account"); 

                Collaboration collaboration = new Collaboration();

                collaboration.setIstitution(istitution);
                collaboration.setDescription(description);

                collaboration.setStartDate(java.sql.Date.valueOf(startDate));
                collaboration.setEndDate(java.sql.Date.valueOf(endDate));

                collaboration.setFkPhdstudent(loggedPerson.getfkAccount()); //da verificare

                CollaborationManager.getInstance().update(collaborationID, collaboration);
                result.put("result", true);

                out.println("<script type=\"text/javascript\">");
                out.println("alert('La collaborazione è stata modificata');");
                out.println("location='profileNuovo.jsp';");
                out.println("</script>");
            } catch (SQLException ex) {
                Logger.getLogger(UpdateCollaborationServlet.class.getName()).log(Level.SEVERE, null, ex);
                result.put("result", false);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UpdateCollaborationServlet.class.getName()).log(Level.SEVERE, null, ex);
            } 
            out.write(result.toString());

        } catch (JSONException ex) {
            Logger.getLogger(UpdateCollaborationServlet.class.getName()).log(Level.SEVERE, null, ex);
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
