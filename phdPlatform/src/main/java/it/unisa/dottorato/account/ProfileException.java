package it.unisa.dottorato.account;

/**Classe per la gestione delle eccezioni sui profili
 *
 * @author Armando
 */
public class ProfileException extends Exception {
    
    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'Profile data error.'
     * 
     */
    public ProfileException() {
        super("Profile data error.");
    }
    
}
