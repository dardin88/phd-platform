package it.unisa.dottorato.curriculumcic;

/** Classe per la gestione delle eccezioni sui curriculumcic
 *
 * @author tommaso
 */
public class CurriculumcicException extends Exception{

    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'errore oggetto Curriculumcic'
     * 
     */
    public CurriculumcicException() {
        super("errore oggetto Curriculumcic");
    }

    
    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio <code>message</code>
     * 
     */
    public CurriculumcicException(String message) {
        super(message);
    }

}
