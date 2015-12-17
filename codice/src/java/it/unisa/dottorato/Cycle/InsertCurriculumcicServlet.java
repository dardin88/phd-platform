package it.unisa.dottorato.Cycle;

import it.unisa.dottorato.curriculumcic.Curriculumcic;
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

/** Servlet incaricata ad effettuare la richiesta di inserimento di una coppia
 * curriculum-ciclo (curriculumcic)
 * @author Tommaso Minichiello
 */
@WebServlet(name = "InsertCurriculumcic", urlPatterns = {"/dottorato/InsertCurriculumcic"})
public class InsertCurriculumcicServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request oggetto request per accedere ai parametri inviati attraverso
     * il metodo getParameter per ottenere il numero del ciclo <code>number</code> 
     * e il nome del curriculum <code>name</code> per effettuare l'inserimento di 
     * una nuova coppia curriculum-ciclo; la chiave esterna all'email di un docente
     * (cioè il coordinatore della coppia curriculumn-ciclo) è posta inizialmente
     * a <code>null</code>
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        try {

            JSONObject result = new JSONObject();
        int number = Integer.parseInt(request.getParameter("number"));
        String name = request.getParameter("name");
        
        
        Curriculumcic aCurriculumcic = new Curriculumcic();
        aCurriculumcic.setfkCycle(number);
        aCurriculumcic.setfkCurriculum(name);
        aCurriculumcic.setfkProfessor(null);
        
        result.put("result", true);

        try {
            CycleManager.getInstance().insertCurriculumcic(aCurriculumcic);
        } catch (ClassNotFoundException | SQLException ex) {
            result.put("result", false);
            Logger.getLogger(InsertCurriculumcicServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        out.write(result.toString());

        } catch (JSONException ex) {
            Logger.getLogger(InsertCurriculumcicServlet.class.getName()).log(Level.SEVERE, null, ex);
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
