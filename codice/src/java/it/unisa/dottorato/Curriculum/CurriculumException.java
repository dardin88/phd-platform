package it.unisa.dottorato.Curriculum;

/**Classe per la gestione delle eccezioni sugli oggetti curriculum
 *
 * @author tommaso
 */
public class CurriculumException extends Exception{

    
    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'Errore oggetto curriculum'
     * 
     */
    public CurriculumException() {
        super("Errore oggetto curriculum");
    }
    
    /** Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio <code>pMessage</code>
     * 
     * @param pMessage 
     */
    public CurriculumException(String pMessage) {
        super(pMessage);
    }
}
