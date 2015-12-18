package it.unisa.dottorato.exception;

/**Classe per la gestione delle eccezioni delle collaborazioni
 *
 * @author andre
 */
public class CollaborationException extends Exception{

    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'errore oggetto Collaboration'
     * 
     */
    public CollaborationException() {
        super("errore oggetto Collaboration");
    }

    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio message
     * @param message
     * 
     */
    public CollaborationException(String message) {
        super(message);
    }

}
