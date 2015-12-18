package it.unisa.dottorato.exception;

/**Classe dell'oggetto YearException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>year</code>
 *
 * @author andre
 */
public class YearException extends Exception{
    
    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'L'anno e' sbagliato'
     * 
     */
     public YearException () {
        super("L'anno e' sbagliato");
    }
   
     /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio pMessage
     * @param pMessage
     * 
     */
     public YearException(String pMessage) {
        super(pMessage);
    }
}
