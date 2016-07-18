package it.unisa.dottorato.news;
import it.unisa.dottorato.exception.IdException;
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
/**Servlet incaricata ad effettuare la richiesta di ricerca di news dato un id
 *
 * @author Rembor
 */

@WebServlet(name = "GetNewsbyId", urlPatterns = {"/GetNewsbyId"})
public class GetNewsByIdServlet extends HttpServlet{
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request oggetto request per accedere ai parametri inviati attraverso
     * il metodo getParameter per ottenere l'id della news idNews per effettuare 
     * la richiesta di ricerca della news
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, IdException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int id = Integer.parseInt(request.getParameter("idNews"));
            JSONObject result = new JSONObject();
            try {
               News avviso = NewsManager.getInstance().getNewsById(id);
                result.put("idNews", avviso.getId());
                result.put("title", avviso.getTitle());
                result.put("description", avviso.getDescription());
                result.put("recipients", avviso.getRecipients());

                out.write(result.toString());
            } catch (SQLException | JSONException | ConnectionException | ClassNotFoundException ex) {
                Logger.getLogger(GetNewsByIdServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (IdException ex) {
            Logger.getLogger(GetNewsByIdServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GetNewsByIdServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (IdException ex) {
            Logger.getLogger(GetNewsByIdServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GetNewsByIdServlet.class.getName()).log(Level.SEVERE, null, ex);
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
