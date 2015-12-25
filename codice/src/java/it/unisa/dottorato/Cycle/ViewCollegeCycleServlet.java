package it.unisa.dottorato.Cycle;

import it.unisa.dottorato.account.Professor;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**Servlet incaricata ad effettuare la richiesta di visualizzazione della 
 * lista del collegio dei docenti di un ciclo
 *
 * @author Tommaso Minichiello
 */
@WebServlet(name = "ViewCollegeCycleServlet", urlPatterns = {"/dottorato/ViewCollegeCycleServlet"})
public class ViewCollegeCycleServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods
     * Ricostruisce un array list di professori che formano il collegio docenti 
     * di un ciclo
     *
     * @param request  oggetto request per accedere ai parametri inviati attraverso
     * il metodo getParameter per ottenere il numero del ciclo <code>number</code> 
     * per effettuare la ricerca dei professori che formano il collegio dei docenti
     * del ciclo
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
             int number = Integer.parseInt(request.getParameter("number"));
            JSONObject result = new JSONObject();
            try {
                ArrayList<Professor> prof = CycleManager.getInstance().viewCollegeCycle(number);
                JSONArray resultArray = new JSONArray(prof);
                result.put("prof", resultArray);
                out.write(result.toString());
            } catch (ClassNotFoundException | SQLException | JSONException ex) {
                Logger.getLogger(ViewCollegeCycleServlet.class.getName()).log(Level.SEVERE, null, ex);
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
