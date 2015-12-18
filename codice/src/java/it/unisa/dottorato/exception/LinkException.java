package it.unisa.dottorato.exception;

/**Classe dell'oggetto IstitutionException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>link</code>
 *
 * @author andre
 */
public class LinkException extends Exception{
    
     public LinkException () {
        super("Il link è sbagliato");
    }
   
     public LinkException(String pMessage) {
        super(pMessage);
    }
}