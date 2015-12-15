package it.unisa.dottorato.exception;

/**Classe dell'oggetto IstitutionException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>reference</code>
 *
 * @author andre
 */
public class ReferenceException extends Exception{
    
     public ReferenceException () {
        super("Il campo delle referenze e' sbagliato");
    }
   
     public ReferenceException(String pMessage) {
        super(pMessage);
    }
}