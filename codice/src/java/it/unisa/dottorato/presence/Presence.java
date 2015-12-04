/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.presence;


import it.unisa.dottorato.account.PhdStudent;
import it.unisa.dottorato.account.Professor;
import it.unisa.dottorato.phdCourse.Lesson;
import java.io.Serializable;
import java.util.ArrayList;


/**
 *
 * @author Rembor
 */
public class Presence implements Serializable {
    
    private String fkPhdstudent ;
    private String fkLesson;
    public boolean isPresent=false;
    public boolean setPermission=false;

    public boolean isSetPermission() {
        return setPermission;
    }

    public void setSetPermission(boolean setPermission) {
        this.setPermission = setPermission;
    }
    
    
    
     

    public String getFkPhdstudent() {
        return fkPhdstudent;
    }

    public String getFkLesson() {
        return fkLesson;
    }

    public void setFkPhdstudent(String fkPhdstudent) {
        this.fkPhdstudent = fkPhdstudent;
    }

    public void setFkLesson(String fkLesson) {
        this.fkLesson = fkLesson;
    }
     public boolean isIsPresent() {
        return isPresent;
    }

    public void setIsPresent(boolean isSigned) {
        this.isPresent = isSigned;
    }

    
    
    
    
}
