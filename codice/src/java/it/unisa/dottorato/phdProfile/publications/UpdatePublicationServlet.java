package it.unisa.dottorato.phdProfile.publications;

import it.unisa.dottorato.account.PhdStudent;
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
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;

/**Servlet incaricata ad effettuare la richiesta di aggiornamento di una pubblicazione
 *
 * @author gemmacatolino
 */
@WebServlet(name = "UpdatePublicationServlet", urlPatterns = {"/dottorato/UpdatePublicationServlet"})
public class UpdatePublicationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request oggetto request per accedere ai parametri inviati attraverso
     * il metodo getParameter per ottenere il titolo, la publicationIssue, l'anno,
     * il numero di pagine, il link, il tipo, gli altri autori e l'abstract di una
     * pubblicazione per effettuare la richiesta di modifica di una nuova pubblicazione
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        JSONObject result = new JSONObject();
        PrintWriter out = response.getWriter();

        try {
            try {
                response.setContentType("text/html;charset=UTF-8");

                int idPublication = Integer.parseInt("" + request.getSession().getAttribute("idPublication"));
                String title = request.getParameter("title");
                String publicationIssue = request.getParameter("publicationIsse");
                String year = request.getParameter("year");
                String numberPage = request.getParameter("numberPage");
                String link = request.getParameter("link");
                String type = request.getParameter("type");
                String authors = request.getParameter("otherAuthors");
                String pAbstract = request.getParameter("abstract");

                HttpSession session = request.getSession();
                PhdStudent loggedPerson = (PhdStudent) session.getAttribute("phdStudent"); //da verificare

                Publication publication = new Publication();

                publication.setTitle(title);
                publication.setPublicationIssue(publicationIssue);
                publication.setYear(year);
                publication.setNumberPages(Integer.parseInt(numberPage));
                publication.setLink(link);
                publication.setType(type);
                publication.setAuthors(authors);
                publication.setAbstract(pAbstract);
                publication.setFkPhdstudent(loggedPerson.getfkAccount()); // da modificare ancora

                PublicationManager.getInstance().update(idPublication, publication);
                result.put("result", true);

                out.println("<script type=\"text/javascript\">");
                out.println("alert('La pubblicazione è stata modificata.');");
                out.println("location='publicationActivity.jsp';");
                out.println("</script>");
            } catch (SQLException ex) {
                Logger.getLogger(UpdatePublicationServlet.class.getName()).log(Level.SEVERE, null, ex);
                result.put("result", false);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UpdatePublicationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.write(result.toString());

        } catch (JSONException ex) {
            Logger.getLogger(UpdatePublicationServlet.class.getName()).log(Level.SEVERE, null, ex);
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
