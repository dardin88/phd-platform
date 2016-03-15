package it.unisa.dottorato.exception;

/**Classe dell'oggetto PlaceException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>place</code>
 *
 * @author andre
 */
public class PlaceException extends Exception{
    
    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'Il posto e' sbagliato'
     * 
     */
     public PlaceException () {
        super("Il posto e' sbagliato");
    }
   
     /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio pMessage
     * @param pMessage
     * 
     */
     public PlaceException(String pMessage) {
        super(pMessage);
    }
}