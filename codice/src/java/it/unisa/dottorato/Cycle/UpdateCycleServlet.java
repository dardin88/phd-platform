package it.unisa.dottorato.Cycle;

import it.unisa.dottorato.exception.DateException;
import it.unisa.dottorato.exception.DescriptionException;
import it.unisa.dottorato.exception.IdException;
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


/** Servlet incaricata ad effettuare la richiesta di aggiornamento di un ciclo 
 * @author Tommaso Minichiello
 */
@WebServlet(name = "UpdateCycle", urlPatterns = {"/dottorato/UpdateCycle"})
public class UpdateCycleServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  oggetto request per accedere ai parametri inviati attraverso
     * il metodo getParameter per ottenere il numero del ciclo <code>number</code>, 
     * la descrizione del ciclo <code>description</code> e l'anno del ciclo <code>year</code>
     * per effettuare l'aggiornamento di unciclo; la chiave esterna all'email di un docente
     * (cioè il coordinatore del ciclo) è posta inizialmente a <code>null</code>
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CycleException, IdException, DateException, DescriptionException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            JSONObject result = new JSONObject();

            int number = Integer.parseInt(request.getParameter("number"));
            String description = request.getParameter("description");
            String year = request.getParameter("year");

            Cycle aPhdCycle = new Cycle();
            aPhdCycle.setYear(year);
            aPhdCycle.setDescription(description);

            result.put("result", true);

            try {
                CycleManager.getInstance().updateCycle(number, aPhdCycle);

            } catch (ClassNotFoundException | SQLException ex) {
                result.put("result", false);
                Logger.getLogger(UpdateCycleServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            out.write(result.toString());

        } catch (JSONException ex) {
            Logger.getLogger(UpdateCycleServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (CycleException ex) {
            Logger.getLogger(UpdateCycleServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IdException ex) {
            Logger.getLogger(UpdateCycleServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DateException ex) {
            Logger.getLogger(UpdateCycleServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DescriptionException ex) {
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
        } catch (CycleException ex) {
            Logger.getLogger(UpdateCycleServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IdException ex) {
            Logger.getLogger(UpdateCycleServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DateException ex) {
            Logger.getLogger(UpdateCycleServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DescriptionException ex) {
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
