package it.unisa.dottorato.exception;

/**Classe dell'oggetto pAbstractException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>pAbstract</code>
 *
 * @author andre
 */
public class pAbstractException extends Exception{
    
    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'Il campo dell'abstract e' sbagliato'
     * 
     */
     public pAbstractException () {
        super("Il campo dell'abstract e' sbagliato");
    }
   
     /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio pMessage
     * @param pMessage
     * 
     */
     public pAbstractException(String pMessage) {
        super(pMessage);
    }
}