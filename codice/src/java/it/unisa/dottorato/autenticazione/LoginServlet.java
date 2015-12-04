/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.autenticazione;

import it.unisa.dottorato.account.Professor;
import it.unisa.dottorato.account.PhdStudent;
import it.unisa.dottorato.account.Account;
import it.unisa.dottorato.account.AccountManager;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.integrazione.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        try {
            String username = request.getParameter("email");
            String password = request.getParameter("password");

            AccountManager accountManager = AccountManager.getInstance();
            Account account = accountManager.login(username, password);

            if (account != null) {
               if (account instanceof PhdStudent) {
                    session.removeAttribute("loginError");
                    session.setAttribute("account", account);
                    response.sendRedirect("dottorato/index.jsp");
                } else if (account instanceof Professor) {
                    session.removeAttribute("loginError");
                    session.setAttribute("account", account);
                    response.sendRedirect("indexLog.jsp");    
                } else {
                    session.setAttribute("loginError", "error");
                    response.sendRedirect("login.jsp");
                }
            } else {
                session.setAttribute("loginError", "error");
                response.sendRedirect("login.jsp");
            }
        } catch (SQLException sqlException) {
            out.print("<h1>SQL Exception: </h1>" + sqlException.getMessage());
        } catch (ConnectionException connectionException) {
            out.print("<h1>Connection Exception</h1>");
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
