package it.unisa.dottorato.Tutorate;


import it.unisa.dottorato.account.Account;
import it.unisa.dottorato.account.AccountManager;
import it.unisa.dottorato.account.Professor;
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

/**Servlet incaricata ad effettuare la richiesta di vidualizzazione del tutor di
 * un dottorando
 *
 * @author Giuseppe Picciocchi
 */
@WebServlet(name = "GetTutorServlet", urlPatterns = {"/GetTutorServlet"})
public class GetTutorServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request oggetto request per accedere ai parametri inviati attraverso
     * il metodo getParameter per ottenere l'email dello studente <code>fkAccount</code>
     * per effettuare la visualizzazione del profilo del suo tutor
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws it.unisa.dottorato.autenticazione.EmailException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, EmailException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String aStudent = request.getParameter("fkAccount");
            JSONObject result = new JSONObject();
            try {
                Professor aPerson = AccountManager.getInstance().getTutor(aStudent);

                if(aPerson != null){
                    result.put("fkAccount", aPerson.getSecondaryEmail());
                    result.put("name", aPerson.getName());
                    result.put("surname", aPerson.getSurname()); 
                    result.put("result",true);                  
                }else
                     result.put("result",false);
                out.write(result.toString());
                
            } catch (SQLException | JSONException | ClassNotFoundException ex) {
                Logger.getLogger(GetTutorServlet.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    result.put("result",false);
                } catch (JSONException ex1) {
                    Logger.getLogger(GetTutorServlet.class.getName()).log(Level.SEVERE, null, ex1);
                }

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
        try {
            processRequest(request, response);
        } catch (EmailException ex) {
            Logger.getLogger(GetTutorServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GetTutorServlet.class.getName()).log(Level.SEVERE, null, ex);
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
