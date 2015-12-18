package it.unisa.dottorato.exception;

/**Classe dell'oggetto IstitutionException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>year</code>
 *
 * @author andre
 */
public class YearException extends Exception{
    
     public YearException () {
        super("L'anno e' sbagliato");
    }
   
     public YearException(String pMessage) {
        super(pMessage);
    }
}
