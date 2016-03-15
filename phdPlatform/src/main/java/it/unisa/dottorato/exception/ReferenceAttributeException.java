package it.unisa.dottorato.exception;

/**Classe dell'oggetto ReferenceAttributeException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>reference</code>
 *
 * @author andre
 */
public class ReferenceAttributeException extends Exception{
    
    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'Il campo delle referenze e' sbagliato'
     * 
     */
     public ReferenceAttributeException () {
        super("Il campo delle referenze e' sbagliato");
    }
   
     /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio pMessage
     * @param pMessage
     * 
     */
     public ReferenceAttributeException(String pMessage) {
        super(pMessage);
    }
}