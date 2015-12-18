package it.unisa.dottorato.exception;

/**Classe dell'oggetto DateException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>date</code>
 *
 * @author Rembor
 */
public class DateException extends Exception{
   
    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'La data e' sbagliata! '
     * 
     */
     public DateException () {
        super("La data e' sbagliata! ");
    }
   
     /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio pMessage
     * @param pMessage
     * 
     */
     public DateException(String pMessage) {
        super(pMessage);
    }
}
