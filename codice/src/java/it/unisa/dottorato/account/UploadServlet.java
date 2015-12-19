package it.unisa.dottorato.account;

import it.unisa.dottorato.autenticazione.EmailException;
import it.unisa.dottorato.autenticazione.PasswordException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**Servlet incaricata ad effettuare la richiesta di ....???
 *
 * @author Armando
 */
@WebServlet(name = "UploadServlet", urlPatterns = {"/UploadFile"})
@MultipartConfig
public class UploadServlet extends HttpServlet
{
  
  //
  
  protected void processRequest(HttpServletRequest request, 
   HttpServletResponse response) throws ServletException, IOException
         { 
    response.setContentType("text/html;charset=UTF-8"); 
    PrintWriter out = response.getWriter();
    String name = request.getParameter("name"); //<input type="name" name="name">
    String description = request.getParameter("description"); //  <input type="text" name="description">
    Part filePart = request.getPart("file"); //  <input type="file" name="file">
    String fileName = filePart.getSubmittedFileName();
    filePart.write("localhost/phd-platform/test/");
    
    out.println("<script type=\"text/javascript\">");
            out.println("alert('Siamo Nel servlet del FILE!!!');");
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

