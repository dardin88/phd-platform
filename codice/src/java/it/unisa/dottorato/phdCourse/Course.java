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

/*La classe Course identifica il corso del calendario*/

public class Course implements Serializable{
    
    private int idCourse; // variabile per identificare un corso ( chiave primaria)
    private String curriculum; // variabile utilizzata per assegnare un curriculum
    private int cycle;  // variabile utilizzata per assegnare un ciclo
    private String name; // variabile utilizzata per assegnare un nome
    private String FK_curriculum; // variabile per la chiave esterna al "curriculum"
    private int FK_cycle; // variabile per la chiave esterna al "ciclo"
    private String description;
    private Date startDate;
    private Date endDate;
    
    
    
    // metodo utilizzato per restituire l'id del corso
    public int getIdCourse(){
        return idCourse;            
    }
    
    // metodo per settare l'id del corso
    public void setIdCourse(int id){
        this.idCourse = id;
    }

// metodo utilizzato per restituire il curriculum del corso
    public String getCurriculum(){
        return curriculum;            
    }
   
    // metodo per settare il curriculum del corso
    public void setCurriculum(String curr){
        this.curriculum = curr;
    }

    
    // metodo utilizzato per restituire il ciclo del corso
    public int getCycle(){
        return cycle;
    }
    
    //metodo per settare il ciclo del corso
    public void setCycle(int ciclo){
        this.cycle = ciclo;
    }
    
    
    //metodo utilizzato per restituire il nome del corso
    public String getName(){
        return name;
    }
    
    //metodo per settare il nome del corso
    public void setName(String nome){
        this.name = nome;
    }
    
    //metodo utilizzato per restituire la chiave esterna al curriculum
    public String getFK_curriculum(){
        return FK_curriculum;
    }
    
    //metodo per settare la chiave esterna al curriculum
    public void setFK_curriculum(String chiave_curr){
        this.FK_curriculum = chiave_curr;
    }
    
    
    //metodo utilizzato per restituire la chiave esterna al ciclo
    public int getFK_cycle(){
        return FK_cycle;
    }
    
    //metodo per settare la chiave esterna al ciclo
    public void setFK_curriculum(int chiave_cycle){
        this.FK_cycle = chiave_cycle;
    }
 
    
    
    // metodo utilizzato per restituire la descrizione del corso
    public String getDescription(){
        return description;            
    }
    
    // metodo per settare la decrizione del corso
    public void setDescription(String descrizione){
        this.description = descrizione;
    }
    
    
     // metodo utilizzato per restituire la data di inizio corso
    public Date getStartDate(){
        return startDate;            
    }
    
    // metodo per settare la data di inizio corso
    public void setStartDate(Date date){
        this.startDate = date;
    }
    
   // metodo utilizzato per restituire la data fine del corso
    public Date getEndDate(){
        return endDate;            
    }
    
    // metodo per settare la data di fine corso
    public void setEndDate(Date date){
        this.endDate = date;
    } 
}
