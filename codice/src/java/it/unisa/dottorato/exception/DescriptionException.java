package it.unisa.dottorato.exception;

/**Classe dell'oggetto DescriptionException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>description</code>
 *
 * @author Rembor
 */
public class DescriptionException extends Exception{
    
     public DescriptionException () {
        super("La descrizione e' sbagliata! ");
    }
   
     public DescriptionException(String pMessage) {
        super(pMessage);
    }
}
