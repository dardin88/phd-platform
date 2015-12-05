/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.account;

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

/**
 *
 * @author gemmacatolino
 */
@WebServlet(name = "UpdateProfileServlet", urlPatterns = {"/dottorato/UpdateProfileServlet"})
public class UpdateProfileServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
            out.println("location='profile.jsp';");
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
