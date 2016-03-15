package it.unisa.dottorato.account;

import it.unisa.dottorato.Tutorate.UpdateTutorServlet;
import it.unisa.dottorato.autenticazione.EmailException;
import it.unisa.integrazione.database.exception.ConnectionException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**Servlet incaricata ad effettuare la richiesta di aggiornamento di un tipo di account
 * di un account
 *
 * @author Armando
 */
@WebServlet(name = "ChangeTypeServlet", urlPatterns = {"/ChangeTypeServlet"})

public class ChangeTypeServlet extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request oggetto request per accedere ai parametri inviati attraverso
     * il metodo getParameter per ottenere l'email dell'account <code>index</code>
     * e il nuovo tipo di account <code>newType</code> per cambiare il tipo di 
     * account
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws SQLException if a sql error occurs
     * @throws ConnectionException if a connection error occurs
     * @throws NullAccountException if an account error occurs
     * @throws EmailException if an email error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ConnectionException, NullAccountException, EmailException, ClassNotFoundException, JSONException, ProfileException, NameException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            JSONObject result = new JSONObject();

            String email = request.getParameter("userEmail");
            String newType = request.getParameter("newType");

            result.put("result", true);

            try {
                AccountManager.getInstance().changeType(email, newType);

            } catch (ClassNotFoundException | SQLException ex) {
                result.put("result", false);
                Logger.getLogger(ChangeTypeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            out.write(result.toString());

        } catch (JSONException ex) {
            Logger.getLogger(ChangeTypeServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException | ConnectionException | NullAccountException | EmailException | ClassNotFoundException | JSONException | ProfileException ex) {
            Logger.getLogger(ChangeTypeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NameException ex) {
            Logger.getLogger(ChangeTypeServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException | ConnectionException | NullAccountException | EmailException | ClassNotFoundException | JSONException | ProfileException ex) {
            Logger.getLogger(ChangeTypeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NameException ex) {
            Logger.getLogger(ChangeTypeServlet.class.getName()).log(Level.SEVERE, null, ex);
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
