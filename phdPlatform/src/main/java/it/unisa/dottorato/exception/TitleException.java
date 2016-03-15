package it.unisa.dottorato.exception;
/**Classe dell'oggetto TitleException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>title</code>
 *
 * @author Rembor
 */
public class TitleException extends Exception{
    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'Il titolo e' sbagliato!'
     * 
     */
     public TitleException() {
        super("Il titolo e' sbagliato! ");
    }
   
     /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio pMessage
     * @param pMessage
     * 
     */
     public TitleException(String pMessage) {
        super(pMessage);
    }
}