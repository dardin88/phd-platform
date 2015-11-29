/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.phdCourse;


/**
 *
 * @author Giuseppe Picciocchi
 */

/*La classe Course identifica il corso del calendario*/

public class Course {
    
    private int idCourse; // variabile per identificare un corso ( chiave primaria)
    private String curriculum; // variabile utilizzata per assegnare un curriculum
    private int cycle;  // variabile utilizzata per assegnare un ciclo
    private String name; // variabile utilizzata per assegnare un nome
    private String FK_curriculum; // variabile per la chiave esterna al "curriculum"
    private int FK_cycle; // variabile per la chiave esterna al "ciclo"
    
    
    
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
    
}
