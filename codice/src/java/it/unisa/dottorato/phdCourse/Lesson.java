/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.phdCourse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
/**
 *
 * @author Giuseppe Picciocchi
 */

//La classe lesson rappresenta una lezione del calendario
public class Lesson implements Serializable{
    
    private int idLesson;// variabile per identificare l'id della lezione (chiave primaria)
    private SimpleDateFormat sdf = new SimpleDateFormat(); // variabile per l'utilizzo delle date
   
    private Date startDate;// variabile per identificare l'inizio della lezione
    private Date endDate;  // variabile per identificare la fine della lezione
    private String name; // variabile per identificare il nome della lezione
    private String classroom;// variabile per identificare la classe in cui si svolgerà la lezione
    private String description;// variabile per identificare la descrizione della lezione
    private int cycle;// variabile per identificare il ciclo a cui appartiene la lezione
    private int curriculum;// variabile per identificare il curriculum a cui appartiene la lezione
    private int FK_course;// variabile per identificare la chiave esterna al corso della lezione
    
    
    // metodo utilizzato per restituire l'id della lezione
    public int getIdLesson(){
        return idLesson;            
    }
    
    // metodo per settare l'id della lezione
    public void setIdLesson(int id){
        this.idLesson = id;
    }
    
    // metodo utilizzato per restituire la data di inizio lezione
    public String getStartDate(){
            String dataStr = sdf.format(startDate);
            sdf.applyPattern("dd MMMM yyyy-HH.mm");
            return dataStr;
    }
    
    // metodo utilizzato per settare la data di inizio lezione
    public void setStartDate(String data) throws ParseException{
            sdf.applyPattern("dd MMMM yyyy-HH.mm");
            this.startDate = sdf.parse(data);
    }
    
     // metodo utilizzato per restituire la data di fine lezione
    public String getEndDate(){
            String dataStr = sdf.format(endDate);
            sdf.applyPattern("dd MMMM yyyy-HH.mm");
            return dataStr;
    }
    
    // metodo utilizzato per settare la data di fine lezione
    public void setEndDate(String data) throws ParseException{
            sdf.applyPattern("dd MMMM yyyy-HH.mm");
            this.endDate = sdf.parse(data);
    }
    
    
     // metodo utilizzato per restituire il nome della lezione
    public String getName(){
        return name;            
    }
    
    // metodo per settare il nome della lezione
    public void setName(String nome){
        this.name = nome;
    }
    
    
     // metodo utilizzato per restituire la classe della lezione
    public String getClassroom(){
        return classroom;            
    }
    
    // metodo per settare la classe della lezione
    public void setClassroom(String classe){
        this.classroom = classe;
    }
    
    
     // metodo utilizzato per restituire la descrizione della lezione
    public String getDescription(){
        return description;            
    }
    
    // metodo per settare la decrizione della lezione
    public void setDescription(String descrizione){
        this.description = descrizione;
    }
    
     // metodo utilizzato per restituire il ciclo della lezione
    public int getCycle(){
        return cycle;            
    }
    
    // metodo per settare il ciclo della lezione
    public void setCycle(int ciclo){
        this.cycle = ciclo;
    }
    
    // metodo utilizzato per restituire il curriculum della lezione
    public int getCurriculum(){
        return curriculum;            
    }
    
    // metodo per settare il curriculum della lezione
    public void setCurriculum(int curri){
        this.curriculum = curri;
    }
    
    // metodo utilizzato per restituire la chiave esterna al corso della lezione
    public int getFK_course(){
        return FK_course;            
    }
    
    // metodo per settare la chiave esterna al corso della lezione
    public void setFK_course(int chiave_course){
        this.FK_course = chiave_course;
    }
                                            }
    