package it.unisa.dottorato.phdCourse;

import java.io.Serializable;
import java.util.Date;
/** Classe dell'oggetto Lesson; rappresenta una lezione del calendario
 * Ha come attributi idLesson, data, startTime, endTime, name, classroom,
 * description, cycle, curriculum e FK_course che rappresentano rispettivamente
 * l'id della lezione, la data, l'ora di inizio e di fine, il nome della lezione, 
 * la classe, la descrizione della lezione, il ciclo e il curriculum ad esso associato, 
 * ed il corso di cui fa parte (chiave esterna a course)
 *
 * @author Giuseppe Picciocchi
 */

public class Lesson implements Serializable{
    
    private int idLesson;
    private Date data; 
    private String startTime;
    private String endTime;  
    private String name; 
    private String classroom;
    private String description;
    private int cycle;
    private String curriculum;
    private int FK_course;    
    
   /**Metodo della classe incaricato di ritornare l'id della lezione
    * 
    * @return restituisce l'id della lezione
    */
    public int getIdLesson(){
        return idLesson;            
    }
    
   /** Metodo della classe incaricato di settare l'id della lezione
    * 
    * @param id 
    */
    public void setIdLesson(int id){
        this.idLesson = id;
    }
    
    /** Metodo della classe incaricato di ritornare la data della lezione
     * 
     * @return restituisce la data della lezione
     */
    public Date getData(){
        return data;            
    }
    
    /** Metodo della classe incaricato di settare la data della lezione
     * 
     * @param date 
     */
    public void setDate(Date date){
        this.data = date;
    }
    
    /** Metodo della classe incaricato di ritornare l'ora di inizio di lezione
     * 
     * @return restituisce l'ora di inizio della lezione
     */
    public String getStartTime(){
        return startTime;            
    }
    
    /** Metodo della classe incaricato di settare l'ora di inzio della lezione
     * 
     * @param start_time 
     */
    public void setStartTime(String start_time){
        this.startTime = start_time;
    }
    
   /** Metodo della classe incaricato di ritornare l'ora di fine lezione
    * 
    * @return restituisce l'ora di fine lezione
    */
    public String getEndTime(){
        return endTime;            
    }
    
    /** Metodo della classe incaricato di settare l'ora di fine lezione
     * 
     * @param end_time 
     */
    public void setEndTime(String end_time){
        this.endTime = end_time;
    }
    
    /** Metodo della classe incaricato di ritornare il nome della lezione
     * 
     * @return restituisce il nome della lezione
     */
    public String getName(){
        return name;            
    }
    
    /** Metodo della classe incaricato di settare il nome della lezione
     * 
     * @param nome 
     */
    public void setName(String nome){
        this.name = nome;
    }
    
    
    /** Metodo della classe incaricato di ritornare la classe dove si svolge
     * la lezione
     * 
     * @return restituisce la classe dove si svolge la lezione
     */
    public String getClassroom(){
        return classroom;            
    }
    
    /** Metodo della classe incaricato di settare la classe dove si svolge la lezione
     * 
     * @param classe 
     */
    public void setClassroom(String classe){
        this.classroom = classe;
    }
    
    
    /** Metodo della classe incaricato di ritornare la descrizione della lezione
     * 
     * @return restituisce la descrizione della lezione
     */
    public String getDescription(){
        return description;            
    }
    
    /** Metodo della classe incaricato di settare la descrizione della lezione
     * 
     * @param descrizione 
     */
    public void setDescription(String descrizione){
        this.description = descrizione;
    }
    
    /** Metodo della classe incaricato di ritornare il numero del ciclo a cui la lezione 
     * e' associata
     * 
     * @return restituisce il numero del ciclo a cui la lezione e' associata
     */
    public int getCycle(){
        return cycle;            
    }
    
    /** Metodo della classe incaricato di settare il numero del ciclo a cui
     * la lezione e' associata
     * 
     * @param ciclo 
     */
    public void setCycle(int ciclo){
        this.cycle = ciclo;
    }
    
    /** Metodo della classe incaricato di ritornare il curriculum a cui la lezione
     * e' associata
     * 
     * @return restituisce il curriculum a cui la lezione e' associata
     */
    public String getCurriculum(){
        return curriculum;            
    }
    
    /** Metodo della classe incaricato di settare il curriculum a cui la lezione
     * e' associata
     * 
     * @param curri 
     */
    public void setCurriculum(String curri){
        this.curriculum = curri;
    }
    
    /** Metodo della classe incaricato di ritornare l'id del corso a cui la lezione
     * e' associata
     * 
     * @return restituisce l'id del corso a cui la lezione e' associata
     */
    public int getFK_course(){
        return FK_course;            
    }
    
    /** Metodo della classe incaricato di settare l'id del corso a cui la 
     * lezione e' associata
     * 
     * @param chiave_course 
     */
    public void setFK_course(int chiave_course){
        this.FK_course = chiave_course;
    }
 }
    