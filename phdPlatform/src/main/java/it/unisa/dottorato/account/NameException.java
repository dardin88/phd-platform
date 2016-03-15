package it.unisa.dottorato.account;

/**Classe per la gestione delle eccezioni sui nomi
 *
 * @author Armando
 */
public class NameException extends Exception {
    
    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'Name is wrong.'
     * 
     */
    public NameException() {
        super("Name is wrong.");
    }
}
