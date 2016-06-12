/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.presence;

import it.unisa.dottorato.account.AccountManager;
import it.unisa.dottorato.account.PhdStudent;
import it.unisa.dottorato.account.Professor;
import it.unisa.dottorato.exception.IdException;
import it.unisa.dottorato.phdCourse.Lesson;
import it.unisa.dottorato.utility.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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

/**Servlet incaricata di effettuare la richiesta per ottenere i corsi di interesse del docente e i dottorandi 
 * che seguono questi corsi
 *
 * @author cadav
 */
@WebServlet(name = "GetPhdStudentsCoursebyProfessor", urlPatterns = {"/GetPhdStudentsCoursebyProfessor"})
public class GetPhdStudentsCoursebyProfessor extends HttpServlet {

   /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request oggetto request per accedere ai parametri inviati attraverso
     * il metodo getParameter per ottenere l'id della lezione idLesson per effettuare
     * la richiesta di ricerca e visualizzazione delle presenze di una lezione
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException, IdException, PhdStudentexception, ParseException {
        response.setContentType("text/html;charset=UTF-8");
          ArrayList<PhdStudent> course =null;
        PhdStudent student=null;
       
        
        try (PrintWriter out = response.getWriter()) {
           
              HttpSession session = request.getSession();
                  Professor prof= (Professor) session.getAttribute("account");
 
  int cyclenumber=parseInt(request.getParameter("Ciclo"));
  String Professor1 = prof.getSecondaryEmail();
                String Professor = request.getParameter("fkProfessor");
            
            
                try { 
   
                course= AccountManager.getInstance().getPhdStudentsCoursebyProfessor(Professor1, cyclenumber);
                JSONArray resultArray = new JSONArray();
              PhdStudent[]  temp= new PhdStudent[course.size()];
              temp = course.toArray(temp);
                
                for(int i=0;i<temp.length;i++){
                 ArrayList<Lesson> oreLezioni=PresenceManager.getInstance().getLessonsHours(Utility.AppendQuote(temp[i].getSecondaryEmail()), cyclenumber, Utility.AppendQuote(temp[i].getLink()));
                 ArrayList<Lesson> oreLezioniTotali=PresenceManager.getInstance().getLessonsHoursTotal(Utility.AppendQuote(temp[i].getLink()), cyclenumber);
                 int hoursStudent = calculateHours(oreLezioni);
                 int totalHours = calculateHours(oreLezioniTotali);
                 ArrayList<Presence> presenze = PresenceManager.getInstance().getTotalLesson((Utility.AppendQuote(temp[i].getSecondaryEmail())), (Utility.AppendQuote(temp[i].getLink())),cyclenumber);
                JSONObject result = new JSONObject();
                result.put("nome", temp[i].getLink());
                result.put("presenze", presenze.get(0).getTotalPresence());
                result.put("presenzeEff", presenze.get(0).getPresenzeEff()); 
                result.put("Assenze", presenze.get(0).getAssenze()); 
                result.put("DottorandoName", temp[i].getName());
                result.put("DottorandoSurname", temp[i].getSurname());
                result.put("OreTotali", totalHours);
                result.put("OreFrequentate", hoursStudent);
                resultArray.put(result);
                }
                 //Map<String,ArrayList<JSONObject>> map = tabellaPhd(resultArray);
                 JSONObject toSend = new JSONObject(tabellaPhd(resultArray));
                out.write(toSend.toString());
            }  catch (JSONException ex) {
                Logger.getLogger(GetCourseByProfessorServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(GetPhdStudentsCoursebyProfessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetPhdStudentsCoursebyProfessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IdException ex) {
            Logger.getLogger(GetPhdStudentsCoursebyProfessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PhdStudentexception ex) {
            Logger.getLogger(GetPhdStudentsCoursebyProfessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(GetPhdStudentsCoursebyProfessor.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GetPhdStudentsCoursebyProfessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetPhdStudentsCoursebyProfessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IdException ex) {
            Logger.getLogger(GetPhdStudentsCoursebyProfessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PhdStudentexception ex) {
            Logger.getLogger(GetPhdStudentsCoursebyProfessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(GetPhdStudentsCoursebyProfessor.class.getName()).log(Level.SEVERE, null, ex);
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

   private Map<String,ArrayList<JSONObject>> tabellaPhd(JSONArray json ) throws JSONException{
       Map<String,ArrayList<JSONObject>> map = new HashMap<>();
      for(int i=0; i<json.length(); i++){
          String key = json.getJSONObject(i).getString("nome");
          JSONObject obj = new JSONObject();
           obj.put("presenze", json.getJSONObject(i).getInt("presenze"));
           obj.put("presenzeEff",json.getJSONObject(i).getInt("presenzeEff")); 
           obj.put("Assenze", json.getJSONObject(i).getInt("Assenze")); 
           obj.put("DottorandoName", json.getJSONObject(i).getString("DottorandoName"));
           obj.put("DottorandoSurname",json.getJSONObject(i).getString("DottorandoSurname") );
           obj.put("OreTotali", json.getJSONObject(i).getInt("OreTotali"));
           obj.put("OreFrequentate", json.getJSONObject(i).getInt("OreFrequentate"));
          if(!map.containsKey(key)){
          ArrayList<JSONObject> tmpArray = new ArrayList<>();
          
          tmpArray.add(obj);
          map.put(key, tmpArray);
          }else{
          map.get(key).add(obj);
          }
      }
   return map;
   }

    private int calculateHours(ArrayList<Lesson> oreLezioni) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm a"); 
        int oreTot = 0;
      
        for(Lesson i: oreLezioni){
                   Date d1;
                   Date d2;
                    d1= df.parse(i.getData()+" "+i.getEndTime());
                    d2= df.parse(i.getData()+" "+i.getStartTime());
                    int hoursDifference = (int)((d1.getTime() - d2.getTime()) / 3600000L);
                    oreTot+=hoursDifference;
        }
        return oreTot;
    }

  
}
