package it.unisa.dottorato.phdProfile.missions;

import java.io.Serializable;
import java.util.Date;

/**Classe dell'oggetto Mission; rappresenta le missioni che un dottorando effettua
 * Ha come attributi idMission, description, startDate, endDate, reference, place
 * e fkPhdstudent che rappresentano rispettivamente l'id, la descrizione, la data
 * di inizio e di fine di una missiona, la referenza ad un collegamento esterno, il
 * luogo dove e' avvenuta la missione, e la mail principale del dottorando a cui
 * ha partecipato
 * 
 * @author 
 */
public class Mission implements Serializable{
    
    private int idMission;
    private String description;
    private Date startDate;
    private Date endDate;
    private String reference;
    private String place;
    private String fkPhdstudent;

    /** Metodo della classe incaricato di ritornare l'id della missione
     * 
     * @return restituisce l'id della missione
     */
    public int getIdMission() {
        return idMission;
    }

    /** Metodo della classe incaricato di settare l'id della missione
     * 
     * @param idMission 
     */
    public void setIdMission(int idMission) {
        this.idMission = idMission;
    }

    /** Metodo della classe incaricato di ritornare la descrizione della missione
     * 
     * @return restituisce la descrizione della missione
     */
    public String getDescription() {
        return description;
    }

    /** Metodo della classe incaricato di settare la descrizione della missione 
     * 
     * @param description 
     */
    public void setDescription(String description) {
        this.description = description;
    }

   /** Metodo della classe incaricato di ritornare la data di inzio della missione
     * 
     * @return restituisce la data di inzio della missione
     */
    public Date getStartDate() {
        return startDate;
    }

    /** Metodo della classe incaricato a settare la data di inizio della missione
     * 
     * @param startDate 
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

   /** Metodo della classe incaricato di ritornare la data di fine missione
     * 
     * @return restituisce la data di fine missione
     */
    public Date getEndDate() {
        return endDate;
    }

    /**Metodo della classe incaricato di settare la data di fine missione
     * 
     * @param endDate 
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
 
    /** Metodo della classe incaricato di ritornare la referenza della missione
     * 
     * @return restituisce la referenza della missione
     */
    public String getReference() {
        return reference;
    }

    /** Metodo della classe incaricato di settare la referenza della missione
     * 
     * @param reference 
     */
    public void setReference(String reference) {
        this.reference = reference;
    }
    
    /** Metodo della classe incaricato di ritornare il luogo della missione
     * 
     * @return restituisce il luogo della missione
     */
    public String getPlace() {
        return place;
    }

    /** Metodo della classe incaricato di settare il luogo della missione
     * 
     * @param place 
     */
    public void setPlace(String place) {
        this.place = place;
    }
    /** Metodo della classe incaricato di ritornare l'email principale del dottorando
     * che ha partecipato alla missione
     * @return restituisce l'email principale del dottorando
     */
    public String getFkPhdstudent() {
        return fkPhdstudent;
    }

    /** Metodo della classe incaricato di settare l'email del dottorando che ha 
     * partecipato alla missione
     * 
     * @param fkPhdstudent 
     */
    public void setFkPhdstudent(String fkPhdstudent) {
        this.fkPhdstudent = fkPhdstudent;
    }    

}
