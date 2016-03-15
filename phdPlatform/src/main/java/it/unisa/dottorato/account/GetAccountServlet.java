package it.unisa.dottorato.account;
import it.unisa.dottorato.autenticazione.EmailException;
import it.unisa.integrazione.database.exception.ConnectionException;
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
/**Servlet incaricata ad effettuare la richiesta di ricerca di account data un'email
 *
 *
 * @author Rembor
 */
@WebServlet(name = "GetAccountbyEmail", urlPatterns = {"/GetAccountbyEmail"})

public class GetAccountServlet extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request oggetto request per accedere ai parametri inviati attraverso
     * il metodo getParameter per ottenere l'email del dottorando <code>index</code>
     * per effettuare la ricerca e visualizzarne il profilo
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, EmailException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String id = request.getParameter("index");
            JSONObject result = new JSONObject();
            try {
              Account avviso = AccountManager.getInstance().getAccountByEmail(id);
              if(avviso.getTypeAccount().equals("phdstudent")){
                PhdStudent student = (PhdStudent) AccountManager.getInstance().getAccountByEmail(id);
                result.put("secondaryEmail", student.getSecondaryEmail());
                result.put("email", student.getEmail());
                result.put("surname", student.getSurname());
                result.put("name", student.getName());
                result.put("password", student.getPassword());
                result.put("typeAccount", student.getTypeAccount());
                result.put("isAdministrator", student.isAdmin());
                result.put("fkCycle", student.getfkCycle());
                result.put("fkCurriculum", student.getfkCurriculum());
            }
              else{
                result.put("secondaryEmail", avviso.getSecondaryEmail());
                result.put("email", avviso.getEmail());
                result.put("surname", avviso.getSurname());
                result.put("name", avviso.getName());
                result.put("password", avviso.getPassword());
                result.put("typeAccount", avviso.getTypeAccount());
                result.put("isAdministrator", avviso.isAdmin());
                
              }
                
                out.write(result.toString());
            } catch (SQLException | JSONException | ConnectionException | ClassNotFoundException ex) {
                Logger.getLogger(GetAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GetAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GetAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
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
