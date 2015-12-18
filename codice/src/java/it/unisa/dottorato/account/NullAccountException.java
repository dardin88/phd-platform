package it.unisa.dottorato.account;

/**Classe per la gestione delle eccezioni sugli account nulli
 *
 * @author Armando
 */
public class NullAccountException extends Exception {
    
    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'Account is null'
     * 
     */
    public NullAccountException() {
        super("Account is null");
    }
    
}
