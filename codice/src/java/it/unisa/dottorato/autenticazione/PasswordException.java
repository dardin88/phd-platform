package it.unisa.dottorato.autenticazione;

/** Classe per la gestione delle eccezioni sulle password
 * @author Armando
 */
public class PasswordException extends Exception {

    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'Password is wrong.'
     * 
     */
    public PasswordException() {
        super("Password error");
    }
    
    
}
