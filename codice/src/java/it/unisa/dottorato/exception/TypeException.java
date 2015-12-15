package it.unisa.dottorato.exception;

/**Classe dell'oggetto IstitutionException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>type</code>
 *
 * @author andre
 */
public class TypeException extends Exception{
    
     public TypeException () {
        super("Il tipo e' sbagliato");
    }
   
     public TypeException(String pMessage) {
        super(pMessage);
    }
}