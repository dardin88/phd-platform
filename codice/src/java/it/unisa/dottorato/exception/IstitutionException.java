package it.unisa.dottorato.exception;

/**Classe dell'oggetto IstitutionException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>istitution</code>
 *
 * @author andre
 */
public class IstitutionException extends Exception{
    
     public IstitutionException () {
        super("L'istituzione è sbagliata");
    }
   
     public IstitutionException(String pMessage) {
        super(pMessage);
    }
}
