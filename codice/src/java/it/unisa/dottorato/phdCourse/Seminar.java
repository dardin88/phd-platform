/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.phdCourse;

import java.text.SimpleDateFormat;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
/**
 *
 * @author Giuseppe Picciocchi
 */
//La classe Seminar identifica un seminario all'interno del calendario
public class Seminar implements Serializable{
    
    private int idSeminar;// variabile per identificare l'id del seminario (chiave primaria)
    private SimpleDateFormat sdf = new SimpleDateFormat(); // variabile per l'utilizzo delle date
    private Date startDate;// variabile per identificare l'inizio del seminario
    private Date endDate;  // variabile per identificare la fine del seminario
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
    
    // metodo utilizzato per restituire la data di inizio seminario
    public String getStartDate(){
            String dataStr = sdf.format(startDate);
            sdf.applyPattern("dd MMMM yyyy-HH.mm");
            return dataStr;
    }
    
    // metodo utilizzato per settare la data di inizio seminario
    public void setStartDate(String data) throws ParseException{
            sdf.applyPattern("dd MMMM yyyy-HH.mm");
            this.startDate = sdf.parse(data);
    }
    
     // metodo utilizzato per restituire la data di fine seminario
    public String getEndDate(){
            String dataStr = sdf.format(endDate);
            sdf.applyPattern("dd MMMM yyyy-HH.mm");
            return dataStr;
    }
    
    // metodo utilizzato per settare la data di fine seminario
    public void setEndDate(String data) throws ParseException{
            sdf.applyPattern("dd MMMM yyyy-HH.mm");
            this.endDate = sdf.parse(data);
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
