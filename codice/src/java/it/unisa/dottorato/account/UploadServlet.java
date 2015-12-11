package it.unisa.dottorato.account;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**Servlet incaricata ad effettuare la richiesta di ....???
 *
 * @author Armando
 */
@MultipartConfig
public class UploadServlet extends HttpServlet
{
  
  //boh
  @Override
  protected void doPost(HttpServletRequest request, 
   HttpServletResponse response) throws ServletException, IOException
         {
    String name = request.getParameter("name"); //<input type="name" name="name">
    String description = request.getParameter("description"); //  <input type="text" name="description">
    Part filePart = request.getPart("file"); //  <input type="file" name="file">
    String fileName = filePart.getSubmittedFileName();
    filePart.write("localhost/phd-platform/test/");
   }

}