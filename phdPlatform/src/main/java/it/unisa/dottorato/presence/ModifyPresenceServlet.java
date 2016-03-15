package it.unisa.dottorato.presence;
import it.unisa.dottorato.exception.IdException;
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
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**Servlet incaricata ad effettuare la richiesta di modificare una presenze
 *
 * @author Rembor
 */
@WebServlet(name = "ModifyPresence", urlPatterns = {"/ModifyPresence"})
public class ModifyPresenceServlet extends HttpServlet{
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request oggetto request per accedere ai parametri inviati attraverso
     * il metodo getParameter per ottenere il nuovo parametro booleano per settare
     * la presenza, newSignature, l'email del dottorando, il nome della lezione
     * per effettuare la richiesta di modifica di una presenza
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws JSONException if a JSON error occurs
     */
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException, IdException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JSONObject result = new JSONObject();
        try {

            
   
            String fkPhdstudent = request.getParameter("fkPhdstudent");
            int idLesson=Integer.parseInt(request.getParameter("fkLesson"));
           PresenceManager.getInstance().modifyPresence(fkPhdstudent,idLesson);
            
       result.put("result",true);
           
        }catch (SQLException ex) {
                result.put("result", false);
                Logger.getLogger(ModifyPresenceServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
   
         

         catch (PhdStudentexception ex) { 
             Logger.getLogger(ModifyPresenceServlet.class.getName()).log(Level.SEVERE, null, ex);
         } 
           finally {
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
         } catch (JSONException ex) {
             Logger.getLogger(ModifyPresenceServlet.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IdException ex) {
             Logger.getLogger(ModifyPresenceServlet.class.getName()).log(Level.SEVERE, null, ex);
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
         } catch (JSONException ex) {
             Logger.getLogger(ModifyPresenceServlet.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IdException ex) {
             Logger.getLogger(ModifyPresenceServlet.class.getName()).log(Level.SEVERE, null, ex);
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
