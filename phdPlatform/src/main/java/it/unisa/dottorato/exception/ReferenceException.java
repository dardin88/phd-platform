package it.unisa.dottorato.exception;

/**Classe dell'oggetto ReferenceException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>reference</code>
 *
 * @author andre
 */
public class ReferenceException extends Exception{
    
    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'Il campo delle referenze e' sbagliato'
     * 
     */
     public ReferenceException () {
        super("Il campo delle referenze e' sbagliato");
    }
   
     /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio pMessage
     * @param pMessage
     * 
     */
     public ReferenceException(String pMessage) {
        super(pMessage);
    }
}