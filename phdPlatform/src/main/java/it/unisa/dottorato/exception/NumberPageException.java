package it.unisa.dottorato.exception;

/**Classe dell'oggetto NumberPageException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>numberPage</code>
 *
 * @author andre
 */
public class NumberPageException extends Exception{
    
    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'Il numero di pagine e' sbagliato'
     * 
     */
     public NumberPageException() {
        super("Il numero di pagine è sbagliato");
    }
   
     /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio pMessage
     * @param pMessage
     * 
     */
     public NumberPageException(String pMessage) {
        super(pMessage);
    }
}