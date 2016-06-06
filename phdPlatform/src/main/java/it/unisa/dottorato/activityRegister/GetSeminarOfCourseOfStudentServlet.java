/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.activityRegister;

import it.unisa.dottorato.account.PhdStudent;
import it.unisa.dottorato.phdCourse.Course;
import it.unisa.dottorato.phdCourse.Seminar;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
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
 * Servlet che restituisce tutti i seminari e il corso a cui appartengono.
 * la selezione avviene in base all'anno corrente. vengono restituiti tutti i seminari
 * che si sono tenuti nell'anno accademico che va da settembre a settembre
 * @author Liliana
 */
@WebServlet(name = "GetSeminarOfCourseOfStudent", urlPatterns = {"/GetSeminarOfCourseOfStudent"})
public class GetSeminarOfCourseOfStudentServlet extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
               
        JSONObject result = new JSONObject();

        try (PrintWriter out = response.getWriter()) {
            
            HttpSession session = request.getSession();
            PhdStudent loggedPerson = (PhdStudent) session.getAttribute("account");
            String startYear = ""+ (Calendar.getInstance().get(Calendar.YEAR)-1);
           
            ArrayList<Seminar> seminar = (ArrayList<Seminar>)ActivityRegisterManager.getInstance().getSeminarActivitiesByStudent(loggedPerson.getfkAccount(),startYear); 
    
            ArrayList<Course> courses = (ArrayList<Course>)ActivityRegisterManager.getInstance().getSeminarCoursesOfStudent(loggedPerson.getfkAccount(),startYear);   

           JSONArray resultArray = new JSONArray();
           
            for (int i = 0; i < courses.size(); i++)
                for(int j=0; j < seminar.size(); j++)
                    if(courses.get(i).getIdCourse() == seminar.get(j).getFK_course()){
                        
                        JSONObject jo = new JSONObject();
                        jo.put("idSeminar", seminar.get(j).getIdSeminar());
                        jo.put("name", seminar.get(j).getName());
                        jo.put("date", seminar.get(j).getData());
                        jo.put("startTime", seminar.get(j).getStartTime());
                        jo.put("endTime", seminar.get(j).getEndTime());
                        jo.put("nameSpeacker", seminar.get(j).getNameSpeacker());
                        jo.put("description", seminar.get(j).getDescription());
                        jo.put("place", seminar.get(j).getPlace());
                        jo.put("fkCourse", seminar.get(j).getFK_course());

                        jo.put("courseName", courses.get(i).getName());

                        resultArray.put(jo);
                    }
            
            result.put("seminarsOfCourse", resultArray);
            out.write(result.toString());
        } catch (JSONException ex) {
                Logger.getLogger(GetSeminarOfCourseOfStudentServlet.class.getName()).log(Level.SEVERE, null, ex);
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
