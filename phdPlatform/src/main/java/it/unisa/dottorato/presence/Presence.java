package it.unisa.dottorato.presence;

import java.io.Serializable;


/**Classe dell'oggetto Presence; rappresenta la presenza di un dottorando ad una lezione
 * Ha come attributi fkPhdstudent, fkLesson, isPresent, setPermission che rappresentano
 * rispettivamente l'email principale del dottorando, l'id della lezione, e due valori 
 * booleani per indicare se si è presenti o no alla lezione
 * @author Rembor
 */
public class Presence implements Serializable {
    
    private String fkPhdstudent ;
    private  int fkLesson;
    public boolean isPresent=false;
/** Metodo della classe incaricato di settare l'id della lezione
     * 
     * @param fkLesson 
     */
    public void setFkLesson(int fkLesson) {
        this.fkLesson = fkLesson;
    }
/** Metodo della classe incaricato di ritornare l'id della lezione
     * 
     * @return restituisce il nome della lezione
     */
    public int getFkLesson() {
        return fkLesson;
    }

    
    
     
    /** Metodo della classe incaricato di ritornare l'email del dottorando della lezione
     * 
     * @return restituisce l'email del dottorando della lezione
     */
    public String getFkPhdstudent() {
        return fkPhdstudent;
    }

    
   

    /**  Metodo della classe incaricato di ritornare l'email del dottorando della lezione
     * 
     * @param fkPhdstudent 
     */
    public void setFkPhdstudent(String fkPhdstudent) {
        this.fkPhdstudent = fkPhdstudent;
    }

    
   
    /** Metodo della classe incaricato di ritornare il valore booleano isPresent
     * 
     * @return restituisce true se il dottorando è presente, false altrimenti
     */
     public boolean isIsPresent() {
        return isPresent;
    }

     /** Metodo della classe incaricato di settare il valore booleano isPresent
      * 
      * @param isSigned 
      */
    public void setIsPresent(boolean isSigned) {
        this.isPresent = isSigned;
    }

    
}
