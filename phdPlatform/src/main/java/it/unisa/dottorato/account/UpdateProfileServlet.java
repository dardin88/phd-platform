package it.unisa.dottorato.account;

import it.unisa.dottorato.autenticazione.EmailException;
import it.unisa.dottorato.autenticazione.PasswordException;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.integrazione.database.exception.MissingDataException;
import it.unisa.dottorato.autenticazione.RegistrationServlet;
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

/**Servlet incaricata ad effettuare la richiesta di aggiornamento di un profilo
 * di un utente
 *
 * @author gemmacatolino
 */
@WebServlet(name = "UpdateProfileServlet", urlPatterns = {"/UpdateProfileServlet"})
public class UpdateProfileServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request oggetto request per accedere ai parametri inviati attraverso
     * il metodo getParameter per ottenere il nome, il cognome, l'email secondaria
     * e la password di un account; in seguit si verifica se e' un dottorando o 
     * un professore ottenendo altri attributi a seconda della verifica
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws PasswordException if a password error occurs
     * @throws NullAccountException if an account error occurs
     * @throws EmailException if an email error occurs
     * @throws ProfileException if a profile error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NullAccountException, ProfileException, PasswordException, EmailException, TelephoneException, NameException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Account pAccount;
        
        try {
            HttpSession session = request.getSession();
            Account loggedPerson =  (Account)session.getAttribute("account");
            AccountManager manager = AccountManager.getInstance();
            
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String secondaryEmail = request.getParameter("secondaryEmail");
            String password = request.getParameter("password");
            
            loggedPerson.setName(name);
            loggedPerson.setSurname(surname);
            loggedPerson.setSecondaryEmail(secondaryEmail);
            loggedPerson.setPassword(password);
            
            if(loggedPerson instanceof Professor) {
                String link = request.getParameter("link");
                String department = request.getParameter("department");
                ((Professor) loggedPerson).setLink(link);
                ((Professor) loggedPerson).setDepartment(department);
            }
            
            if(loggedPerson instanceof PhdStudent) {
                String researchInterest = request.getParameter("researchInterest");
                String telephone = request.getParameter("telephone");
                String link = request.getParameter("link");
                String department = request.getParameter("department");
                ((PhdStudent) loggedPerson).setResearchInterest(researchInterest);
                ((PhdStudent) loggedPerson).setTelephone(telephone);
                ((PhdStudent) loggedPerson).setLink(link);
                ((PhdStudent) loggedPerson).setDepartment(department);
                
            }
            
            manager.updateProfile(loggedPerson.getEmail(), loggedPerson);
                                   
            out.println("<script type=\"text/javascript\">");
            out.println("alert('La modifica è andata a buon fine');");
            out.println("location='profileNuovo.jsp';");
            out.println("</script>");

           // response.sendRedirect("login.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionException ex) {
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MissingDataException ex) {
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (NullAccountException ex) {
            Logger.getLogger(UpdateProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProfileException ex) {
            Logger.getLogger(UpdateProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PasswordException ex) {
            Logger.getLogger(UpdateProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EmailException ex) {
            Logger.getLogger(UpdateProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TelephoneException ex) {
            Logger.getLogger(UpdateProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NameException ex) {
            Logger.getLogger(UpdateProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (NullAccountException ex) {
            Logger.getLogger(UpdateProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProfileException ex) {
            Logger.getLogger(UpdateProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PasswordException ex) {
            Logger.getLogger(UpdateProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EmailException ex) {
            Logger.getLogger(UpdateProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TelephoneException ex) {
            Logger.getLogger(UpdateProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NameException ex) {
            Logger.getLogger(UpdateProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
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
