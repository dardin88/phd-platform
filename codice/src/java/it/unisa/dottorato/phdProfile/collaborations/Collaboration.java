package it.unisa.dottorato.phdProfile.collaborations;

import java.io.Serializable;
import java.util.Date;

/** Classe dell'oggetto Collaboration; rappresenta le collaborazioni di un
 * dottorando; ha come attributi idCollaboration, description, startDate, endDate,
 * istitution, fkPhdstudent che rappresentano rispettivamente l'id, la descrizione,
 * la data di inizio e di fine, e l'istituzione della collaborazione, infine l'email
 * del dottorando a cui ha preso parte
 * 
 * @author ???
 */
public class Collaboration implements Serializable {
    
    private int idCollaboration;
    private String description;
    private Date startDate;
    private Date endDate;
    private String istitution;
    private String fkPhdstudent;

    /** Metodo della classe incaricato di ritornare l'id della collaborazione
     * 
     * @return restituisce l'id della collaborazione
     */
    public int getIdCollaboration() {
        return idCollaboration;
    }

    /** Metodo della classe incaricato a settare l'id della collaborazione 
     * 
     * @param idCollaboration 
     */
    public void setIdCollaboration(int idCollaboration) {
        this.idCollaboration = idCollaboration;
    }
    
    /** Metodo della classe incaricato a ritornare l'istituzione della collaborazione
     * 
     * @return restituisce l'istituzione della collaborazione
     */
    public String getIstitution() {
        return istitution;
    }

    /** Metodo della classe incaricato di settare l'istituzione della collaborazione
     * 
     * @param istitution 
     */
    public void setIstitution(String istitution) {
        this.istitution = istitution;
    }

    /** Metodo della classe incaricato di ritornare la descrizione della collaborazione
     * 
     * @return restituisce la descrizione della collaborazione
     */
    public String getDescription() {
        return description;
    }

    /** Metodo della classe incaricato di settare la descrizione della collaborazione 
     * 
     * @param description 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /** Metodo della classe incaricato di ritornare la data di inzio della collaborazione
     * 
     * @return restituisce la data di inzio della collaborazione
     */
    public Date getStartDate() {
        return startDate;
    }

    /** Metodo della classe incaricato a settare la data di inizio della collaborazione
     * 
     * @param startDate 
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /** Metodo della classe incaricato di ritornare la data di fine collaborazione
     * 
     * @return restituisce la data di fine collaborazione
     */
    public Date getEndDate() {
        return endDate;
    }

    /**Metodo della classe incaricato di settare la data di fine collaborazione
     * 
     * @param endDate 
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /** Metodo della classe incaricato di ritornare l'email principale del dottorando
     * che ha partecipato alla collaborazione
     * @return restituisce l'email principale del dottorando
     */
    public String getFkPhdstudent() {
        return fkPhdstudent;
    }

    /** Metodo della classe incaricato di settare l'email del dottorando che ha 
     * partecipato alla collaborazione
     * 
     * @param fkPhdstudent 
     */
    public void setFkPhdstudent(String fkPhdstudent) {
        this.fkPhdstudent = fkPhdstudent;
    }  
    
}
