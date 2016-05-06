/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.phdCourse;

import it.unisa.dottorato.account.Account;
import it.unisa.dottorato.account.PhdStudent;
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
import java.util.Iterator;
/**
 *
 * @author Rembor
 */

    @WebServlet(name = "GetAllCourse", urlPatterns = {"/GetAllCourse"})
public class GetAllCourseServlet  extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request oggetto request per accedere alla sessione chiamante attraverso
     * il metodo getSession per ottenere la sessione chiamante la servlet e
     * ricavarsi il corso
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            JSONObject result = new JSONObject();
            try {
                
                HttpSession session = request.getSession();
                Account acc= (Account) session.getAttribute("account");
                CalendarManager mng=CalendarManager.getInstance();
                ArrayList<Course> course= new ArrayList();
                if(acc.getTypeAccount().equals("phdstudent"))
                {
                    PhdStudent loggedPerson=(PhdStudent) acc;
                    ArrayList<Integer> IdCourse= mng.getCourseListId(loggedPerson.getfkCycle(), loggedPerson.getfkCurriculum());
                    Iterator<Integer> myIteretor = IdCourse.iterator();
                    while (myIteretor.hasNext())
                    {
                        Integer id = myIteretor.next();
                        course.add(mng.getCourseById(id));
                        
                    }
   
                }
                else
                {
                    course =  mng.getAllCourse(); // da modificare ancora
                }
                
                
                JSONArray resultArray = new JSONArray(course);
                result.put("course", resultArray);
                out.write(result.toString());
            } catch (SQLException | JSONException | IdException ex) {
                Logger.getLogger(GetAllLessonServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GetAllCourseServlet.class.getName()).log(Level.SEVERE, null, ex);
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
