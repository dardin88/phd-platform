package it.unisa.dottorato.exception;

/**Classe dell'oggetto IdException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>id</code>
 *
 * @author Rembor
 */
public class IdException extends Exception{
    
     public IdException() {
        super("L'id  e' minore di  0 ");
    }
   
     public IdException(String pMessage) {
        super(pMessage);
    }
}