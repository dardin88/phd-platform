package it.unisa.dottorato.phdCourse;

import java.io.Serializable;
import java.util.Date;
/** Classe dell'oggetto Course che identifica il corso del calendario
 * Ha come attributi idCourse, curriculum, cycle, name, FK_curriculum, 
 * FK_cycle, description, startDate, endDate, che rappresentano rispettivamente
 * l'id del corso, il curriculum, il ciclo e il nome del corso, le chiavi esterne
 * per il curriculum e il ciclo, la descrizione del corso e la data di inizio e di 
 * fine del corso
 *
 * @author Giuseppe Picciocchi
 */



public class Course implements Serializable{
    
    private int idCourse; 
    private String fkCurriculum; 
    private String name; 
    private int fkCycle; 
    private String description;
    private Date startDate;
    private Date endDate;
    
    
    
    /** Metodo della classe incaricato di rirotnare l'id del corso
     * 
     * @return restituisce l'id del corso
     */
    public int getIdCourse(){
        return idCourse;            
    }
    
    /** Metodo della classe incaricato di settare l'id del corso
     * 
     * @param id 
     */
    public void setIdCourse(int id){
        this.idCourse = id;
    }

    /** Metodo della classe incaricato di ritornare il nome del curriculum
     * del corso
     * 
     * @return restituisce il nome del curriculum del corso
     */
    public String getFkCurriculum(){
        return fkCurriculum;            
    }
   
    /** Metodo della classe incaricato di settare il curriculum del corso
     * 
     * @param curr 
     */
    public void setFkCurriculum(String curr){
        this.fkCurriculum = curr;
    }
    
    /** Metodo della classe incaricato di ritornare il numero del ciclo del corso
     * 
     * @return restituisce il numero del ciclo del corso
     */
    public int getFkCycle(){
        return fkCycle;
    }
    
    /** Metodo della classe incaricato di settare il numero del ciclo del corso
     * 
     * @param ciclo 
     */
    public void setFkCycle(int ciclo){
        this.fkCycle = ciclo;
    }
    
    /** Metodo della classe incaricato di ritornare il nome del corso
     *
     * @return restituisce il nome del corso
     */
    public String getName(){
        return name;
    }
    
    /** Metodo della classe incaricato di settare il nome del corso
     * 
     * @param nome 
     */
    public void setName(String nome){
        this.name = nome;
    }
    
    /** Metodo della classe incaricato di ritornare la descrizione del corso
     *  
     * @return restituisce la descrizione del corso
     */
    public String getDescription(){
        return description;            
    }
    
    /** Metodo della classe incaricato di settare la descrizione del corso
     * 
     * @param descrizione 
     */
    public void setDescription(String descrizione){
        this.description = descrizione;
    }
    
    
    /** Metodo della classe incaricato di ritornare la data di inizio del corso
     * 
     * @return restituisce la data di inizio del corso
     */ 
    public Date getStartDate(){
        return startDate;            
    }
    
    /** Metodo della classe incaricato di settare la data di inzio del corso
     * 
     * @param date 
     */
    public void setStartDate(Date date){
        this.startDate = date;
    }
    
    /** Metodo della classe incaricato di ritornare la data di fine corso
     * 
     * @return restituisce la data di fine corso
     */
    public Date getEndDate(){
        return endDate;            
    }
    
    /** Metodo della classe incaricato di settare la data di fine corso
     * 
     * @param date 
     */
    public void setEndDate(Date date){
        this.endDate = date;
    }
}
