package it.unisa.dottorato.Cycle;

/**Classe per la gestione delle eccezioni sugli oggetti Cycle
 *
 * @author tommaso
 */
public class CycleException extends Exception {
    
    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'Errore oggetto cycle'
     * 
     */
       public CycleException() {
        super("Errore oggetto cycle");
    }
    
    /** Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio <code>pMessage</code>
     * 
     * @param pMessage 
     */
    public CycleException(String pMessage) {
        super(pMessage);
    }
}
