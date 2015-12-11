package it.unisa.dottorato.exception;

/**Classe dell'oggetto NameException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>name</code>
 *
 * @author Rembor
 * 
 */
public class NameException extends Exception{
    
     public NameException () {
        super("Il nome  e' sbagliato! ");
    }
   
     public NameException(String pMessage) {
        super(pMessage);
    }
}
