package it.unisa.dottorato.exception;

/**Classe dell'oggetto OtherAuthrosException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>otherAuthors</code>
 *
 * @author andre
 */
public class OtherAuthorsException extends Exception{
    
    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'Il campo degli altri autori e' sbagliato'
     * 
     */
     public OtherAuthorsException () {
        super("Il campo degli altri autori e' sbagliato");
    }
   
     /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio pMessage
     * @param pMessage
     * 
     */
     public OtherAuthorsException(String pMessage) {
        super(pMessage);
    }
}
