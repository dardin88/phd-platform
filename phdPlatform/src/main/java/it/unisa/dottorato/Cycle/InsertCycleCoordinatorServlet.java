package it.unisa.dottorato.Cycle;

import it.unisa.dottorato.exception.ReferenceException;
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

/** Servlet incaricata ad effettuare la richiesta di inserimento di un 
 * coordinatore in un ciclo
 *
 * @author Tommaso Minichiello
 */
@WebServlet(name = "InsertCycleCoordinator", urlPatterns = {"/InsertCycleCoordinator"})
public class InsertCycleCoordinatorServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request oggetto request per accedere ai parametri inviati attraverso
     * il metodo getParameter per ottenere il numero del ciclo <code>number</code> 
     * e l'emai del professore <code>fkProfessore</code> per effettuare l'inserimento di 
     * un nuovo coordinatore del ciclo
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws it.unisa.dottorato.exception.ReferenceException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ReferenceException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            JSONObject result = new JSONObject();
            int number = Integer.parseInt( request.getParameter("number"));
            String professor = request.getParameter("fkProfessor");
            
           
            
            result.put("result", true);

            try {
                CycleManager.getInstance().insertCycleCoordinator(number,professor);
            } catch (ClassNotFoundException | SQLException ex) {
                result.put("result", false);
                Logger.getLogger(InsertCycleCoordinatorServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            out.write(result.toString());

        } catch (JSONException ex) {
            Logger.getLogger(InsertCycleCoordinatorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
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
        } catch (Exception ex) {
            Logger.getLogger(InsertCycleCoordinatorServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(InsertCycleCoordinatorServlet.class.getName()).log(Level.SEVERE, null, ex);
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
