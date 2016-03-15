package it.unisa.dottorato.exception;

/**Classe per la gestione delle eccezioni delle pubblicazioni
 *
 * @author andre
 */
public class PublicationException extends Exception{

    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'errore oggetto Pubblicazione'
     * 
     */
    public PublicationException() {
        
        super("errore oggetto Pubblicazione");
    }

    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio message
     * @param message
     * 
     */
    public PublicationException(String message) {
        super(message);
    }

}
