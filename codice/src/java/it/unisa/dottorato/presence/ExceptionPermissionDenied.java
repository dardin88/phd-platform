package it.unisa.dottorato.presence;

/**Classe per la gestione delle eccezioni dei permessi negati
 *
 * @author Rembor
 */
public class ExceptionPermissionDenied extends Exception {

  

    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'permesso negato!'
     * 
     */
    public ExceptionPermissionDenied() {
        super("permesso negato!");
    }

    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio pMessage
     * @param pMessage
     * 
     */
    public ExceptionPermissionDenied(String pMessage) {
        super(pMessage);
    }

    
}
  

