package it.unisa.dottorato.phdCourse;

import java.io.Serializable;
import java.util.Date;
/**Classe dell'oggetto Seminar, che identifica un seminario all'interno del
 * calendario; ha come attributi idSeminar, data, startTime, endTime, name,
 * namespeacker, description, place, FK_course che rappresentano rispettivamente
 * l'id del seminario, la data in cui si terrà, l'ora di inizio e di fine, il nome
 * del seminario, il nome dello speaker, la descrizione, il luogo dove si terrà e
 * l'id del corso a cui il seminario e' associato
 *
 * @author Giuseppe Picciocchi
 */

public class Seminar implements Serializable{
    
    private int idSeminar;
    private Date data; 
    private String startTime;
    private String endTime;  
    private String name; 
    private String namespeacker;
    private String description;
    private String place;
    private int FK_course;
    
    
    /** Metodo della classe incaricato di ritornare l'id del seminario
     * 
     * @return restituisce l'id del seminario
     */
    public int getIdSeminar(){
        return idSeminar;            
    }
    
    /** Metodo della classe incaricato di settare l'id del seminario
     * 
     * @param id 
     */
    public void setIdSeminar(int id){
        this.idSeminar = id;
    }
    
    /** Metodo della classe incaricato di ritornare la data del seminario
     * 
     * @return restituisce la data del seminario
     */
    public Date getData(){
        return data;            
    }
    
    /** Metodo della classe incaricato di settare la data del seminario
     * 
     * @param date 
     */
    public void setDate(Date date){
        this.data = date;
    }
    
   
    /** Metodo della classe incaricato di ritornare l'ora di inizio del seminario
     * 
     * @return restituisce l'ora di inizio del seminario
     */
    public String getStartTime(){
        return startTime;            
    }
    
    /** Metodo della classe incaricato di settare l'ora di inzio del seminario
     * 
     * @param start_time 
     */
    public void setStartTime(String start_time){
        this.startTime = start_time;
    }
    
   
    /** Metodo della classe incaricato di ritornare l'ora di fine seminario
    * 
    * @return restituisce l'ora di fine seminario
    */
    public String getEndTime(){
        return endTime;            
    }
    
    /** Metodo della classe incaricato di settare l'ora di fine seminario
     * 
     * @param end_time 
     */
    public void setEndTime(String end_time){
        this.endTime = end_time;
    }
    
    
    
    /** Metodo della classe incaricato di ritornare il nome del seminario
     * 
     * @return restituisce il nome del seminario
     */
    public String getName(){
        return name;            
    }
    
    /** Metodo della classe incaricato di settare il nome del seminario
     * 
     * @param nome 
     */
    public void setName(String nome){
        this.name = nome;
    }
    
    /** Metodo della classe incaricato di ritornare il nome dello speaker
     * del seminario
     * 
     * @return restituisce il nome dello speaker del seminario
     */
    public String getNameSpeacker(){
        return namespeacker;            
    }
    
    /** Metodo della classe incaricato di settare il nome dello speaker
     * del seminario
     * 
     * @param nome_speacker 
     */
    public void setNameSpeacker(String nome_speacker){
        this.namespeacker = nome_speacker;
    }
    
    
    /** Metodo della classe incaricato di ritornare la descrizione del seminario
     * 
     * @return restituisce la descrizione del seminario
     */
    public String getDescription(){
        return description;            
    }
    
    /** Metodo della classe incaricato di settare la descrizione del seminario
     * 
     * @param descrizione 
     */
    public void setDescription(String descrizione){
        this.description = descrizione;
    }
    
    /** Metodo della classe incaricato di ritornare il luogo del seminario
     * 
     * @return restituisce il luogo del seminario
     */
    public String getPlace(){
        return place;            
    }
    
    /** Metodo della classe incaricato di settare il luogo del seminario
     * 
     * @param posto 
     */
    public void setPlace(String posto){
        this.place = posto;
    }
    
    
   /** Metodo della classe incaricato di ritornare l'id del corso a cui il seminario
     * e' associato
     * 
     * @return restituisce l'id del corso a cui il seminario e' associato
     */
    public int getFK_course(){
        return FK_course;            
    }
    
    /** Metodo della classe incaricato di settare l'id del corso a cui il 
     * seminario e' associato
     * 
     * @param chiave_course 
     */
    public void setFK_course(int chiave_course){
        this.FK_course = chiave_course;
    }
}
