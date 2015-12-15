package it.unisa.dottorato.exception;

/**Classe dell'oggetto IstitutionException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>pAbstract</code>
 *
 * @author andre
 */
public class pAbstractException extends Exception{
    
     public pAbstractException () {
        super("Il campo dell'abstract e' sbagliato");
    }
   
     public pAbstractException(String pMessage) {
        super(pMessage);
    }
}