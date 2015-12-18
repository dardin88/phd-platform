package it.unisa.dottorato.account;

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

/**Servlet incaricata ad effettuare la richiesta di aggiornamento di un tipo di account
 * di un account
 *
 * @author Armando
 */
@WebServlet(name = "ChangeTypeServlet", urlPatterns = {"/dottorato/ChangeTypeServlet"})

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
            throws ServletException, IOException, SQLException, ConnectionException, NullAccountException, EmailException {
        
        AccountManager manager = AccountManager.getInstance();
        ArrayList<Account> accounts = manager.getAccountList();
        PrintWriter out = response.getWriter();
        String index = request.getParameter("index"); // da integrare con l'interfaccia
        String newType = request.getParameter("newType"); // idem 
        manager.changeType(accounts.get(Integer.parseInt(index)), newType);
        
         out.println("<script type=\"text/javascript\">");
         out.println("alert('La modifica è andata a buon fine');");
         out.println("location='amministrazione.jsp';"); //
         out.println("</script>");
    
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            try {
                try {
                    processRequest(request, response);
                } catch (NullAccountException ex) {
                    Logger.getLogger(ChangeTypeServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (EmailException ex) {
                    Logger.getLogger(ChangeTypeServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ChangeTypeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ConnectionException ex) {
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
        } catch (SQLException ex) {
            Logger.getLogger(ChangeTypeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionException ex) {
            Logger.getLogger(ChangeTypeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullAccountException ex) {
            Logger.getLogger(ChangeTypeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EmailException ex) {
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
