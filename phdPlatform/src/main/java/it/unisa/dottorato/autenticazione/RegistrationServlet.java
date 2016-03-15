package it.unisa.dottorato.autenticazione;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.integrazione.database.exception.MissingDataException;
import it.unisa.dottorato.account.Account;
import it.unisa.dottorato.account.AccountManager;
import it.unisa.dottorato.account.NameException;
import it.unisa.dottorato.account.NullAccountException;
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

/**Servlet incaricata ad effettuare la richiesta di registrazione di un
 * utente alla piattaforma
 *
 * @author gemmacatolino
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request oggetto request per accedere ai parametri inviati attraverso
     * il metodo getParameter per ottenere il nome, il cognome, l'email principale
     * e secondaria, e la password di un nuovo account per effettuare la richiesta
     * di registrazione
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NullAccountException, EmailException, PasswordException, NameException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String email = request.getParameter("email");
            String secondaryEmail = request.getParameter("secondaryEmail");
            String password = request.getParameter("password");            
            
            Account account = new Account();
            account.setName(name);
            account.setSurname(surname);
            account.setSecondaryEmail(secondaryEmail);
            account.setEmail(email);
            account.setPassword(password);
            account.setAdmin(false);
            
            LoginManager manager = LoginManager.getInstance();
            manager.register(account);
            
            out.println("<script type=\"text/javascript\">");
            out.println("alert('La registrazione è andata a buon fine');");
            out.println("location='login.jsp';");
            out.println("</script>");
            
           // response.sendRedirect("login.jsp");
            
        } catch (SQLException ex ) {
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EmailException ex) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('l'email deve essere universitaria);");
            out.println("</script>");
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
        doPost(request, response);
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
        } catch (NullAccountException | EmailException | PasswordException | NameException ex) {
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
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
