/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.phdCourse;

import java.io.Serializable;
import java.util.Date;
/**
 *
 * @author Giuseppe Picciocchi
 */

//La classe lesson rappresenta una lezione del calendario
public class Lesson implements Serializable{
    
    private int idLesson;// variabile per identificare l'id della lezione (chiave primaria)
    private Date data; // variabile per identificare la data di una lezione
    private int startTime;// variabile per identificare l'inizio della lezione
    private int endTime;  // variabile per identificare la fine della lezione
    private String name; // variabile per identificare il nome della lezione
    private String classroom;// variabile per identificare la classe in cui si svolgerà la lezione
    private String description;// variabile per identificare la descrizione della lezione
    private int cycle;// variabile per identificare il ciclo a cui appartiene la lezione
    private String curriculum;// variabile per identificare il curriculum a cui appartiene la lezione
    private int FK_course;// variabile per identificare la chiave esterna al corso della lezione
    
    
    // metodo utilizzato per restituire l'id della lezione
    public int getIdLesson(){
        return idLesson;            
    }
    
    // metodo per settare l'id della lezione
    public void setIdLesson(int id){
        this.idLesson = id;
    }
    
    // metodo utilizzato per restituire la data della lezione
    public Date getData(){
        return data;            
    }
    
    // metodo per settare la data di una lezione
    public void setDate(Date date){
        this.data = date;
    }
    
    // metodo utilizzato per restituire l'ora di inizio lezione
    public int getStartTime(){
        return startTime;            
    }
    
    // metodo per settare l'ora di inizio di lezione
    public void setStartTime(int start_time){
        this.startTime = start_time;
    }
    
    // metodo utilizzato per restituire l'ora di fine lezione
    public int getEndTime(){
        return endTime;            
    }
    
    // metodo per settare l'ora di fine lezione
    public void setEndTime(int end_time){
        this.endTime = end_time;
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
    public String getCurriculum(){
        return curriculum;            
    }
    
    // metodo per settare il curriculum della lezione
    public void setCurriculum(String curri){
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
    