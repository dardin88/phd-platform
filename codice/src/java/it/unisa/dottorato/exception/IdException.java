package it.unisa.dottorato.exception;

/**Classe dell'oggetto IdException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>id</code>
 *
 * @author Rembor
 */
public class IdException extends Exception{
    
    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'L'id e' minore di 0'
     * 
     */
     public IdException() {
        super("L'id  e' minore di  0 ");
    }
   
     /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio pMessage
     * @param pMessage
     * 
     */
     public IdException(String pMessage) {
        super(pMessage);
    }
}