/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.presence;

import it.unisa.dottorato.account.Account;
import it.unisa.dottorato.account.PhdStudent;
import it.unisa.dottorato.exception.IdException;
import it.unisa.dottorato.phdCourse.Course;
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

/**
 *Servlet incaricata di effettuare la richiesta delle lezioni, delle presenze e delle assenze dello studente 
 * @author cadav
 */
@WebServlet(name = "GetTotalPresenceServlet", urlPatterns = {"/GetTotalPresenceServlet"})
public class GetTotalPresenceServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
                  throws ServletException, IOException, PhdStudentexception, ParseException {
       response.setContentType("text/html;charset=UTF-8");
PrintWriter out = response.getWriter();

        try {
            PhdStudent student=null;
              HttpSession session = request.getSession();
     Account accountType=    (Account)   session.getAttribute("account");
   
     if(accountType.getTypeAccount().equals("phdstudent")){
                student = (PhdStudent) session.getAttribute("account");
    
 String dottorando = student.getfkAccount();
 int cyclenumber=parseInt(request.getParameter("Ciclo"));

     //  String coursename=request.getParameter("Coursename");
     ArrayList<Course> courses=PresenceManager.getInstance().getCorsobyDottorando(Utility.AppendQuote(dottorando), cyclenumber);
     
   
                 
         JSONArray resultArray = new JSONArray();
     for(Course i: courses){
          JSONObject result = new JSONObject();
                ArrayList<Presence> presenze = PresenceManager.getInstance().getTotalLesson((Utility.AppendQuote(dottorando)), (Utility.AppendQuote(i.getName())),cyclenumber);
                
            ArrayList<Lesson> oreLezioni=PresenceManager.getInstance().getLessonsHours(Utility.AppendQuote(dottorando), cyclenumber, Utility.AppendQuote(i.getName()));
             ArrayList<Lesson> oreLezioniTotali=PresenceManager.getInstance().getLessonsHoursTotal(Utility.AppendQuote(i.getName()), cyclenumber);
             
                int hoursStudent = calculateHours(oreLezioni);
                 int totalHours = calculateHours(oreLezioniTotali);
                result.put("nome", i.getName());
                result.put("presenze", presenze.get(0).getTotalPresence());
                result.put("presenzeEff", presenze.get(0).getPresenzeEff()); 
                result.put("Assenze", presenze.get(0).getAssenze()); 
                result.put("OreTotali", totalHours);
                result.put("OreFrequentate", hoursStudent);
                 resultArray.put(result);
             
                 
       
     }
    
      out.write(resultArray.toString()); }
   
            } catch (SQLException | JSONException ex) {
                Logger.getLogger(GetPresenceToLessonServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IdException ex) {
             Logger.getLogger(GetPresenceToLessonServlet.class.getName()).log(Level.SEVERE, null, ex);
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(GetPresenceToLessonServlet.class.getName()).log(Level.SEVERE, null, ex);
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
       } catch (PhdStudentexception ex) {
           Logger.getLogger(GetTotalPresenceServlet.class.getName()).log(Level.SEVERE, null, ex);
       } catch (ParseException ex) {
           Logger.getLogger(GetTotalPresenceServlet.class.getName()).log(Level.SEVERE, null, ex);
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
       } catch (PhdStudentexception ex) {
           Logger.getLogger(GetTotalPresenceServlet.class.getName()).log(Level.SEVERE, null, ex);
       } catch (ParseException ex) {
           Logger.getLogger(GetTotalPresenceServlet.class.getName()).log(Level.SEVERE, null, ex);
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
