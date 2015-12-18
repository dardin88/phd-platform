package it.unisa.dottorato.exception;

/**Classe dell'oggetto NameException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>name</code>
 *
 * @author Rembor
 * 
 */
public class NameException extends Exception{
    
    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'Il nome e' sbagliato!'
     * 
     */
     public NameException () {
        super("Il nome  e' sbagliato! ");
    }
   
     /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio pMessage
     * @param pMessage
     * 
     */
     public NameException(String pMessage) {
        super(pMessage);
    }
}
