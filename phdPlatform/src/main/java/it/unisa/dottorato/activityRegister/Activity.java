/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.activityRegister;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Ernesto Pizza
 */
public class Activity {
    
    private int idActivity;
    private String name;
    private String description;
    private Timestamp startDateTime;
    private Timestamp endDateTime;
    private float totalTime;
    private String typology;
    private String fkPhdStudent;    

    public int getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(int idActivity) {
        this.idActivity = idActivity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Timestamp startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Timestamp getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Timestamp endDateTime) {
        this.endDateTime = endDateTime;
    }

    public float getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(float totalTime) {
        this.totalTime = totalTime;
    }

    public String getTypology() {
        return typology;
    }

    public void setTypology(String typology) {
        this.typology = typology;
    }

    public String getFkPhdStudent() {
        return fkPhdStudent;
    }

    public void setFkPhdStudent(String fkPhdStudent) {
        this.fkPhdStudent = fkPhdStudent;
    }
    
    @Override
    public String toString(){
        return this.getIdActivity() 
                +" " + this.getName()
                +" " + this.getDescription()
                +" " + this.getStartDateTime()
                +" " + this.getEndDateTime()
                +" " + this.getTotalTime()
                +" " + this.getTypology()
                +" " + this.getFkPhdStudent();
    }
}
