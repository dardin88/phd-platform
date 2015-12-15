package it.unisa.dottorato.exception;

/**Classe dell'oggetto IdException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>numberPage</code>
 *
 * @author andre
 */
public class NumberPageException extends Exception{
    
     public NumberPageException() {
        super("Il numero di pagine è sbagliato");
    }
   
     public NumberPageException(String pMessage) {
        super(pMessage);
    }
}