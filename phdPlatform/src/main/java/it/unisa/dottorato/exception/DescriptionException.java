package it.unisa.dottorato.exception;

/**Classe dell'oggetto DescriptionException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>description</code>
 *
 * @author Rembor
 */
public class DescriptionException extends Exception{
    
    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'La descrizione e' sbagliata!'
     * 
     */
     public DescriptionException () {
        super("La descrizione e' sbagliata! ");
    }
   
     /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio pMessage
     * @param pMessage
     * 
     */
     public DescriptionException(String pMessage) {
        super(pMessage);
    }
}
