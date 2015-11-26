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
//La classe Seminar identifica un seminario all'interno del calendario
public class Seminar implements Serializable{
    
    private int idSeminar;// variabile per identificare l'id del seminario (chiave primaria)
    private Date data; // variabile per identificare la data di un seminario
    private int startTime;// variabile per identificare l'inizio di un seminario
    private int endTime;  // variabile per identificare la fine di un seminario
    private String name; // variabile per identificare il nome del seminario
    private String namespeacker;// variabile per identificare lo speaker del seminario
    private String description;// variabile per identificare la descrizione del seminario
    private String place;// variabile per identificare il posto in cui si terrà il seminario
    private int FK_course;// variabile per identificare la chiave esterna al corso del seminario
    
    
     // metodo utilizzato per restituire l'id del seminario
    public int getIdSeminar(){
        return idSeminar;            
    }
    
    // metodo per settare l'id della lezione
    public void setIdLesson(int id){
        this.idSeminar = id;
    }
    
    // metodo utilizzato per restituire la data di un seminario
    public Date getData(){
        return data;            
    }
    
    // metodo per settare la data di un seminario
    public void setDate(Date date){
        this.data = date;
    }
    
    // metodo utilizzato per restituire l'ora di inizio seminario
    public int getStartTime(){
        return startTime;            
    }
    
    // metodo per settare l'ora di inizio seminario
    public void setStartTime(int start_time){
        this.startTime = start_time;
    }
    
    // metodo utilizzato per restituire l'ora di fine seminario
    public int getEndTime(){
        return endTime;            
    }
    
    // metodo per settare l'ora di fine seminario
    public void setEndTime(int end_time){
        this.endTime = end_time;
    }
    
    
     // metodo utilizzato per restituire il nome del seminario
    public String getName(){
        return name;            
    }
    
    // metodo per settare il nome del seminario
    public void setName(String nome){
        this.name = nome;
    }
    
     // metodo utilizzato per restituire lo speaker del seminario
    public String getNameSpeacker(){
        return namespeacker;            
    }
    
    // metodo per settare lo speaker del seminario
    public void setNameSpeacker(String nome_speacker){
        this.namespeacker = nome_speacker;
    }
    
     // metodo utilizzato per restituire la descrizione della lezione
    public String getDescription(){
        return description;            
    }
    
    // metodo per settare la decrizione della lezione
    public void setDescription(String descrizione){
        this.description = descrizione;
    }
    
     // metodo utilizzato per restituire il posto in cui si terrà il seminario
    public String getPlace(){
        return place;            
    }
    
    // metodo per settare il posto in cui si terrà il seminario
    public void setPlace(String posto){
        this.place = posto;
    }
    
    // metodo utilizzato per restituire la chiave esterna al corso del seminario
    public int getFK_course(){
        return FK_course;            
    }
    
    // metodo per settare la chiave esterna al corso del seminario
    public void setFK_course(int chiave_course){
        this.FK_course = chiave_course;
    }
}
