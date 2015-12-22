package it.unisa.dottorato.Cycle;

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


/**Servlet incaricata ad effettuare la richiesta di inserimento di un ciclo
 *
 * @author Tommaso Minichiello
 */
@WebServlet(name = "InsertCycle", urlPatterns = {"/dottorato/InsertCycle"})
public class InsertCycleServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request oggetto request per accedere ai parametri inviati attraverso
     * il metodo getParameter per ottenere il numero del nuovo ciclo <code>number</code>,
     * l'anno del nuovo ciclo <code>year</code> e l'email del coordinatore <code>professor</code> 
     * per effettuare l'inserimento di un nuovo ciclo
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        try {
            response.setContentType("text/html;charset=UTF-8");
            
            PrintWriter out = response.getWriter();
            JSONObject result = new JSONObject();
            
            int number = CycleManager.getInstance().nextNumber();
            String description = request.getParameter("description");
            String year = request.getParameter("year");
            
            Cycle aPhdCycle = new Cycle();
            aPhdCycle.setNumber(number);
            aPhdCycle.setDescription(description);
            aPhdCycle.setYear(year);
            
            try {
                CycleManager.getInstance().insertCycle(aPhdCycle);
            } catch (ClassNotFoundException | SQLException ex)  {
                result.put("result", false);
                Logger.getLogger(InsertCycleServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            out.write(result.toString());
            
        } catch (JSONException ex) {
            Logger.getLogger(InsertCycleServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            throws ServletException, IOException{
           try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateCycleServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UpdateCycleServlet.class.getName()).log(Level.SEVERE, null, ex);
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
