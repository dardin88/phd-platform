package it.unisa.dottorato.exception;

/** Classe per la gestione delle eccezioni delle Missioni
 *
 * @author andre
 */
public class MissionException extends Exception{

    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'errore oggetto Missione'
     * 
     */
    public MissionException() {
        super("errore oggetto Missione");
    }

    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio message
     * @param message
     * 
     */
    public MissionException(String message) {
        super(message);
    }

}
