package it.unisa.dottorato.Tutorate;

import it.unisa.dottorato.account.AccountManager;
import it.unisa.dottorato.autenticazione.EmailException;
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

/**Servlet incaricaricata ad effettuare la richiesta di cancellazione di un tutor
 *
 * @author Giuseppe
 */
@WebServlet(name = "DeleteTutorServlet", urlPatterns = {"/DeleteTutorServlet"})
public class DeleteTutorServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request oggetto request per accedere ai parametri inviati attraverso
     * il metodo getParameter per ottenere l'email dello studente <code>idStudent</code>
     * per effettuare la cancellazione del suo tutor
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws it.unisa.dottorato.autenticazione.EmailException
     */
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, EmailException {
        
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        try {

            JSONObject result = new JSONObject();
            String idStudent = request.getParameter("idStudent");
            
            result.put("result", true);

            try {
                AccountManager.getInstance().deleteStudentTutor(idStudent);
            } catch (ClassNotFoundException | SQLException ex) {
                result.put("result", false);
                Logger.getLogger(DeleteTutorServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            out.write(result.toString());

        } catch (JSONException ex) {
            Logger.getLogger(DeleteTutorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
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
         } catch (EmailException ex) {
             Logger.getLogger(DeleteTutorServlet.class.getName()).log(Level.SEVERE, null, ex);
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
         } catch (EmailException ex) {
             Logger.getLogger(DeleteTutorServlet.class.getName()).log(Level.SEVERE, null, ex);
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
