package it.unisa.dottorato.exception;

/**Classe dell'oggetto IstitutionException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>otherAuthors</code>
 *
 * @author andre
 */
public class OtherAuthorsException extends Exception{
    
     public OtherAuthorsException () {
        super("Il campo degli altri autori e' sbagliato");
    }
   
     public OtherAuthorsException(String pMessage) {
        super(pMessage);
    }
}
