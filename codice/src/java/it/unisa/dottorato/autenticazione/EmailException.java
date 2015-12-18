package it.unisa.dottorato.autenticazione;

/**Classe per la gestione delle eccezioni sulle email
 *
 * @author Armando
 */
public class EmailException extends Exception {
    
    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'Email is wrong.'
     * 
     */
    public EmailException() {
        super("Email is wrong.");
    }
    
}
