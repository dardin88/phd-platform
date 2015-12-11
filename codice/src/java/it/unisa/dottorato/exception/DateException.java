package it.unisa.dottorato.exception;

/**Classe dell'oggetto DateException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>date</code>
 *
 * @author Rembor
 */
public class DateException extends Exception{
    
     public DateException () {
        super("La data e' sbagliata! ");
    }
   
     public DateException(String pMessage) {
        super(pMessage);
    }
}
