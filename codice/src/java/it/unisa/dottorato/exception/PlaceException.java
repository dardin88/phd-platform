package it.unisa.dottorato.exception;

/**Classe dell'oggetto IstitutionException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>place</code>
 *
 * @author andre
 */
public class PlaceException extends Exception{
    
     public PlaceException () {
        super("Il posto e' sbagliato");
    }
   
     public PlaceException(String pMessage) {
        super(pMessage);
    }
}