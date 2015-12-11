package it.unisa.dottorato.exception;
/**Classe dell'oggetto TitleException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>title</code>
 *
 * @author Rembor
 */
public class TitleException extends Exception{
    
     public TitleException() {
        super("Il titolo e' sbagliato! ");
    }
   
     public TitleException(String pMessage) {
        super(pMessage);
    }
}